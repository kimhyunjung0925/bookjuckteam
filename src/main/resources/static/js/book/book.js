// input과 가격 정보 DOM 요소를 가져옵니다.
var orderQtyInput = document.querySelector('#orderQty');
var orderPriceInput = document.querySelector('#orderPrice');

var loginState = document.querySelector('#loginState');

// ============================== 로그인 상태 확인 ====================================
function showConfirm() {
    console.log(loginState.value);
    // 로그인상태 확인 후 로그인 안되어있으면 로그인창으로, 로그인 되어있으면 장바구니로
    if (loginState.value == 0) {
        if (confirm("현재 장바구니 기능은 회원만 가능합니다. 로그인창으로 이동하시겠습니까?")) {
            location.href = `/user/login`
        }
    } else {
        clickCartBtn();
    }
//장바구니에 이미 담음 상품이 있어 수량이 추가 되었습니다. 장바구니로 이동하시겠습니까?
}

// ============================== 장바구니 추가 기능 ====================================
function clickCartBtn() {
    let orderQty = parseInt(document.querySelector("#orderQty").value) || 0;
    let orderItemId = document.querySelector('#itemId').value;

    const data = {
        itemId: orderItemId,
        itemQty: orderQty,
    };

    fetch("/cart/addCart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then((data) => {
            console.log(data.state);
            if (data.state === "insert" ?
                confirm("상품이 장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?")
                : confirm("장바구니에 이미 담은 상품이 있어 수량이 추가 되었습니다. 장바구니로 이동하시겠습니까?")) {
                //한번더 처리 제대로 되었는지 확인.
                // if (data.redirectUrl) {
                window.location.href = `/cart/cart`; // 페이지 리디렉션
                // }
            //취소 눌렀을 땐 장바구니에 담고 페이지 그대로 있게
            } else {
                // 리다이렉션 URL이 없다면 실패, 메시지 consoel 출력함 (또는 사용자에게 메시지 표시)
                console.log(data.message);

            }
        })
        .catch(error => console.error("Error:", error));

    //
};

// ============================== 수량 +,- 버튼 기능 ====================================
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

