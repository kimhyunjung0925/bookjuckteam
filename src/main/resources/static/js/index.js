const myFetch = {
    send: function(fetchObj, cb) {
        return fetchObj
            // .then(res => res.json())
            .then(res => {
                if (!res.ok) {
                    throw new Error(`Server responded with status ${res.status}`);
                }
                return res.json();
            })
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
        console.log(cb);
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

//글자수 제한 하는 함수
function textLengthOverCut(ctnt, len, lastTxt) {
    if (len == "" || len == null) { // 기본값
        len = 10;
    }
    if (lastTxt == "" || lastTxt == null) { // 기본값
        lastTxt = "...";
    }
    if (ctnt.length > len) {
        ctnt = ctnt.substr(0, len) + lastTxt;
    }
    return ctnt;
}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d)/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};

