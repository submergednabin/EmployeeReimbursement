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
    window.location = "./login.html";
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



if(getCookie("name") != ""){

    
    

    let nameCookies = getCookie("name");
    document.getElementById("navbarDropdown").innerHTML=nameCookies;

    document.getElementById("submit-status-check").addEventListener("click", getStatus);
    
    // logout
    document.getElementById("logout").addEventListener("click", deleteCookie);

    async function getStatus(){
        let status = document.getElementById("reimbursementStatus").value;
        if(status == 0){
            
            lastUrl = nameCookies;
        }else if(status>0 && status<4){
            lastUrl = nameCookies + "/" + status;
        }else{
            console.log("please put a correct status")
        }

        let response = await fetch(url + "status" + "/" + lastUrl, {credentials:"include"} );

        console.log(response);

        if(response.status==200){
            let data = await response.json();
            console.log(data);
            let count = 0;
            let username;
            let fullname;
            let resolvername;
            let uEmail;
            let type;
            let cStatus;
            let amount;
            let arr = "<tr>";
            if(data !="" ){
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
                   
                    "<td>"+ data[i].reimbType.type +"</td>"+
                    "<td>"+ data[i].description +"</td>"+
                    // "<td>"+ data[i].reimb_receipt +"</td>"+
                    "<td>"+ data[i].amount +"</td>"+
                    "<td>"+ data[i].submitted_date +"</td>"+
                    "<td>"+ data[i].status.type +"</td>"+
                    "<td>"+ resolvername +"</td>"+
                    "<td>";
                    // if(data[i].status.type=="Pending"){
                        arr +=  '<button class="btn btn-sm btn-danger"  value="'+ data[i].id +'" id="view-details'+i+'">View</button>';
                        
                    // }
                    
                    
                    arr += "</td>"+
                    "</tr><tr>";
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
            var l = 0;
            let modalWrap = null;
            for(let x = 0; x < data.length ; x++){
                console.log(data[x]);
                l++;
                if(data[x].user){
                    fullname = data[x].user.firstName + " " + data[x].user.lastName ;
                    username = data[x].user.username;
                    uEmail = data[x].user.email;
                }else{
                    fullname = "N/A";
                    uEmail = "N/A";
                }
                amount = data[x].amount

                if(data[x].resolver){
                    resolvername = data[x].resolver.flrstName +" "+ data[x].resolver.lastName;
                }else{
                    resolvername = "N/A"
                }
                document.getElementById('view-details'+x).addEventListener("click", async function(){
                    
                    rID = document.getElementById('view-details'+x).attributes['value'].value;
                    if(modalWrap != null){
                        modalWrap.remove();
                    }

                    let response = await fetch(url +"user-detail/"+username +"/"+rID, {credentials:"include"} );
                    
                    if(response.status==200){
                        let info = await response.json();
                        console.log("xxxx");
                        console.log(info.id);
                        
                        modalWrap = document.createElement("div");
                    modalWrap.innerHTML = `<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">Information Sheet</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">`+
                        `<div class="detail-section">
                            <table class="table">
                                    <tr>
                                        <th>FullName</th>
                                        <td>`+ info.user.firstName +` `+ info.user.lastName +`</td>
                                        <tr>
                                        <tr>
                                        <th>Amount</th>
                                        <td>`+ info.amount +`</td>
                                        <tr>
                                        <tr>
                                        <th>Description</th>
                                        <td>`+ info.description +`</td>
                                        
                                        <tr>
                                        <tr>
                                        <th>Reimbursement Type</th>
                                        <td>`+ info.reimbType.type +`</td>
                                        
                                        <tr>
                                        <tr>
                                        <th>Reimbursement Status</th>
                                        <td>`+ info.status.id +`</td>
                                    <tr>

                            </table>
                        </div>`
                        +`</div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                          
                        </div>
                      </div>
                    </div>
                  </div>`;

                  document.body.append(modalWrap);
                //   alert(rID);
                  
                  var myModal = new bootstrap.Modal(document.getElementById("exampleModal"));
                
                    myModal.show();
                    }
                    
                //   var modal = new bootstrap.Modal(modalWrap.querySelector('modal')); //bootstrap provdide
                  
                })


              
                
            }
            

        }else{
            console.log(response.status);
            
            console.log("Error Please Check your code");
        }
    }

}else {
    deleteCookie();
    window.location = "./login.html";
}

