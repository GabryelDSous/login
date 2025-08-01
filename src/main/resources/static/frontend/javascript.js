const loginForm = document.getElementById("loginForm");
const errorMsg = document.getElementById("errorMsg");

loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;
    login({ email, password });
});

async function login(data) {
    try {
        const response = await fetch("http://localhost:8080/logins/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const error = await response.json();
            errorMsg.textContent = error.message || "Erro inesperado";
            return;
        }

        const user = await response.json();
        console.log("Login bem-sucedido:", user);
        showToast(error.message || "Erro inesperado", true);
    } catch (err) {
        console.error("Erro de rede:", err);
        errorMsg.textContent = "Erro de conexão com o servidor";
    }
}

function showToast(message, isError = false) {
    const toast = document.getElementById("toast");
    toast.textContent = message;
    toast.style.backgroundColor = isError ? "#e74c3c" : "#2ecc71"; // vermelho ou verde
    toast.style.visibility = "visible";
    toast.style.opacity = "1";
    toast.style.bottom = "50px";

    setTimeout(() => {
    toast.style.opacity = "0";
    toast.style.bottom = "30px";
    toast.style.visibility = "hidden";
    }, 3000);
}