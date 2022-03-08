let sort = document.querySelector('#sort');
sort.addEventListener('click', () => {

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
        selectVal: document.querySelector('#sort1').value
    })
})