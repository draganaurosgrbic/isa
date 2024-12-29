Vue.component("zahtevPregledObrada", {
	
	data: function(){
		return{
			sale: {},
			lekari: {},
			sveSale: {},
			backupSale: {},
			zahtevi: {},
			backup: {},
			zahtevSelected: {},
			salaSelected: {},
			showModal: false,
			lekarIzmena: false,
			promenjenDatum: false,
			selectedSala: false,
			selectedZahtev: false,
			trebaSlobodan: false,
			pretraga: '',
			datum: '',
			vreme: '',
			refreshDatum: '',
			slobodan: {},
			greska: false,
			greskaDatum: '',
			greskaVreme: '',
			noviLekar: '',
			kalendarZauzetosti: {},
			nemaRezultata: '',
			novLekarIme: '',
			novLekarId: '',
			novLekarPocetak: '',
			novLekarKraj: ''
		}
	}, 

	template: `
	
	<div>
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a v-if="selectedZahtev==false && selectedSala==false" class="navbar-brand" href="#/zahtevPregledObrada">ZAHTEVI ZA POSETU</a>
  <a v-else-if="selectedZahtev" class="navbar-brand" href="#/zahtevPregledObrada">SLOBODNE SALE {{formatiraj(zahtevSelected.datum)}}</a>
  <a v-else class="navbar-brand" href="#/zahtevPregledObrada">KALENDAR ZAUZETOSTI</a>
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
		<button v-if="selectedSala" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="rezervisiPredlog()">Rezervisi po preporuci: {{formatiraj(slobodan)}}</button>
		<button v-if="selectedSala" class="btn btn-outline-success my-2 my-sm-0" type="submit" id="show-modal" @click="showModal = true" v-on:click="izmeniLekara()">Izmeni lekara</button>
      </ul>
      
      <form class="form-inline my-2 my-lg-0">
      <input v-if="selectedZahtev" class="form-control mr-sm-2" type="text" v-model="vreme" placeholder="Unesite vreme" aria-label="Search">
      <input v-if="selectedZahtev"  class="form-control mr-sm-0" type="date" v-model="refreshDatum" placeholder="Odaberite datum" aria-label="Search">
	  <button v-if="selectedZahtev"  v-on:click="osveziDatum()" class="btn"><i class="fa fa-refresh fa-2x"></i></button>
      <input v-if="selectedZahtev" class="form-control mr-sm-2" type="text" placeholder="Unesite naziv/broj sale" aria-label="Search" v-model="pretraga">
      <button v-if="selectedZahtev" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretrazi</button>
      
      
      <input v-if="selectedSala" class="form-control mr-sm-2" type="text" v-model="vreme" placeholder="Unesite vreme" aria-label="Search">
      <input v-if="selectedSala" class="form-control mr-sm-2" type="date" v-model="datum" placeholder="Odaberite datum" aria-label="Search">
      
      <button v-if="selectedSala" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="reserve()">Rezervisi</button>
      
    </form>
  </div>
</nav>
	<div v-if="lekarIzmena">
		<modal v-if="showModal" @close="showModal = false">
        	<h3 slot="header">Izmena lekara</h3>
			<div slot="body">
			<table  class="table">
				<tr bgcolor="#f2f2f2">
				<th>Trenutni lekar</th>
				<th> Radno vreme </th>
				</tr>
		
				<tr>
				<td>{{zahtevSelected.lekar}}</td>
				<td>{{zahtevSelected.lekarPocetak}} - {{zahtevSelected.lekarKraj}}</td>
				</tr>
			</table>	
			<table>
			<tr><td>Nov lekar: </td>
			<td><select v-model="noviLekar">
			<option v-for="l in lekari">{{l.ime}} {{l.prezime}}</option>
			</select></td><td></td></tr>
			</table>
			</div>
        					
        		<div slot="footer">
        		<button @click="showModal=false" style="margin:5px;" class="btn btn-dark" v-on:click="promeniLekara(noviLekar)"> Sacuvaj </button>       						
				<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" > Nazad </button>								
				</div>
		</modal>
		
	</div>
	<div v-if="selectedZahtev==false && selectedSala==false" >
	<table class="table">
		<tr >
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
			<td v-if="zahtevi.length != 0"> <button v-on:click="selektovanZahtev(z)" class="btn btn-success"><i class="fa fa-ticket"></i>Nadji salu</button></td></tr>
		</table>	
	</div>
		<div v-else-if="selectedZahtev && promenjenDatum==false">
			
		<table class="table">
		<tr bgcolor="#f2f2f2">
			<th>Naziv </th>
			<th> Broj </th>
		</tr>
		
		<tr v-for="s in sale">
			<td>{{s.naziv}}</td>
			<td>{{s.broj}}</td>
			<td><button v-on:click="detaljiSala(s)" class="btn btn-success"><i class="fa fa-ticket"></i>REZERVISI</button></td></tr>
		</table>	
		<h3>{{nemaRezultata}}</h3>
		</div>
		
		<div v-else-if="promenjenDatum && selectedZahtev && selectedSala==false">
			
		<table class="table">
		<tr >
			<th>Naziv </th>
			<th> Broj </th>
		</tr>
		
		<tr v-for="s in sale">
			<td>{{s.naziv}}</td>
			<td>{{s.broj}}</td>
			<td><button v-on:click="detaljiSala(s)" class="btn btn-success"><i class="fa fa-ticket"></i>REZERVISI</button></td></tr>
		</table>	
		
		<h3>{{nemaRezultata}}</h3>
		</div>
		
		<div v-else-if="selectedSala">
			<table class="table">
				<tr bgcolor="#f2f2f2">
				<th>Pocetak</th>
				<th>Kraj</th>
			</tr>
			
			<tr v-for="d in salaSelected.kalendar">
				<td>{{formatiraj(d.pocetak)}}</td>
				<td>{{formatiraj(d.kraj)}}</td></tr>
			</table>	
		
		</div>
		<div v-if="nemaRezultata!=''">
		<a @click.prevent="prikaziSveSale()" class="navbar-brand" href="#">Prikazi sve sale</a>
		<a @click.prevent="ponistiPretragu()" class="navbar-brand" href="#">Ponisti pretragu</a>
	</div>
	</div>
	`, 
	
	mounted(){
		//dobavljam zahteve za kliniku
		axios.get("/zahtevPoseta/klinika/pregledi")
		.then(response => {
			this.zahtevi = response.data;
			this.backup = response.data;
		})
		.catch(reponse => {
			this.$router.push("/");
		});
		// nalazi SVE sale
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.sveSale = response.data;
			this.backupSale = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		//nalazi sve lekare klinike 
		axios.get("/lekar/admin/pregled")
		.then(response => {
			this.lekari = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
	}, 
	methods: {
		//ako je iz dijaloga izabrao novog lekara
		promeniLekara: function(noviLekar) {
			for (let l of this.lekari){
				if (l.ime.concat(" ", l.prezime) === noviLekar)
					this.novLekarId = l.id;
					this.novLekarPocetak = l.pocetnoVreme.toString();
					this.novLekarKraj = l.krajnjeVreme.toString();
			}
			this.zahtevSelected.lekar = noviLekar ;
			this.zahtevSelected.idLekar = this.novLekarId;
			this.zahtevSelected.lekarPocetak = this.novLekarPocetak;
			this.zahtevSelected.lekarKraj = this.novLekarKraj;
			this.lekarIzmena = false;
			this.showModal = false;
		},
		//ako je kliknuo na dugme
		izmeniLekara : function() {
			this.lekarIzmena = true;
		},
		
		ponistiPretragu : function() {
			this.nemaRezultata = '';
			this.pretraga = '';
			this.promenjenDatum = false;	
			this.nadjiSale();
		},
		
		prikaziSveSale : function() {
			this.sale = this.sveSale;
			this.backup = this.backupSale;
			this.promenjenDatum = true;
			this.nemaRezultata = '';
		},
		//kad klikne na dugme refresh, treba da nadje slobodne sale za taj datum
		osveziDatum : function() {
			this.vremePromena();
			this.osvezi();
			this.proveriVreme();
			
			if (this.refreshDatum!='' && (new Date(this.refreshDatum) <= new Date())){
				this.greska = true;
				alert("Datum nije validan!")
			}
			
			if (this.refreshDatum=='' && this.vreme==''){
				this.greska = true;
				alert("Unesite datum/vreme");
			}
			

			if (this.refreshDatum=='' && this.vreme!='') {
				this.zahtevSelected.datum = this.zahtevSelected.datum.slice(0,10).concat(" ",this.vreme);				
			}
			if (this.refreshDatum!='' && this.vreme==''){
				this.zahtevSelected.datum = this.refreshDatum.concat(" ",this.zahtevSelected.datum.slice(11));
			}
			if (this.refreshDatum!='' && this.vreme!=''){
				this.zahtevSelected.datum = this.refreshDatum.concat(" ",this.vreme);
			}
			if (this.greska) {return;}
			this.promenjenDatum = true;
			this.nadjiSale();
 			
		},
		
		rezervisiPredlog : function() {
			this.zahtevSelected.datum = this.slobodan;
			this.posaljiZahtev(); 
		
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
			return this.vreme; 
		},

		//rezervacija kod pretrage datuma u kalendaru zauzeca
		reserve: function() {
			this.osvezi();
			this.vremePromena();
			
			if (this.datum!='' && (new Date(this.datum) <= new Date())){
				this.greska = true;
				alert("Datum nije validan!")
			}
			
			if (this.datum=='' && this.vreme==''){
				this.greska = true;
				alert("Unesite datum/vreme");
			}
			

			if (this.datum=='' && this.vreme!='') {
				this.zahtevSelected.datum = this.zahtevSelected.datum.slice(0,10).concat(" ",this.vreme);				
			}
			if (this.vreme==''){
				this.greska = true;
				alert("Odaberite vreme!");
			}
			if (this.datum!='' && this.vreme!=''){
				this.zahtevSelected.datum = this.datum.concat(" ",this.vreme);
			}
			this.proveriVreme();
			if (this.greska) {return;}
			this.zahtevSelected.idSale = this.salaSelected.id;
			this.posaljiZahtev();
		},
		//kada klikne na dugme rezervisi sa stranice za pretragu sala
		detaljiSala : function(s) {
			this.zahtevSelected.idSale = s.id;
			this.salaSelected = s;	
			this.posaljiZahtev();
		},
		//salje zahtev za registraciju
		posaljiZahtev: function() {
			axios.post("/sala/admin/pregled/rezervacijaSale", this.zahtevSelected)
			.then(response => {
				alert("Uspesno rezervisano!");
				this.$router.push("/adminHome");
			})
			.catch(response => {
				this.slobodan = response.response.data;
				if (this.sale.length!=0 || selectedSala){
					alert("Lekar je zauzet!");
				}
				this.selectedSala = true;
				this.selectedZahtev = false;
			});
		},
		//pronalazi sale za novi datum kod pretrage, nakon refresh klika
		//salje post zahtev
		nadjiSale : function() {
			console.log(this.zahtevSelected.datum);
			axios.post("/sala/admin/slobodni", this.zahtevSelected)
			.then(response => {
				this.sale = response.data;
				this.backup = response.data;
				if (this.sale.length===0) {
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
		//reaguje na dugme nadji Salu (prikaz zahteva posete)
		selektovanZahtev: function(z) {
			// nalazi sve slobodne sale za slektovan zahtev
			this.zahtevSelected = z;
			this.selectedZahtev = true;
			this.selectedSala = false;
			this.nadjiSale();
		},
		//pretraga ime ILI broj
		search: function() {
			console.log("pretraga");
			this.sale = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let s of this.backup){
            	let passedNaziv = (this.pretraga != '') ? (s.naziv.toLowerCase().includes(lowerPretraga)) : true;
                let passedBroj = (this.pretraga != '') ? (s.broj.toLowerCase().includes(lowerPretraga)) : true;                    
                if (passedNaziv || passedBroj) this.sale.push(s);
            }
            
            if (this.sale.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage! Pokusajte ponovo, ili: ";
            }
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
			  
		},
		//provera ispravnosti unetog vremena od strane korisnika
		proveriVreme :function() {
			
			if (!this.vreme.includes(':') && ((this.vreme.length>2 || this.vreme.length<1) || parseInt(this.vreme)>25)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			if (this.vreme.includes(':') && (this.vreme.length != 5)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}

		} ,
		
		osvezi: function(){
			this.greskaDatum = "";
			this.greska = false;
		}
		
	}
		
});