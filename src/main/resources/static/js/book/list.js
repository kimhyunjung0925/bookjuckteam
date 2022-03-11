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
            div.className = "flex flex-direc-column align_items_center"
            div.innerHTML = `
                    <a href="/book/detail?isbn=${item.isbn}"><img src="${item.cover}" class="book_img_1013"></a>
                    <a href="/book/detail?isbn=${item.isbn}" class="width200p center flex"><div class="text_oneline">${item.title}</div></a>
                    <div class="flex color_grey" style="flex-wrap: wrap;">
                       <div class="">${item.author}</div>
                       <div>&nbsp;|&nbsp;</div>
                       <div>${item.publisher}</div>
                    </div>
                    <div class="color_red">${item.priceStandard}</div>
                `;
        })


    }, {
        selectVal: sort.options[sortidx].value,
        categoryBookjuck: encodeURIComponent(uriVal.get('categoryBookjuck'))
    })

})
