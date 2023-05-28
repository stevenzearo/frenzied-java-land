$(function () {
    function setImages() {
        var imgs = $("div.main-content * img")
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

        getArticles(function (response) {
            let articleSummaries = response.article_summaries;
            for (let i = 0; i < articleSummaries.length; i++) {
                let articleData = articleSummaries[i]
                var title = $(articleTemplate).find("h2 a")
                $(title).text(articleData.title)

                var author = $(articleTemplate).find("li.admin a")
                $(author).text(articleData.author)

                var createdTime = new Date(articleData.created_time).toLocaleString()
                var date = $(articleTemplate).find("li.date")
                $(date).text(createdTime)

                var digest = $(articleTemplate).find("h6")
                $(digest).text(articleData.digest)

                var articleImgSrc = articleData.thumb_url
                let img = $(articleTemplate).find("img")
                $(img).attr("data-src", articleImgSrc)

                let redirect = $(articleTemplate).find("a.readmore-btn")
                $(redirect).attr("href", "./article-single.html?article_id=" + articleData.id)
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