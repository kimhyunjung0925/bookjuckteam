let sort = document.querySelector('#sort');
let uri = encodeURIComponent('국내도서%');



sort.addEventListener('click', (e) => {
    let sortidx = document.querySelector('#sort').options.selectedIndex;
    console.log(sort.options[sortidx].value)

    myFetch.get("/book/kor", a => {
        console.log(a)
        let list = document.querySelector('.list');
        list.innerHTML = '';
        a.forEach(item => {
            let div = document.createElement('div');
            list.append(div);
            div.innerHTML = `
                <div>${item.title}</div>
               <div><img src=${item.cover}></div>
               <div> ${item.priceStandard}</div>
               <div> ${item.publisher}</div>
               <div>${item.author}</div>
               <div >${item.pubDate}</div>
               <div> ${item.description}</div>
                `;
        })


    }, {
        selectVal: sort.options[sortidx].value,
        categoryBookjuck: uri
    })


})

