$(function () {
    function setImages() {
        var imgs = $("div.main-content * img")
        for (let i = 0; i < imgs.length; i++) {
            const img = imgs[i]
            var src = $(img).attr("data-src")
            setImage(img, src);
        }
    }

    let articleTemplate = null
    function initArticles() {
        let parent = $("section.blog-sec div div.main-content div.post_items");
        articleTemplate = $("section.blog-sec div div.main-content div.post_items div.post_item")[0]
        parent.empty()
    }
    let skip = 0;
    let total = 0;
    function setArticles(skip = 0, limit = 5) {
        let parent = $("section.blog-sec div div.main-content div.post_items");
        getArticles(skip, limit, function (response) {
            total = response.total
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
        }).then(setImages)
    }
    function setLoadMoreEvent() {
        var loadMoreBtn = $("section.blog-sec div div.main-content button.load-more-btn")[0]
        $(loadMoreBtn).click(function () {
            skip += 5;
            if (skip > total) return
            setArticles(skip, 5)
        })
    }
    setLoadMoreEvent()
    initArticles()
    setArticles()
    /*
    new Promise(function (resolve, reject) {
        setArticles()
    }).then(function () {
        setImages()
    })
*/
});