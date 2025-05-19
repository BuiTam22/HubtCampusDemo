package com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO;

public class JudgeRunResponseDTO {
    private String output;
    private String status;
    private String message;

    public JudgeRunResponseDTO(String output, String status, String message) {
        this.output = output;
        this.status = status;
        this.message = message;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
