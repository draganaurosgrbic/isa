//autentifikacija:
const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const promenaSifre = {template: '<promenaSifre></promenaSifre>'}
const aktiviranjeNaloga = {template: '<aktiviranjeNaloga></aktiviranjeNaloga>'}

//pocetna stranica:
const superAdminHome = {template: '<superAdminHome></superAdminHome>'}
const adminHome = {template: '<adminHome></adminHome>'}
const lekarHome = {template: '<lekarHome></lekarHome>'}
const sestraHome = {template: '<sestraHome></sestraHome>'}
const pacijentHome = {template: '<pacijentHome></pacijentHome>'}

//profil:
const superAdminProfil = {template: '<superAdminProfil></superAdminProfil>'}
const adminProfil = {template: '<adminProfil></adminProfil>'}
const lekarProfil = {template: '<lekarProfil></lekarProfil>'}
const sestraProfil = {template: '<sestraProfil></sestraProfil>'}
const pacijentProfil = {template: '<pacijentProfil></pacijentProfil>'}

//kreiranje:
const dodajSuperAdmina = {template: '<dodajSuperAdmina></dodajSuperAdmina>'}
const dodajAdmina = {template: '<dodajAdmina></dodajAdmina>'}
const dodajLekara = {template: '<dodajLekara></dodajLekara>'}
const dodajPacijenta = {template: '<dodajPacijenta></dodajPacijenta>'}
const dodajDijagnozu = {template: '<dodajDijagnozu></dodajDijagnozu>'}
const dodajLek = {template: '<dodajLek></dodajLek>'}
const dodajKliniku = {template: '<dodajKliniku></dodajKliniku>'}
const dodajTipPosete = {template: '<dodajTipPosete></dodajTipPosete>'}
const dodajSalu = {template: '<dodajSalu></dodajSalu>'}
const dodajPregled = {template: '<dodajPregled></dodajPregled>'}

//pretraga:
const lekariPretraga = {template: '<lekariPretraga></lekariPretraga>'}
const sestrePretraga = {template: '<sestrePretraga></sestrePretraga>'}
const dijagnozePretraga = {template: '<dijagnozePretraga></dijagnozaPretraga>'}
const lekoviPretraga = {template: '<lekoviPretraga></lekoviPretraga>'}
const tipPosetePretraga = {template: '<tipPosetePretraga></tipPosetePretraga>'}
const salaPretraga = {template: '<salaPretraga></salaPretraga>'}

//zahtevi:
const zahtevRegistracijaObrada = {template: '<zahtevRegistracijaObrada></zahtevRegistracijaObrada>'}
const zahtevPosetaSlanje = {template: '<zahtevPosetaSlanje></zahtevPosetaSlanje>'}
const zahtevPregledObrada = {template: '<zahtevPregledObrada></zahtevPregledObrada>'}
const zahtevOperacijaObrada = {template: '<zahtevOperacijaObrada></zahtevOperacijaObrada>'}
const zahtevOdmorSlanje = {template: '<zahtevOdmorSlanje></zahtevOdmorSlanje>'}
const zahtevOdmorObrada = {template: '<zahtevOdmorObrada></zahtevOdmorObrada>'}
const izvestaj = {template: '<izvestaj></izvestaj>'}

//pacijent:
const karton = {template: '<karton></karton>'}
const bolesti = {template: '<bolesti></bolesti>'}
const termini = {template: '<termini></termini>'}
const zahtevTermini = {template: '<zahtevTermini></zahtevTermini>'}
const klinikeSlobodno = {template: '<klinikeSlobodno></klinikeSlobodno>'}
const klinikeLekari = {template: '<klinikeLekari></klinikeLekari>'}

//lekar:
const radniKalendar = {template: '<radniKalendar></radniKalendar>'}
const godisnjiOdmori = {template: '<godisnjiOdmori></godisnjiOdmori>'}
const zapocetPregled = {template: '<zapocetPregled></zapocetPregled>'}
const lekarPacijenti = {template: '<lekarPacijenti></lekarPacijenti>'}

//sestra:
const sestraPacijenti = {template: '<sestraPacijenti></sestraPacijenti>'}
const overaRecepta = {template: '<overaRecepta></overaRecepta>'}

const router = new VueRouter({
	
	mode: 'hash', 
	routes: [
		
		{path: '/', component: prijava}, 
		{path: '/registracija', component: registracija}, 
		{path: '/promenaSifre', component: promenaSifre}, 
		{path: '/aktiviranjeNaloga', component: aktiviranjeNaloga}, 

		{path: '/superAdminHome', component: superAdminHome}, 
		{path: '/adminHome', component: adminHome}, 
		{path: '/lekarHome', component: lekarHome}, 
		{path: '/sestraHome', component: sestraHome}, 
		{path: '/pacijentHome', component: pacijentHome}, 

		{path: '/superAdminProfil', component: superAdminProfil}, 
		{path: '/adminProfil', component: adminProfil}, 
		{path: '/lekarProfil', component: lekarProfil}, 
		{path: '/sestraProfil', component: sestraProfil}, 
		{path: '/pacijentProfil', component: pacijentProfil}, 

		{path: '/dodajSuperAdmina', component: dodajSuperAdmina}, 
		{path: '/dodajAdmina', component: dodajAdmina}, 
		{path: '/dodajLekara', component: dodajLekara}, 
		{path: '/dodajPacijenta', component: dodajPacijenta}, 
		{path: '/dodajDijagnozu', component: dodajDijagnozu}, 
		{path: '/dodajLek', component: dodajLek}, 
		{path: '/dodajKliniku', component: dodajKliniku}, 
		{path: '/dodajTipPosete', component: dodajTipPosete}, 
		{path: '/dodajSalu', component: dodajSalu}, 
		{path: '/dodajPregled', component: dodajPregled}, 

		{path: '/lekariPretraga', component: lekariPretraga}, 
		{path: '/sestrePretraga', component: sestrePretraga}, 
		{path: '/dijagnozePretraga', component: dijagnozePretraga}, 
		{path: '/lekoviPretraga', component: lekoviPretraga}, 
		{path: '/tipPosetePretraga', component: tipPosetePretraga},
		{path: '/salaPretraga', component: salaPretraga},
			
		{path: '/zahtevRegistracijaObrada', component: zahtevRegistracijaObrada},
		{path: '/zahtevPosetaSlanje', component: zahtevPosetaSlanje},
		{path: '/zahtevPregledObrada', component: zahtevPregledObrada},
		{path: '/zahtevOperacijaObrada', component: zahtevOperacijaObrada},
		{path: '/zahtevOdmorSlanje', component: zahtevOdmorSlanje},
		{path: '/zahtevOdmorObrada', component: zahtevOdmorObrada},
		{path: '/overaRecepta', component: overaRecepta},
		{path: '/izvestaj', component: izvestaj},
		
		{path: '/karton', component: karton},
		{path: '/bolesti', component: bolesti},
		{path: '/termini', component: termini},
		{path: '/zahtevTermini', component: zahtevTermini},
		{path: '/klinikeSlobodno', component: klinikeSlobodno},
		{path: '/klinikeLekari', component: klinikeLekari},

		{path: '/radniKalendar', component: radniKalendar},
		{path: '/godisnjiOdmori', component: godisnjiOdmori},
		{path: '/zapocetPregled', component: zapocetPregled},
		{path: '/lekarPacijenti', component: lekarPacijenti},
		
		{path: '/sestraPacijenti', component: sestraPacijenti},
		
	]
	
});

var app = new Vue({
	router: router, 
	el: '#mainDiv'
});


