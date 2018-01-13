var USERS  = document.getElementById("users");

fetch("/user_api", {credentials: "include", method: "get"})
    .then(response => response.json())
    .then(data => {
        data.forEach(element => {
            let rowContent = "<td>" + element["username"] + "</td>" +
            "<td>" + element["email"] + "</td>" +
            "<td>" + element["roles"].map(roleObject => roleObject["authority"]).join(", ") + "</td>";
            let tr = document.createElement("tr");
            tr.innerHTML = rowContent;
            let delBtn = document.createElement("button");
            delBtn.innerText = "Delete";
            delBtn.addEventListener("click", function(){
                fetch("/user_api/" + element["id"], {credentials: "include", method: "delete"})
                    .then(alert("User" + element["username"] + "deleted success!"))
                    .catch(alert("Delete fail!"));
            });
            let td = document.createElement("td");
            td.appendChild(delBtn);
            tr.appendChild(td);
            USERS.appendChild(tr);
        });
    })
    .catch(console.log("error"));