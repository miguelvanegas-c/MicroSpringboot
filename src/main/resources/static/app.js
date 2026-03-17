(() => {
  const btn = document.getElementById("btn-test");
  const statusText = document.getElementById("status-text");

  btn.addEventListener("click", () => {
    statusText.textContent = "✓ JavaScript funcionando correctamente.";
    document.getElementById("card-status").classList.add("loaded");
  });
})();