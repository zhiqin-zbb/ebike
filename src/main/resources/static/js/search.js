var search = avalon.define({
    $id: "search",
    name: "",
    productPageInfo: {},

    init: function() {
        search.name = $("#name").html();
        search.search(1);
    },

    search: function (page) {
        $.ajax({
            type: "get",
            url: "/product/search?rows=9&page=" + page + "&name=" + search.name,
            data: {},
            success: function (data) {
                search.productPageInfo = data;
            }
        });
    },

    showPageNav: function (pageNum) {
        if (pageNum == search.productPageInfo.pageNum) {
            return "<span class='page-link'>" + pageNum + "<span class='sr-only'>(current)</span></span>";
        } else {
            return "<a class='page-link' href='javascript:' ms-click='@search(" + pageNum + ")'>" + pageNum + "</a>";
        }
    }
});

search.$watch('onReady', search.init());