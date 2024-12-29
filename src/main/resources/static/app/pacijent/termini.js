Vue.component("termini", {
	
	data: function(){
		return{
			termini: [], 
			terminiBackup: [],
			selectedTermin: {}, 
			selected: false, 
			datum: '', 
			pretraga: ''
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
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
		
			<modal v-if="selected" @close="selected=false">
							
				<div slot="body">

				<table class="table">
			
					<tr>
						<th scope="row">Datum: </th>
						<td><input type="text" v-model="datum" class="form-control" disabled></td>
					</tr>
								
					<tr>
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="selectedTermin.adresa" class="form-control" disabled></td>
					</tr>
				
					<tr>
						<th scope="row">Cena: </th>
						<td><input type="text" v-model="selectedTermin.novaCena" class="form-control" disabled></td>
					</tr>
								
					<tr>
						<th scope="row">Naziv termina: </th>
						<td><input type="text" v-model="selectedTermin.naziv" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row" style="width: 50%;">Trajanje termina: </th>
						<td style="width: 70%;"><input type="text" v-model="selectedTermin.trajanje" class="form-control" disabled></td>
					</tr>
				
					<tr>
						<th scope="row">Sala: </th>
						<td><input type="text" v-model="selectedTermin.sala" class="form-control" disabled></td>
					</tr>
						
					<tr>
						<th scope="row">Lekari: </th>
						<td><select class="form-control" v-bind:size="selectedTermin.lekari.length" disabled multiple>
							<option v-for="l in selectedTermin.lekari">
								{{l}}
							</option>
						</select></td>
					</tr>
			
				</table>

				</div>
				
				<div slot="footer">
	        		<button style="margin:5px;" class="btn btn-dark" v-on:click="otkazi()" v-if="can_cancel(selectedTermin.datum)">Otkazi</button>       						
					<button style="margin:5px;" class="btn btn-secondary" @click="selected=false">Nazad</button>								
				</div>			
				
			</modal>
		
		</div>
		
		<div class="container" id="cosak">
		
			<h2>Zakazane posete</h2><br>
			
			<table v-if="termini.length>0" class="table table-hover">
			
				<thead>
					
					<tr>
					
						<th scope="col">Datum</th>
						<th scope="col">Klinika</th>
						<th scope="col">Vrsta termina</th>
						<th scope="col">Cena</th>
					
					</tr>
				
				</thead>
				
				<tbody>
				
					<tr v-for="t in termini" v-on:click="selectTermin(t)">
				
						<td>{{formatiraj(t.datum)}}</td>
						<td>{{t.klinika}}</td>
						<td>{{t.tipPosete}}</td>
						<td>{{t.novaCena}}</td>

					</tr>
				</tbody>
			
			</table>
			
			<h3 v-if="termini.length==0" style="color: #00CED1;">
				Nema rezultata pretrage.
			</h3>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/termini")
		.then(response => {
			this.termini = response.data;
			this.terminiBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		Date.prototype.addHours = function(h){
		    this.setHours(this.getHours()+h);
		    return this;
		}
		
	}, 
	
	methods: {
		
		can_cancel: function(pocetak) {
			let datum = Date.parse(pocetak);
			
			if (((datum - Date.now()) / 1000 / 60 / 60) >= 24)
				return true;
			
			return false;
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
		
		refresh: function(){
			location.reload();
		}, 
		
		selectTermin: function(termin){
			this.selectedTermin = termin;
			this.selected = true;
			this.datum = this.formatiraj(this.selectedTermin.datum);
		}, 
		
		otkazi: function(){

			let temp = confirm("Da li ste sigurni?");
        	if (!temp) return;

			axios.delete("/poseta/otkazi/" + this.selectedTermin.id)
			.then(response => {
				alert("Poseta uspesno otkazana!");
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		}, 
		
		search: function(){

			this.termini = [];
			let lowerPretraga = this.pretraga.toLowerCase();
			
			for (let t of this.terminiBackup){
				let klinikaPassed = lowerPretraga != '' ? t.klinika.toLowerCase().includes(lowerPretraga) : true;
				let tipPosetePassed = lowerPretraga != '' ? t.tipPosete.toLowerCase().includes(lowerPretraga) : true;
				if (klinikaPassed || tipPosetePassed) this.termini.push(t);
			}
			
		}
		
	}
	
});