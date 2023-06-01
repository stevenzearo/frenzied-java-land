const BASE_URL = "http://localhost:8080"

function sendRequest(url = "", queryParam = {}, requestBody = {}, method = "") {

}

function getImage(src = "") {
    let url = BASE_URL + "/hland/material/article-image";
    return axios.get(url, {params: {url: src}})
}

function setImage(img, src) {
    getImage(src).then(function (response) {
        $(img).attr("src", "data:img/png;base64," + response.data.data)
    })
}

function getAccessToken() {
    let url = BASE_URL + "/hland/access-token?renew=false";
    return axios.post(url)
}

// 68_AhYqctYugAl6yRJQoybkPi_QkWGzQ8bY8XcRmQan9AMrV8TdTVoaAR7i6y15-xf1pJSXCW3Rq4dMiqe0AgUwIURSuGcVXD3XXqxxJAQJN-wZpd7RECNyRJddXRoJCKfAFAEXF
function getArticles(func) {
    var requestBody = {
        skip: 0,
        limit: 100
    }

    let requestConfig = {
        timeout: 30000,
        contentType: "application/json"
    };

    return axios({
        url: BASE_URL + "/article/summary",
        method: "put",
        data: requestBody,
        config: requestConfig
    }).then(function (response) {
        func(response.data)
    }).catch(function (error) {
        console.log(JSON.stringify(error))
    })
}
function getArticle(article_id = "", func) {
    let requestConfig = {
        timeout: 30000,
        contentType: "application/json"
    };

    return axios({
        url: BASE_URL + "/article/" + article_id,
        method: "get",
        config: requestConfig
    }).then(function (response) {
        func(response.data)
    }).catch(function (error) {
        console.log(JSON.stringify(error))
    })
}

function setCategory(title = "", url = "") {
    return {
        title: title,
        url: url
    }
}

function setArticle(title = "", postImageUrl = "", categories = [{}], wechatUrl = "", author, createdTime = 0, commentCount = 0) {
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
    setArticle("一图看懂GitFlow最佳实践", "img/git.png", [categories[0]], "https://mp.weixin.qq.com/s/MLGhaVie3vPqQqmw3nMNRQ", authors[0], 0, 998),
    setArticle("ChatGPT使用初体验", "img/chatgpt.png", [categories[0]], "https://mp.weixin.qq.com/s/0PzdbPweyN3Fsra2sX_lQg", authors[0], 0, 998),
    setArticle("Gradle 入门指引", "img/gradle.jpg", [categories[0]], "https://mp.weixin.qq.com/s/gvximjyeaEcROwjf2WUhfQ", authors[0], 0, 998),
    setArticle("一图看懂GitFlow最佳实践", "img/git.png", [categories[0]], "https://mp.weixin.qq.com/s/MLGhaVie3vPqQqmw3nMNRQ", authors[0], 0, 998),
]

