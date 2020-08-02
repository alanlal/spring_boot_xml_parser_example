(function(){

    const API_URL = "http://localhost:8080/api/v1/"

    const searchSubmit = document.querySelector("#search");
    searchSubmit.addEventListener("click",function(event){
        const searchByKey = document.querySelector("#search_by").value;
        const searchByValue = document.querySelector("#search_value").value;

        const queryUrl = `${API_URL}/${searchByKey}?search=${searchByValue}`

        fetch(queryUrl)
            .then(data=>{
                if(data.ok){
                    return data.json()
                } else {
                    throw Error(data.headers)
                }
            })
            .then(renderResults)
            .catch(renderError)
    })

    function renderError(data){
        console.log(data)
    }

    function renderResults(jsonData){
        const resultsContainer = document.querySelector("#result_container")

        if(resultsContainer.children.length!=0){
            resultsContainer.innerHTML = ""
        }

        if(jsonData.length==0){
            let rowDetails = `
                <p class="no_data_found">No Data Found</p>
            `

            resultsContainer.innerHTML = rowDetails;
            return
        }

        jsonData.forEach(rowData=>{
            const {name,address,phoneNumber,pension,salary} = rowData

            let rowDetails = `
                    <p class="user_name">${name}</p>
                    <p class="address">${address}</p>
                    <p class="phoneNumber">${phoneNumber}</p>
                    <p class="pension_amount">${pension}</p>
                    <p class="salary_amount">${salary}</p>
            `

            let row = document.createElement("div")
            row.classList.add("row")
            row.innerHTML = rowDetails

            resultsContainer.appendChild(row)
        })
    }

})()