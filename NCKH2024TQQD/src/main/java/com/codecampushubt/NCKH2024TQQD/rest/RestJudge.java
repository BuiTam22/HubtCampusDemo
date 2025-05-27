package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dao.ExerciseTestCaseRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.JudgeRequestDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.JudgeRunResponseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionResponseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.ContestExerciseAttempt.AttemptInfoDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseSubmissionRequest;
import com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO;
import com.codecampushubt.NCKH2024TQQD.entity.*;
import com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices.CodingExerciseService;
import com.codecampushubt.NCKH2024TQQD.service.CodingSubmissionServices.CodingSubmissionService;
import com.codecampushubt.NCKH2024TQQD.service.ContestExerciseAttemptServices.ContestExerciseAttemptService;
import com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices.EssayExerciseService;
import com.codecampushubt.NCKH2024TQQD.service.EssaySubmissionServices.EssaySubmissionService;
import com.codecampushubt.NCKH2024TQQD.service.JudgeServices.JudgeService;
import com.codecampushubt.NCKH2024TQQD.service.LessonServices.LessonService;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/judge")
public class RestJudge {
    @Value("${GEMINI_API_KEY}")
    private String GEMINI_API_KEY;

    private final JudgeService judgeService;
    private final ExerciseTestCaseRepository exerciseTestCaseRepository;
    private final UserService userService;
    private final CodingExerciseService codingExerciseService;
    private final CodingSubmissionService codingSubmissionService;
    private final WebClient webClient;
    private final EssayExerciseService essayExerciseService;
    private final EssaySubmissionService essaySubmissionService;
    private final ContestExerciseAttemptService contestExerciseAttemptService;
    private final LessonService lessonService;


    @Autowired
    public RestJudge(JudgeService judgeService, ExerciseTestCaseRepository exerciseTestCaseRepository, UserService userService, CodingExerciseService codingExerciseService, CodingSubmissionService codingSubmissionService, WebClient webClient, EssayExerciseService essayExerciseService, EssaySubmissionService essaySubmissionService, ContestExerciseAttemptService contestExerciseAttemptService, LessonService lessonService) {
        this.judgeService = judgeService;
        this.exerciseTestCaseRepository = exerciseTestCaseRepository;
        this.userService = userService;
        this.codingExerciseService = codingExerciseService;
        this.codingSubmissionService = codingSubmissionService;
        this.webClient = webClient;
        this.essayExerciseService = essayExerciseService;
        this.essaySubmissionService = essaySubmissionService;
        this.contestExerciseAttemptService = contestExerciseAttemptService;
        this.lessonService = lessonService;
    }

    @PostMapping("/run")
    public JudgeRunResponseDTO handleRunCode(@RequestBody JudgeRequestDTO request){
        Set<ExerciseTestCasesDTO> exerciseTestCases = exerciseTestCaseRepository.getExerciseTestCasesDTOByExerciseID(request.getExerciseID());
        return  judgeService.runUserCode(request, exerciseTestCases);
    }

    @PostMapping("submit")
    public CodingSubmissionResponseDTO handleSubmitCode(@RequestBody JudgeRequestDTO request){
        Set<ExerciseTestCasesDTO> exerciseTestCases = exerciseTestCaseRepository.getExerciseTestCasesDTOByExerciseID(request.getExerciseID());
        // lấy ra submission để lưu vào DB và trả ra cho client
        CodingSubmissionResponseDTO submission = judgeService.submitUserCode(request, exerciseTestCases);
        submission.setExerciseID(request.getExerciseID());

        // Lưu Submission vào DB
        User userEntity = userService.getUserEntityByID(submission.getUserID());
        CodingExercise codingExercise = codingExerciseService.getExerciseEntityByID(request.getExerciseID());
        CodingSubmission codingSubmission = new CodingSubmission();

        codingSubmission.setCode(submission.getCode());
        codingSubmission.setLanguage(submission.getLanguage());
        codingSubmission.setStatus(submission.getStatus());
        codingSubmission.setTestCasesPassed(submission.getTestCasesPassed());
        codingSubmission.setTotalTestCases(submission.getTotalTestCases());
        codingSubmission.setScore(submission.getScore());
        codingSubmission.setExercise(codingExercise);
        codingSubmission.setUser(userEntity);
        codingSubmission.setExecutionTime(1);
        codingSubmission.setMemoryUsed(10);
        codingSubmission.setSubmittedAt(LocalDateTime.now());
        CodingSubmission newSubmission = codingSubmissionService.save(codingSubmission);


        // Kiểm tra nếu là contest thì cho vào attempt
        if(codingExerciseService.isExerciseInContestLesson(request.getExerciseID()) == true){
            // CHECK SỐ LẦN NỘP BÀI VÀ LƯU VÀO ContestAttempt
            AttemptInfoDTO attempInfo = contestExerciseAttemptService.getAttemptInfoDTOByuserIDAndExerciseID(UserContext.getUserID(), request.getExerciseID(), "coding");


            if(attempInfo != null && attempInfo.getAttemptNumber() != null && attempInfo.getAttemptNumber() >0){
                System.out.println("Lần làm bài thứ " + (attempInfo.getAttemptNumber() + 1));
            }

            if (attempInfo == null){
                attempInfo = new AttemptInfoDTO();
                attempInfo.setAttemptNumber(0);
                attempInfo.setExerciseType("coding");
                attempInfo.setLessonID(codingExerciseService.getLessonIDByExerciseID(request.getExerciseID()));
            }

            ContestExerciseAttempt exerciseAttempt = new ContestExerciseAttempt();
            exerciseAttempt.setExerciseID(request.getExerciseID());
            CourseLesson lesson = lessonService.findById(attempInfo.getlessonID())
                    .orElseThrow(() -> new RuntimeException("Lesson not found"));
            exerciseAttempt.setLesson(lesson);
            User user = new User();
            user.setUserID(UserContext.getUserID());
            exerciseAttempt.setUser(user);
            exerciseAttempt.setSubmittedAt(LocalDateTime.now());
            exerciseAttempt.setExerciseType(attempInfo.getExerciseType());
            Integer currentAttempt = attempInfo.getAttemptNumber() == null ? 0 : attempInfo.getAttemptNumber();
            exerciseAttempt.setAttemptNumber(currentAttempt + 1);
            Number score = submission.getScore();
            exerciseAttempt.setScore(score != null ? score.doubleValue() : 0.0);

            // lưu attempt mới
            contestExerciseAttemptService.save(exerciseAttempt);
        }

        return submission;
    }

