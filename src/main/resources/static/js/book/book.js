var orderQtyInput = document.querySelector('#orderQty');
var orderPriceInput = document.querySelector('#orderPrice');
var currentQuantity = parseInt(orderQtyInput.value); // Use orderQtyInput
var currentPrice = parseInt(orderPriceInput.value); // Use orderPriceInput
orderQtyInput.addEventListener('input', function () {
    let inputValue = orderQtyInput.value; // Use orderQtyInput
    if (inputValue <= 0) {
        inputValue = 1;
       orderQtyInput.value = '1';
    }

    let totalPrice = inputValue * currentPrice;
    document.querySelector('#totalPrice').textContent = `${totalPrice.toLocaleString()}원`;
});

function changeQuantity(change) {
    let newQuantity = currentQuantity + change;

    if (newQuantity >= 1) { // 수량은 최소 1이어야 합니다.
        orderQtyInput.value = newQuantity; // Use orderQtyInput
        let totalPrice = newQuantity * currentPrice;
        document.querySelector('#totalPrice').textContent = `${totalPrice.toLocaleString()}원`;
        currentQuantity = newQuantity; // Update currentQuantity
    }
}
// 초기 총 가격 설정
// document.querySelector('#totalPrice').textContent = `총가격: ${totalPrice.toLocaleString()}원`;

