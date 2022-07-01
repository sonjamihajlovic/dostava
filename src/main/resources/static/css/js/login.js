$(document).on("submit", "#login", function (event) {
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
            window.localStorage.setItem("ID", response.id);
            window.localStorage.setItem("ULOGA", response.uloga);
            //if(response.uloga=="KUPAC"){
              //  window.location.href = "homepage.html";
               // return;
           // }
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