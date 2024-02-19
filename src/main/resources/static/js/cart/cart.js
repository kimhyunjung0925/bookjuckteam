var itemCheck = document.querySelectorAll('.itemCheck');
var itemPrice = document.querySelector('#itemPrice');
var itemQty = document.querySelector('#itemQty');


var totalPrice = document.querySelector('#totalPrice');
var deliveryFee = document.querySelector('#deliveryFee');
var payPrice = document.querySelector('#payPrice');
var payTotalQty = document.querySelector('#payTotalQty');


var orderQtyInput = document.querySelector('#orderQty');
var oriQty = document.querySelector('#oriQty');

// 페이지 로드 시 함수 실행
window.onload = function() {
    // 'checkAll' 체크박스의 현재 상태를 가져옵니다.
    var checkAllBox = document.getElementById('checkAll');
    // 'toggleCheckboxes' 함수를 호출하여 모든 체크박스의 상태를 설정합니다.
    toggleCheckboxes(checkAllBox.checked);

    // 'checkAll' 체크박스에 클릭시 이벤트
    checkAllBox.addEventListener('click', function() {
        // 체크박스 상태 변경 시 'toggleCheckboxes' 함수를 호출합니다.
        toggleCheckboxes(this.checked);
    });

    // 개별 'itemCheck' 체크박스에 대한 이벤트
    setupItemCheckListeners();

    // 체크박스를 클릭할 때마다 합계를 다시 계산합니다.
    itemCheck.forEach(function(checkbox) {
        checkbox.addEventListener('click', calculateAndDisplayTotal);
    });

    // 'checkAll' 체크박스에 클릭 이벤트가 발생할 때 합계를 다시 계산합니다.
    document.getElementById('checkAll').addEventListener('click', calculateAndDisplayTotal);

    // 초기 합계 계산
    calculateAndDisplayTotal();
};

// 모든 'itemCheck' 체크박스에 대한 이벤트 리스너 설정
function setupItemCheckListeners() {
    // var itemChecks = document.querySelectorAll('.itemCheck');
    itemCheck.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            // 'itemCheck' 중 하나라도 체크가 해제되면 'checkAll' 체크박스도 해제
            var allChecked = Array.from(itemCheck).every(c => c.checked);
            document.getElementById('checkAll').checked = allChecked;
        });
    });
}

// 전체선택 함수
function toggleCheckboxes(checked) {
    // 'itemCheck' 클래스를 가진 모든 체크박스를 찾습니다.

    // 각 체크박스의 체크 상태를 인수로 받은 상태에 맞춥니다.
    itemCheck.forEach(function(checkbox) {
        checkbox.checked = checked;
    });
}

// 체크박스 상태에 따라 합계를 계산하고 결과를 표시하는 함수
function calculateAndDisplayTotal() {
    let total = 0; // 총액을 저장할 변수
    let chkCnt = 0
    // 체크된 각 'itemCheck' 체크박스에 대해 반복
    itemCheck.forEach(function(checkbox) {


        if (checkbox.checked) {
            // getAttribute 사용
            let qty = parseInt(checkbox.getAttribute('data-qty'), 10);
            let price = parseInt(checkbox.getAttribute('data-price'), 10);

            total += price * qty;
            chkCnt += 1;
        }
    });
    //선택 상품 금액
    const commaTotal = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    totalPrice.textContent = commaTotal + "원"// 소수점 두 자리까지

    if(total === 0) {
        //배송비
        deliveryFee.innerHTML = "0원"
        // 결제 예정 금액
        payPrice.innerHTML ="0원"
        // 주문하기 버튼
    }else if(total > 30000) {
        //배송비
        deliveryFee.innerHTML = "무료배송"
        // 결제 예정 금액
        payPrice.innerHTML = commaTotal + "원"
        // 주문하기 버튼

    } else{
        //배송비
        deliveryFee.innerHTML = "+2,500원"
        // 결제 예정 금액
        const TotalPay = total + 2500
        const commaPay = TotalPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
        payPrice.innerHTML = commaPay + "원"
    }

    //주문하기 옆 ()
    if(chkCnt === 0) {
        payTotalQty.value = "주문하기(0)"
    } else {
        payTotalQty.value = "주문하기" + "(" + chkCnt + ")"

    }

}

