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
    window.location = "/login.html";
}

function checkCookie() {
    let user = getCookie("username");
    if (user != "") {
      alert("Welcome again " + user);
    } else {
      //destroycookie and login
      deleteCookie();
      window.location = "/login.html";
    }
}



if(getCookie("name") != ""){

    
    

    let nameCookies = getCookie("name");
    document.getElementById("navbarDropdown").innerHTML=nameCookies;

    document.getElementById("submit-status-check").addEventListener("onload", getStatus);
    
    

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
            if(data !="" ){
            for(let i=0; i<data.length; i++){
                count++;
                let row = document.createElement("tr");

                let cell1 = document.createElement("td");
                cell1.innerHTML = count;
                row.appendChild(cell1);

                let cell2 = document.createElement("td");
                cell2.innerHTML = data[i].user.firstName +" " + data[i].user.lastName;
                row.appendChild(cell2);

                let cell3 = document.createElement("td");
                cell3.innerHTML = data[i].user.username;
                row.appendChild(cell3);

                let cell4 = document.createElement("td");
                cell4.innerHTML = data[i].user.email;
                row.appendChild(cell4);

                let cell5 = document.createElement("td");
                cell5.innerHTML = data[i].reimbType.type;
                row.appendChild(cell5);

                let cell6 = document.createElement("td");
                cell6.innerHTML = data[i].reimb_receipt;
                row.appendChild(cell6);

                let cell7 = document.createElement("td");
                cell7.innerHTML = data[i].amount;
                row.appendChild(cell7);

                let cell8 = document.createElement("td");
                cell8.innerHTML = data[i].submitted_date;
                row.appendChild(cell8);

                let cell9 = document.createElement("td");
                cell9.innerHTML = data[i].status.type;
                row.appendChild(cell9);

                let cellx = document.createElement("td");
                if(data[i].resolver){
                    cellx.innerHTML = data[i].resolver.firstName +" "+ data[i].resolver.lastName;
                    row.appendChild(cellx);
                }else{
                    cellx.innerHTML = "Not Resolved";
                    row.appendChild(cellx);
                }
                
                let cellxi = document.createElement("td");
                cellxi.innerHTML = '<a href="#" id="edit" >Edit</a><a href="#" value="'+data[i].id+'" id="delete">Delete</a>';
                row.appendChild(cellxi);

                console.log(data[i].user.username);
                
                document.getElementById("table-status-data").appendChild(row);
                
                document.getElementById("submit-status-check").disabled = true;

            }
                
            }
            else{
                console.log("Hi No data");
                document.getElementById("table-status-data").innerHTML = '<tr>No Data Available</tr>'
            }
          
            

        }else{
            console.log(response.status);
            console.log("Error Please Check your code");
        }
    }

}else {
    deleteCookie();
    window.location = "/login.html";
}

