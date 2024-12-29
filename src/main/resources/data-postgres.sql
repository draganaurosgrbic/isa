----------DIJAGNOZE----------
insert into dijagnoza (sifra, naziv)
values ('D1', 'Votum seoaratum');
insert into dijagnoza (sifra, naziv)
values ('D2', 'Tuo te pede metire');
insert into dijagnoza (sifra, naziv)
values ('D3', 'Terminus praefixus');
insert into dijagnoza (sifra, naziv)
values ('D4', 'Superflua non nocent');
insert into dijagnoza (sifra, naziv)
values ('D5', 'Superflua non nocent');
insert into dijagnoza (sifra, naziv)
values ('D6', 'Sensu stricto');
insert into dijagnoza (sifra, naziv)
values ('D7', 'Res imobilis');
insert into dijagnoza (sifra, naziv)
values ('D8', 'Ratio decinedi');
insert into dijagnoza (sifra, naziv)
values ('D9', 'Pro re nata');
insert into dijagnoza  (sifra, naziv)
values ('D10', 'Primus inter pares');

----------LEKOVI----------
insert into lek (sifra, naziv)
values ('L1', 'Damnum irreparabile');
insert into lek (sifra, naziv)
values ('L2', 'E pluribus unum');
insert into lek (sifra, naziv)
values ('L3', 'Exceptio afirmat regulam');
insert into lek (sifra, naziv)
values ('L4', 'Experientia docet');
insert into lek (sifra, naziv)
values ('L5', 'Ferae inter se placidae sunt');
insert into lek (sifra, naziv)
values ('L6', 'Quotidiana vilescunt');
insert into lek (sifra, naziv)
values ('L7', 'Ex oriente lux');
insert into lek (sifra, naziv)
values ('L8', 'Capitis deminutio maxima');
insert into lek (sifra, naziv)
values ('L9', 'Locum tenens');
insert into lek (sifra, naziv)
values ('L10', 'Mandatum sine clausula');

----------KLINIKE----------
insert into klinika (naziv, opis, adresa)
values ('Nikad nije kasno!', 'U nasoj klinici lecimo sve slucajeve koji misle da su izgubljeni. Kod nas cete se osecati kao novi. Dodjite kod nas! Resicemo sve Vase probleme koje imate, i zdravstvene, a i one ostale. Cekamo Vas nestrpljivo!', 'Lasla Gala 23, Novi Sad');
insert into klinika (naziv, opis, adresa)
values ('I kad pomislis da je kraj, ipak nije!', 'Nemojte mislite da Vam je ostalo malo vremena. Kod nas to ne postoji. Dodjite kod nas! Popusti koje delimo, povoljni pregledi koje nudimo, nema takvih nigde drugde! Sve sto Vama treba je novac, a mi cemo ostalo resiti. Cekamo Vas!', 'Vase Stajica 3, Novi Sad');
insert into klinika (naziv, opis, adresa)
values ('Za sitnu raju', 'Nemate para, a zelite da zivite? Mi smo resenje za Vas! Kod nas mozete naci odgovarajuce lecenje za minimalan iznos novac, a vrhunski kvalitet. Za samo 1000 evra dobicete detaljan pregled celokupnog Vaseg zdravstvenog stanja. Cekamo na Vas!', 'Tolstojeva 13, Novi Sad');
insert into klinika (naziv, opis, adresa)
values ('Za one sto brzo stare', 'Primecujete da ste ostarili brze od svojih roditelja? Mi smo resenje za Vas! Analiziramo celokupno zdravstveno stanje i dajemo odgovore na sva pitanja. Dodjite kod nas! Cekamo Vas, ionako nemamo nikoga da lecimo trenutno.', 'Fruskogorska 13, Novi Sad');

----------TIPOVI POSETA PRVE KLINIKE----------
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'kompletan pregled', 1, 30, 2000, 1, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'pregled fizicke izdrzljivosti', 2, 0, 3000, 1, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija stitne zlezde', 4, 0, 10000, 1, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija slepog creva', 3, 30, 8000, 1, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija zuci', 2, 30, 6000, 1, true, 0);

