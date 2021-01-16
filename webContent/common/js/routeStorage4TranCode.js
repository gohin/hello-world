/**
 * 明细查询查看详情页面路由闭包
 */
var ROUTE_STORAGE = ROUTE_STORAGE || {};
(function routeStorage() {
    function eleobj() {
        page='';
        url='';
        height='';
        width='';
        function setPage(page) {
            this.page= page;
            return this;
        }
        function setUrl(url) {
            this.url = url;
            return this;
        }
        function setHeight(h) {
            this.height = h;
            return this;
        }
        function setWidth(w) {
            this.width = w;
            return this;
        }
        this.setPage = setPage;
        this.setUrl = setUrl;
        this.setHeight = setHeight;
        this.setWidth = setWidth;
    }
    function tranCodeObj() {
        inits={};
        view={};
        function setInit() {
            this.inits = new eleobj();
            return this.inits;
        }
        function setView() {
            this.view = new eleobj();
            return this.view;
        }
        this.setInit = setInit;
        this.setView = setView;
    };
    var tranCodeRoute = {};
    var productRoute = {};
    var initTranCodeRouteStorage = function (tranCode) {
        if (tranCodeRoute[tranCode] == undefined || tranCodeRoute[tranCode] == null || tranCodeRoute[tranCode] == '') {
            tranCodeRoute[tranCode] = new tranCodeObj();
        }
        return tranCodeRoute[tranCode];
    };
    var initProductIdRouteStorage = function (productId) {
        if (productRoute[productId] == undefined || productRoute[productId] == null || productRoute[productId] == '') {
            productRoute[productId] = new tranCodeObj();
        }
        return productRoute[productId];
    };
    var getViewRoute4TranCode = function (tranCode,productId) {
        var routeInfo = tranCodeRoute[tranCode];
        if (routeInfo == undefined || routeInfo == null || routeInfo ==''){
            return getRouteDef(productId)['view'];
        }
        return routeInfo['view'];
    };
    var getInitRoute4TranCode = function (tranCode, productId) {
        var routeInfo = tranCodeRoute[tranCode];
        if (routeInfo == undefined || routeInfo == null || routeInfo == ''){
            return getRouteDef(productId)['inits'];
        }
        return routeInfo['inits'];
    };
    var getRouteUrl = function (tranCode, type) {

        return tranCodeRoute[tranCode]['view'][type];
    };
    var getRouteDef = function (productId) {
        return productRoute[productId];
    };
    var clear =function () {
        tranCodeRoute = {};
        productRoute = {};
    };
    ROUTE_STORAGE.getViewRoute = getViewRoute4TranCode;
    ROUTE_STORAGE.getInitRoute = getInitRoute4TranCode;
    ROUTE_STORAGE.clear = clear;
    ROUTE_STORAGE.tranCode = initTranCodeRouteStorage;
    ROUTE_STORAGE.productId= initProductIdRouteStorage;
}());