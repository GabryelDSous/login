document.addEventListener("DOMContentLoaded", () => {
    const userData = JSON.parse(sessionStorage.getItem("userData"))
    if(!userData){
        alert("Unauthenticated user")
        window.location.href = "../index.html"
    }
    console.log(userData)
    document.getElementById("loginName").innerHTML += userData.name
})