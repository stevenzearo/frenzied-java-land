$(function () {
    var postContents = $("section[class='news-banner'] * div[class='post-content']")
    for (let i = 0; i < postContents.length; i++) {
        let article = articles[i];
        category = article.categories[0]
        var content = postContents[i]
        var parent = $(content).parent()
        $(parent).attr("style", "background:url('" + article.postImageUrl + "')no-repeat;")

        var categoryDom = $(content).find("a[class='category-ttl']")
        $(categoryDom).attr("href", category.url)
        $(categoryDom).text(category.title)
        var titleDom = $(content).find("a h3[class='post-ttl']")
        $(titleDom).parent().attr("href", article.wechatUrl)
        $(titleDom).text(article.title)
        var authorDom = $(content).find("li[class='admin'] a")
        $(authorDom).text(article.author.name)
        $(authorDom).attr("href", authorDom.homePageUrl)

        var dateDom = $(content).find("li[class='date']")
        var d = article.createdTime
        var dateStr = d.toLocaleDateString()
        $(dateDom).text(dateStr)
        var commentDom = $(content).find("li[class='comment']")
        $(commentDom).text("(" + article.commentCount + ") Comments")
    }
    // console.log(postContents.length)
});