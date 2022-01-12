    const base_url = "http://localhost:8090/";

    document.getElementById("login-btn").addEventListener("click", loginFunc);

//function to getCookie value
function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
}

function deleteCookie(){
    document.cookie = "name=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
}

function checkCookie() {
    let user = getCookie("username");
    if (user != "") {
      alert("Welcome again " + user);
    } else {
      //destroycookie and login
      deleteCookie();
      window.location = "./login.html";
    }
}




async function loginFunc(){
    
    // Login functions 
    let usern = document.getElementById("inputUsername").value;
    let passd = document.getElementById("inputPassword").value;
    let roleId = document.getElementById("roleId").value;
    
    let user = {
    
        username:usern,
        password:passd,
        role_id:roleId
    }
    
    if(usern == ""){
        
        document.getElementById("error-message-username").innerHTML = "This field is Required!! ";
       
    }else if(passd ==""){
        document.getElementById("error-message-pass").innerHTML = "This field is Required !!";
        
    }
    else{

        let response = await fetch(base_url + "login", {

            method: "POST",
            body:JSON.stringify(user),
            headers : { 
                'Content-Type': 'application/json',
                'Accept': 'application/json'
               },
            credentials: "include",
        });
        
        if(response.status == 200){
        
            deleteCookie();
            
            document.cookie = "name=" + usern +"; SameSite=None; Secure";
            if(roleId==1){
                window.location = "./reimbursementForm.html";
            }else{
                window.location = "./admin/managerPanel.html"
            }
         
    
        }else{
            document.getElementById("message-error").innerHTML = "Invalid Username/Password";
        }
    }
    // console.log(user);
    
    
    

}