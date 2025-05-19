        let monacoEditor;

        require.config({ paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor@0.45.0/min/vs' } });
        require(['vs/editor/editor.main'], function () {
            const initialCode = document.getElementById('editor').innerText.trim();
            document.getElementById('editor').innerText = ''; // Xóa nội dung cũ để tránh trùng lặp

            monacoEditor = monaco.editor.create(document.getElementById('editor'), {
                value: initialCode || '// Viết code vào đây',
                        language: document.getElementById('languageSelector').value,
                        theme: 'vs-dark',
                        automaticLayout: true,
                        fontSize: 14, // Cỡ chữ mặc định
                        lineHeight: 24, // Chiều cao dòng
                        fontFamily: 'Menlo, Monaco, "Courier New", monospace', // Font chữ
                        minimap: {
                            enabled: true
                        },
                        scrollbar: {
                            verticalScrollbarSize: 8,
                            horizontalScrollbarSize: 8
                        }
            });

            document.getElementById('languageSelector').addEventListener('change', function () {
                const lang = this.value;
                monaco.editor.setModelLanguage(monacoEditor.getModel(), lang);
            });
        });

        // Hàm lấy code khi chạy hoặc nộp
        function getCodeSubmission() {
            return {
                sourceCode: monacoEditor.getValue(),
                language: document.getElementById('languageSelector').value,
                exerciseID: document.getElementById('editor').getAttribute('data-exercise-id')
            };
        }

        // Bạn có thể dùng hàm getCodeSubmission trong handle-run-code.js / handle-submit-code.js