// input과 가격 정보 DOM 요소를 가져옵니다.
var orderQtyInput = document.querySelector('#orderQty');
var orderPriceInput = document.querySelector('#orderPrice');

function getCurrentPrice() {
    return parseInt(orderPriceInput.value); // 가격 정보를 실시간으로 가져옵니다.
}

function updateTotalPrice(quantity) {
    let totalPrice = quantity * getCurrentPrice();
    document.querySelector('#totalPrice').textContent = `${totalPrice.toLocaleString()}원`;
}

orderQtyInput.addEventListener('input', function () {
    let inputValue = parseInt(orderQtyInput.value) || 1;

    if (inputValue <= 0) {
        inputValue = 1;
        orderQtyInput.value = '1';
    }

    updateTotalPrice(inputValue);
});

function changeQuantity(change) {
    let currentQuantity = parseInt(orderQtyInput.value) || 0;
    let newQuantity = currentQuantity + change;

    if (newQuantity >= 1) {
        orderQtyInput.value = newQuantity;
        updateTotalPrice(newQuantity);
    }
}

// 초기 총 가격 설정
updateTotalPrice(parseInt(orderQtyInput.value));

document.querySelector("#addCartBtn").addEventListener("click", function() {

    let orderQty = parseInt(document.querySelector("#orderQty").value) || 0;
    let orderItemId = document.querySelector('#itemId').value;

    const data = {
        itemId: orderItemId,
        itemQty: orderQty
    };

    // fetch("/cart/addCart", {
    //     method: "POST",
    //     headers: {
    //         "Content-Type": "application/json",
    //         "Accept": "application/json"
    //     },
    //     body: JSON.stringify(data)
    // })
    //     .then(response => {
    //         if (!response.ok) {
    //             throw new Error('Network response was not ok');
    //         }
    //         return response.json();
    //
    //     })
    //     .then(result => {
    //         if(result.success) {
    //             alert("카트 담기 성공");
    //         } else {
    //             alert("카트 담기 실패: " + result.message);
    //         }
    //
    //     })
    //     .catch(error => {
    //         console.error('Error:', error);
    //
    //         alert("카트 담기 실패");
    //     });
    fetch("/cart/addCart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            if(data.redirectUrl) {
                window.location.href = data.redirectUrl; // 페이지 리디렉션
            }
        })
        .catch(error => console.error('Error:', error));

});







// var orderQtyInput = document.querySelector('#orderQty');
//
// var orderPriceInput = document.querySelector('#orderPrice');
// var currentPrice = parseInt(orderPriceInput.value); // Use orderPriceInput
// orderQtyInput.addEventListener('input', function () {
//     let inputValue = parseInt(orderQtyInput.value); // Use orderQtyInput
//
//     if (inputValue <= 0) {
//         inputValue = 1;
//         orderQtyInput.value = '1';
//     }
//
//     let totalPrice = inputValue * currentPrice;
//     document.querySelector('#totalPrice').textContent = `${totalPrice.toLocaleString()}원`;
//
// });
//
// function changeQuantity(change) {

//     let orderQtyInput = document.querySelector('#orderQty');
//     let currentQuantity = parseInt(orderQtyInput.value);
//
//     let newQuantity = currentQuantity + change;
//
//     if (newQuantity >= 1) { // 수량은 최소 1이어야 합니다.
//         orderQtyInput.value = newQuantity; // Use orderQtyInput
//         let totalPrice = newQuantity * currentPrice;
//         document.querySelector('#totalPrice').textContent = `${totalPrice.toLocaleString()}원`;
//         currentQuantity = newQuantity; // Update currentQuantity
//     }
// }
// // 초기 총 가격 설정
// // document.querySelector('#totalPrice').textContent = `총가격: ${totalPrice.toLocaleString()}원`;
//


