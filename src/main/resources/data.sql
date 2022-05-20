INSERT INTO TIP_KUPCA(ime, procenat) VALUES ('ZLATNI', 15);
INSERT INTO TIP_KUPCA(ime, procenat) VALUES ('SREBRNI', 10);
INSERT INTO TIP_KUPCA(ime, procenat) VALUES ('BRONZANI', 5);
INSERT INTO TIP_KUPCA(ime, procenat) VALUES ('REGULARNI', 0);

INSERT INTO LOKACIJA(adresa, geografska_duzina, geografska_sirina) VALUES ('Djure Jaksica 45', 56, 78);
INSERT INTO LOKACIJA(adresa, geografska_duzina, geografska_sirina) VALUES ('Mite Mitica 145', 58, 79);
INSERT INTO LOKACIJA(adresa, geografska_duzina, geografska_sirina) VALUES ('Kosovska 2', 50, 80);
INSERT INTO LOKACIJA(adresa, geografska_duzina, geografska_sirina) VALUES ('Doza Djerdja 24', 44, 30);
INSERT INTO LOKACIJA(adresa, geografska_duzina, geografska_sirina) VALUES ('Marka Miljanova 3', 60, 80);

INSERT INTO RESTORAN(naziv, tip_restorana, lokacija_id) VALUES ('Riblji svet', 'GRCKI', 1);
INSERT INTO RESTORAN(naziv, tip_restorana, lokacija_id) VALUES ('Italiano', 'ITALIJANSKI', 2);
INSERT INTO RESTORAN(naziv, tip_restorana, lokacija_id) VALUES ('Tortilja', 'MEKSICKI', 3);
INSERT INTO RESTORAN(naziv, tip_restorana, lokacija_id) VALUES ('Jing Jang', 'KINESKI', 4);
INSERT INTO RESTORAN(naziv, tip_restorana, lokacija_id) VALUES ('Sarmica', 'SRPSKI', 5);

INSERT INTO KORISNIK (aktivan, dtype, korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga, tipKupca_id) VALUES (true,'Kupac','tica', '123123', 'Tijana', 'Varadjanin', 'ZENSKI', '2001-12-18', 'KUPAC',1);
INSERT INTO KORISNIK (aktivan, dtype, korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga) VALUES (true,'Dostavljac', 'pera', '567', 'Petar', 'Petrovic', 'MUSKI', '1999-11-01', 'DOSTAVLJAC');
INSERT INTO KORISNIK (aktivan, dtype, korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga, restoran_id) VALUES (true,'Menadzer', 'luka', 'm222', 'Luka', 'Lukovic', 'MUSKI', '1994-02-03', 'MENADZER', 1);
INSERT INTO KORISNIK (aktivan, dtype, korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga, tipKupca_id) VALUES (true,'Kupac', 'sofi', '2233', 'Sofija', 'Mitic', 'ZENSKI', '1998-09-03', 'KUPAC', 2);
//INSERT INTO KORISNIK (aktivan, dtype, korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga, restoran_id) VALUES (false,'Menadzer', 'miki', 'gggg', 'Mirko', 'Mirkic', 'MUSKI', '2000-04-03', 'MENADZER', 4);
INSERT INTO KORISNIK (aktivan, dtype, korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga) VALUES (false,'Admin','ivka', '456cao', 'Ivana', 'Mirkovic', 'ZENSKI', '1987-10-18', 'ADMIN');

INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Pasta sa 4 vrste sira', '600', 'JELO', '250', 'Originalna italijanska pasta!');
INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Marinirana bela riba', '850', 'JELO', '200', 'Uz prilog-bareni krompir i salatu!');
INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Sangria', '200', 'PICE', '0.2', 'Italijanska sangria-Sangria Verde!');
INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Tortilja piletina', '350', 'JELO', '280', 'Meksicka tortilja br 1!');
INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Fanta', '150', 'PICE', '0.3', 'Fanta sokata');
INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Sarma', '440', 'JELO', '300', 'Sarmica sarma');
INSERT INTO ARTIKAL (naziv, cena, tip, kolicina, opis) VALUES ('Piletina s kikirikijem', '700', 'JELO', '450', 'Original iz Kine');

INSERT INTO KOMENTAR(ocena, text, kupac_id, restoran_id) VALUES ('5', 'Odlicna hrana i usluga!', 1, 2);
INSERT INTO KOMENTAR(ocena, text, kupac_id, restoran_id) VALUES ('1', 'Izuzetno losa usluga, neljubazno osoblje.', 2, 4);
INSERT INTO KOMENTAR(ocena, text, kupac_id, restoran_id) VALUES ('3', 'Solidno ali nista specijalno.', 1, 3);

INSERT INTO PORUDZBINA (uuid, cena, status, vreme_porudzbine, restoran_id, kupac_id) VALUES ('b5acb2a2509a4a86a28d388b9a385839','800', 'CEKA_DOSTAVLJACA','2022-02-02 15:05:00.000000', 2, 1);
INSERT INTO PORUDZBINA (uuid, cena, status, vreme_porudzbine, restoran_id, kupac_id) VALUES('d5acb2a2509a4a86a28d388b9a385839','500', 'DOSTAVLJENA','2022-03-02 18:05:00.000000', 3, 1);
INSERT INTO PORUDZBINA (uuid, cena, status, vreme_porudzbine, restoran_id, kupac_id) VALUES('b4acb2a2509a4a86a28d388b9a385839','850', 'U_PRIPREMI','2022-08-02 20:05:00.000000', 1, 1);
INSERT INTO PORUDZBINA (uuid, cena, status, vreme_porudzbine, restoran_id, kupac_id) VALUES('d6acb2a2509a4a86a28d388b9a385839', '590', 'U_PRIPREMI','2022-01-02 10:05:00.000000', 5, 1);
INSERT INTO PORUDZBINA (uuid, cena, status, vreme_porudzbine, restoran_id, kupac_id) VALUES('d9acb2a2509a4a86a28d388b9a385839', '700', 'U_TRANSPORTU','2021-08-02 20:05:00.000000', 4, 2);

INSERT INTO STAVKA_PORUDZBINE (kolicina, artikal_id,porudzbina_id) VALUES(5,  1, 'd6acb2a2509a4a86a28d388b9a385839');
INSERT INTO STAVKA_PORUDZBINE (kolicina, artikal_id,porudzbina_id) VALUES(5,  1, 'd9acb2a2509a4a86a28d388b9a385839');
INSERT INTO STAVKA_PORUDZBINE (kolicina, artikal_id,porudzbina_id) VALUES(5,  1, 'b4acb2a2509a4a86a28d388b9a385839');
