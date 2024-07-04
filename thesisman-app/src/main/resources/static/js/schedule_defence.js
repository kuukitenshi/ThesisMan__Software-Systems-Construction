const typeField = document.getElementById("ftype");
const classRoomField = document.getElementById("fclassroom");
const classRoomLabel = document.querySelector('label[for="fclassroom"]')

typeField.addEventListener('change', (event) => {
    if (typeField.value === 'REMOTE') {
        classRoomField.setAttribute('disabled', 'true');
        classRoomField.setAttribute('hidden', 'hidden');
        classRoomLabel.setAttribute('hidden', 'hidden');
    } else {
        classRoomField.removeAttribute('disabled');
        classRoomField.removeAttribute('hidden');
        classRoomLabel.removeAttribute('hidden');
    }
});
