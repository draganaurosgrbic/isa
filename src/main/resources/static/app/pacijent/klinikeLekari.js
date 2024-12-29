Vue.component("klinikeLekari", {
	
	data: function(){
		return{
			klinike: [], 
			klinikeBackup: [], 
			lekariBackup: [], 
			tipovi: [], 
			selectedKlinika: {}, 
			klinikaSelected: false, 
			selectedLekar: {}, 
			lekarSelected: false, 
			tipPregleda: '', 
			datumPregleda: null, 
			nazivKlinike: '',
			lokacijaKlinike: '', 
			ocenaKlinike: 0, 
			imeLekara: '', 
			prezimeLekara: '', 
			ocenaLekara: 0, 
			pretraga: false, 
			zakazivanje: false, 
			greskaPretraga: '', 
			zahtev: {}, 
			imePrezime: '', 
			datum: '', 
			prikaziKliniku: false
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;" v-if="!klinikaSelected && !lekarSelected && !zakazivanje">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;" v-if="klinikaSelected">
        <a class="nav-link" href="#/klinikeLekari" v-on:click="refresh()">
          <i class="fa fa-reply"></i>
          Nazad
          <span class="sr-only">(current)</span>
          </a>
      </li>
      </ul>
      <ul class="navbar-nav" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;" v-if="klinikaSelected">
        <a class="nav-link" href="#" v-on:click="prikaziKliniku=true" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-info"></i>
          Detalji klinike
          <span class="sr-only">(current)</span>
        </a>
      </li>
    </ul>    
    
    <ul class="navbar-nav mr-auto" style="margin-left: 150px;">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-sort"></i>
          Sortiranje
          <span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" @click.prevent="naziv_sort()" href="#" v-if="!klinikaSelected">naziv</a>
          <a class="dropdown-item" @click.prevent="adresa_sort()" href="#" v-if="!klinikaSelected">adresa</a>
          <a class="dropdown-item" @click.prevent="klinika_ocena_sort()" href="#" v-if="!klinikaSelected">ocena</a>
          <a class="dropdown-item" @click.prevent="ime_sort()" href="#" v-if="klinikaSelected">ime</a>
          <a class="dropdown-item" @click.prevent="prezime_sort()" href="#" v-if="klinikaSelected">prezime</a>
          <a class="dropdown-item" @click.prevent="lekar_ocena_sort()" href="#" v-if="klinikaSelected">ocena</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
    </ul>
    
    <ul class="navbar-nav " style="margin-left: 150px;">
		<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-search"></i>
          Pretraga
          <span class="sr-only">(current)</span>
        </a>
        <div  class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown" id="pretraga">
			
			<form>
				<h3>Pretraga</h3>
					<table class="table">
				
						<tr>
							<th scope="row">Tip pregleda: </th>
							<td><select v-model="tipPregleda" class="form-control">
							<option v-for="t in tipovi">
								{{t}}
							</option>
							</select></td>
						</tr>
						<tr>
							<th scope="row">Datum pregleda: </th>
							<td><input type="date" v-model="datumPregleda" class="form-control" onKeyDown="return false"></td>
						</tr>
						<tr>
							<td><button v-on:click="pretrazi()" class="btn btn-outline-secondary">PRETRAZI</button></td>
							<td>{{greskaPretraga}}</td>
						</tr>
						<tr v-if="!klinikaSelected">
							<th scope="row">Naziv klinike: </th>
							<td><input type="text" v-model="nazivKlinike" class="form-control"></td>
						</tr>
						<tr v-if="!klinikaSelected">
							<th scope="row">Adresa klinike: </th>
							<td><input type="text" v-model="lokacijaKlinike" class="form-control"></td>
						</tr>
						<tr v-if="!klinikaSelected">
							<th scope="row">Ocena klinike: </th>
							<td><input type="number" v-model="ocenaKlinike" class="form-control" min="0" max="10" onKeyDown="return false"></td>
						</tr>
						<tr v-if="klinikaSelected">
							<th scope="row">Ime lekara: </th>
							<td><input type="text" v-model="imeLekara" class="form-control"></td>
							
						</tr>
						<tr v-if="klinikaSelected">
						
							<th scope="row">Prezime lekara: </th>
							<td><input type="text" v-model="prezimeLekara" class="form-control"></td>
						</tr>
						<tr v-if="klinikaSelected">
							<th scope="row">Ocena lekara: </th>
							<td><input type="number" v-model="ocenaLekara" class="form-control" min="0" max="10" onKeyDown="return false"></td>

						</tr>
						<tr>
							<td v-if="!lekarSelected"><button v-on:click="filtriraj()" class="btn btn-outline-secondary">FILTIRAJ</button></td>
						</tr>
				
				</table>
			
			</form>
			
        </div>      
      </li>
    </ul>
    
  </div>
</nav>
	
	</div>
	
		<div>
		
			<modal v-if="prikaziKliniku" @close="prikaziKliniku=false">
					
				<div slot="body">
				
				<table class="table">
				
					<tr>
						<th scope="row">Naziv: </th>
						<td><input type="text" v-model="selectedKlinika.naziv" class="form-control" disabled></td>
					</tr>
			
					<tr>
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="selectedKlinika.adresa" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Ocena: </th>
						<td><input type="text" v-model="selectedKlinika.ocena" class="form-control" disabled></td>
					</tr>
					
				</table>
				
				<label style="font-size: 25px">Opis</label>
				<textarea disabled>{{selectedKlinika.opis}}</textarea>
				
				</div>
				
				<div slot="footer">
					<button style="margin:5px;" class="btn btn-secondary" @click="prikaziKliniku=false">Zatvori</button>								
				</div>			
		
			</modal>
		
		</div>
	
		<div>
		
			<modal v-if="lekarSelected && selectedLekar.satnica.length==0" @close="lekarSelected=false">
			
				<h2 slot="header">Detalji lekara</h2>
			
				<div slot="body">
				
					<table class="table">
					
						<tr>
							<th scope="row">Ime: </th>
							<td><input type="text" v-model="selectedLekar.ime" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Prezime: </th>
							<td><input type="text" v-model="selectedLekar.prezime" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Telefon: </th>
							<td><input type="text" v-model="selectedLekar.telefon" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Email: </th>
							<td><input type="text" v-model="selectedLekar.email" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Ocena: </th>
							<td><input type="text" v-model="selectedLekar.prosecnaOcena" class="form-control" disabled></td>
						</tr>
					
					</table>

				</div>
				
				<div slot="footer">
					<button style="margin:5px;" class="btn btn-secondary" @click="lekarSelected=false">Zatvori</button>								
				</div>			
			
			</modal>
		
		</div>
		
		<div>
		
			<modala v-if="lekarSelected && selectedLekar.satnica.length>0" @close="lekarSelected=false">
																					
				<div slot="body">
				
				<br>
				
				<div class="row">
				
				<div class="col" id="okvir">
				
				<h2>Detalji lekara</h2><br>
					
					<table class="table">
					
						<tr>
							<th scope="row">Ime: </th>
							<td><input type="text" v-model="selectedLekar.ime" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Prezime: </th>
							<td><input type="text" v-model="selectedLekar.prezime" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Telefon: </th>
							<td><input type="text" v-model="selectedLekar.telefon" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Email: </th>
							<td><input type="text" v-model="selectedLekar.email" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Ocena: </th>
							<td><input type="text" v-model="selectedLekar.prosecnaOcena" class="form-control" disabled></td>
						</tr>
					
					</table>
				
				</div>
				
				<div class="col" style="overflow: scroll; max-height: 450px;" >
									
					<table class="table table-bordered" style="text-align: center;">
					
						<thead>
						
							<th scope="col">Vreme</th>
							<th scope="col">Zakazivanje</th>
						
						</thead>
						
						<tbody>
						
							<tr v-for="s in selectedLekar.satnica">
								
								<td style="width: 50%;">{{formatiraj(s)}}</td>
								<td><button class="btn btn-outline-secondary" v-on:click="zakazi(s)">ZAKAZI</button></td>
								
							</tr>
						
						</tbody>
					
					</table>
				
				</div>
				
				</div>

				</div>
				
				<div slot="footer">
					<button class="btn btn-secondary" @click="lekarSelected=false">Zatvori</button>								
				</div>			
			
			</modala>
		
		</div>
		
		<div>
		
			<modal v-if="zakazivanje" @close="zakazivanje=false">
			
				<div slot="body">
				
					<table class="table">
				
						<tr>
							<th scope="row">Datum pregleda: </th>
							<td><input type="text" v-model="datum" class="form-control" disabled></td>
						</tr>
						<tr>
							<th scope="row">Naziv pregleda: </th>
							<td><input type="text" v-model="tipPregleda" class="form-control" disabled></td>
						</tr>	
						<tr>
							<th scope="row">Trajanje pregleda: </th>
							<td><input type="text" v-model="selectedKlinika.trajanje" class="form-control" disabled></td>
						</tr>
						<tr>
							<th scope="row">Lekar: </th>
							<td><input type="text" v-model="imePrezime" class="form-control" disabled></td>
						</tr>
						<tr>
							<th scope="row">Cena pregleda: </th>
							<td><input type="text" v-model="selectedKlinika.cena" class="form-control" disabled></td>
						</tr>
				
					</table>

				
				</div>
				
				<div slot="footer">
	        		<button style="margin:5px;" class="btn btn-dark" v-on:click="zakaziPregled()">Zakazi</button>       						
					<button style="margin:5px;" class="btn btn-secondary" @click="zakazivanje=false">Nazad</button>								
				</div>
			
			</modal>
		
		</div>
	
		<div class="row">
										
			<div v-if="klinikaSelected" class="container" id="cosak">
			
				<h2>Zaposleni lekari</h2><br>
				
				<table v-if="selectedKlinika.lekari.length>0" class="table table-hover">
					
					<thead>
						<tr>
							<th scope="col">Ime</th>
							<th scope="col">Prezime</th>
							<th scope="col">Ocena</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="l in selectedKlinika.lekari" v-on:click="selectLekar(l)" v-if="!pretraga || l.satnica.length>0">
							<td>{{l.ime}}</td>
							<td>{{l.prezime}}</td>
							<td>{{l.prosecnaOcena}}</td>
						</tr>
					</tbody>
				</table>
				
				<h3 v-if="selectedKlinika.lekari.length==0" style="color: #00CED1;">
					Nema rezultata pretrage.
				</h3>
			
			
			</div>
		
			<div v-else class="container" id="cosak">
				
				<h2>Klinike</h2><br>
				
				<table v-if="klinike.length>0" class="table table-hover">
					
					<thead>
					
						<tr>
							<th scope="col">Naziv</th>
							<th scope="col">Adresa</th>
							<th scope="col">Ocena</th>
							<th v-if="pretraga" scope="col">Trajanje</th>
							<th v-if="pretraga" scope="col">Cena</th>

						</tr>
					</thead>
					
					<tbody>
						
						<tr v-for="k in klinike" v-on:click="selectKlinika(k)">
							<td>{{k.naziv}}</td>
							<td>{{k.adresa}}</td>
							<td>{{k.ocena}}</td>
							<td v-if="pretraga">{{k.trajanje}}</td>
							<td v-if="pretraga">{{k.cena}}</td>

						</tr>
					
					</tbody>
				
				</table>
				
				<h3 v-if="klinike.length==0" style="color: #00CED1;">
					Nema rezultata pretrage.
				</h3>
			
			</div>
			
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/klinika/pretraga")
		.then(response => {
			this.klinike = response.data;
			this.klinikeBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/tipPosete/nazivi")
		.then(response => {
			this.tipovi = response.data
		})
		.catch(response => {
			this.$router.push("/");

		});
		
	}, 
	
	methods: {
		
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
		
		refresh: function(){
			this.klinikaSelected = false;
			this.lekarSelected = false;
			this.zakazivanje = false;
		}, 
		
		osvezi: function(){
			this.greskaPretraga = '';
		},
		
		selectKlinika: function(klinika){
			this.osvezi();
			this.klinikaSelected = true;
			this.lekarSelected = false;
			this.zakazivanje = false;
			this.selectedKlinika = klinika;
			this.lekariBackup = this.selectedKlinika.lekari;
		}, 
		
		selectLekar: function(lekar){
			this.osvezi();
			this.lekarSelected = true;
			this.selectedLekar = lekar;			
		}, 
		
		pretrazi: function(){
			
			this.osvezi();
						
			if (this.tipPregelda == '' || this.datumPregleda == null || this.datumPregleda == ''){
				this.greskaPretraga = "Unesite tip pregleda i datum. ";
				return;
			}
			if (new Date(this.datumPregleda) <= new Date()){
				this.greskaPretraga = "Datum mora biti veci od trenutnog. ";
				return;
			}
			
			axios.post("/klinika/pretraga", {"tipPregleda": this.tipPregleda, "datumPregleda": this.datumPregleda})
			.then(response => {
				
				this.klinike = response.data;
				this.klinikeBackup = response.data;
				this.pretraga = true;
				let found = false;
				
				for (let i of this.klinike){
					if (i.id == this.selectedKlinika.id){
						this.selectedKlinika = i;
						this.lekariBackup = i.lekari;
						found = true;
						break;
					}
				}
				if (!found){
					this.selectedKlinika.lekari = [];
					this.lekariBackup = [];
				}
				
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		},
		
		filtriraj: function(){
			
			if (!this.klinikaSelected){
				let lowerPretraga1 = this.nazivKlinike.toLowerCase();
				let lowerPretraga2 = this.lokacijaKlinike.toLowerCase();
				this.klinike = [];
				for (let i of this.klinikeBackup){
					let nazivPassed = lowerPretraga1 != '' ? i.naziv.toLowerCase().includes(lowerPretraga1) : true;
					let adresaPassed = lowerPretraga2 != '' ? i.adresa.toLowerCase().includes(lowerPretraga2) : true;
					let ocenaPassed = i.ocena >= this.ocenaKlinike;
					if (nazivPassed && adresaPassed && ocenaPassed) this.klinike.push(i);
				}
			}
			else {
				let lowerPretraga1 = this.imeLekara.toLowerCase();
				let lowerPretraga2 = this.prezimeLekara.toLowerCase();
				this.selectedKlinika.lekari = [];
				for (let i of this.lekariBackup){
					let imePassed = lowerPretraga1 != '' ? i.ime.toLowerCase().includes(lowerPretraga1) : true;
					let prezimePassed = lowerPretraga2 != '' ? i.prezime.toLowerCase().includes(lowerPretraga2) : true;
					let ocenaPassed = i.prosecnaOcena >= this.ocenaLekara;
					if (imePassed && prezimePassed && ocenaPassed) this.selectedKlinika.lekari.push(i);
				}
			}
			
		}, 
		
		zakazi: function(vreme){
			this.zakazivanje = true;
			this.zahtev = {"lekar": this.selectedLekar.id, "datum": vreme, "id": null};
			this.imePrezime = this.selectedLekar.ime + " " + this.selectedLekar.prezime;
			this.datum = this.formatiraj(this.zahtev.datum);
		}, 
		
		zakaziPregled: function(){
			
			axios.post("/zahtevPoseta/kreiranje", this.zahtev)
			.then(response => {
				alert("Zahtev poslat!");
				this.$router.push("/zahtevTermini");
			})
			.catch(response => {
				alert("Imate zakazane posete u isto vreme!!");
			});
			
		}, 
		
		naziv_sort(){
			let lista = this.klinike;
			this.klinike = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].naziv > lista[i].naziv) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.klinike = lista;
		}, 
		
		adresa_sort(){
			let lista = this.klinike;
			this.klinike = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].adresa > lista[i].adresa) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.klinike = lista;
		}, 
		
		klinika_ocena_sort(){
			let lista = this.klinike;
			this.klinike = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].ocena < lista[i].ocena) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.klinike = lista;
		},
		
		ime_sort(){

			let lista = this.selectedKlinika.lekari;
			this.selectedKlinika.lekari = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].ime > lista[i].ime) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.selectedKlinika.lekari = lista;
			
		},
		
		prezime_sort(){
			
			let lista = this.selectedKlinika.lekari;
			this.selectedKlinika.lekari = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].prezime > lista[i].prezime) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.selectedKlinika.lekari = lista;

			
		}, 
		
		lekar_ocena_sort(){
			let lista = this.selectedKlinika.lekari;
			this.selectedKlinika.lekari = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].prosecnaOcena < lista[i].prosecnaOcena) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.selectedKlinika.lekari = lista;

		}
		
	}, 
	
});