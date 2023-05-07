const BASE_URL = "http://localhost:9090"

function sendRequest(url = "", queryParam = {}, requestBody = {}, method = "") {

}

function getImage(src = "") {
    let url = BASE_URL + "/hland/material/article-image";
    return axios.get(url, {params: {url: src}})
}

function getAccessToken() {
    let url = BASE_URL + "/hland/access-token?renew=false";
    return axios.post(url)
}

// 68_AhYqctYugAl6yRJQoybkPi_QkWGzQ8bY8XcRmQan9AMrV8TdTVoaAR7i6y15-xf1pJSXCW3Rq4dMiqe0AgUwIURSuGcVXD3XXqxxJAQJN-wZpd7RECNyRJddXRoJCKfAFAEXF
function getArticles(func) {
    var accessToken = localStorage.getItem("access_token")
    if (accessToken == null) {
        getAccessToken().then(function (response) {
            let token = response.data;
            console.log(token)
            localStorage.setItem("access_token", token)
            if (response.status !== 200) {
                alert("获取Token失败！")
                return new Promise()
            } else {
                return wechatGetMaterial(token, func)
            }
        })
    } else {
        return wechatGetMaterial(accessToken, func)
    }
}

function wechatGetMaterial(accessToken = "", func) {
    var requestBody = {
        type: "NEWS",
        offset: 0,
        count: 20
    }
    let requestConfig = {
        params: {
            access_token: accessToken
        },
        timeout: 30000,
        contentType: "application/json"
    };

    return axios.post(BASE_URL + "/hland/material/article", requestBody, requestConfig).then(function (response) {
        func(response.data)
    }).catch(function (error) {
        console.log(JSON.stringify(error))

        if (error.message.indexOf("412") >= 0 || error.message.indexOf("413") >= 0) {
            localStorage.clear("access_token")
        } else {
        }
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

