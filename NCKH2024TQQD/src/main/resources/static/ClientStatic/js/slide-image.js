document.addEventListener("DOMContentLoaded", function () {
  const scrollContainer = document.getElementById("brandScroll");
  const wrapper = scrollContainer.querySelector(".brand-logos-wrapper");
  const original = wrapper.querySelector(".brand-logos");
  const clone = original.cloneNode(true);
  wrapper.appendChild(clone);
  let scrollX = 0;
  const speed = 0.5;
  let isVisible = false;

  function animate() {
    if (!isVisible) return requestAnimationFrame(animate);

    scrollX += speed;
    if (scrollX >= original.offsetWidth) scrollX = 0;
    wrapper.style.transform = `translateX(-${scrollX}px)`;
    requestAnimationFrame(animate);
  }

  const observer = new IntersectionObserver(
    ([entry]) => {
      isVisible = entry.isIntersecting;
    },
    { threshold: 0.5 }
  );

  observer.observe(scrollContainer);
  requestAnimationFrame(animate);
});