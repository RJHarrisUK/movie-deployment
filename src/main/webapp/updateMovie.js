
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

const updateMovieHandler = () => {

let updateId = sessionStorage.getItem("updateTerm"); 

    let movie = {

        title: document.getElementById("title").value,
        ageRating: document.getElementById("rating").value,
        poster: document.getElementById("poster").value

    }

    multi("PUT", "http://localhost:8888/movieapp/api/movie/updateMovie/"+updateId, JSON.stringify(movie)).then(val => {

        console.log(val);
        document.location.href = "movies.html";

    })
}