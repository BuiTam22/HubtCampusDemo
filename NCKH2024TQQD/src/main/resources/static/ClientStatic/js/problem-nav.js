
    document.addEventListener("DOMContentLoaded", function() {
        const tabs = document.querySelectorAll(".nav-tab");
        const currentPath = window.location.pathname; //

        tabs.forEach(tab => {
            const tabPath = new URL(tab.href).pathname; // Chỉ lấy phần path của href
            if (tabPath === currentPath) {
                tabs.forEach(t => t.classList.remove('active')); // Bỏ active cũ
                tab.classList.add('active'); // Active tab mới
            }
        });
    });

