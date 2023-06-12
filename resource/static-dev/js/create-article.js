$(function () {
    function setImages() {
        var imgs = $("div.blog-single * img")
        for (let i = 0; i < imgs.length; i++) {
            const img = imgs[i]
            var src = $(img).attr("data-src")


            if (src !== undefined && src !== "") {
                setImage(img, src);
            }
        }
    }

    const url = new URL(window.location.href)
    let articleId = url.searchParams.get("article_id")
    function preview() {
        let dataTemplate = $("div.blog-single div.post_item")
        let title = $(dataTemplate).find("h2 a")
        console.log(response.title)
        $(title).text(response.title)

        let author = $(dataTemplate).find("ul.post-tools li.admin a")
        $(author).text(response.author)
        var createdTime = new Date(response.created_time).toLocaleString()
        var date = $(dataTemplate).find("li.date")
        $(date).text(createdTime)

        $(dataTemplate).find("div.content").html(response.content)

        let img = $(dataTemplate).find("img.blog-image")
        $(img).attr("data-src", response.thumb_url)
    }
    function submit() {

    }

    $("#preview").click(function () {
        alert(1)
    })

    $("#submit").click(function () {
        alert(2)
    })
})