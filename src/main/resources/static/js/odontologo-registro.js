let formRegistro = document.querySelector('#form-registro');
formRegistro.addEventListener('submit', function(event) {
    event.preventDefault();

    let odontologo = {
        matricula: document.querySelector('#matricula').value,
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value
    };


    const setting = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(odontologo)
    }

    fetch('/odontologos', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})
