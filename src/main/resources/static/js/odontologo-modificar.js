let formModificar = document.querySelector('#form-modificar');
formModificar.addEventListener('submit', function(event) {
    event.preventDefault();
    const url = window.location.href;
    const id = url.substring(url.lastIndexOf('/') + 1);

    const data = {
        id: parseInt(id),
        apellido: document.querySelector('#last-name').value,
        nombre: document.querySelector('#first-name').value,
        matricula: document.querySelector('#matricula').value,
    };
    console.log(data)

    const setting = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    }

    fetch('/odontologos', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})