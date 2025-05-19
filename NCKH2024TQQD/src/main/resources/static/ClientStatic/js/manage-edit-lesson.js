document.getElementById('manage-score').addEventListener('click', function() {
  const slug = this.getAttribute('data-slug');
  window.location.href = `/management/contest/score/${slug}`;
});
