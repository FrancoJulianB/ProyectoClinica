function deletePaciente(id) {
    fetch("/pacientes/" + id, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            location.reload();
        }
    });
}