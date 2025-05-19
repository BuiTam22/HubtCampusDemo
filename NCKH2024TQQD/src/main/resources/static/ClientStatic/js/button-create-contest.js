// button Create contest
document.addEventListener('DOMContentLoaded', function() {
  const createContestButton = document.querySelector('#createContestButton');
  if (createContestButton) {
    createContestButton.addEventListener('click', function() {
      console.log('Create Contest button clicked'); // Kiểm tra sự kiện đã được kích hoạt chưa
      window.location.href = '/management/contest/create';
    });
  } else {
    console.log('Create Contest button not found');
  }
});
