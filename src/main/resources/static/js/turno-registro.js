let formModificar = document.querySelector('#form-registro');
formModificar.addEventListener('submit', function(event) {
    event.preventDefault();

    const paciente = {
        id:document.querySelector('#paciente-id').value,
    }
    const odontologo = {
        id:document.querySelector('#odontologo-id').value,
    }

    const turno = {
        paciente: paciente,
        odontologo: odontologo,
        fecha: document.querySelector('#fecha').value,
        hora: document.querySelector('#hora').value
    };
    console.log(turno)

    const setting = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(turno)
    }

    fetch('/turnos', setting)
        .then(response => response.json())
        .then(dataResponse => {
        })
        .catch(error => console.log(error))
})