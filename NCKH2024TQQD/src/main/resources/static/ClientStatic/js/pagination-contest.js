document.addEventListener("DOMContentLoaded", function () {
    const rowsPerPage = 6; // Số mục mỗi trang (thay đổi tùy ý)
    const container = document.getElementById("contest-list");
    const items = Array.from(container.querySelectorAll(".col-12"));
    const pagination = document.getElementById("pagination");
    let currentPage = 1;

    function displayItems() {
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        items.forEach((item, index) => {
            item.style.display = (index >= start && index < end) ? "" : "none";
        });
    }

    function setupPagination() {
        pagination.innerHTML = "";
        const pageCount = Math.ceil(items.length / rowsPerPage);

        // Previous button
        const prevItem = document.createElement("li");
        prevItem.className = `page-item ${currentPage === 1 ? 'disabled' : ''}`;
        const prevLink = document.createElement("a");
        prevLink.className = "page-link";
        prevLink.href = "#";
        prevLink.textContent = "Previous";
        prevLink.onclick = (e) => {
            e.preventDefault();
            if (currentPage > 1) {
                currentPage--;
                displayItems();
                setupPagination();
            }
        };
        prevItem.appendChild(prevLink);
        pagination.appendChild(prevItem);

        // Page number buttons
        for (let i = 1; i <= pageCount; i++) {
            const li = document.createElement("li");
            li.className = `page-item ${currentPage === i ? 'active' : ''}`;
            const link = document.createElement("a");
            link.className = "page-link";
            link.href = "#";
            link.textContent = i;
            link.onclick = (e) => {
                e.preventDefault();
                currentPage = i;
                displayItems();
                setupPagination();
            };
            li.appendChild(link);
            pagination.appendChild(li);
        }

        // Next button
        const nextItem = document.createElement("li");
        nextItem.className = `page-item ${currentPage === pageCount ? 'disabled' : ''}`;
        const nextLink = document.createElement("a");
        nextLink.className = "page-link";
        nextLink.href = "#";
        nextLink.textContent = "Next";
        nextLink.onclick = (e) => {
            e.preventDefault();
            if (currentPage < pageCount) {
                currentPage++;
                displayItems();
                setupPagination();
            }
        };
        nextItem.appendChild(nextLink);
        pagination.appendChild(nextItem);
    }

    // Gọi lần đầu
    displayItems();
    setupPagination();
});