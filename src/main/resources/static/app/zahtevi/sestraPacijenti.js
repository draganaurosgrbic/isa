Vue.component("sestraPacijenti", {
	
	data: function(){
		return{
			pacijenti: {},
			selectedPacijent: {}, 
			selectedIzvestaj: {}, 
			selected: false, 
			izvestajSelected: false,
			backup: {},
			pomocna: {},
			nemaRezultata: '',
			pretraga: ''
		}
	}, 

	template: `
	
<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/sestraPacijenti">LISTA PACIJENATA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
		<li class="nav-item active">
        <a class="nav-link" href="#/sestraHome">
          <i class="fa fa-home"></i>
          <span class="sr-only">(current)</span>
          </a>
		</li>
		</ul>
  </div>
		
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      <li v-if="!selected && !izvestajSelected" class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-sort"></i>
          Sortiraj po
          <span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" @click.prevent="sort_ime()" href="#">imenu</a>
          <a class="dropdown-item" @click.prevent="sort_prezime()" href="#">prezimenu</a>
          <a  class="dropdown-item" @click.prevent="sort_email()" href="#">broju osiguranika</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
    </ul>
   </div>
    <form class="form-inline my-2 my-lg-0" v-if="!selected && !izvestajSelected">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretrazite" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretrazi</button>
    </form>

</nav>	
		<div v-if="selected" class="container"> 
		
		<div class="row">
		
			<div class="card" id="box">
			
				<h2>Karton</h2><br>
				
				<table class="table">
				
				<tbody>
					<tr>
						<th scope="row">Broj osiguranika: </th>
						<td colspan="2"><input type="text" v-model="selectedPacijent.kartonObj.brojOsiguranika" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Visina: </th>
						<td><input type="number" step="0.01" min="0" v-model="selectedPacijent.kartonObj.visina" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Tezina: </th>
						<td><input type="number" step="0.01" min="0" v-model="selectedPacijent.kartonObj.tezina" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Leva dioptrija: </th>
						<td><input type="number" step="0.01" v-model="selectedPacijent.kartonObj.levaDioptrija" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Desna dioptrija: </th>
						<td><input type="number" step="0.01" v-model="selectedPacijent.kartonObj.desnaDioptrija" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Krvna grupa: </th>
						<td colspan="2"><input type="text" v-model="selectedPacijent.kartonObj.krvnaGrupa" class="form-control" disabled></td>
					</tr>

				</tbody>
			</table>		
		</div>
		
			<div style="margin-top: 10%; margin-left: 10%;">
				
				<h2>Izvestaji</h2><br>
				
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Poseta</th>
							<th scope="col">Datum</th>
							<th scope="col">Opis</th>
						</tr>
					</thead>
					<tbody>
						
						<tr v-for="i in selectedPacijent.stariIzvestaji" v-on:click="selektovanIzvestaj(i)">
							<td>{{i.posetaNaziv}}</td>
							<td>{{formatiraj(i.datum)}}</td>
							<td>{{i.opis}}</td>
						</tr>
					
					</tbody>

				</table>
				
				<br>
				
				<div class="row justify-content-center">
					<button class="btn btn-success" type="submit" v-on:click="povratakPacijenti()">Povratak</button>
				</div>
				
			</div>
	</div>
	</div>
	<div v-else-if="izvestajSelected" class="row">
		
		<div class="card" id="tableBox">
			
			<h2 class="row justify-content-center">Izvestaj o poseti</h2><br>
				
			<table class="table">
				<tbody>
					<tr>
						<th scope="row">Opis: </th>
						<td><input type="text" v-model="selectedIzvestaj.opis" class="form-control" disabled></td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Dijagnoze: </th>
						<td>
							<select multiple style="min-width: 300px;" disabled>
				                <option v-for="d in selectedIzvestaj.dijagnozeSifre" :value="d.id">
				                    {{d}}
				                </option>
			                </select>
			            </td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Lekovi: </th>
						<td>
							<select multiple style="min-width: 300px;" disabled>
				                <option v-for="l in selectedIzvestaj.lekoviSifre" :value="l.id">
				                    {{l}}
				                </option>
			                </select>
			            </td>
						<td></td>
					</tr>

				</tbody>
						
			</table>
		
			<br>
				
			<div class="row justify-content-center">
				<button class="btn btn-success" type="submit" v-on:click="povratakDetalji()">Povratak</button>
			</div>
				
		</div>
	</div>
	
	<div v-else class="box">
		<table class="table">
		<tr >
			<th> Ime </th>
			<th> Prezime </th>
			<th> Broj osiguranika </th>
		</tr>
		
		<tr v-for="p in pacijenti" >
			<td>{{p.ime}}</td>
			<td>{{p.prezime}}</td>
			<td>{{p.kartonObj.brojOsiguranika}}</td>
			<td><button v-on:click="selektovanPacijent(p)" class="btn btn-success"><i class="fa fa-info-circle"></i>Detalji</button></td>
			
		</tr>
	</table>	
		<h3>{{nemaRezultata}}</h3>
	</div>
	</div>
	</div>
	`, 
	
	methods: {
		povratakDetalji: function() {
			this.selectedIzvestaj = {};
			this.izvestajSelected = false;
			this.selected = true;
		},
		
		povratakPacijenti: function() {
			this.selectedPacijent = {};
			this.selected = false;
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
		
		selektovanIzvestaj: function(i) {
			this.selectedIzvestaj = i;
			this.izvestajSelected = true;
			this.selected = false;
		},
		
		selektovanPacijent: function(p) {
			this.selectedPacijent = p;
			this.selected = true;
			this.izvestajSelected = false;
		},
	
		search: function(){
			
			this.pacijenti = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let l of this.backup){
            	let imePrezime = (l.ime.concat(" ",l.prezime)).toLowerCase();
            	let prezimeIme = (l.prezime.concat(" ", l.ime)).toLowerCase();
            	if (lowerPretraga.includes(" ")) { //ime i prezime
            		let passedImePrezime = (this.pretraga != '') ? (imePrezime.includes(lowerPretraga) || imePrezime === lowerPretraga) : true;
                    let passedPrezimeIme = (this.pretraga != '') ? (prezimeIme.includes(lowerPretraga) || prezimeIme === lowerPretraga) : true;
                    if (passedImePrezime || passedPrezimeIme ) this.pacijenti.push(l);
            	}
            	else { //ili ime ili prezime
            		let passedIme = (this.pretraga != '') ? (l.ime.toLowerCase().includes(lowerPretraga)) : true;
                    let passedPrezime = (this.pretraga != '') ? (l.prezime.toLowerCase().includes(lowerPretraga)) : true;                    
                    let passedBroj = (this.pretraga != '') ? (l.kartonObj.brojOsiguranika.includes(lowerPretraga)) : true;
                    if (passedIme  || passedPrezime || passedBroj) this.pacijenti.push(l);
            	}
                                
            }
            
            if (this.pacijenti.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		osvezi : function() {
			this.nemaRezultata = "";
			this.pretraga = "";
		},
		sort_ime: function(){
			let lista = this.pacijenti;
			this.pacijenti = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].ime < lista[i].ime) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.pacijenti = lista;
		}, 
		sort_prezime: function(){
			let lista = this.pacijenti;
			this.pacijenti = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].prezime < lista[i].prezime) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.pacijenti = lista;
		}, 
		sort_email: function(){
			let lista = this.pacijenti;
			this.pacijenti = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].email < lista[i].email) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.pacijenti = lista;
		}
	},
	
	mounted(){
		axios.get("/sestra/pacijenti") 
		.then(response => {
			this.pacijenti = response.data;
			this.backup = response.data;
		})
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});