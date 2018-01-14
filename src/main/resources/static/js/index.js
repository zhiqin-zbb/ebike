var index = avalon.define({
    $id: "index",
    brandId: 0,
    brandList: {},
    productPageInfo: {},
    carouselImgUrlList: {},
    advertisementList: {},

    init: function () {
        // 图片轮播
        $.ajax({
            type: "get",
            url: "/adv/carousel",
            data: {},
            success: function (data) {
                index.carouselImgUrlList = data;
            }
        });

        // 商品列表
        index.request(0, 0);

        // 底部广告
        $.ajax({
            type: "get",
            url: "/adv/advertisementList",
            data: {},
            success: function (data) {
                index.advertisementList = data;
            }
        });
    },

    request: function (brandId, page) {
        $.ajax({
            type: "get",
            url: "/product/list?rows=9&page=" + page + "&brandId=" + brandId,
            data: {},
            success: function (data) {
                index.brandId = data.brandId;
                index.brandList = data.brandList;
                index.productPageInfo = data.productPageInfo;
            }
        });
    },

    showPageNav: function (pageNum) {
        if (pageNum == index.productPageInfo.pageNum) {
            return "<span class='page-link'>" + pageNum + "<span class='sr-only'>(current)</span></span>";
        } else {
            return "<a class='page-link' href='javascript:' ms-click='@request(" + index.brandId + "," + pageNum + ")'>" + pageNum + "</a>";
        }
    }
});

index.$watch('onReady', index.init());