----------TIPOVI POSETA DRUGE KLINIKE----------
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'kompletan pregled', 1, 45, 2500, 2, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'pregled psihickog stanja', 1, 45, 3500, 2, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija stitne zlezde', 3, 20, 9000, 2, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija slepog creva', 2, 50, 8500, 2, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija zuci', 2, 45, 4000, 2, true, 0);

----------TIPOVI POSETA TRECE KLINIKE----------
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'kompletan pregled', 2, 15, 3000, 3, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'pregled fizicke izdrzljivosti', 2, 15, 3500, 3, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija stitne zlezde', 4, 15, 12000, 3, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija slepog creva', 2, 55, 6000, 3, true, 0);

----------TIPOVI POSETA CETVRTE KLINIKE----------
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'pregled psihickog stanja', 2, 15, 3000, 4, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (true, 'pregled fizicke izdrzljivosti', 3, 25, 2500, 4, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija stitne zlezde', 2, 45, 11000, 4, true, 0);
insert into tip_posete (pregled, naziv, sati, minute, cena, klinika, aktivan, version)
values (false, 'operacija slepog creva', 3, 10, 5000, 4, true, 0);

----------SALE PRVE KLINIKE----------
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S11', 'Glavna sala', 1, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S12', 'Srednja sala', 1, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S13', 'Mala sala', 1, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S14', 'Pomocna sala', 1, true, 0);

----------SALE DRUGE KLINIKE----------
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S21', 'Velika sala', 2, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S22', 'Rezervna sala', 2, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S23', 'Mala sala', 2, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S24', 'Sala u podrumu', 2, true, 0);

----------SALE TRECE KLINIKE----------
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S31', 'Glavna sala', 3, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S32', 'Mala sala', 3, true, 0);

----------SALE CETVRTE KLINIKE----------
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S41', 'Najveca sala', 4, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S42', 'Glavna sala', 4, true, 0);
insert into sala (broj, naziv, klinika, aktivan, version)
values ('S43', 'Najmanja sala', 4, true, 0);

----------PACIJENTI----------
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version)
values ('pacijent', 'draganaasd@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Dragana', 'Grbic', '0649604001', 'Srbija', 'Novi Sad', 'Lasla Gala 23', true, true, 0);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version)
values ('pacijent', 'pacijent1@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Ognjen', 'Markovic', '0640043024', 'Srbija', 'Novi Sad', 'Cara Lazara 12', true, true, 0);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version)
values ('pacijent', 'pacijent2@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Nada', 'Ivkovic', '0643101459', 'Srbija', 'Novi Sad', 'Vojvodjanskih brigada 10', true, true, 0);

----------KARTONI----------
insert into karton (broj_osiguranika, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa, pacijent)
values ('1029384756', 180, 65, 0, 0, 6, 1);
update korisnik set karton = 1 where id = 1;
insert into karton (broj_osiguranika, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa, pacijent)
values ('1357924680', 170, 55, 0.2, -3, 2, 2);
update korisnik set karton = 2 where id = 2;
insert into karton (broj_osiguranika, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa, pacijent)
values ('7701928599', 150, 50, 3, -3, 4, 3);
update korisnik set karton = 3 where id = 3;

----------SUPER ADMINI----------
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version)
values ('super', 'dragana.grbic.98@uns.ac.rs', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Petar', 'Nikolic', '0628847810', 'Srbija', 'Novi Sad', 'Patrijarha Pavla 7', true, true, 0);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version)
values ('super', 'superadmin@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Nektarije', 'Valjevski', '0648804045','Srbija', 'Novi Sad', 'Zeleznicka 13', true, true, 0);

----------ADMINI----------
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika)
values ('admin', 'malinavojvodic123@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Milica', 'Poparic', '0611178980', 'Srbija', 'Novi Sad', 'Maksima Gorkog 62', true, true, 0, 1);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika)
values ('admin', 'poparic.sw21.2017@uns.ac.rs', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Djole', 'Vlajkovic', '0607789123', 'Srbija', 'Novi Sad', 'Kisacka 25', true, true, 0, 2);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika)
values ('admin', 'covekimastan@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Marina', 'Blekarski', '0659980403', 'Srbija', 'Novi Sad', 'Temerinski put 17', true, true, 0, 3);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika)
values ('admin', 'admin@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Ivan', 'Gasparovski', '0613345699', 'Srbija', 'Novi Sad', 'Radnicka 19A', true, true, 0, 4);

