package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.dao.ExerciseTestCaseRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.JudgeRequestDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.JudgeRunResponseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionResponseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseSubmissionRequest;
import com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO;
import com.codecampushubt.NCKH2024TQQD.entity.CodingExercise;
import com.codecampushubt.NCKH2024TQQD.entity.CodingSubmission;
import com.codecampushubt.NCKH2024TQQD.entity.User;
import com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices.CodingExerciseService;
import com.codecampushubt.NCKH2024TQQD.service.CodingSubmissionServices.CodingSubmissionService;
import com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices.EssayExerciseService;
import com.codecampushubt.NCKH2024TQQD.service.JudgeServices.JudgeService;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import com.ibm.icu.text.UFieldPosition;
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


    @Autowired
    public RestJudge(JudgeService judgeService, ExerciseTestCaseRepository exerciseTestCaseRepository, UserService userService, CodingExerciseService codingExerciseService, CodingSubmissionService codingSubmissionService, WebClient webClient, EssayExerciseService essayExerciseService) {
        this.judgeService = judgeService;
        this.exerciseTestCaseRepository = exerciseTestCaseRepository;
        this.userService = userService;
        this.codingExerciseService = codingExerciseService;
        this.codingSubmissionService = codingSubmissionService;
        this.webClient = webClient;
        this.essayExerciseService = essayExerciseService;
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
        User user = userService.getUserEntityByID(submission.getUserID());
        CodingExercise codingExercise = codingExerciseService.getExerciseEntityByID(submission.getExerciseID());
        CodingSubmission codingSubmission = new CodingSubmission();

        codingSubmission.setCode(submission.getCode());
        codingSubmission.setLanguage(submission.getLanguage());
        codingSubmission.setStatus(submission.getStatus());
        codingSubmission.setTestCasesPassed(submission.getTestCasesPassed());
        codingSubmission.setTotalTestCases(submission.getTotalTestCases());
        codingSubmission.setScore(submission.getScore());
        codingSubmission.setExercise(codingExercise);
        codingSubmission.setUser(user);
        codingSubmission.setExecutionTime(1);
        codingSubmission.setMemoryUsed(10);
        codingSubmission.setSubmittedAt(LocalDateTime.now());
        CodingSubmission newSubmission =  codingSubmissionService.save(codingSubmission);

        return submission;
    }

    @PostMapping("/essay/submit")
    public ResponseEntity<?> submitEssayExercise(@RequestBody EssayExerciseSubmissionRequest request) {

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

            if (matcher.find()) {
                String json = matcher.group();
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                Map<String, Object> result = mapper.readValue(json, Map.class);
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.ok(Map.of("feedback", rawText, "score", 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã có lỗi xảy ra khi gọi Gemini API.");
        }
    }

}
