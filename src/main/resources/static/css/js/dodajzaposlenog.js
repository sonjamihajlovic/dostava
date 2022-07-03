$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.

    $(document).on("submit", "#addFitnessCenterForm", function (event) {     // kada je submit-ovana forma za kreiranje novog zaposlenog
        event.preventDefault();                                         // sprečavamo automatsko slanje zahteva da bismo pokupili (i validirali) podatke iz forme

        // preuzimamo vrednosti unete u formi
        let ime = $("#ime").val();
        let prezime = $("#prezime").val();
        let korisnickoIme = $("#korisnickoIme").val();
        let lozinka = $("#lozinka").val();
        let pol = $("#pol").val();
        let datum = $("#datum").val();

        let noviZaposleni = {
            ime,
            prezime,
            korisnickoIme,
            lozinka,
            pol,
            datum
        }

        // ajax poziv za kreiranje novog zaposlenog na backend-u
        $.ajax({
            type: "POST",                                               // HTTP metoda je POST
            url: "http://localhost:8081//create",                 // URL na koji se šalju podaci
            dataType: "json",                                           // tip povratne vrednosti
            contentType: "application/json",                            // tip podataka koje šaljemo
            data: JSON.stringify(newFitnessCenter),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
            success: function (response) {                              // ova f-ja se izvršava posle uspešnog zahteva
                console.log(response);                                  // ispisujemo u konzoli povratnu vrednost radi provere

                alert("FitnessCenter " + response.id + " je uspešno kreiran!");
                window.location.href = "index.html";

            },
            error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
                alert("Greška prilikom dodavanja Fitnes Centra!");
            }
        });
    });

});