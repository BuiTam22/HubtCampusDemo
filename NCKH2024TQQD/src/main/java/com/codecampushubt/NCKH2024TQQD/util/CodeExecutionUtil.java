package com.codecampushubt.NCKH2024TQQD.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Utility class dùng để biên dịch, thực thi code, và quản lý file tạm
 */
public class CodeExecutionUtil {

    /**
     * Thực thi 1 lệnh command-line trong thư mục chỉ định.
     *
     * @param command Danh sách chuỗi đại diện cho lệnh cần thực thi (ví dụ: ["javac", "Main.java"])
     * @param workingDir Thư mục làm việc nơi command sẽ được thực thi
     * @return Output của command sau khi chạy xong
     * @throws IOException Nếu có lỗi I/O
     * @throws InterruptedException Nếu tiến trình bị ngắt
     */
    public static String runCommand(List<String> command, File workingDir) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(workingDir); // Thiết lập thư mục làm việc
        builder.redirectErrorStream(true); // Gộp cả stderr và stdout
        Process process = builder.start(); // Bắt đầu tiến trình

        // Đặt timeout để tránh treo tiến trình quá lâu
        if (!process.waitFor(10, TimeUnit.SECONDS)) {
            process.destroyForcibly(); // Dừng tiến trình nếu quá thời gian
            throw new RuntimeException("Process timeout after 10 seconds");
        }

        // Đọc output từ tiến trình
        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.lines().collect(Collectors.joining("\n"));
        }

        // Nếu exit code khác 0 (lỗi), ném ra ngoại lệ CompilationException kèm output
        if (process.exitValue() != 0) {
            throw new CompilationException("Command failed: " + String.join(" ", command) + "\nOutput: " + output);
        }

        return output;
    }

    /**
     * Thực thi 1 lệnh command-line trong thư mục chỉ định và truyền input vào chương trình.
     *
     * @param command Danh sách chuỗi đại diện cho lệnh cần thực thi (ví dụ: ["java", "Main"])
     * @param input Chuỗi input sẽ được truyền vào tiến trình qua stdin (System.in)
     * @param workingDir Thư mục làm việc nơi command sẽ được thực thi
     * @return Output của chương trình sau khi chạy xong
     * @throws IOException Nếu có lỗi I/O
     * @throws InterruptedException Nếu tiến trình bị ngắt
     */
    public static String runCommandWithInput(List<String> command, String input, File workingDir)
            throws IOException, InterruptedException {

        // Khởi tạo tiến trình với command được cung cấp
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(workingDir); // Thiết lập thư mục làm việc
        builder.redirectErrorStream(true); // Gộp cả stderr và stdout

        // Bắt đầu tiến trình
        Process process = builder.start();

        // Gửi input vào tiến trình thông qua stdin
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(input);    // Ghi input vào tiến trình
            writer.flush();         // Đảm bảo dữ liệu được đẩy đi ngay lập tức
        }

        // Đọc kết quả output từ stdout
        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.lines().collect(Collectors.joining("\n")); // Gom tất cả dòng thành chuỗi kết quả
        }

        // Đợi tiến trình kết thúc (có thể dùng thêm timeout nếu muốn an toàn hơn)
        process.waitFor();

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
                // Duyệt từ dưới lên (xoá file trước rồi thư mục)
                Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while deleting files", e);
        }
    }

    /**
     * Exception đại diện cho lỗi biên dịch code.
     */
    public static class CompilationException extends RuntimeException {
        private final String output;

        public CompilationException(String message) {
            super(message);
            // Lấy phần output từ thông báo lỗi nếu có
            if (message.contains("Output: ")) {
                this.output = message.substring(message.indexOf("Output: ") + 8);
            } else {
                this.output = "";
            }
        }

        public String getOutput() {
            return output;
        }
    }
}
