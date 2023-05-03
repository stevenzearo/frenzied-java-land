$(function (){
    var imgs = $("div.a-article * img")
    for (let i = 0; i < imgs.length; i++) {
        const img = imgs[i]
        var src = $(img).attr("data-src")
        $.ajax({
            url: "http://localhost:9090/image/article-image",
            data: JSON.stringify({"url": src}),
            contentType: "application/json",
            type: "PUT",
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:63342",
                "Access-Control-Allow-Headers": "*",
                "dataType": "application/json",
                "access-control-allow-origin": "*"
            },
            success: function (data) {
                $(img).attr("src", "data:img/png;base64," + data.data)
            },
            error: function (error) {
                console.log(JSON.stringify(error))
            }
        })
    }
});