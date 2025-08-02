document.addEventListener("DOMContentLoaded", ()=>{
    const loginForm = document.getElementById("loginForm")
    const errorMsg = document.getElementById("errorMsg")

    loginForm.addEventListener("submit", async (e)=>{
        e.preventDefault()

        const loginEmail = document.getElementById("loginEmail").value
        const loginPassword = document.getElementById("loginPassword").value

        errorMsg.style.display = "block"

        const URL = "http://localhost:8080/logins/login"
        try{
            const response = await fetch(URL,{
                method:"POST",
                headers:{
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    email:loginEmail,
                    password:loginPassword
                })
            })
            if(!response.ok){
                const errorData = await response.json()
                    errorMsg.style.background = '#490000ff'
                    errorMsg.style.border = '1px solid #ff0000ff'
                    errorMsg.style.color = 'red'
                    errorMsg.textContent = errorData.message || "Unknow error"
                return
            }
            const user = await response.json()

            sessionStorage.setItem("userData", JSON.stringify(user))
            window.location.href = "../html/login.html"


            errorMsg.style.background = '#a5dda5'
            errorMsg.style.border = '1px solid #67ff53'
            errorMsg.style.color = 'green'
            errorMsg.textContent = 'Successuful login'
        } catch(err){
            console.log("NetWork error: " + err);
            errorMsg.textContent = 'Try latter'
        }
    })
})