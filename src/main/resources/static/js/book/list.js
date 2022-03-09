let sort = document.querySelector('#sort');
let uri = encodeURIComponent('국내도서%');


sort.addEventListener('click', (e) => {
    let sortidx = document.querySelector('#sort').options.selectedIndex;
    let uriVal = new URL(window.location.href).searchParams;

    console.log(uriVal.get('categoryBookjuck'))


    myFetch.get("/book/kor", a => {
        console.log(a)
        let list = document.querySelector('.list');
        list.innerHTML = '';
        a.forEach(item => {
            let div = document.createElement('div');
            list.append(div);

            div.innerHTML = `
                 <div class="flex-direc-column center">
                       <a href="/book/detail?isbn=${item.isbn}"> <img src="${item.cover}"></a>
                        <div class="text_oneline">${item.title}</div>
                        <div class="flex color_grey" style="flex-wrap: wrap;">
                            <div class="text_oneline">${item.author}</div>
                            <div>&nbsp;|&nbsp;</div>
                            <div>${item.publisher}</div>
                        </div>
                        <div class="color_red">${item.priceStandard}</div>
                    </div>
                  
                `;
        })


    }, {
        selectVal: sort.options[sortidx].value,
        categoryBookjuck: encodeURIComponent(uriVal.get('categoryBookjuck'))
    })

})
