   
    const multi = (method, url, body) => {
    
        return new Promise(
    
            function (res, rej) {
    
                const req = new XMLHttpRequest();
    
                req.onload = () => {
    
                    if (req.status === 200) {
                        res(req.response);
                    } else {
                        const reason = new Error('Rejected');
                        rej(reason);
                    }
    
                }
    
                req.open(method, url)
    
                req.send(body);
    
            }
        );
    
    }
    
        // const container = document.getElementById('movieTable');
    
        // if (container.rows.length > 1) {
    
        //     let tableSize = container.rows.length;
        //     for (i = tableSize; i > 1; i--) {
        //         container.deleteRow(i - 1);
        //     }
    
        // }
    
        multi("GET", "http://34.65.182.174:8888/movieapp/api/user/getAllUsers").then(val => {
    
            let data = JSON.parse(val);
    
            console.log(data);
    
            const container = document.getElementById('userTable');
    
            for (let user of data) {
    
                let aRow = document.createElement('tr');
    
                container.appendChild(aRow);
    
                let name = document.createElement('td');
                name.innerHTML = user.name;
    
                let detail = document.createElement('td');
                let detailButton = document.createElement('button');
    
                detailButton.id = user.id;
                detailButton.innerText = "More Detail";
                detailButton.onclick = detailButtonHandler;
    
                detail.innerHTML = detailButton;
    
                aRow.appendChild(name);
                aRow.appendChild(detailButton);
    
            }
    
        })
            .catch(function (error) { console.log(error.message) });
    
    
    const detailButtonHandler = () => {

        sessionStorage.setItem('userToSearch', event.target.id)
    
        location.href = 'user.html';
    }