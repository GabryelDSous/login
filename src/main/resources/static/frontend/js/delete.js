document.addEventListener("DOMContentLoaded", ()=>{
    const loginForm = document.getElementById("deleteForm")
    const errorMsg = document.getElementById("errorMsg")

    loginForm.addEventListener("submit", async (e)=>{
        e.preventDefault()

        const loginEmail = document.getElementById("loginEmail").value
        const loginPassword = document.getElementById("loginPassword").value

        errorMsg.style.display = 'block'
        const URL = "http://localhost:8080/logins"
        try{
            const response = await fetch(URL,{
                method:"DELETE",
                headers:{
                    "Content-Type":"application/json"
                },
                body:JSON.stringify({
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
            const user = response.json()
            errorMsg.style.background = '#a5dda5'
            errorMsg.style.border = '1px solid #67ff53'
            errorMsg.style.color = 'green'
            errorMsg.textContent = 'account deleted successfully'
        } catch(err){
            console.log("NetWork error: " + err)
            errorMsg.textContent = "Try latter"
        }

    })
})