const openModalButtons = document.querySelectorAll('[data-modal-target]')
const closeModalButtons = document.querySelectorAll('[data-close-button]')
const overlay = document.getElementById('overlay')

openModalButtons.forEach(button => {
  button.addEventListener('click', () => {
    const modal = document.querySelector(button.dataset.modalTarget)
    openModal(modal)
  })
})

overlay.addEventListener('click', () => {
  const modals = document.querySelectorAll('.modal.active')
  modals.forEach(modal => {
    closeModal(modal)
  })
})

closeModalButtons.forEach(button => {
  button.addEventListener('click', () => {
    const modal = button.closest('.xmodal')
    closeModal(modal)
  })
})

function openModal(modal) {
  if (modal == null) return
  modal.classList.add('active')
  overlay.classList.add('active')
}

function closeModal(modal) {
  if (modal == null) return
  modal.classList.remove('active')
  overlay.classList.remove('active')
}

function showValue(shipName) {
  alert(shipName);
}

$(document).ready(function(){
    $(".updateShip").on('click', function(){
        var currentRow = $(this).closest("tr");
        var col1 = currentRow.find("td:eq(0)").html();
        var col2 = currentRow.find("td:eq(1)").html();
        var col3 = currentRow.find("td:eq(2)").html();
        var col4 = currentRow.find("td:eq(3)").html();
//        $('#div1').before('<div style="background-color:yellow"> New div </div>')
//        var setValue= document.getElementById("shipName").value = shipName;
        console.log(col4);
        var setValue = document.getElementById("shipName").value = col2
        var setValue2 = document.getElementById("shipMaximumWarp").value = col3;
        var setValue3 = document.getElementById("planetType").innerText = col4;
    })
})
