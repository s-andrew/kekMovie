var MOVIE_API = "/movie_api";
var RESULT_ROW_TEMPLATE = "";


var searchBtn = document.getElementById("searchBtn");
var searchField = document.getElementById("searchField");
var resultDiv = document.getElementById("result");
var infoP = document.getElementById("info")

var DATA;

function addResultRow(parentElement, cathegory, name, id){
    let li = document.createElement("li");
    let button = document.createElement("button");
    // button.href = MOVIE_API + "/" + cathegory + "/" + id;
    button.setAttribute("data-id", cathegory + "/" + id);
    button.innerText = name;
    button.addEventListener("click", function(){
        resultDiv.innerHTML = "";
        fetch("/movie_api/" + cathegory + "/" + id, {credentials: "include", method: "get"})
            .then(response => response.json())
            .then(function(data){
                DATA = data;
                if (cathegory == "movies_by_character" || cathegory == "movies")
                    movieResultProcess(data);
                if (cathegory == "movies_by_genry" || cathegory == "movies_by_keyword" || cathegory == "movies_by_production_company"){
                    let h2 = document.createElement("h2");
                    h2.innerText = cathegory;
                    resultDiv.appendChild(h2);
                    let ul = document.createElement("ul");
                    data.forEach(element => {
                        addResultRow(ul, "movies", element["value"], element["key"]);
                    });
                    resultDiv.appendChild(ul);
                }
                if (cathegory == "movies_by_person")
                    globalSearchResultProcess(data);                
            });
    });
    li.appendChild(button);
    parentElement.appendChild(li);
}

searchBtn.addEventListener("click", function(){
    resultDiv.innerHTML = "";
    info.style.display = "none";
    fetch("/movie_api/global_search?f="+searchField.value, {credentials: "include", method: "get"})
        .then(response => response.json())
        .then(data => globalSearchResultProcess(data))
        .catch(function(url){
            console.log("Error:")
            console.log(url);
        });
});


function globalSearchResultProcess(data){
    DATA = data;
    for(var categoryKey in data){
        let h2 = document.createElement("h2");
        h2.innerText = categoryKey.replace("movies_by_", "");
        resultDiv.appendChild(h2);
        let ul = document.createElement("ul");
        data[categoryKey].forEach(element => addResultRow(ul, categoryKey, element["value"], element["key"]));
        resultDiv.appendChild(ul);
    }
}

function movieResultProcess(data){
    DATA = data;
    let h2 = document.createElement("h2");
    h2.innerText = data["title"];
    let p = document.createElement("p");
    p.innerText = JSON.stringify(data, null, 4);
    resultDiv.appendChild(h2);
    resultDiv.appendChild(p);
}

