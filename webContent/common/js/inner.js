var obj = {};
//匿名方法
var mathz = (function() {
    // 内部方法定义
    function newadd(a,b) {
        return a+b;}
    function newsub(a,b) {
        return a-b;}

    // obj属性add
    obj.add = function add(a,b){
        // 内部方法使用
        console.log(newadd(a,b));
    };
    // 赋值给mathz
    return {sub : function sub(a,b){
            console.log(newsub(a,b));
        }
    }
})();
console.log(mathz.sub(1,2));

console.log(obj.add(1,2));
var mathz1 ={
    // 方法定义
    add1 : function newadd(a,b) {
        return a+b;},
    add2 : function newsub(a,b) {
        return a-b;}
};

mathz1.add1(1,2);

