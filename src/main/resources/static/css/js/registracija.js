$(document).on("submit", "#registracija", function (event) {
    event.preventDefault();

    // preuzimamo vrednosti unete u formi
    let ime = $("#ime").val();
    let prezime = $("#prezime").val();
    let korisnickoIme = $("#korisnickoIme").val();
    //let email = $("#email").val();
    let lozinka = $("#lozinka").val();
    //let kontakt = $("#kontakt").val();
    let datum = $("#datum").val();
    let sex = $("#sex").is(":checked")?"ZENSKI":"MUSKI";


    let newEmployee = formToJson1(ime, prezime, korisnickoIme, lozinka, datum, sex);




    $.ajax({
        type: "POST",
        url: "http://localhost:8081/api/registration",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newEmployee),
        success: function (response) {
            console.log(response);

            alert("Korisnik " + response.id + " je uspešno kreiran!");
            window.location.href = "login.html";
        },
        error: function () {
            alert("Greška prilikom Registracije!");
        }
    });
});


function formToJson1(ki,loz,im,prez,pol,dR)
{
    return JSON.stringify(
        {
            "korisnickoIme":ki,
            "lozinka":loz,
            "ime":im,
            "prezime":prez,
            "sex":pol,
            "datum":dR
        }
    );
}

function formToJson2(ki,loz)
{
    return JSON.stringify(
        {
            "korisnickoIme":ki,
            "password":loz
        }
    );
}