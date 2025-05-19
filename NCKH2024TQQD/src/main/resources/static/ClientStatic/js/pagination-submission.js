 document.addEventListener("DOMContentLoaded", function () {
        const rowsPerPage = 15; // Số dòng mỗi trang

        const table = document.querySelector(".custom-table tbody");
        const rows = Array.from(table.querySelectorAll("tr"));
        const pagination = document.getElementById("pagination");
        let currentPage = 1;

        function displayRows() {
            const start = (currentPage - 1) * rowsPerPage;
            const end = start + rowsPerPage;
            rows.forEach((row, index) => {
                row.style.display = (index >= start && index < end) ? "" : "none";
            });
        }

        function setupPagination() {
            pagination.innerHTML = "";
            const pageCount = Math.ceil(rows.length / rowsPerPage);

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
                    displayRows();
                    setupPagination();
                }
            };
            prevItem.appendChild(prevLink);
            pagination.appendChild(prevItem);

            // Page number buttons
            for (let i = 1; i <= pageCount; i++) {
                const pageItem = document.createElement("li");
                pageItem.className = `page-item ${i === currentPage ? 'active' : ''}`;
                const pageLink = document.createElement("a");
                pageLink.className = "page-link";
                pageLink.href = "#";
                pageLink.textContent = i;
                pageLink.onclick = (e) => {
                    e.preventDefault();
                    currentPage = i;
                    displayRows();
                    setupPagination();
                };
                pageItem.appendChild(pageLink);
                pagination.appendChild(pageItem);
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
                    displayRows();
                    setupPagination();
                }
            };
            nextItem.appendChild(nextLink);
            pagination.appendChild(nextItem);
        }

        displayRows();
        setupPagination();
    });