function uploadPhoto(){
    //上传头像文件
}

function changeCaptcha(bool) {
    //ajax异步加载验证码
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState==4&&xmlhttp.status==200){
            document.getElementById("captcha").innerText=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","/captcha",bool);
    xmlhttp.send();
}

function sendSMS() {
    var sendButton = document.getElementById("phone");
    sendButton.style.pointerEvents = "none";
    console.log("禁用按钮")
    var phone = document.getElementById("phoneNumber").value;
    //ajax异步发送手机验证码
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState==4&&xmlhttp.status==200){
            setTimeout(setButtonclickble(),10000);
        }
    }
    xmlhttp.open("GET","/sendSMS?phoneNumber="+phone,false);
    xmlhttp.send();
    console.log("发送data")
}

function setButtonclickble() {
    var sendButton = document.getElementById("phone");
    sendButton.style.pointerEvents = "auto";
}

function pwdLogin() {
    var login = document.getElementsByClassName("wrap-login");
    login[0].classList.remove("hidden");
    login[0].classList.add("show");
    login[1].classList.remove("show");
    login[1].classList.add("hidden");

    var tags = document.getElementsByClassName("top_tag");
    tags[0].classList.add("active");
    tags[1].classList.remove("active");
}

function phoneLogin() {
    var login = document.getElementsByClassName("wrap-login");
    login[0].classList.remove("show");
    login[0].classList.add("hidden");
    login[1].classList.remove("hidden");
    login[1].classList.add("show");

    var tags = document.getElementsByClassName("top_tag");
    tags[0].classList.remove("active");
    tags[1].classList.add("active");
}

function changeContent(position){
    var selfs = document.getElementsByClassName("self");
    var honor = document.getElementsByClassName("honor");
    var experience = document.getElementsByClassName("experience");
    console.log(selfs.length);
    if(position==1){
        for(var i=0;i<selfs.length;i++){
            selfs[i].classList.remove("hidden");
            selfs[i].classList.add("show");
        }
        honor[0].classList.remove("show");
        honor[0].classList.add("hidden");
        experience[0].classList.remove("show");
        experience[0].classList.add("hidden");
    }
    else if(position==2){
        for(var i=0;i<selfs.length;i++){
            selfs[i].classList.remove("show");
            selfs[i].classList.add("hidden");
        }
        honor[0].classList.remove("hidden");
        honor[0].classList.add("show");
        experience[0].classList.remove("show");
        experience[0].classList.add("hidden");
    }
    else{
        for(var i=0;i<selfs.length;i++){
            selfs[i].classList.remove("show");
            selfs[i].classList.add("hidden");
        }
        honor[0].classList.remove("show");
        honor[0].classList.add("hidden");
        experience[0].classList.remove("hidden");
        experience[0].classList.add("show");
    }
    
}

function getUserInfo(){
    console.log("test");
    var xmlhttp = new XMLHttpRequest();
    var info;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState==4&&xmlhttp.status==200){
            info = JSON.parse(xmlhttp.responseText);
            localStorage.userInfo=xmlhttp.responseText;
            showInfo(info);
            console.log(info)
            
        }
    }
    xmlhttp.open("GET","/userInfo",true);
    xmlhttp.send();
    // document.onreadystatechange = function(){
    //     if(document.readyState=="complete"){
    //         showInfo(info);
    //     }
    // }
}

function showInfo(info){
    //将数据库返回的用户数据渲染至页面上
    //获取数据
    var userId = info["userId"];//用户名
    var name = info["uname"];//姓名
    var jianjie = info["jianjie"];//简介
    var edujl = info["edujl"];//教育经历
    var majorbg = info["majorbg"];
    var hobby = info["hobby"];//爱好
    var honor = info["honor"];//奖项
    var xmjl = info["xmjl"];//项目
    var contact = info["contact"];//联系方式
    var photo = info["photo"];//照片url

    //更新内容
    document.getElementsByClassName("top")[0].innerText=formatInfo(userId);
    document.getElementById("name").innerText=formatInfo(name);
    document.getElementsByClassName("brief_content")[0].innerText=formatInfo(jianjie);
    document.getElementsByClassName("edu_content")[0].innerText=formatInfo(edujl);
    document.getElementsByClassName("back_content")[0].innerText=formatInfo(majorbg);
    document.getElementsByClassName("hobby_content")[0].innerText=formatInfo(hobby);
    document.getElementById("contact").innerText="tel："+formatInfo(contact);
    document.getElementById("honor").innerText=formatInfo(honor);
    document.getElementById("xmjl").innerText=formatInfo(xmjl);
    if(photo!=null&&photo!=''){
        document.getElementsByClassName("photo")[0].src = "/resources/images/"+photo;
    }
    
}

function formatInfo(info){
    if(info!="undefined"){
        return info;
    }
    return "还没有填写呀";
}

function modifyInfo() {
    window.location.href = "/resources/html/modify.html";
}