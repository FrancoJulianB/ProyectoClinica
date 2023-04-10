let formModificar = document.querySelector('#form-modificar');
formModificar.addEventListener('submit', function(event) {
    event.preventDefault();
    const url = window.location.href;
    const id = url.substring(url.lastIndexOf('/') + 1);

    const odontologo = {
        id: parseInt(id),
        apellido: document.querySelector('#apellido').value,
        nombre: document.querySelector('#nombre').value,
        matricula: document.querySelector('#matricula').value,
    };

    const setting = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(odontologo)
    }

    fetch('/odontologos', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})