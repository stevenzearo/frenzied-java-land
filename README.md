# IChat项目
This project is starting for running a WeChat official account which called FrenziedJavaLand, sharing technologies and skills using for running and doing integrations for this WeChat official account.  
This project is an opensource project, project source code published on GitHub, which URL is: [ichat](https://github.com/stevenzearo/ichat)  
#### We very welcome partners who willing to do distributions for this project, not only developing but also running.  
#### In this project you can learn any technology or skill which adopted in real commercial project and you are interested in. Including back-end developing, front-end developing, site reliability engineering, article writing.  

项目启动的初衷是为了运营一个叫做`FrenziedJavaLand`的公众号，分享整个公众号运营和开发所应用到的技术和技巧。  
整个项目是开源的，项目源代码发布在GitHub上面，地址：[ichat](https://github.com/stevenzearo/ichat)  
#### 我们十分欢迎感兴趣的小伙伴参与到项目的开发与运营当中。  
#### 在这个项目当中，你可以学习到先进的企业项目开发技能，项目运营技能，文章书写技能等。  
#### 我们希望所有参与到项目当中的小伙伴都能学到自己想学，并且实际企业当中真正在用的，前沿技术和技能
#### 有任何想法的小伙伴，欢迎直接在GitHub上面留言或者关注公众号，在公众号上私信我们，我们收到消息后，都会尽快第一时间回复。
我们的公众号二维码：  
![FrenziedJavaLand](https://github.com/stevenzearo/ichat/tree/master/docs/img/frenzied_java_land.jpg)

## 项目结构
```
---ichat
   |--backend # 后端相关服务代码
   |  |--ichat-service
   |  |--ichat-service-db-migration # ichat-service数据库版本控制模块，主要采用flyway的R模式
   |  |--ichat-service-interface # ichat-service需要向外暴露的接口
   |--common
   |  |--common-lib # 公共依赖包
   |--demo
   |  |--java-demo # 项目运营中需要做代码介绍时使用
   |--deployment # 项目部署目录，一般不存在，本地部署时，注意不要提交到GitHub
   |--docs # 项目需要用到的文档，包括代码开发，项目运营等需要用到的文档
   |--ichat-site # 项目网关服务，主要用于和内部服务交互，接口会暴露到外网
   |--registry-site # 项目服务的注册中心，基于euraka的注册中心，用于服务之间的接口调用
   |--resource # 项目前端代码
   |  |--static # 项目前端部署使用的资源
   |  |--static-dev # 项目前端开发目录
   |--wechat_integration # Python相关的试验，忽略
```

## 项目技术栈
项目中已使用或将要使用到的技术栈，包括不限于以下：
1. 后端：Java Gradle, SpringBoot, Eurka, MySQL, MongoDB
2. 前端：Jquery, Bootstrap
3. DevOps: Ubuntu, Bash, Docker, Nginx 
4. 其他第三方API，SDK等 
5. Idea, WebStorm，Postman，其他项目开发运营辅助工具等  

(注：我们的开发人员目前主要是做后端开发的，所以对前端的技术选型并没有采用前端最新的技术。  
**十分欢迎感兴趣的前端小伙伴们加入我们，优化我们的前端页面，分享前端技术知识，完善我们的前端技术栈**)

# 项目代码规范
注释：
1. 注释尽量采用英文注释（如果不太清楚的地方可以百度，顺便提高自己的英语水平），我们希望尽量避免因为不同的本地环境的字符编码集，带来的开发过程的困扰
2. 命名尽量能够清楚表达含义，尽量不要出现中文缩写，中文拼音
3. 其他的，可参考Google, Alibaba的代码规范  
（注：英语表达能力好的小伙伴，欢迎直接使用英语开发，包括注释，命名，提交记录表述等）

# Git代码提交流程
提交代码时，请从最新的master切出你的功能分支，然后再在你的功能分支上进行开发，开发好后在GitHub上创建Pull Request。  
我们会在第一时间Review你的代码，一起探讨需要更改的地方。  
代码Review的形式取决于据开发人员的实际条件，我们欢迎以视频会议的形式进行。  
当然不方便的小伙伴，我们也会直接在GitHub上对应的Pull Request中的记录上提前进行评论。  
参考： ![Git最佳实践](https://github.com/stevenzearo/ichat/tree/master/docs/img/git_flow.png)