let OrderQty = document.querySelector('#orderQty');
// let OrderPlus = document.querySelector('#OrderPlus');
// let OrderMinus = document.querySelector('#OrderMinus');
let orderPrice = document.querySelector('#orderPrice');

function add() {
    OrderQty.value++;// hm 값을 1 증가
    let sum_ = parseInt(OrderQty.value) * orderPrice.value;// hm 문자값을 정수로 변환 * sell_price	해서 금액 계산
    document.getElementById("my_sum").innerHTML = sum_.toLocaleString('ko-KR');// id가 my_sum인 요소를 찾아 값을 위에서 계산한 sum_ 값으로 설정, 각 나라 표시방식에 맞게 설정해줌
}

function del() {

    if (OrderQty.value > 1) {// hm(amounts에서 가져온 값)가 1보다 크면
        OrderQty.value--;// hm 값 1 감소
        var sum_ = parseInt(OrderQty.value) * orderPrice.value;
        document.getElementById("my_sum").innerHTML = sum_.toLocaleString('ko-KR');
    }
}