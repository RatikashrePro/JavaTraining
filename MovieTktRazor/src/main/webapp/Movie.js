/* ---------------------------
   Ticket Booking Script
   --------------------------- */

// Replace this with your Razorpay test key
const RAZORPAY_KEY = "rzp_test_REPLACE_WITH_YOUR_KEY";

// Global state (stored also in localStorage)
let state = {
  selectedShow: null,
  selectedSeats: [],
  customer: {
    name: "",
    email: "",
    phone: "",
  },
  totalAmount: 0,
};

// Sample shows (you can fetch from API/database later)
const shows = [
  {
    id: 1,
    title: "Avengers: Endgame",
    price: 250,
    img: "https://source.unsplash.com/600x400/?movie,marvel",
    rating: "⭐ 4.9",
  },
  {
    id: 2,
    title: "Inception",
    price: 200,
    img: "https://source.unsplash.com/600x400/?cinema,dream",
    rating: "⭐ 4.8",
  },
  {
    id: 3,
    title: "The Dark Knight",
    price: 220,
    img: "https://source.unsplash.com/600x400/?batman,movie",
    rating: "⭐ 5.0",
  },
];

/* ---------------------------
   Gallery Rendering
   --------------------------- */
function renderGallery() {
  const gallery = document.querySelector("#gallery");
  if (!gallery) return;
  gallery.innerHTML = shows
    .map(
      (show) => `
      <div class="card">
        <img src="${show.img}" alt="${show.title}" />
        <h3>${show.title}</h3>
        <p>${show.rating}</p>
        <p>₹${show.price}</p>
        <button onclick="selectShow(${show.id})">Book Now</button>
      </div>
    `
    )
    .join("");
}

function selectShow(id) {
  const show = shows.find((s) => s.id === id);
  state.selectedShow = show;
  state.selectedSeats = [];
  saveState();
  window.location.href = "booking.html";
}

/* ---------------------------
   Seat Booking Page
   --------------------------- */
function renderSeats() {
  const seatContainer = document.querySelector("#seats");
  if (!seatContainer) return;

  seatContainer.innerHTML = "";
  for (let row = 0; row < 8; row++) {
    let rowDiv = document.createElement("div");
    rowDiv.className = "seat-row";
    for (let col = 0; col < 12; col++) {
      let seatNum = `${row + 1}-${col + 1}`;
      let btn = document.createElement("button");
      btn.className = "seat";
      btn.textContent = seatNum;
      btn.onclick = () => toggleSeat(seatNum);
      if (state.selectedSeats.includes(seatNum)) {
        btn.classList.add("selected");
      }
      rowDiv.appendChild(btn);
    }
    seatContainer.appendChild(rowDiv);
  }
  updateBookingSummary();
}

function toggleSeat(seat) {
  if (state.selectedSeats.includes(seat)) {
    state.selectedSeats = state.selectedSeats.filter((s) => s !== seat);
  } else {
    state.selectedSeats.push(seat);
  }
  saveState();
  renderSeats();
}

function updateBookingSummary() {
  const summary = document.querySelector("#summary");
  if (!summary || !state.selectedShow) return;

  const total = state.selectedSeats.length * state.selectedShow.price;
  state.totalAmount = total;
  saveState();

  summary.innerHTML = `
    <h3>Booking Summary</h3>
    <p><strong>Movie:</strong> ${state.selectedShow.title}</p>
    <p><strong>Seats:</strong> ${state.selectedSeats.join(", ") || "None"}</p>
    <p><strong>Total:</strong> ₹${total}</p>
    <button onclick="goToCheckout()" ${
      total === 0 ? "disabled" : ""
    }>Proceed to Checkout</button>
  `;
}

function goToCheckout() {
  window.location.href = "checkout.html";
}

/* ---------------------------
   Checkout Page
   --------------------------- */
function renderCheckout() {
  const checkout = document.querySelector("#checkout");
  if (!checkout || !state.selectedShow) return;

  checkout.innerHTML = `
    <h3>Checkout</h3>
    <p><strong>Movie:</strong> ${state.selectedShow.title}</p>
    <p><strong>Seats:</strong> ${state.selectedSeats.join(", ")}</p>
    <p><strong>Total:</strong> ₹${state.totalAmount}</p>
    <form id="customerForm">
      <input type="text" id="name" placeholder="Your Name" required />
      <input type="email" id="email" placeholder="Your Email" required />
      <input type="tel" id="phone" placeholder="Your Phone" required />
      <button type="submit">Pay with Razorpay</button>
    </form>
  `;

  document.querySelector("#customerForm").onsubmit = (e) => {
    e.preventDefault();
    state.customer.name = document.querySelector("#name").value;
    state.customer.email = document.querySelector("#email").value;
    state.customer.phone = document.querySelector("#phone").value;
    saveState();
    payWithRazorpay();
  };
}

/* ---------------------------
   Razorpay Payment
   --------------------------- */
function payWithRazorpay() {
  const options = {
    key: RAZORPAY_KEY,
    amount: state.totalAmount * 100, // in paise
    currency: "INR",
    name: "Ticket Booking",
    description: state.selectedShow.title,
    image: "assets/logo.svg",
    handler: function (response) {
      console.log("Payment successful:", response);
      localStorage.setItem("lastPayment", JSON.stringify(response));
      window.location.href = "confirmation.html";
    },
    prefill: {
      name: state.customer.name,
      email: state.customer.email,
      contact: state.customer.phone,
    },
    theme: {
      color: "#0ef",
    },
  };
  const rzp = new Razorpay(options);
  rzp.open();
}

/* ---------------------------
   Confirmation Page
   --------------------------- */
function renderConfirmation() {
  const conf = document.querySelector("#confirmation");
  if (!conf || !state.selectedShow) return;

  const payment = JSON.parse(localStorage.getItem("lastPayment")) || {};
  conf.innerHTML = `
    <h2>🎉 Booking Confirmed!</h2>
    <p><strong>Movie:</strong> ${state.selectedShow.title}</p>
    <p><strong>Seats:</strong> ${state.selectedSeats.join(", ")}</p>
    <p><strong>Total Paid:</strong> ₹${state.totalAmount}</p>
    <p><strong>Payment ID:</strong> ${payment.razorpay_payment_id || "N/A"}</p>
    <button onclick="window.location.href='index.html'">Back to Home</button>
  `;
}

/* ---------------------------
   State Persistence
   --------------------------- */
function saveState() {
  localStorage.setItem("ticketState", JSON.stringify(state));
}

function loadState() {
  const saved = localStorage.getItem("ticketState");
  if (saved) {
    state = JSON.parse(saved);
  }
}

/* ---------------------------
   Init
   --------------------------- */
window.onload = () => {
  loadState();
  renderGallery();
  renderSeats();
  renderCheckout();
  renderConfirmation();
};
/**
 * 
 */