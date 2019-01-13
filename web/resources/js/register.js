function ckNameUseable(name){
    console.log(name);
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState==4&&xmlhttp.status==200){
            if(xmlhttp.responseText==='1'){
                document.getElementById("tip").style.display="block";
            }
        }
    }
    xmlhttp.open("GET","/checkName?name="+name,true);
    xmlhttp.send();
}

function checkPwd(){
    var pwd1 = document.getElementById("pwd1").nodeValue;
    var pwd2 = document.getElementById("pwd2").nodeValue;
    var b = pwd1!=''&&pwd2!=''&&pwd1==pwd2;
    console.log(pwd1,pwd2,b);
    return b;
}

