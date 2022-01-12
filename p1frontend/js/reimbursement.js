const base_url = "http://localhost:8090/";

document.getElementById("submit-reimbursement").addEventListener("click", addReimbursement);

// function setCookie(cname, cvalue, exdays) {
//     const d = new Date();
//     d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
//     let expires = "expires="+d.toUTCString();
//     document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/"; ;
// }
       
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
    window.location = "./login.html";
}
  
  function checkCookie() {
    let user = getCookie("username");
    if (user != "") {
    //   alert("Welcome again " + user);
    } else {
      deleteCookie();
      window.BeforeUnloadEvent();
    }
  }
  if(getCookie("name") != ""){
    let nameCookies = getCookie("name");
    document.getElementById("navbarDropdown").innerHTML=nameCookies;
  }else {
    //destroycookie and login
       // logout
    deleteCookie();
    window.location = "/login.html";
  }
document.getElementById("logout").addEventListener("click", deleteCookie);

async function addReimbursement(event){
    
    let amount = document.getElementById("reimbursementAmount").value;

    let reimb_receipt = document.getElementById("receipt").files[0]; 
    // document.getElementById("receipt").value;
    console.log(reimb_receipt);
    let type = document.getElementById("reimbursementType").value;
    let typeName = {
        reimbursementType1:"Lodging",
        reimbursementType2:"Travel",
        reimbursementType3:"Food",
        reimbursementType4:"Travel",
    }
    console.log(type);


    if(type == 1){
        typeName.reimbursementType1;
    }else if(type==2){
        typeName.reimbursementType2
    }else if(type==3){
        typeName.reimbursementType3;
    }else{
        typeName.reimbursementType4;
    }
    let description = document.getElementById("description").value;
    
    
    //Form Validation
    if(type == ""){
        
        let errorColor = document.getElementsByTagName("select");
        errorColor.classList.add("error-message"); 
    }
    else if(amount == ""){
        let errorColor = document.getElementById("reimbursementAmount");
        errorColor.classList.add("error-message"); 
    }
    else if(description == ""){
        let errorColor = document.getElementById("description");
        errorColor.classList.add("error-message"); 
    }
    else{

    let username = getCookie("name");
    today = new Date();
    let submitted_date = today.getDate() + "/" + today.getMonth() + "/" + today.getFullYear();
    if(username != ""){
        console.log(username + "after cookie retrieveds");
        let details = {

            amount:amount,
            submitted_date:submitted_date,
            description:description,
            reimb_receipt:"receipt"+ Math.floor(Math.random())+ ".jpg",
            user: {
                
                username: username,
                
                role: {
                    id: 1,
                   
                }
            },

            resolver: null,
            status: {
                id: 1,
                // type: "Pending"
            },
            reimbType: {
                id: type,
                // type: typeName
            }
            
        }

        
    //int amount, String submitted_date, String description, String reimb_receipt, User user,
    //User resolver, ReimbursementStatus status, ReimbursementType reimbType
    

    

        let response = await fetch(base_url + "add-reimbursement", {
    
            method: "POST",
            body:JSON.stringify(details),
            credentials: "include",
    
        });
        console.log(response);

        if(response.status == 200){
            
            let successMessage = document.getElementById("message-messages");
            successMessage.innerHTML = '<p class="success-message">Successfully Inserted</p>'
            document.getElementById("reimbursementType").value="";
            document.getElementById("description").value="";
            document.getElementById("reimbursementAmount").value="";
            document.getElementById("receipt").value="";
            // window.location = "./reimbursementForm.html";
        
            
        }else{
            console.log("failed to insert information into database");
        }

    }
    else{
        // let errorSession = document.createElement("p");
        // let message = "Sorry!! Session Ended.. Try Logging In again";
        // error.innerHTML 
        let putError = document.getElementById("message-messages");
        putError.innerHTML = '<p class="session-error">Sorry!! Session Expired, Please Login Again!!</p>';
    }
            
    }

    

    

        

}