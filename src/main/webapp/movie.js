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

const movieToSearch = sessionStorage.getItem("searchTerm");

multi("GET", "http://34.65.182.174:8888/movieapp/api/movie/getAMovie/" + movieToSearch).then(val => {

    let data = JSON.parse(val);
 
    console.log(data);

    document.getElementById('Title').innerText = data.title;
    document.getElementById('AgeRating').innerText = data.ageRating;
    document.getElementById('Image').src = data.poster;

})
    .catch(function (error) { console.log(error.message) });