    @PostMapping("/essay/submit")
    public ResponseEntity<?> submitEssayExercise(@RequestBody EssayExerciseSubmissionRequest request) {
        AttemptInfoDTO attempInfo = contestExerciseAttemptService.getAttemptInfoDTOByuserIDAndExerciseID(UserContext.getUserID(), request.getExerciseID(), "essay");


        if(attempInfo != null && attempInfo.getAttemptNumber() != null && attempInfo.getAttemptNumber() >0){
            System.out.println("Lần làm bài thứ " + (attempInfo.getAttemptNumber() + 1));
        }
        if (attempInfo == null){
            attempInfo = new AttemptInfoDTO();
            attempInfo.setAttemptNumber(0);
            attempInfo.setExerciseType("essay");
            attempInfo.setLessonID(essayExerciseService.getLessonIDByExerciseID(request.getExerciseID()));
        }

        ContestExerciseAttempt exerciseAttempt = new ContestExerciseAttempt();
        exerciseAttempt.setExerciseID(request.getExerciseID());
        CourseLesson lesson = lessonService.findById(attempInfo.getlessonID())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        exerciseAttempt.setLesson(lesson);

        User user = new User();
        user.setUserID(UserContext.getUserID());
        exerciseAttempt.setUser(user);
        exerciseAttempt.setSubmittedAt(LocalDateTime.now());
        exerciseAttempt.setExerciseType(attempInfo.getExerciseType());
        Integer currentAttempt = attempInfo.getAttemptNumber() == null ? 0 : attempInfo.getAttemptNumber();
        exerciseAttempt.setAttemptNumber(currentAttempt + 1);



        String expectedAnswer = essayExerciseService.getExpectedAnswerOfEssayExerciseByExerciseID(request.getExerciseID());

        String prompt = "So sánh bài làm sinh viên với đáp án dưới đây. Hãy đưa ra nhận xét chi tiết và chấm điểm (thang điểm 10). Trả về kết quả dưới dạng JSON: {\"feedback\": \"...\", \"score\": số thực từ 0 đến 10}\n\n"
                + "Đáp án:\n" + expectedAnswer + "\n\n"
                + "Bài làm của sinh viên:\n" + request.getContent();

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + GEMINI_API_KEY;

        try {
            Mono<Map> responseMono = webClient.post()
                    .uri(url)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class);

            Map response = responseMono.block();
            List candidates = (List) response.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không có kết quả từ Gemini.");
            }

            Map contentMap = (Map) ((Map) candidates.get(0)).get("content");
            List parts = (List) contentMap.get("parts");
            String rawText = (String) ((Map) parts.get(0)).get("text");

            // Trích JSON bằng regex hoặc chuyển sang parser an toàn hơn
            Pattern jsonPattern = Pattern.compile("\\{.*?\\}", Pattern.DOTALL);
            Matcher matcher = jsonPattern.matcher(rawText);

            EssaySubmission submission = new EssaySubmission();
            EssayExercise exercise = new EssayExercise();
            exercise.setExerciseID(request.getExerciseID());
            User userSubmit = new User();
            userSubmit.setUserID(UserContext.getUserID());
            submission.setExercise(exercise);
            submission.setUser(userSubmit);
            submission.setAnswerText(request.getContent());
            submission.setSubmittedAt(LocalDateTime.now());


            if (matcher.find()) {
                String json = matcher.group();
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                Map<String, Object> result = mapper.readValue(json, Map.class);
                submission.setFeedback((String) result.get("feedback"));
                submission.setScore(Double.valueOf(result.get("score").toString()));
                exerciseAttempt.setScore(Double.valueOf(result.get("score").toString()));

                // Lưu vào DB
                essaySubmissionService.save(submission);
                contestExerciseAttemptService.save(exerciseAttempt);

                return ResponseEntity.ok(result);
            } else {
                submission.setFeedback(rawText);
                submission.setScore(0.0);
                essaySubmissionService.save(submission);
                contestExerciseAttemptService.save(exerciseAttempt);
                return ResponseEntity.ok(Map.of("feedback", rawText, "score", 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã có lỗi xảy ra khi gọi Gemini API.");
        }
    }

}
