let formModificar= document.querySelector('#form-modificar');
formModificar.addEventListener('submit', function(event) {
    event.preventDefault();
    const url = window.location.href;
    const id = url.substring(url.lastIndexOf('/') + 1);

    const domicilio = {
        provincia: document.querySelector('#provincia').value,
        localidad: document.querySelector('#localidad').value,
        calle: document.querySelector('#calle').value,
        numeroCalle: parseInt(document.querySelector('#numero-calle').value)
    };

    let paciente = {
        id:id,
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        dni: document.querySelector('#dni').value,
        email: document.querySelector('#email').value,
        domicilio: domicilio
    };


    const setting = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(paciente)
    }

    fetch('/pacientes', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})