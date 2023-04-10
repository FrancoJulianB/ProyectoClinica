function deleteOdontologo(id) {
    fetch("/odontologos/" + id, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            location.reload();
        }
    });
}