----------LEKARI----------
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'nasmejlservis@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Ivona', 'Miljakovic', '0645576987', 'Srbija', 'Novi Sad', 'Puskinova 21', true, true, 0, 1, '2020-04-01 10:00', '2020-04-01 20:00', 1);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'nikolicpetar91@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Zoran', 'Ivkovic', '0617784543', 'Srbija', 'Novi Sad', 'Gogoljeva 15', true, true, 0, 1, '2020-04-01 10:00', '2020-04-01 18:00', 1);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'nikolicpetar91@yahoo.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Mirko', 'Pavlovic', '0654464576', 'Srbija', 'Novi Sad', 'Zmaj Jovina 10', true, true, 0, 1, '2020-04-01 08:00', '2020-04-01 16:00', 2);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'nikolic.sw31.2017@uns.ac.rs', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Uros', 'Kralj', '0636675600', 'Srbija', 'Novi Sad', 'Kralja Petra 38', true, true, 0, 2, '2020-04-01 08:00', '2020-04-01 14:00', 6);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar1@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Milena', 'Parcevic', '0697785566', 'Srbija', 'Novi Sad', 'Narodnog Fronta 13', true, true, 0, 2, '2020-04-01 08:00', '2020-04-01 20:00', 7);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar2@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Teodor', 'Sofkovski', '0661234546', 'Srbija', 'Novi Sad', 'Trg Dositeja 7', true, true, 0, 2, '2020-04-01 08:00', '2020-04-01 17:00', 7);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar3@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Filip', 'Valjevic', '0600099897', 'Srbija', 'Novi Sad', 'Alekse Santica 28', true, true, 0, 3, '2020-04-01 10:00', '2020-04-01 18:00', 11);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar4@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Ilija', 'Hijevljanin', '0656656677', 'Srbija', 'Novi Sad', 'Brace Ribnikara 19', true, true, 0, 3, '2020-04-01 10:00', '2020-04-01 16:00', 11);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar5@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Mihajlo', 'Tatarkovski', '0697787700', 'Srbija', 'Novi Sad', 'Cara Dusana 16', true, true, 0, 3, '2020-04-01 10:00', '2020-04-01 14:00', 12);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar6@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Goran', 'Curovski', '0624457878', 'Srbija', 'Novi Sad', 'Bulevar Oslobodjenja 35', true, true, 0, 4, '2020-04-01 10:00', '2020-04-01 22:00', 15);
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme, specijalizacija)
values ('lekar', 'lekar7@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Marta', 'Oljavljovic', '0666065566', 'Srbija', 'Novi Sad', 'Dimitrija Avramovica 24', true, true, 0, 4, '2020-04-01 10:00', '2020-04-01 14:00', 16);

