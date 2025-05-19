package com.codecampushubt.NCKH2024TQQD.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Utility class dùng để biên dịch, thực thi code trong Docker container để bảo mật
 */
public class DockerCodeExecutionUtil {

    private static final String DOCKER_IMAGE = "java-runner:latest";
    private static final int EXECUTION_TIMEOUT_SECONDS = 10;
    private static final int MEMORY_LIMIT_MB = 256;

    /**
     * Biên dịch mã Java trong một Docker container
     *
     * @param workingDir Thư mục chứa mã nguồn
     * @throws IOException Nếu có lỗi thao tác với file
     * @throws InterruptedException Nếu tiến trình bị ngắt
     * @throws CompilationException Nếu biên dịch gặp lỗi
     */
    public static void compileJavaInContainer(Path workingDir) throws IOException, InterruptedException {
        List<String> dockerCommand = new ArrayList<>(Arrays.asList(
                "docker", "run", "--rm",                                 // Xóa container sau khi chạy xong
                "--network", "none",                                     // Không cho phép kết nối mạng
                "--memory=" + MEMORY_LIMIT_MB + "m",                     // Giới hạn bộ nhớ
                "--cpus=0.5",                                            // Giới hạn CPU
                "--pids-limit=100",                                      // Giới hạn số tiến trình
                "--cap-drop=ALL",                                        // Loại bỏ tất cả các khả năng đặc biệt
                "--security-opt=no-new-privileges",                      // Không cho phép nâng cao đặc quyền
                "-v", workingDir.toAbsolutePath() + ":/app:Z",           // Mount thư mục code vào container
                "-w", "/app",                                            // Đặt thư mục làm việc
                DOCKER_IMAGE,                                            // Sử dụng image Java đã tạo
                "javac", "Main.java"                                     // Lệnh biên dịch
        ));

        ProcessBuilder builder = new ProcessBuilder(dockerCommand);
        builder.redirectErrorStream(true);
        Process process = builder.start();

        if (!process.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
            process.destroyForcibly();
            throw new RuntimeException("Quá thời gian biên dịch sau " + EXECUTION_TIMEOUT_SECONDS + " giây");
        }

        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.lines().collect(Collectors.joining("\n"));
        }

        if (process.exitValue() != 0) {
            throw new CompilationException("Lỗi biên dịch: " + output);
        }
    }

    /**
     * Chạy mã Java đã biên dịch trong Docker container
     *
     * @param workingDir Thư mục chứa mã đã biên dịch
     * @param input Dữ liệu đầu vào cho chương trình (nếu có)
     * @return Đầu ra của chương trình
     * @throws IOException Nếu có lỗi thao tác với file
     * @throws InterruptedException Nếu tiến trình bị ngắt
     */
    public static String runJavaInContainer(Path workingDir, String input) throws IOException, InterruptedException {
        String containerId = "java-execution-" + UUID.randomUUID().toString().substring(0, 8);

        List<String> dockerCommand = new ArrayList<>(Arrays.asList(
                "docker", "run", "--rm", "--name", containerId,          // Đặt tên và xóa container sau khi chạy
                "--network", "none",                                     // Không cho phép kết nối mạng
                "--memory=" + MEMORY_LIMIT_MB + "m",                     // Giới hạn bộ nhớ
                "--cpus=0.5",                                            // Giới hạn CPU
                "--ulimit", "nproc=50:100",                              // Giới hạn số tiến trình
                "--pids-limit=100",                                      // Giới hạn số tiến trình
                "--cap-drop=ALL",                                        // Loại bỏ tất cả các khả năng đặc biệt
                "--security-opt=no-new-privileges",                      // Không cho phép nâng cao đặc quyền
                "-v", workingDir.toAbsolutePath() + ":/app:Z",           // Mount thư mục code vào container
                "-w", "/app",                                            // Đặt thư mục làm việc
                "-i",                                                    // Cho phép đầu vào
                DOCKER_IMAGE,                                           // Sử dụng image Java đã tạo
                "java", "Main"                                           // Lệnh chạy Java
        ));

        ProcessBuilder builder = new ProcessBuilder(dockerCommand);
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Nếu có input, gửi vào stdin của process
        if (input != null && !input.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(input);
                writer.flush();
            }
        }

        // Đặt timeout để tránh chương trình chạy vô hạn
        if (!process.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
            // Buộc dừng container nếu quá thời gian
            try {
                new ProcessBuilder("docker", "kill", containerId).start().waitFor();
            } catch (Exception e) {
                // Bỏ qua lỗi khi cố gắng dừng container
            }
            process.destroyForcibly();
            return "Chương trình chạy quá lâu và đã bị hủy sau " + EXECUTION_TIMEOUT_SECONDS + " giây";
        }

        // Đọc output từ process
        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.lines().collect(Collectors.joining("\n"));
        }

        return output;
    }

    /**
     * Xóa toàn bộ thư mục và các file con bên trong nó.
     *
     * @param path Đường dẫn tới thư mục cần xóa
     */
    public static void deleteDirectoryRecursively(Path path) {
        try {
            if (Files.exists(path)) {
                Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi xóa files", e);
        }
    }

    /**
     * Exception đại diện cho lỗi biên dịch code.
     */
    public static class CompilationException extends RuntimeException {
        private final String output;

        public CompilationException(String message) {
            super(message);
            this.output = message;
        }

        public String getOutput() {
            return output;
        }
    }
}