// TASK 1: PRODUCT CLASS
class Product {
    constructor(id, name, price, image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
}

// TASK 1: DATA
let products = [];

// TASK 3: CART (WITH STORAGE)

let cart = JSON.parse(localStorage.getItem("cart")) || [];

// TASK 2: dynamic rendering

async function fetchProducts() {

    try {

        const response = await fetch("http://localhost:8080/api/v1/products");

        // Check response status
        if (!response.ok) {

            if (response.status === 404) {
                throw new Error("Products not found");
            }

            if (response.status === 500) {
                throw new Error("Internal server error");
            }

            throw new Error("Failed to fetch products");
        }

        products = await response.json();

        renderProducts(products);

    } catch (error) {

        console.error("Fetch error:", error.message);

        const productContainer = document.querySelector(".products-grid");

        if (productContainer) {
            productContainer.innerHTML = `
                <h2>${error.message}</h2>
            `;
        }
    }
}

function renderProducts(products) {

    const productContainer = document.querySelector(".products-grid");

    if (!productContainer) return;

    productContainer.innerHTML = "";

    // Empty state
    if (products.length === 0) {

        productContainer.innerHTML = `
            <h2>No products available</h2>
        `;

        return;
    }

    products.forEach(product => {

        const card = document.createElement("article");

        const title = document.createElement("h3");
        title.textContent = product.name;

        const img = document.createElement("img");

        img.src = product.image_url;
        img.alt = product.name;

        const price = document.createElement("p");
        price.textContent = "₱" + product.price;
        price.classList.add("price");

        const button = document.createElement("button");
        button.textContent = "Add to Cart";
        button.setAttribute("data-id", product.id);

        card.appendChild(title);
        card.appendChild(img);
        card.appendChild(price);
        card.appendChild(button);

        productContainer.appendChild(card);
    });
}
// TASK 3: EVENT DELEGATION

document.body.addEventListener("click", function (event) {
    if (
        event.target.tagName === "BUTTON" &&
        event.target.textContent === "Add to Cart"
    ) {
        const id = parseInt(event.target.getAttribute("data-id"));
        const product = products.find(p => p.id === id);

        const existing = cart.find(item => item.id === id);

        if (existing) {
            existing.qty++;
        } else {
            cart.push({ ...product, qty: 1 });
        }

        renderCart();

        // Popup with total
        const total = cart.reduce((sum, item) => sum + item.price * item.qty, 0);
        alert("Added to cart successfully! Total: ₱" + total);

        // Animation
        event.target.parentElement.classList.add("fade-in");
        setTimeout(() => {
            event.target.parentElement.classList.remove("fade-in");
        }, 500);
    }
});

// TASK 3: RENDER CART

function renderCart() {
    const cartList = document.querySelector(".cart-items");
    const subtotalEl = document.getElementById("Subtotal");
    const emptyMsg = document.getElementById("emptyMsg");

    if (!cartList) return;

    cartList.innerHTML = "";

    if (cart.length === 0) {
        if (emptyMsg) emptyMsg.style.display = "block";
    } else {
        if (emptyMsg) emptyMsg.style.display = "none";
    }

    cart.forEach(item => {
        const li = document.createElement("li");

        const name = document.createElement("h3");
        name.textContent = item.name;

        const price = document.createElement("p");
        price.textContent = "₱" + item.price;

        const qty = document.createElement("input");
        qty.type = "number";
        qty.value = item.qty;
        qty.min = 0;

        qty.addEventListener("change", function () {
            if (qty.value == 0) {
                cart = cart.filter(cartItem => cartItem.id !== item.id);
            } else {
                item.qty = parseInt(qty.value);
            }
            renderCart();
        });

        li.appendChild(name);
        li.appendChild(price);
        li.appendChild(qty);

        cartList.appendChild(li);
    });

    //Compute total
    const total = cart.reduce((sum, item) => sum + item.price * item.qty, 0);

    // Display total
    if (subtotalEl) {
        subtotalEl.textContent = "₱" + total;
    }

    //Save cart
    localStorage.setItem("cart", JSON.stringify(cart));
}

// TASK 4: FORM VALIDATION

const form = document.querySelector("form");

if (form) {
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const inputs = form.querySelectorAll("input");
        const errorMsg = document.querySelector("#errorMsg");

        let isValid = true;

        inputs.forEach(input => {
            if (input.value === "") {
                input.classList.add("error");
                isValid = false;
            } else {
                input.classList.remove("error");
            }
        });

        if (!isValid) {
            if (errorMsg) {
                errorMsg.textContent = "Please fill out all fields";
            }
        } else {
            console.log("Order successful!");
            window.location.href = "thankyou.html";
        }
    });
}


// TASK 5: USER ACCOUNT

const currentUser = {
    name: "Kj",
    orderHistory: [
        {
            id: 1,
            date: "Feb 1, 2026",
            total: 1020,
            items: ["Minimal Watch"]
        },
        {
            id: 2,
            date: "Feb 10, 2026",
            total: 2500,
            items: ["Smart Watch"]
        }
    ]
};

// Dynamic greeting
const headerTitle = document.querySelector("header h1");

if (headerTitle && headerTitle.textContent.includes("Welcome")) {
    headerTitle.textContent = "Welcome, " + currentUser.name;
}

// Expand order details
document.querySelectorAll("details").forEach((detail, index) => {
    const summary = detail.querySelector("summary");

    summary.addEventListener("click", function () {
        const order = currentUser.orderHistory[index];

        let existing = detail.querySelector(".order-details");

        if (!existing) {
            const content = document.createElement("div");
            content.classList.add("order-details");

            content.innerHTML = `
                <p>Date: ${order.date}</p>
                <p>Total: ₱${order.total}</p>
                <p>Items: ${order.items.join(", ")}</p>
            `;

            detail.appendChild(content);
        }
    });
});

document.addEventListener("DOMContentLoaded", () => {

    fetchProducts();

    renderCart();
});
