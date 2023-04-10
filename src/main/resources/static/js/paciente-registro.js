let formRegistro = document.querySelector('#form-registro');
formRegistro.addEventListener('submit', function(event) {
    event.preventDefault();

    const domicilio = {
        provincia: document.querySelector('#provincia').value,
        localidad: document.querySelector('#localidad').value,
        calle: document.querySelector('#calle').value,
        numeroCalle: document.querySelector('#numero-calle').value
    };

    let paciente = {
        nombre: document.querySelector('#first-name').value,
        apellido: document.querySelector('#last-name').value,
        dni: document.querySelector('#dni').value,
        email: document.querySelector('#email').value,
        domicilio: domicilio
    };


    const setting = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(paciente)
    }

    fetch('/pacientes', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})
