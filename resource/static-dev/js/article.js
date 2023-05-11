$(function () {
    function setImage(img, src) {
        getImage(src).then(function (response) {
            $(img).attr("src", "data:img/png;base64," + response.data.data)
        })
    }

    function setImages() {
        var imgs = $("div.main-content * img")
        console.log(imgs.length)
        for (let i = 0; i < imgs.length; i++) {
            const img = imgs[i]
            var src = $(img).attr("data-src")
            setImage(img, src);
        }
    }

    function setArticles() {
        var articleTemplate = $("section.blog-sec div div.main-content div.post_item")[0]
        let parent = $("section.blog-sec div div.main-content");
        parent.empty()
        getArticles(function (data) {
            let materials = data.materials;
            for (let i = 0; i < materials.length; i++) {
                var material = materials[i]
                let articleData = material.content.articles[0];

                var title = $(articleTemplate).find("h2 a")
                $(title).text(articleData.title)

                var author = $(articleTemplate).find("li.admin a")
                $(author).text(articleData.author)

                var date = $(articleTemplate).find("li.date a")
                $(date).text(material.content.createdTime)

                var digest = $(articleTemplate).find("h6")
                $(digest).text(articleData.digest)

                var articleImgSrc = articleData.thumbUrl
                let img = $(articleTemplate).find("img");
                $(img).attr("data-src", articleImgSrc)

                let content = "<div class='post_item'>" + $(articleTemplate).html() + "</div>";
                parent.append(content)
            }
            parent.append("<button class=\"load-more-btn\">load more</button>")
        }).then(function () {
            setImages()
        })
    }
    setArticles()
    /*
    new Promise(function (resolve, reject) {
        setArticles()
    }).then(function () {
        setImages()
    })
*/
});