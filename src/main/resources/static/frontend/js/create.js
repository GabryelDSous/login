document.addEventListener("DOMContentLoaded", ()=>{
    const loginForm = document.getElementById("createForm")
    const errorMsg = document.getElementById("errorMsg")

    loginForm.addEventListener("submit", async (e)=>{
        e.preventDefault()

        const loginName = document.getElementById("loginName").value
        const loginEmail = document.getElementById("loginEmail").value
        const loginPassword = document.getElementById("loginPassword").value
        const loginYear = document.getElementById("birthYear").value

        errorMsg.style.display = 'block'
        const URL = 'http://localhost:8080/logins'
        try{
            const response = await fetch(URL,{
                method:"POST",
                headers:{
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    name:loginName,
                    email:loginEmail,
                    password:loginPassword,
                    birthYear:loginYear
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
            errorMsg.style.background = '#a5dda5'
            errorMsg.style.border = '1px solid #67ff53'
            errorMsg.style.color = 'green'
            errorMsg.textContent = 'account created successfully'
        } catch(err){
            console.log("NetWork error: " + err);
            errorMsg.textContent = 'Try latter'
        }
    })
})