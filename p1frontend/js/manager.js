const url = "http://localhost:8090/";

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
    window.location = "../managerLogin.html";
}

function checkCookie() {
    let user = getCookie("username");
    if (user != "") {
      alert("Welcome again " + user);
    } else {
      //destroycookie and login
      deleteCookie();
      window.location = "../managerLogin.html";
    }
}


// logout
document.getElementById("logout").addEventListener("click", deleteCookie);
if(getCookie("name") != ""){

    let nameCookies = getCookie("name");
    document.getElementById("navbarDropdown").innerHTML=nameCookies;

    document.getElementById("submitManagerBtn").addEventListener("click", getStatus);
    
    //Main Asynchornous Fetch for gathering information from server 
    async function getStatus(){
        let status = document.getElementById("statusCheck").value;

        let response = await fetch(url + "manager/status/" + status, {credentials:"include"} );

        console.log(response);
        if(response.status==200){
            let data = await response.json();

            let count = 0;
            let username;
                let fullname;
                let resolvername;
                let uEmail;
                let type;
                let cStatus;
                let arr = "<tr>";
            if(data !="" ){
                //Loop The table Information
                for(let i=0; i<data.length; i++){
                    count++;
                    if(data[i].user){
                        fullname = data[i].user.firstName + " " + data[i].user.lastName ;
                        username = data[i].user.username;
                        uEmail = data[i].user.email;
                    }else{
                        fullname = "N/A";
                        uEmail = "N/A";
                    }

                    if(data[i].resolver){
                        resolvername = data[i].resolver.firstName +" "+ data[i].resolver.lastName;
                    }else{
                        resolvername = "N/A"
                    }
                    //    let tbtn = ""
                       arr += "<td>"+ count +"</td>"+
                        "<td>"+ fullname +"</td>"+
                        "<td>"+ username +"</td>"+
                        "<td>"+ uEmail +"</td>"+
                        "<td>"+ data[i].reimbType.type +"</td>"+
                        "<td>"+ data[i].reimb_receipt +"</td>"+
                        "<td>"+ data[i].amount +"</td>"+
                        "<td>"+ data[i].submitted_date +"</td>"+
                        "<td>"+ data[i].status.type +"</td>"+
                        "<td>"+ resolvername +"</td>"+
                        "<td>";
                        if(data[i].status.type=="Pending"){
                            arr += '<button class="btn btn-sm btn-primary" value="'+ data[i].id +'" id="reject'+count+'">Approve</button>'+
                            '<button class="btn btn-sm btn-danger" value="'+ data[i].id +'" id="approve'+count+'">Reject</button>'+
    
                            '<button class="btn btn-sm btn-danger" hidden >Confirm</button>'+
                            '<button class="btn btn-sm btn-danger" hidden>Cancel</button>';
                            
                        }
                        
                        
                        arr += "</td>"+
                        "</tr><tr>";
                        
                        // '<button class="btn btn-sm btn-primary" value="'+ data[i].id +'" id="reject'+count+'">Approve</button>'+
                        // '<button class="btn btn-sm btn-danger">Reject</button>'+

                        // '<button class="btn btn-sm btn-danger" hidden >Confirm</button>'+
                        // '<button class="btn btn-sm btn-danger" hidden>Cancel</button>'+
                        

                }
                
                
                
                
            }
            else{
                arr += "<tr><td>No Data Available<td></tr>"
                document.getElementById("table-status-data").innerHTML = arr;
            }


            arr += "</tr>"
            if(arr.length>0){
                
                document.getElementById("table-status-data").innerHTML= arr
                
            }else{ 
                arr += "<tr><td>No Data Available<td></tr>"
                document.getElementById("table-status-data").innerHTML = arr;
            }
            let tableData = document.getElementById('table-design');
            for(let x = 1; x < tableData.rows.length; x++){
                document.getElementById('approve'+x).addEventListener("click", async function(){
                    rID = document.getElementById('approve'+x).attributes['value'].value;
                    
                    let denialStatus = {
                        id:rID,
                        
                        user: null,
            
                        resolver: {
                            
                            username: getCookie("name"),
                            
                            role: null
                        },
                        status: {
                            id: 3,
                            // type: "Denied"
                        },
                        reimbType: null
                    }
                    let response = await fetch(url + "manager/update-reimbursement", {

                        method: "POST",
                        body:JSON.stringify(denialStatus),
                        headers : { 
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                           },
                        credentials: "include",
                    });
                    
                    if(response.status==200){
                        // approve is for denying the status and vice versa
                        document.getElementById('approve'+x).parentElement.parentElement.remove();

                    }

                })


                document.getElementById('reject'+x).addEventListener("click", async function(){
                    reimbId = document.getElementById('reject'+x).attributes['value'].value;
                    
                    let details = {
                        id:reimbId,
                        
                        user: null,
            
                        resolver: {
                            
                            username: getCookie("name"),
                            
                            role: null
                        },
                        status: {
                            id: 2,
                            // type: "Approved"
                        },
                        reimbType: null
                    }
                    let response = await fetch(url + "manager/update-reimbursement", {

                        method: "POST",
                        body:JSON.stringify(details),
                        headers : { 
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                           },
                        credentials: "include",
                    });
                    
                    if(response.status==200){

                        document.getElementById('reject'+x).parentElement.parentElement.remove();

                    }






                })
                
            }
            
            
           

            

        }else{
            console.log("Error Please Check your code");
        }

        
        
        
        
    }



}else {
    deleteCookie();
    window.location = "../managerLogin.html";
}

