document.addEventListener("DOMContentLoaded", function () {
    const runButton = document.querySelector(".run-button");
    const codeEditor = document.getElementById("editor");
    const languageSelector = document.querySelector(".language-selector");

    const testCase = document.querySelector(".test-cases");

    // Ẩn test cases ban đầu
    testCase.style.display = "none";

    // Thêm spinner vào HTML
    runButton.innerHTML = `
        <span class="button-text">Chạy code</span>
        <span class="spinner-border spinner-border-sm d-none" role="status"></span>
    `;

    runButton.addEventListener("click", async () => {
        // Vô hiệu hóa nút và hiển thị spinner
        runButton.disabled = true;
        runButton.querySelector(".button-text").textContent = "Đang chạy...";
        runButton.querySelector(".spinner-border").classList.remove("d-none");

        const {sourceCode, language, exerciseID } = getCodeSubmission();

        try {
            const response = await fetch("/api/judge/run", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    language: language,
                    sourceCode: sourceCode,
                    exerciseID: exerciseID
                })
            });

            if (!response.ok) {
                throw new Error("Có lỗi xảy ra khi gửi code.");
            }

            const result = await response.json();

            // Hiện test cases sau khi chạy xong
            testCase.style.display = "none";

            console.log("Kết quả chạy thử:", result);
            alert("Kết quả: " + (result.output || result.message));

        } catch (error) {
            console.error("Lỗi:", error);
            alert("Không thể chạy code. Vui lòng thử lại.");
        } finally{
            // Khôi phục trạng thái nút
            runButton.disabled = false;
            runButton.querySelector(".button-text").textContent = "Chạy code";
            runButton.querySelector(".spinner-border").classList.add("d-none");
        }
    });
});
