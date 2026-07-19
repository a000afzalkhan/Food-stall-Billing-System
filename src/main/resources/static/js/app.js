const money = new Intl.NumberFormat("en-IN", {
  currency: "INR",
  minimumFractionDigits: 2,
  style: "currency",
});

const inputs = Array.from(document.querySelectorAll(".qty-input"));
const billRows = document.querySelector("#billRows");
const subtotalText = document.querySelector("#subtotalText");
const gstText = document.querySelector("#gstText");
const grandText = document.querySelector("#grandText");
const gstSwitch = document.querySelector("#gstSwitch");

document.querySelectorAll(".qty-btn").forEach((button) => {
  button.addEventListener("click", () => {
    const input = button.parentElement.querySelector(".qty-input");
    const action = button.dataset.action;
    const current = Number(input.value || 0);
    const max = Number(input.max || 0);

    if (action === "plus") {
      input.value = Math.min(max, current + 1);
    } else {
      input.value = Math.max(0, current - 1);
    }

    renderBill();
  });
});

inputs.forEach((input) => input.addEventListener("input", renderBill));
gstSwitch?.addEventListener("change", renderBill);

function renderBill() {
  let subtotal = 0;
  const selectedRows = inputs
    .map((input) => {
      const quantity = Math.max(0, Number(input.value || 0));
      const price = Number(input.dataset.price);
      const total = quantity * price;
      subtotal += total;
      return {
        name: input.dataset.name,
        quantity,
        total,
      };
    })
    .filter((row) => row.quantity > 0);

  if (selectedRows.length === 0) {
    billRows.className = "bill-rows empty";
    billRows.textContent = "No items selected yet.";
  } else {
    billRows.className = "bill-rows";
    billRows.innerHTML = selectedRows
      .map((row) => `
        <div class="bill-row">
          <span>${escapeHtml(row.name)} x ${row.quantity}</span>
          <strong>${money.format(row.total)}</strong>
        </div>
      `)
      .join("");
  }

  const gst = gstSwitch?.checked ? subtotal * 0.05 : 0;
  subtotalText.textContent = money.format(subtotal);
  gstText.textContent = money.format(gst);
  grandText.textContent = money.format(subtotal + gst);
}

function escapeHtml(value) {
  return String(value).replace(/[&<>"']/g, (char) => {
    return { "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#039;" }[char];
  });
}

renderBill();
