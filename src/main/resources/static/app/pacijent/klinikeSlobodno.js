Vue.component("klinikeSlobodno", {
	
	data: function(){
		return{
			klinike: [], 
			klinikeBackup: [],
			poseteBackup: [],
			selectedKlinika: {}, 
			klinikaSelected: false, 
			selectedPoseta: {}, 
			posetaSelected: false, 
			datum: '', 
			pretraga: '', 
			prikaziKliniku: false
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;" v-if="!klinikaSelected">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;" v-if="klinikaSelected">
        <a class="nav-link" href="#/klinikeSlobodno" v-on:click="refresh()">
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
        
        <ul class="navbar-nav mr-auto" style="margin: auto;" v-if="!klinikaSelected">
      <li class="nav-item dropdown" style="min-width: 100px;">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-sort"></i>
          Sortiranje
          <span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" @click.prevent="naziv_sort()" href="#">naziv</a>
          <a class="dropdown-item" @click.prevent="adresa_sort()" href="#">adresa</a>
          <a class="dropdown-item" @click.prevent="ocena_sort()" href="#">ocena</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
    </ul>

        <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretraga" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretraga</button>
    </form>

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
		
			<modal v-if="posetaSelected" @close="posetaSelected=false">
			
				<div slot="body">
				
				<table class="table">
				
					<tr>
						<th scope="col">Datum: </th>
						<td><input type="text" v-model="datum" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Naziv pregleda: </th>
						<td><input type="text" v-model="selectedPoseta.naziv" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Originalna cena: </th>
						<td><input type="text" v-model="selectedPoseta.cena" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Popust: </th>
						<td><input type="text" v-model="selectedPoseta.popust" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Trajanje pregleda: </th>
						<td><input type="text" v-model="selectedPoseta.trajanje" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Sala: </th>
						<td><input type="text" v-model="selectedPoseta.sala" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Lekari: </th>
						<td><select v-bind:size="selectedPoseta.lekari.length" class="form-control" disabled multiple>
							<option v-for="l in selectedPoseta.lekari">
								{{l}}
							</option>
						</select></td>
					</tr>
			
				</table>

				</div>
				
				<div slot="footer">
	        		<button style="margin:5px;" class="btn btn-dark" v-on:click="zakazi()">Zakazi</button>       						
					<button style="margin:5px;" class="btn btn-secondary" @click="posetaSelected=false">Nazad</button>								
				</div>			

			</modal>
		
		</div>
		
		<div v-if="klinikaSelected" class="container" id="cosak">
		
			<h2>Slobodni termini</h2><br>
				
			<table v-if="selectedKlinika.posete.length>0" class="table table-hover">
				
				<thead>
					<tr>
						<th scope="col">Datum</th>
						<th scope="col">Naziv pregleda</th>
						<th scope="col">Popust</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="p in selectedKlinika.posete" v-on:click="selectPoseta(p)">
						<td>{{formatiraj(p.datum)}}</td>
						<td>{{p.naziv}}</td>
						<td>{{p.popust}}</td>
					</tr>
				</tbody>
			</table>
			
			<h3 v-if="selectedKlinika.posete.length==0" style="color: #00CED1;">
				Nema rezultata pretrage.
			</h3>
		
		</div>
	
		<div v-else class="container" id="cosak">
		
			<h2>Povoljni pregledi</h2><br>
			
			<table v-if="klinike.length>0" class="table table-hover">
			
				<thead>
					<th scope="col">Naziv klinike</th>
					<th scope="col">Adresa</th>
					<th scope="col">Ocena</th>
				</thead>
				
				<tbody>
					<tr v-for="k in klinike" v-on:click="selectKlinika(k)">
						<td>{{k.naziv}}</td>
						<td>{{k.adresa}}</td>
						<td>{{k.ocena}}</td>
					</tr>
				</tbody>
			
			</table>
			
			<h3 v-if="klinike.length==0" style="color: #00CED1;">
				Nema rezultata pretrage.
			</h3>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/klinika/slobodno")
		.then(response => {
			this.klinike = response.data;
			this.klinikeBackup = response.data;
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
			this.posetaSelected = false;
		}, 
				
		selectKlinika: function(klinika){
			this.selectedKlinika = klinika;
			this.klinikaSelected = true;
			this.posetaSelected = false;
			this.poseteBackup = this.selectedKlinika.posete;
		}, 
		
		selectPoseta: function(poseta){
			this.selectedPoseta = poseta;
			this.posetaSelected = true;
			this.datum = this.formatiraj(this.selectedPoseta.datum);
		}, 
		
		zakazi: function(){
			
			axios.get("/poseta/zakazi/" + this.selectedPoseta.id)
			.then(response => {
				alert("Pregled zakazan!");
				this.$router.push("/termini");				
			})
			.catch(response => {
				alert("Imate zakazane posete u isto vreme!!");
			});
			
		}, 
		
		search: function(){

			let lowerPretraga = this.pretraga.toLowerCase();
			
			if (!this.klinikaSelected){
				
				this.klinike = [];
				for (let k of this.klinikeBackup){
					let nazivPassed = lowerPretraga != '' ? k.naziv.toLowerCase().includes(lowerPretraga) : true;
					let adresaPassed = lowerPretraga != '' ? k.adresa.toLowerCase().includes(lowerPretraga) : true;
					if (nazivPassed || adresaPassed) this.klinike.push(k);
				}
				
			}
			
			else{
				this.selectedKlinika.posete = [];
				for (let p of this.poseteBackup){
					let nazivPassed = lowerPretraga != '' ? p.naziv.toLowerCase().includes(lowerPretraga) : true;
					if (nazivPassed) this.selectedKlinika.posete.push(p);
				}
			}
			
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
		
		ocena_sort(){
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
		}
		
	}
	
});