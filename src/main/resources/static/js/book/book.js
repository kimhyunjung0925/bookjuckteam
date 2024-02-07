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


function addCart() {

    let orderQty = parseInt(orderQtyInput.value) || 0;
    let orderItemId = document.querySelector('#itemId');

    const param = {
        itemId : orderItemId.value,
        itemQty : orderQty.valueOf()
    };


    myFetch.post(`/addCart`, data => {
        console.log(data);
        // clickData(data.result);
        resend();
    },param)

}


// function clickData(data){
//
//     let suckey = document.querySelector('#sucKey'); //이메일 인증 실패,성공 알림 innertext
//     btnElem = document.querySelector('.btnElem');
//     btnElem.addEventListener('click', () => {
//         keyElem = document.querySelector('#keyElem');
//         if(Number(keyElem.value) === data){
//             suckey.innerHTML="이메일 인증 성공하였어요.";
//         } else {
//             console.log(keyElem.value === data)
//             suckey.innerHTML="이메일 인증 실패하였어요. 다시 입력해주세요.";
//         }
//     })
// }







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


