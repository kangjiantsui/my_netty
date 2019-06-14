var i = 100;//单一单位概率储蓄值
var n = 100;//测试次数
var s = 0;//计数器
function myRandom(min, max) {//随机数生成器
    return Math.round(Math.random() * (max - min) + min);
}

function sRandom(v) {//v=>随机概率
    if (myRandom(0, i) < v) {//触发的情况
        i = i + 100 - v;
        s++;
    } else {
        i = i - v
    }
    ;//未触发
}

for (var a = 0; a < n; a++) {
    sRandom(15);
}
console.log(s)
