document.querySelectorAll('.view-results').forEach(button => {
    button.addEventListener('click', function() {
        const row = this.closest('tr');
        const detailSection = document.getElementById('submissionDetail');

        // Lấy dữ liệu
        const detailData = {
            code: row.dataset.code,
            passed: row.dataset.passed,
            total: row.dataset.total,
            score: row.dataset.score,
            language: row.dataset.language
        };

        // Cập nhật nội dung
        document.getElementById('detailPassed').textContent = detailData.passed;
        document.getElementById('detailTotal').textContent = detailData.total;
        document.getElementById('detailScore').textContent = detailData.score;
        document.getElementById('detailLanguage').textContent = detailData.language;
        document.getElementById('detailCode').textContent = detailData.code;

        // Hiển thị và scroll đến section
        detailSection.style.display = 'block';
        detailSection.scrollIntoView({ behavior: 'smooth' });
    });
});