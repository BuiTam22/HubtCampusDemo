document.addEventListener("DOMContentLoaded", function () {
    const runButton = document.querySelector(".submit-button");
    const codeEditor = document.getElementById("editor");
    const languageSelector = document.querySelector(".language-selector");

    const testCase = document.querySelector(".test-cases");

    // Ẩn test cases ban đầu
    testCase.style.display = "none";

    // Thêm spinner vào HTML
    runButton.innerHTML = `
        <span class="button-text">Nộp</span>
        <span class="spinner-border spinner-border-sm d-none" role="status"></span>
    `;

    runButton.addEventListener("click", async () => {

        // Vô hiệu hóa nút và hiển thị spinner
        runButton.disabled = true;
        runButton.querySelector(".button-text").textContent = "Đang nộp bài...";
        runButton.querySelector(".spinner-border").classList.remove("d-none");

        const {sourceCode, language, exerciseID } = getCodeSubmission();

        try {
            const response = await fetch("/api/judge/submit", {
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
            testCase.style.display = "block";

            console.log("Kết quả", result);
            showTestResult(result);



        } catch (error) {
            console.error("Lỗi:", error);
            alert("Không thể chạy code. Vui lòng thử lại.");
        } finally {
             // Luôn luôn chạy phần này dù thành công hay thất bại
             runButton.disabled = false;
             runButton.querySelector(".button-text").textContent = "Nộp";
             runButton.querySelector(".spinner-border").classList.add("d-none");
        }
    });
    function showTestResult(result) {
        document.getElementById("testResultContainer").style.display = "block";
        document.getElementById("resultLanguage").innerText = result.language;
        document.getElementById("resultStatus").innerText = result.status;
        document.getElementById("resultPassed").innerText = result.testCasesPassed;
        document.getElementById("resultTotal").innerText = result.totalTestCases;
        document.getElementById("resultScore").innerText = result.score;

//        const detailDiv = document.getElementById("testCaseDetails");
//        detailDiv.innerHTML = "";
//
//        result.testCaseResults.forEach(tc => {
//            const tcDiv = document.createElement("div");
//            tcDiv.classList.add("test-case");
//            tcDiv.classList.add(tc.passed ? "passed" : "failed");
//
//            tcDiv.innerHTML = `
//                <div class="test-case-header">Test Case ${tc.index}</div>
//                <pre><strong>Input:</strong> ${tc.input}</pre>
//                <pre><strong>Expected:</strong> ${tc.expectedOutput}</pre>
//                <pre><strong>Output:</strong> ${tc.actualOutput}</pre>
//                <div class="test-case-status">${tc.passed ? "✓ Qua" : "✗ Sai"}</div>
//            `;
//            detailDiv.appendChild(tcDiv);
//        });
    }
});
