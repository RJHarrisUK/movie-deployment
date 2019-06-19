const userHandler = () => {

    document.location = "users.html";

}

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

multi("GET", "http://35.205.127.158:8888/movieapp/api/movie/getAllMovies").then(val => {


    let data = JSON.parse(val);

    console.log(data);

    const container = document.getElementById('movieTable');
    // console.log(data.Search);

    for (let movie of data) {

        let aRow = document.createElement('tr');

        container.appendChild(aRow);

        let title = document.createElement('td');
        title.innerHTML = movie.title;

        let ageRating = document.createElement('td');
        ageRating.innerHTML = movie.ageRating;

        let detail = document.createElement('td');
        let detailButton = document.createElement('button');

        detailButton.id = movie.id;
        detailButton.innerText = "More Detail";
        detailButton.onclick = detailButtonHandler;

        detail.appendChild(detailButton);

        let updateData = document.createElement('td');
        let updateButton = document.createElement('button');

        updateButton.id = movie.id;
        updateButton.innerText = "Update Movie";
        updateButton.onclick = updateButtonHandler;

        updateData.appendChild(updateButton);

        let deleteData = document.createElement('td');
        let deleteButton = document.createElement('button');

        deleteButton.id = movie.id;
        deleteButton.innerText = "Delete Movie";
        deleteButton.onclick = deleteButtonHandler;

        deleteData.appendChild(deleteButton);


        aRow.appendChild(title);
        aRow.appendChild(ageRating);
        aRow.appendChild(detail);
        aRow.appendChild(updateData);
        aRow.appendChild(deleteData);

    }

})
    .catch(function (error) { console.log(error.message) });


const detailButtonHandler = () => {


    sessionStorage.setItem('searchTerm', event.target.id)

    location.href = 'movie.html';
}


const deleteButtonHandler = () => {

    multi("DELETE", "http://35.205.127.158:8888/movieapp/api/movie/deleteMovie/" + event.target.id).then(val => {


        location.href = 'movies.html';
    }

    )
}


const updateButtonHandler = () => {

    sessionStorage.setItem('updateTerm', event.target.id)

    location.href = 'updateMovie.html';
}