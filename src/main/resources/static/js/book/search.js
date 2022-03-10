var kvp = document.location.search.substr(1).split('&');
let i=0;

function insertParam(key, value) {
    key = encodeURIComponent(key);
    value = encodeURIComponent(value);

    for(; i<kvp.length; i++){
        if (kvp[i].startsWith(key + '=')) {
            let pair = kvp[i].split('=');
            pair[1] = value;
            kvp[i] = pair.join('=');
            break;
        }
    }

    if(i >= kvp.length){
        kvp[kvp.length] = [key,value].join('=');
    }

    // can return this or...
    let params = kvp.join('&');

    // reload page with new params
    // document.location.search = params;

    location.href = 'search?'+params;
}

const sortElem = document.querySelector('#sort');

sortElem.addEventListener('change', (e) => {
    let sortidx = sortElem.options.selectedIndex;
    let value = sortElem.options[sortidx].value;

    insertParam('sort',value); //쿼리스트링 변경해서 그 페이지로 보내버림
});

let sort;

for(; i<kvp.length; i++){ //변경한 정렬값을 사이트에 있는 셀렉트에다가 셀렉티드할수있도록 값 찾는 중
    if (kvp[i].startsWith('sort' + '=')) {
        let pair = kvp[i].split('=');
        sort = pair[1];
        break;
    }
}

if(sort){
    switch (sort){
        case 'Accuracy' :
            sortElem.options[0].selected = true;
            break;
        case 'PublishTime' :
            sortElem.options[1].selected = true;
            break;
        case 'CustomerRating' :
            sortElem.options[2].selected = true;
            break;
        case 'SalesPoint' :
            sortElem.options[3].selected = true;
            break;
    }
}



