var viewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    productList: {},
    text: "请求数据",

    request: function (page, brandId) {
        console.log(page);
        console.log(brandId);
        var url = "/product/data?";
        if (page != 0) {
            url += "&page=" + page;
        }
        if (brandId != 0) {
            url += "&brandId=" + brandId;
        }
        $.ajax({
            type: "get",
            url: url,    //向springboot请求数据的url
            data: {},
            success: function (data) {
                viewmodel.productList = data.list;
            }
        });
    }
});