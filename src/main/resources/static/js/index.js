const myFetch = {
    send: function(fetchObj, cb) {
        return fetchObj
            .then(res => res.json())
            .then(cb)
            .catch(e => { console.log(e) });
    },
    get: function(url, cb, param) {
        if(param) {
            const queryString = '?' + Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
            url += queryString;
        }
        return this.send(fetch(url), cb);
    },
    post: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb);
    },
    put: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'put',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb)
    },
    delete: function(url, cb) {
        return this.send(fetch(url, {
            'method': 'delete',
            'headers': { 'Content-Type': 'application/json' },
        }), cb);
    }
}

const regex = {
    uid: /^[a-z]+[a-z0-9]{5,20}$/g,
    upw: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}$/,
    nm: /^([가-힣]{2,10})$/,
    msg: {
        uid: '영소문자/숫자 5~20자리',
        upw: '영문자/숫자/특수문자 조합 8~15자리(대소문자 구분)',
        nm: '한글 2~10글자',
    },
    isWrongWith : function (target, val){
        return (target && val) ? !this[target].test(val) : true;
    }
};