function backToResume(){
    window.history.back(-1);
}


function initInfo(){
    // {
    //     "userId":"upbhy",
    //     "uname":"宾海寅",
    //     "jianjie":"简介",
    //     "edujl":"教育经历",
    //     "majorbg":"专业背景",
    //     "hobby":"爱好",
    //     "honor":"奖项",
    //     "xmjl":"项目经历",
    //     "contact":"18273128805"
    // }
    info = JSON.parse(localStorage.userInfo);
    document.getElementById("name").value=info["uname"];
    document.getElementById("jianjie").innerText=info["jianjie"];
    document.getElementById("edujl").innerText=info["edujl"];
    document.getElementById("majorbg").innerText=info["majorbg"];
    document.getElementById("hobby").innerText=info["hobby"];
    document.getElementById("honor").innerText=info["honor"];
    document.getElementById("xmjl").innerText=info["xmjl"];
    document.getElementById("contact").value=info["contact"];
    // document.onreadystatechange = function(){
    //     if(document.readyState=="complete"){
    //         info = localStorage.userInfo;
    //         document.getElementById("name").innerText=info["uname"];
    //         document.getElementById("jianjie").innerText=info["jianjie"];
    //         document.getElementById("edujl").innerText=info["edyjl"];
    //         document.getElementById("majorbg").innerText=info["majorbg"];
    //         document.getElementById("hobby").innerText=info["hobby"];
    //         document.getElementById("honor").innerText=info["honor"];
    //         document.getElementById("xmjl").innerText=info["xmjl"];
    //         document.getElementById("contact").innerText=info["contact"];
    //     }
    // }
}