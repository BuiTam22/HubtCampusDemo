document.addEventListener("DOMContentLoaded", function () {
    const submitButton = document.querySelector(".submit-button");
    const essayEditor = document.querySelector(".essay-editor");

    const exerciseID = essayEditor.getAttribute("data-exercise-id");

    submitButton.addEventListener("click", function () {
        const userAnswer = essayEditor.innerText.trim();

        if (!userAnswer) {
            alert("Vui lòng nhập nội dung bài làm.");
            return;
        }

        const payload = {
            exerciseID: exerciseID,
            content: userAnswer
        };

        fetch("/api/judge/essay/submit", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Lỗi khi gửi bài!");
                }
                return response.json();
            })
            .then(data => {
                alert("Nộp bài thành công!");
                console.log("Kết quả chấm:", data);
            })
            .catch(error => {
                console.error("Lỗi:", error);
                alert("Đã có lỗi xảy ra khi nộp bài.");
            });
    });
});
