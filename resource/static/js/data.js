function setCategory(title = "", url = "") {
    return {
        title: title,
        url: url
    }
}

function setArticle(title = "", postImageUrl = "",categories = [{}], wechatUrl = "", author, createdTime = 0, commentCount = 0) {
    return {
        title: title,
        postImageUrl: postImageUrl,
        categories: categories,
        wechatUrl: wechatUrl,
        author: author,
        createdTime: new Date(),
        commentCount: commentCount
    }
}

function setAuthor(name = "", homePageUrl = "") {
    return {
        name: name,
        homePageUrl: homePageUrl
    }
}

var categories = [
    setCategory("最新文章", "#"),
    setCategory("聊天机器人", "#"),
    setCategory("今日天气", "#"),
]

var authors = [setAuthor("疯狂Lawrence", "#")]

var articles = [
    setArticle("一图看懂GitFlow最佳实践", "img/git.png",[categories[0]], "https://mp.weixin.qq.com/s/MLGhaVie3vPqQqmw3nMNRQ", authors[0], 0, 998),
    setArticle("ChatGPT使用初体验", "img/chatgpt.png", [categories[0]], "https://mp.weixin.qq.com/s/0PzdbPweyN3Fsra2sX_lQg", authors[0], 0, 998),
    setArticle("Gradle 入门指引", "img/gradle.jpg", [categories[0]], "https://mp.weixin.qq.com/s/gvximjyeaEcROwjf2WUhfQ", authors[0], 0, 998),
    setArticle("一图看懂GitFlow最佳实践", "img/git.png", [categories[0]], "https://mp.weixin.qq.com/s/MLGhaVie3vPqQqmw3nMNRQ", authors[0], 0, 998),
]

