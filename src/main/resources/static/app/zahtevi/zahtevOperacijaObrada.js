Vue.component("zahtevOperacijaObrada", {
	
	data: function(){
		return{
			zahtevi: {},
			zahetviBackup: {},
			zahtevSelected: {},
			selectedZahtev: false,
			salaSelected: {},
			selectedSala: false,
			lekari: {},
			selectedLekari: [],
			salePretraga: [],
			salePretragaBackup: [],
			nemaRezultata: '',
			zahtevOperacijaObrada: {
				'id': '',
				'pregled': false,
				'tipId': '',
				'salaId': '',
				'pacijentId': '',
				'lekariId': [],
				'pocetak': '',
				'kraj': '',
				'pocetakOriginalni': ''
			},
			greska: false,
			greskaDatum: '',
			greskaVreme: '',
			
			pretraga: '',
			vreme: '',
			refreshDatum: {},
		}
	}, 

	template: `
	
	<div>
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a v-if="selectedZahtev==false && selectedSala==false" class="navbar-brand" href="#/zahtevOperacijaObrada">ZAHTEVI ZA POSETU</a>
  <a v-else-if="selectedZahtev==true && selectedSala==false" class="navbar-brand" href="#/zahtevOperacijaObrada">SLOBODNE SALE {{formatiraj(zahtevSelected.datum)}}</a>
  <a v-else class="navbar-brand" href="#/zahtevOperacijaObrada">KALENDAR ZAUZETOSTI SALE</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminHome">
          <i class="fa fa-home"></i>
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
      
    <form class="form-inline my-2 my-lg-0">
      <input v-if="selectedZahtev" class="form-control mr-sm-2" type="text" v-model="vreme" placeholder="Unesite vreme" aria-label="Search">
      <input v-if="selectedZahtev"  class="form-control mr-sm-0" type="date" v-model="refreshDatum" placeholder="Odaberite datum" aria-label="Search">
	  <button v-if="selectedZahtev"  v-on:click="osveziDatum()" class="btn"><i class="fa fa-refresh fa-2x"></i></button>
      {{greskaDatum}}      
    </form>
    <form class="form-inline my-2 my-lg-0">
      <input v-if="selectedZahtev" class="form-control mr-sm-2" type="text" placeholder="Unesite naziv/broj sale" aria-label="Search" v-model="pretraga">
      <button v-if="selectedZahtev" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
    </form>
  </div>
</nav>
	<div v-if="selectedZahtev==false">
		<table class="table">
			<tr bgcolor="#f2f2f2">
				<th>Lekar</th>
				<th>Pacijent</th>
				<th>Naziv pregleda</th>
				<th>Tip pregleda</th>
				<th>Datum i vreme</th>
			</tr>
		
			<tr v-for="z in zahtevi" >
				<td> {{z.lekar}} </td>
				<td> {{z.pacijent}} </td>
				<td> {{z.naziv}} </td>
				<td v-if="z.pregled">Pregled</td>
				<td v-else>Operacija</td>
				<td> {{formatiraj(z.datum)}} </td> 
				<td v-if="zahtevi.length != 0"> <button v-on:click="selektovanZahtev(z)" class="btn"><i class="fa fa-ticket"></i>Obradi</button></td>
			</tr>
		</table>	
	</div>
	<div v-else-if="selectedZahtev && !selectedSala">
		<div class="zahteviObradaOperacijeLeviDiv">	
			<table class="table">
			<tr bgcolor="#f2f2f2">
				<th>Naziv </th>
				<th> Broj </th>
			</tr>
			
			<tr v-for="s in salePretraga">
				<td v-on:click="selectSala(s)">{{s.naziv}}</td>
				<td v-on:click="selectSala(s)">{{s.broj}}</td>
				<td><button v-on:click="rezervisi(s)" class="btn"><i class="fa fa-ticket"></i>REZERVISI</button></td></tr>
			</table>
				
			<h3>{{nemaRezultata}}</h3>
		</div>
		
		<div class="zahteviObradaOperacijeDesniDiv">
			<select v-model="selectedLekari" multiple style="min-width: 300px;">
				<option v-for="l in lekari" :value="l.id">
				{{l.ime + " " + l.prezime}}
				</option>
			</select>
		</div>
		
		<div v-if="nemaRezultata!=''">
			<a @click.prevent="prikaziSveSale()" class="navbar-brand" href="#">Prikazi sve sale</a>
			<a @click.prevent="ponistiPretragu()" class="navbar-brand" href="#">Ponisti pretragu</a>
		</div>
	
	</div>
	
	<div v-else-if="selectedSala && selectedZahtev">
		<div class="zahteviObradaOperacijeLeviDiv">	
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th>Pocetak</th>
					<th>Kraj</th>
				</tr>
					
				<tr v-for="d in salaSelected.kalendar">
					<td>{{formatiraj(d.pocetak)}}</td>
					<td>{{formatiraj(d.kraj)}}</td>
				</tr>
			</table>
		</div>
		
		<div class="zahteviObradaOperacijeDesniDiv">
			<table class="table">
				<tr><td>Prvi Slobodan Termin: <input type="text" v-model="salaSelected.prviSlobodan" disabled></td></tr>
				<tr>
					<td>Obavezni Lekari: 
					<select v-model="selectedLekari" multiple style="min-width: 300px;">
						<option v-for="l in lekari" :value="l.id">
						{{l.ime + " " + l.prezime}}
						</option>
					</select>
					</td>
				</tr>
				<tr><td><button v-on:click="rezervisi(salaSelected)" class="btn"><i class="fa fa-ticket"></i>REZERVISI</button></td></tr>
			</table>
		</div>
	</div>
		
	</div>
	`, 
	
	mounted(){
		//dobavljam zahteve za kliniku
		axios.get("/zahtevPoseta/klinika/operacije")
		.then(response => {
			this.zahtevi = response.data;
			this.zahteviBackup = response.data;
		})
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.salePretraga = response.data;
			this.salePretragaBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
	}, 

	methods: {
		//reaguje na dugme nadji Salu (prikaz zahteva posete)
		selectSala: function(s) {
			this.selectedZahtev = true;
			this.salaSelected = s;
			this.selectedSala = true;
			this.zahtevOperacijaObrada = {};
			this.zahtevOperacijaObrada.pocetakOriginalni = this.zahtevSelected.datum;
			this.selectedLekari = [];
			this.selectedLekari.push(this.zahtevSelected.idLekar);
			this.nadjiPrviSlobodan();
		},
		
		nadjiPrviSlobodan: function() {
			let data = {
				'zahtev': this.zahtevSelected,
				'salaId': this.salaSelected.id,
				'lekari': this.selectedLekari
			}
			
			axios.post("/sala/admin/prviSlobodan", data)
			.then(response => {
				this.salaSelected.prviSlobodan = response.data;
			})
			.catch(response => {
				alert("SERVER ERROR!");
				this.$router.push("/");
			});
		},
		
		selektovanZahtev: function(z) {
			// nalazi sve slobodne sale za slektovan zahtev
			this.zahtevSelected = z;
			this.selectedZahtev = true;
			this.salaSelected = {};
			this.selectedSala = false;
			this.zahtevOperacijaObrada = {};
			this.zahtevOperacijaObrada.pocetakOriginalni = this.zahtevSelected.datum;
			this.selectedLekari = [];
			this.selectedLekari.push(this.zahtevSelected.idLekar);
			this.nadjiSale();
			this.nadjiLekare();
		},
		
		ponistiPretragu : function() {
			this.nemaRezultata = '';
			this.pretraga = '';
			this.zahtevOperacijaObrada = {};
			this.zahtevOperacijaObrada.pocetakOriginalni = this.zahtevSelected.datum;
			this.nadjiSale();
		},
		
		prikaziSveSale : function() {
			this.salePretraga = this.salePretragaBackup;
			this.zahtevOperacijaObrada = {};
			this.zahtevOperacijaObrada.pocetakOriginalni = this.zahtevSelected.datum;
			this.nemaRezultata = '';
		},
		
		//pretraga ime ILI broj
		search: function() {
			console.log("pretraga");
			this.salaSelected = {};
			this.selectedSala = false;
			
			this.salePretraga = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let s of this.salePretragaBackup){
            	let passedNaziv = (this.pretraga != '') ? (s.naziv.toLowerCase().includes(lowerPretraga)) : true;
                let passedBroj = (this.pretraga != '') ? (s.broj.toLowerCase().includes(lowerPretraga)) : true;                    
                if (passedNaziv || passedBroj) this.salePretraga.push(s);
            }
            
            if (this.salePretraga.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage! Pokusajte ponovo, ili: ";
            }
		},
		
		//kad klikne na dugme refresh, treba da nadje slobodne sale za taj datum
		osveziDatum : function() {
			this.vremePromena();
			this.osvezi();
			this.proveriVreme();
			if (this.refreshDatum=='') {
				this.greskaDatum = 'Morate uneti datum';
				this.greska = true;
			}
			
			if (new Date(this.datum) <= new Date()){
				this.greskaDatum = "Datum ne sme biti manji od trenutnog.";
				this.greska = true;
			}
			
			if (this.greska) {return;}
			this.zahtevSelected.datum = this.refreshDatum.concat(" ", this.vreme);
			console.log(this.zahtevSelected.datum);
			this.nadjiSale();
			this.nadjiLekare();
			
			if (this.selectedSala)
				this.nadjiPrviSlobodan();
		},

		//formatiranje vremena, moze da unese 0, 7, 12 pa ja sredim
		vremePromena: function() {
			if (!this.vreme.includes(':') && (this.vreme.length==2)) {
				this.vreme = this.vreme.concat(":00"); 
			}
			if (!this.vreme.includes(':') && (this.vreme.length==1)) {
				this.vreme = '0'.concat(this.vreme,":00"); 
			}
			if (this.vreme.includes(':') && (this.vreme.length==5)) {
				this.vreme=this.vreme;
			}
			if (this.vreme.includes(':') && (this.vreme.length==4)) {
				this.vreme='0'.concat(this.vreme);
			}
			return this.vreme; 
		},

		osvezi: function(){
			this.zahtevOperacijaObrada = {};
			this.zahtevOperacijaObrada.pocetakOriginalni = this.zahtevSelected.datum;
			console.log(this.zahtevSelected.datum);
			this.selectedLekari = [];
			this.selectedLekari.push(this.zahtevSelected.idLekar);
			this.greskaDatum = "";
			this.greskaVreme = "";
			this.greska = false;
		},
		
		//provera ispravnosti unetog vremena od strane korisnika
		proveriVreme :function() {
			if (this.vreme == '') {
				this.greskaVreme = "Morate uneti vreme.";
				this.greska = true;
			}
			
			if (!this.vreme.includes(':') && ((this.vreme.length>2 || this.vreme.length<1) || parseInt(this.vreme)>25)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			if (this.vreme.includes(':') && (this.vreme.length != 5)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
		},
		
		//pronalazi sale za novi datum kod pretrage, nakon refresh klika
		//salje post zahtev
		nadjiSale : function() {
			console.log(this.zahtevSelected.datum);
			axios.post("/sala/admin/slobodni", this.zahtevSelected)
			.then(response => {
				this.salePretraga = response.data;
				this.salePretragaBackup = response.data;
				if (this.salePretraga.length===0) {
					this.nemaRezultata = "Nema rezultata pretrage! Pokusajte ponovo, ili: ";
				}
				else {
					this.nemaRezultata = '';
				}
			})
			.catch(response => {
				this.$router.push("/");
			});
		},

		nadjiLekare : function() {
			axios.post("/lekar/admin/slobodni", this.zahtevSelected)
			.then(response => {
				this.lekari = response.data;
			})
			.catch(response => {
				this.$router.push("/");
			});
		},
		
		//salje zahtev za registraciju
		rezervisi: function(s) {
			if (this.selectedLekari.length === 0) {
				alert("Morate odabrati bar jednog lekara!");
				return;
			}
				
			this.zahtevSelected.idSale = s.id;

			this.zahtevOperacijaObrada.id = this.zahtevSelected.id;
			this.zahtevOperacijaObrada.pregled = this.zahtevSelected.pregled;
			this.zahtevOperacijaObrada.tipId = this.zahtevSelected.idTipa;
			this.zahtevOperacijaObrada.salaId = this.zahtevSelected.idSale;
			this.zahtevOperacijaObrada.pacijentId = this.zahtevSelected.idPacijent;
			this.zahtevOperacijaObrada.pocetak = this.zahtevSelected.datum;
			this.zahtevOperacijaObrada.kraj = this.zahtevSelected.kraj;
			this.zahtevOperacijaObrada.lekariId = this.selectedLekari;
						
			axios.post("/sala/admin/operacija/rezervacijaSale", this.zahtevOperacijaObrada)
			.then(response => {
				alert("Uspesno rezervisano!");
				this.$router.push("/adminHome");
			})
			.catch(response => {
				alert("Sala ili lekar su zauzeti!");				
				this.selectSala(s);
			});
		},
		
		formatiraj: function (date) {
			date = new Date(date);
			let year = date.getFullYear();
			let month = (1 + date.getMonth()).toString();
			month = month.length > 1 ? month : '0' + month;
			let day = date.getDate().toString();
			day = day.length > 1 ? day : '0' + day;
			let hours = date.getHours().toString();
			hours = hours.length > 1 ? hours : '0' + hours;
			let minutes = date.getMinutes().toString();
			minutes = minutes.length > 1 ? minutes : '0' + minutes;
			return day + '/' + month + '/' + year + " " + hours + ":" + minutes;
		}
	}
		
});