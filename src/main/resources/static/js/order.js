var order = avalon.define({
    $id: "order",
    orderPageInfo: {},

    init: function() {
        order.request(0);
    },

    request: function (page) {
        $.ajax({
            type: "get",
            url: "/user/orderList?rows=10&page=" + page,
            data: {},
            success: function (data) {
                if (typeof(data) == "object" && Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length) {
                    order.orderPageInfo = data;
                } else {
                    // session超时
                    window.location="../login";
                }
            }
        });
    },

    showPageNav: function (pageNum) {
        if (pageNum == order.orderPageInfo.pageNum) {
            return "<span class='page-link'>" + pageNum + "<span class='sr-only'>(current)</span></span>";
        } else {
            return "<a class='page-link' href='javascript:' ms-click='@request(" + pageNum + ")'>" + pageNum + "</a>";
        }
    }
});

order.$watch('onReady', order.init());