----------SESTRE----------
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme)
values ('sestra', 'isanalog1@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Ljubica', 'Hrkavski', '0655587070', 'Srbija', 'Novi Sad', 'Papa Pavla 46', true, true, 0, 1, '2020-04-01 10:00', '2020-04-01 22:00');
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme)
values ('sestra', 'sestra1@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Marica', 'Dundarovski', '0666666767', 'Srbija', 'Novi Sad', 'Rumenacka 159', true, true, 0, 2, '2020-04-01 08:00', '2020-04-01 22:00');
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme)
values ('sestra', 'sestra2@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Dusanka', 'Gavrilovic', '0614567788', 'Srbija', 'Novi Sad', 'Cenejska 52', true, true, 0, 3, '2020-04-01 06:00', '2020-04-01 22:00');
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra, version, klinika, pocetno_vreme, krajnje_vreme)
values ('sestra', 'sestra3@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'Jagoda', 'Jabukovic', '0605567879', 'Srbija', 'Novi Sad', 'Bulevar Kralja Petra 11', true, true, 0, 4, '2020-04-01 06:00', '2020-04-01 18:00');

----------ISTORIJA BOLESTI PRVOG PACIJENTA----------
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-01 10:00', 3, 1, 1, 1, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-05 12:00', 3, 1, 2, 2, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-07 16:00', 3, 1, 6, 5, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-11 13:00', 3, 1, 7, 6, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-12 14:00', 3, 1, 18, 13, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-13 15:00', 3, 1, 17, 12, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-04-15 16:00', 3, 1, 18, 13, null, 0);
------------------------------------------------------------
insert into lekar_poseta (lekar, poseta)
values (10, 1);
insert into lekar_poseta (lekar, poseta)
values (11, 2);
insert into lekar_poseta (lekar, poseta)
values (13, 3);
insert into lekar_poseta (lekar, poseta)
values (14, 4);
insert into lekar_poseta (lekar, poseta)
values (19, 5);
insert into lekar_poseta (lekar, poseta)
values (20, 5);
insert into lekar_poseta (lekar, poseta)
values (19, 6);
insert into lekar_poseta (lekar, poseta)
values (20, 6);
insert into lekar_poseta (lekar, poseta)
values (19, 7);
insert into lekar_poseta (lekar, poseta)
values (20, 7);
------------------------------------------------------------
insert into izvestaj (poseta, opis)
values (1, 'Pacijent ima bolove u zelucu, pojacanu kiselinu, osecaj mucnine. Ima problema sa disanjem, glavobolje, nesanice. Neophodna su dodatna ispitivanja. ');
insert into izvestaj (poseta, opis)
values (2, 'Pacijent pati od nesanice, zaboravlja, oseca se malaksalo. Smanjen mu je apetit, zali se na kostobolju. Dati saveti i propisana odgovarajuca terapija. Predlozena dodatna ispitivanja. ');
insert into izvestaj (poseta, opis)
values (3, 'Pacijent je u poslednjih mesec dana primetio da pocinje da sve vise zaboravlja, ima velike glavobolje. Pacijent se oseca malaksalo, sklon nesvesticama. Dati saveti i propisana dijagnoza. ');
insert into izvestaj (poseta, opis)
values (4, 'Pacijent pati od depresije, plasi se za svoje psihicko zdravlje. Sklon agresivnom ponasanju i anskioznosti. Neophodna dodatna ispitivanja. ');
insert into izvestaj (poseta, opis)
values (5, 'Operacija prosla bez poteskoca, pacijent je stabilan. Dati su saveti i propisana odgovarajuca terapija. ');
insert into izvestaj (poseta, opis)
values (6, 'Operacija uspesno obavljena, pacijent se oseca bolje, zdravstveno stanje mu je poboljsano. Savetovan za dodatne preglede i kontrolu. ');
insert into izvestaj (poseta, opis)
values (7, 'Pacijent operisan, bez poteskoca. Dati saveti.');
------------------------------------------------------------
update poseta set izvestaj = 1 where id = 1;
update poseta set izvestaj = 2 where id = 2;
update poseta set izvestaj = 3 where id = 3;
update poseta set izvestaj = 4 where id = 4;
update poseta set izvestaj = 5 where id = 5;
update poseta set izvestaj = 6 where id = 6;
update poseta set izvestaj = 7 where id = 7;
------------------------------------------------------------
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 2);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 3);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (2, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (2, 5);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (3, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (3, 6);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 7);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 8);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (5, 9);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (6, 10);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (7, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (7, 2);
------------------------------------------------------------
insert into terapija (izvestaj, sestra, version)
values (1, 21, 0);
insert into terapija (izvestaj, sestra, version)
values (2, 21, 0);
insert into terapija (izvestaj, sestra, version)
values (3, 22, 0);
insert into terapija (izvestaj, sestra, version)
values (4, 22, 0);
insert into terapija (izvestaj, sestra, version)
values (5, 24, 0);
insert into terapija (izvestaj, sestra, version)
values (6, null, 0);
insert into terapija (izvestaj, sestra, version)
values (7, null, 0);
------------------------------------------------------------
insert into terapija_lek (terapija, lek)
values (1, 1);
insert into terapija_lek (terapija, lek)
values (1, 2);
insert into terapija_lek (terapija, lek)
values (1, 3);
insert into terapija_lek (terapija, lek)
values (2, 4);
insert into terapija_lek (terapija, lek)
values (2, 5);
insert into terapija_lek (terapija, lek)
values (3, 6);
insert into terapija_lek (terapija, lek)
values (4, 7);
insert into terapija_lek (terapija, lek)
values (5, 8);
insert into terapija_lek (terapija, lek)
values (5, 9);
insert into terapija_lek (terapija, lek)
values (5, 10);
insert into terapija_lek (terapija, lek)
values (6, 2);
insert into terapija_lek (terapija, lek)
values (6, 3);
insert into terapija_lek (terapija, lek)
values (7, 9);
------------------------------------------------------------
update izvestaj set terapija = 1 where id = 1;
update izvestaj set terapija = 2 where id = 2;
update izvestaj set terapija = 3 where id = 3;
update izvestaj set terapija = 4 where id = 4;
update izvestaj set terapija = 5 where id = 5;
update izvestaj set terapija = 6 where id = 6;
update izvestaj set terapija = 7 where id = 7;

----------ISTORIJA BOLESTI DRUGOG PACIJENTA----------
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-01 10:00', 3, 2, 1, 1, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-05 12:00', 3, 2, 2, 2, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-07 16:00', 3, 2, 6, 5, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-11 13:00', 3, 2, 7, 6, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-12 14:00', 3, 2, 18, 13, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-13 15:00', 3, 2, 17, 12, null, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-03-15 16:00', 3, 2, 18, 13, null, 0);
------------------------------------------------------------
insert into lekar_poseta (lekar, poseta)
values (10, 8);
insert into lekar_poseta (lekar, poseta)
values (11, 9);
insert into lekar_poseta (lekar, poseta)
values (13, 10);
insert into lekar_poseta (lekar, poseta)
values (14, 11);
insert into lekar_poseta (lekar, poseta)
values (19, 12);
insert into lekar_poseta (lekar, poseta)
values (20, 12);
insert into lekar_poseta (lekar, poseta)
values (19, 13);
insert into lekar_poseta (lekar, poseta)
values (20, 13);
insert into lekar_poseta (lekar, poseta)
values (19, 14);
insert into lekar_poseta (lekar, poseta)
values (20, 14);
------------------------------------------------------------
insert into izvestaj (poseta, opis)
values (8, 'Pacijent ima bolove u zelucu, pojacanu kiselinu, osecaj mucnine. Ima problema sa disanjem, glavobolje, nesanice. Neophodna su dodatna ispitivanja. ');
insert into izvestaj (poseta, opis)
values (9, 'Pacijent pati od nesanice, zaboravlja, oseca se malaksalo. Smanjen mu je apetit, zali se na kostobolju. Dati saveti i propisana odgovarajuca terapija. Predlozena dodatna ispitivanja. ');
insert into izvestaj (poseta, opis)
values (10, 'Pacijent je u poslednjih mesec dana primetio da pocinje da sve vise zaboravlja, ima velike glavobolje. Pacijent se oseca malaksalo, sklon nesvesticama. Dati saveti i propisana dijagnoza. ');
insert into izvestaj (poseta, opis)
values (11, 'Pacijent pati od depresije, plasi se za svoje psihicko zdravlje. Sklon agresivnom ponasanju i anskioznosti. Neophodna dodatna ispitivanja. ');
insert into izvestaj (poseta, opis)
values (12, 'Operacija prosla bez poteskoca, pacijent je stabilan. Dati su saveti i propisana odgovarajuca terapija. ');
insert into izvestaj (poseta, opis)
values (13, 'Operacija uspesno obavljena, pacijent se oseca bolje, zdravstveno stanje mu je poboljsano. Savetovan za dodatne preglede i kontrolu. ');
insert into izvestaj (poseta, opis)
values (14, 'Pacijent operisan, bez poteskoca. Dati saveti.');
------------------------------------------------------------
update poseta set izvestaj = 8 where id = 8;
update poseta set izvestaj = 9 where id = 9;
update poseta set izvestaj = 10 where id = 10;
update poseta set izvestaj = 11 where id = 11;
update poseta set izvestaj = 12 where id = 12;
update poseta set izvestaj = 13 where id = 13;
update poseta set izvestaj = 14 where id = 14;
------------------------------------------------------------
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (8, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (8, 2);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (8, 3);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (9, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (9, 5);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (10, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (10, 6);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (11, 7);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (11, 8);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (12, 9);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (13, 10);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (14, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (14, 2);
------------------------------------------------------------
insert into terapija (izvestaj, sestra, version)
values (8, 21, 0);
insert into terapija (izvestaj, sestra, version)
values (9, 21, 0);
insert into terapija (izvestaj, sestra, version)
values (10, 22, 0);
insert into terapija (izvestaj, sestra, version)
values (11, 22, 0);
insert into terapija (izvestaj, sestra, version)
values (12, 24, 0);
insert into terapija (izvestaj, sestra, version)
values (13, null, 0);
insert into terapija (izvestaj, sestra, version)
values (14, null, 0);
------------------------------------------------------------
insert into terapija_lek (terapija, lek)
values (8, 1);
insert into terapija_lek (terapija, lek)
values (8, 2);
insert into terapija_lek (terapija, lek)
values (8, 3);
insert into terapija_lek (terapija, lek)
values (9, 4);
insert into terapija_lek (terapija, lek)
values (9, 5);
insert into terapija_lek (terapija, lek)
values (10, 6);
insert into terapija_lek (terapija, lek)
values (11, 7);
insert into terapija_lek (terapija, lek)
values (12, 8);
insert into terapija_lek (terapija, lek)
values (12, 9);
insert into terapija_lek (terapija, lek)
values (12, 10);
insert into terapija_lek (terapija, lek)
values (13, 2);
insert into terapija_lek (terapija, lek)
values (13, 3);
insert into terapija_lek (terapija, lek)
values (14, 9);
------------------------------------------------------------
update izvestaj set terapija = 8 where id = 8;
update izvestaj set terapija = 9 where id = 9;
update izvestaj set terapija = 10 where id = 10;
update izvestaj set terapija = 11 where id = 11;
update izvestaj set terapija = 12 where id = 12;
update izvestaj set terapija = 13 where id = 13;
update izvestaj set terapija = 14 where id = 14;

----------SLOBODNI TERMINI----------
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-01 10:00', 0, null, 1, 1, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-02 12:00', 0, null, 1, 2, 0.3, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-03 14:00', 0, null, 2, 2, 0.1, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-04 14:00', 0, null, 2, 3, 0.4, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-05 15:00', 0, null, 6, 5, 0.1, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-06 16:00', 0, null, 7, 6, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-07 16:00', 0, null, 11, 9, 0.5, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-08 16:00', 0, null, 12, 10, 0.4, 0);
------------------------------------------------------------
insert into lekar_poseta (lekar, poseta)
values (10, 15);
insert into lekar_poseta (lekar, poseta)
values (11, 16);
insert into lekar_poseta (lekar, poseta)
values (12, 17);
insert into lekar_poseta (lekar, poseta)
values (10, 18);
insert into lekar_poseta (lekar, poseta)
values (13, 19);
insert into lekar_poseta (lekar, poseta)
values (13, 20);
insert into lekar_poseta (lekar, poseta)
values (16, 21);
insert into lekar_poseta (lekar, poseta)
values (17, 22);

----------RADNI KALENDAR----------
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-05-21 03:30', 1, 1, 1, 1, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-11 11:00', 1, 1, 2, 2, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-12 12:00', 1, 2, 2, 1, 0.2, 0);
insert into poseta (datum, stanje, karton, tip_posete, sala, popust, version)
values ('2020-07-13 13:00', 1, 3, 3, 2, 0.2, 0);

------------------------------------------------------------
insert into lekar_poseta (lekar, poseta)
values (10, 23);
insert into lekar_poseta (lekar, poseta)
values (10, 24);
insert into lekar_poseta (lekar, poseta)
values (10, 25);
insert into lekar_poseta (lekar, poseta)
values (10, 26);

----------ZAHTEVI ZA POSETU----------
insert into zahtev_poseta(datum, karton, lekar, tip_posete, klinika)
values ('2020-09-10 10:00', 1, 10, 1, 1);
insert into zahtev_poseta(datum, karton, lekar, tip_posete, klinika)
values ('2020-09-10 10:00', 2, 11, 1, 1);


