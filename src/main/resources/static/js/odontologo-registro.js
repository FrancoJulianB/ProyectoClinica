let formRegistro = document.querySelector('#form-registro');
console.log("estoy aca")
formRegistro.addEventListener('submit', function(event) {
    event.preventDefault();

    let odontologo = {
        matricula: document.querySelector('#matricula').value,
        nombre: document.querySelector('#first-name').value,
        apellido: document.querySelector('#last-name').value
    };


    const setting = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(odontologo)
    }

    fetch('/odontologos', setting)
        .then(response => response.json())
        .then(dataResponse => {
            console.log(dataResponse.status)
        })
        .catch(error => console.log(error))
})