//+,-버튼 눌렀을 때
function changeQuantity(element, change) {
    // 버튼이 속한 행(tr) 내의 수량 입력 필드(orderQty)를 찾음
    let orderQtyInput = element.closest('tr').querySelector('#orderQty');
    let currentQuantity = parseInt(orderQtyInput.value) || 0;
    let newQuantity = currentQuantity + change;

    let itemId = element.getAttribute('data-item-id');
    let oriQty = parseInt(element.getAttribute('data-qty'), 10);

    if (newQuantity >= 1) {
        orderQtyInput.value = newQuantity;

        // 필요한 데이터를 함께 전달하여 changeItem 호출
        changeItem(itemId,oriQty, newQuantity);
    }
}

// input에서 직접 수량 입력 후 엔터, 마우스 다른곳 클릭 이벤트 리스너 설정
document.querySelectorAll('.orderQty').forEach(orderQtyInput => {
    // blur 이벤트 리스너
    orderQtyInput.addEventListener('blur', function () {
        handleInputAction(this);
    });

    // keypress 이벤트 리스너 (엔터 키 입력 감지)
    orderQtyInput.addEventListener('keypress', function (event) {
        if (event.key === 'Enter' || event.keyCode === 13) {
            event.preventDefault(); // 폼 제출을 방지
            handleInputAction(this);
        }
    });
});

// 이벤트 리스너 실행시 작동(input값 저장)
function handleInputAction(inputElement) {
    let inputValue = parseInt(inputElement.value) || 1;
    let itemId = inputElement.dataset.itemId;
    let oriQty = parseInt(inputElement.dataset.oriQty, 10);

    if (inputValue <= 0) {
        inputElement.value = '1';
        inputValue.value = 1;
    }

    // 필요한 데이터를 함께 전달하여 changeItem 호출
    changeItem(itemId, oriQty, inputValue);
}

//수량변경시 함수 ajax
function changeItem(itemId, oriQty, newQuantity) {
    // itemId와 oriQty 찾기 예시. 실제 요소 구조에 따라 수정이 필요할 수 있습니다.
    let row = orderQtyInput.closest('tr'); // 가정: orderQtyInput이 tr 요소의 하위에 위치

    const data = {
        itemId: itemId,
        itemQty: newQuantity - oriQty
    };


    $.ajax({
        url: "/cart/updCart",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function(data) {

            if(data.redirectUrl) {
                window.location.href = data.redirectUrl; // 페이지 리디렉션
            }
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}


//삭제처리 함수
document.getElementById('deleteIcon').addEventListener('click', function() {
    // 선택된 모든 체크박스를 찾습니다.
    const selectedCheckboxes = document.querySelectorAll('.itemCheck:checked');

    // 선택된 항목의 itemId를 배열에 수집합니다.
    const selectedItemIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.dataset.itemId);

    console.log(selectedItemIds.values());

    if (selectedItemIds.length > 0) {
        // 서버에 삭제 요청을 보냅니다.
        console.log({ itemIds: selectedItemIds });
        $.ajax({
            url: '/cart/delCart', // 실제 요청을 처리할 서버의 URL로 변경해야 합니다.
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ itemIds: selectedItemIds }),
            dataType: 'json',
            success: function(data) {
                console.log(data); // 성공 응답 처리
                // 성공적으로 삭제된 후 페이지를 새로고침하거나, UI를 업데이트합니다.
                location.reload(); // 예시로 페이지 새로고침
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
            }
        });

    } else {
        alert('삭제할 항목을 선택해주세요.');
    }
});

//삭제 함수 fetch
// fetch('/cart/delCart', {
//     method: 'POST',
//     headers: {
//         'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({ itemIds: selectedItemIds }),
// })
//     .then(response => response.json())
//     .then(data => {
//         console.log(data); // 성공 응답 처리
//         // 성공적으로 삭제된 후 페이지를 새로고침하거나, UI를 업데이트합니다.
//         location.reload(); // 예시로 페이지 새로고침
//     })
//     .catch(error => console.error('Error:', error));
//수량변경시 함수 fetch
// function changeItem() {
//     let orderItemId = document.querySelector('#itemId').value;
//
//     const data = {
//         itemId: orderItemId,
//         itemQty: orderQtyInput.value - oriQty.value
//         // itemCover : itemCover
//     };
//
//     fetch("/cart/updCart", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//             "Accept": "application/json"
//         },
//         body: JSON.stringify(data)
//     })
//         .then(response => response.json())
//         .then((data) => {
//             console.log(data.state);
//             if(data.redirectUrl){
//                 window.location.href = data.redirectUrl; // 페이지 리디렉션
//             }
//
//
//         })
//         .catch(error => console.error("Error:", error));
//
//     //
// };
