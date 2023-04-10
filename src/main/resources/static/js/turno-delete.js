function deleteTurno(id) {
    fetch("/turnos/" + id, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            location.reload();
        }
    });
}