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

    getArticle(articleId, function (response) {
        console.log(response)
        let dataTemplate = $("div.blog-single")
        let title = $(dataTemplate).find("h2 a")
        console.log(response.title)
        $(title).text(response.title)

        let author = $(dataTemplate).find("ul.post-tools li.admin a")
        $(author).text(response.author)

        let content = $(dataTemplate).find("div.content")
        $(content).html(response.content)
        // let createTime = $(dataTemplate).find("ul.post-tools li.date")
        // $(createTime).text(response.created_time)


        let img = $(dataTemplate).find("img.blog-image")
        $(img).attr("data-src", response.thumb_url)


/*        let parent = $("section.blog-sec div div.main-content");
        parent.empty()
        parent.append(dataTemplate)*/
    }).then(function () {
        setImages()
    })
})