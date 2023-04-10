let formModificar = document.querySelector('#form-modificar');
formModificar.addEventListener('submit', function(event) {
    event.preventDefault();
    const url = window.location.href;
    const id = url.substring(url.lastIndexOf('/') + 1);

    const paciente = {
        id:document.querySelector('#paciente-id').value,
    }
    const odontologo = {
        id:document.querySelector('#odontologo-id').value,
    }

    const turno = {
        id: parseInt(id),
        paciente: paciente,
        odontologo: odontologo,
        fecha: document.querySelector('#fecha').value,
        hora: document.querySelector('#hora').value
    };

    const setting = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(turno)
    }

    fetch('/turnos', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})