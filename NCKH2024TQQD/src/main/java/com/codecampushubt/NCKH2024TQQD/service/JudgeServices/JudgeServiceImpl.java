package com.codecampushubt.NCKH2024TQQD.service.JudgeServices;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.JudgeRequestDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.JudgeRunResponseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionResponseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import com.codecampushubt.NCKH2024TQQD.util.DockerCodeExecutionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class JudgeServiceImpl implements JudgeService {
    private final UserService userService;

    @Autowired
    public JudgeServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JudgeRunResponseDTO runUserCode(JudgeRequestDTO request, Set<ExerciseTestCasesDTO> exerciseTestCases) {
        String userName = UserContext.getUsername();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String folderName = userName + "-ex" + request.getExerciseID() + "-" + timestamp;

        Path workingDir = Paths.get("Code_Dir", folderName);

        try {
            // Tạo folder lưu code tạm Code_Dir nếu chưa tồn tại
            Files.createDirectories(Paths.get("Code_Dir"));
            Files.createDirectories(workingDir);

            // Tạo và ghi file Main.java
            Path sourceFile = workingDir.resolve("Main.java");
            Files.writeString(sourceFile, request.getSourceCode());

            // Biên dịch sử dụng Docker
            DockerCodeExecutionUtil.compileJavaInContainer(workingDir);

            // Tạo output để trả về alert kết quả testcase bên client
            StringBuilder output = new StringBuilder();

            // Kiểm tra xem bài tập có test case không, nếu không có thì không cần nhập testcase
            if (exerciseTestCases.isEmpty()) {
                output.append(DockerCodeExecutionUtil.runJavaInContainer(workingDir, ""));
            }

            boolean allPassed = true;

            for (ExerciseTestCasesDTO testCase : exerciseTestCases) {
                // Bỏ qua test case ẩn (public = false)
                if (!testCase.getPublic())
                    continue;

                // Chạy với input của testcase public trong Docker
                String outputRun = DockerCodeExecutionUtil.runJavaInContainer(
                        workingDir,
                        testCase.getInput()
                );

                // So sánh output thực tế và output mong đợi
                String expected = testCase.getExpectedOutput().trim();
                String actual = outputRun.trim();

                boolean passed = expected.equals(actual);
                if (!passed)
                    allPassed = false;

                // Ghi nhận kết quả từng test case
                output.append("Test case: \n")
                        .append("Input:\n").append(testCase.getInput()).append("\n")
                        .append("Expected:\n").append(expected).append("\n")
                        .append("Actual:\n").append(actual).append("\n")
                        .append(passed ? "✅ Passed\n\n" : "❌ Failed\n\n");
            }

            String status = allPassed ? "SUCCESS" : "FAIL";
            return new JudgeRunResponseDTO(output.toString(), status, "");

        } catch (IOException | InterruptedException e) {
            return new JudgeRunResponseDTO("", "ERROR", e.getMessage());
        } catch (DockerCodeExecutionUtil.CompilationException e) {
            return new JudgeRunResponseDTO(e.getOutput(), "COMPILATION_ERROR", "Lỗi biên dịch");
        } catch (RuntimeException e) {
            return new JudgeRunResponseDTO("", "ERROR", e.getMessage());
        } finally {
            // Dọn dẹp thư mục
            try {
                DockerCodeExecutionUtil.deleteDirectoryRecursively(workingDir);
            } catch (Exception e) {
                // Bỏ qua lỗi khi dọn dẹp
            }
        }
    }

    @Override
    public CodingSubmissionResponseDTO submitUserCode(JudgeRequestDTO request, Set<ExerciseTestCasesDTO> exerciseTestCases) {
        String userName = UserContext.getUsername();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String folderName = userName + "-ex" + request.getExerciseID() + "-" + timestamp;

        Path workingDir = Paths.get("Code_Dir", folderName);

        try {
            // Tạo folder lưu code tạm Code_Dir nếu chưa tồn tại
            Files.createDirectories(Paths.get("Code_Dir"));
            Files.createDirectories(workingDir);

            // Tạo và ghi file Main.java
            Path sourceFile = workingDir.resolve("Main.java");
            Files.writeString(sourceFile, request.getSourceCode());

            // Biên dịch sử dụng Docker
            DockerCodeExecutionUtil.compileJavaInContainer(workingDir);

            // Tạo output để trả về alert kết quả testcase bên client
            StringBuilder output = new StringBuilder();

            // Kiểm tra xem bài tập có test case không, nếu không có thì không cần nhập testcase
            if (exerciseTestCases.isEmpty()) {
                output.append(DockerCodeExecutionUtil.runJavaInContainer(workingDir, ""));
            }

            boolean allPassed = true;

            Integer testCasePassed = 0;
            Integer totalScore = 0;
            for (ExerciseTestCasesDTO testCase : exerciseTestCases) {
                // Chạy với input của testcase trong Docker
                String outputRun = DockerCodeExecutionUtil.runJavaInContainer(
                        workingDir,
                        testCase.getInput()
                );

                // So sánh output thực tế và output mong đợi
                String expected = testCase.getExpectedOutput().trim();
                String actual = outputRun.trim();

                boolean passed = expected.equals(actual);

                if (!passed)
                    allPassed = false;
                else {
                    testCasePassed += 1;
                    totalScore += testCase.getScore();
                }

                // Ghi nhận kết quả từng test case
                output.append("Test case: \n")
                        .append("Input:\n").append(testCase.getInput()).append("\n")
                        .append("Expected:\n").append(expected).append("\n")
                        .append("Actual:\n").append(actual).append("\n")
                        .append(passed ? "✅ Passed\n\n" : "❌ Failed\n\n");
            }

            String status = allPassed ? "accepted" : "wrong_answer";
            return new CodingSubmissionResponseDTO(null, userService.findUserIDByUserName(userName), request.getSourceCode(),
                    request.getLanguage(), status, testCasePassed, exerciseTestCases.size(), totalScore);

        } catch (IOException | InterruptedException e) {
            return new CodingSubmissionResponseDTO(null, userService.findUserIDByUserName(userName), request.getSourceCode(),
                    request.getLanguage(), "wrong_answer", null, exerciseTestCases.size(), null);
        } catch (DockerCodeExecutionUtil.CompilationException e) {
            return new CodingSubmissionResponseDTO(null, userService.findUserIDByUserName(userName), request.getSourceCode(),
                    request.getLanguage(), "wrong_answer", null, exerciseTestCases.size(), null);
        } catch (RuntimeException e) {
            return new CodingSubmissionResponseDTO(null, userService.findUserIDByUserName(userName), request.getSourceCode(),
                    request.getLanguage(), "wrong_answer", null, exerciseTestCases.size(), null);
        } finally {
            // Dọn dẹp thư mục
            try {
                DockerCodeExecutionUtil.deleteDirectoryRecursively(workingDir);
            } catch (Exception e) {
                // Bỏ qua lỗi khi dọn dẹp
            }
        }
    }
}