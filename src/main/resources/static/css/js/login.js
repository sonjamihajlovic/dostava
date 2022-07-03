/*$(document).on("submit", "#login", function (event) {
    event.preventDefault();
    let korisnickoIme = $("#usernameField").val();
    let lozinka = $("#passwordField").val();


    let newLogin = {
        korisnickoIme,
        lozinka
    }
    console.log(newLogin);
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/api/login",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newLogin),
        success: function (response) {
          //  window.localStorage.setItem("ID", response.id);
            //window.localStorage.setItem("User Name", response.korisnickoIme);
            if(response.korisnickoIme==="tica"){
                window.location.href = "homepage.html";
                return;
            }
           // if(response.uloga=="TRENER"){
            //    window.location.href = "trenerHomePage.html";
            //    return;
           // }
           // if(response.uloga=="ADMINISTRATOR"){
           //    window.location.href = "adminHomePage.html";
           //     return;
           // }

            window.location.href = "homepage.html";
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška prilikom Logovanja!");
        }
    });

});
$("#dugme2").on("click", function (event) {
    console.log("test");
    window.location.href = "registracija.html";
});
/*
$(document).ready(function(){
    $("#but_submit").click(function(){
        var username = $("#txt_user").val().trim();
        var password = $("#txt_password").val().trim();

        if( username != "" && password != "" ){
            $.ajax({
                url:'http://localhost:8081/api/login',
                type:'post',
                data:{username:username,password:password},
                success:function(response){
                    var msg = "";
                    if(response == 1){
                        window.open("http://localhost:8081/api/registration");
                    }else{
                        msg = "Invalid username and password!";
                    }
                    $("#message").html(msg);
                }
            });
        }
    });
});

*/


var settings = {
    "url": "http://localhost:8081/api/login",
    "method": "POST",
    "timeout": 0,
    "headers": {
        "Content-Type": "application/json",
        "Cookie": "JSESSIONID=738586A434BF1CC863C3CF77032584D3"
    },
    "data": JSON.stringify({
        "korisnickoIme": "luka",
        "prezime": "Lukovic",
        "lozinka": "m222"
    }),
};

$.ajax(settings).done(function (response) {
    console.log(response);
});
