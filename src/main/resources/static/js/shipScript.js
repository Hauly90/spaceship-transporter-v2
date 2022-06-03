
function createShipAlert() {
    let result = confirm('Are you sure?');
    result === true ? alert('The ship has been created! 🚀') : event.preventDefault();
}

//function updateShipAlert() {
//    let result = confirm('Are you sure?');
//    result === true ? alert('The ship has been created! 🚀') : event.preventDefault();
//}
