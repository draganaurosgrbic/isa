Vue.component("zahtevTermini", {
	
	data: function(){
		return{
			zahtevi: [], 
			zahteviBackup: [],
			pretraga: '', 
			selectedZahtev: {}, 
			selected: false, 
			datum: ''
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
			
		<div>
		
			<modal v-if="selected" @close="selected=false">
		
				<div slot="body">
				
				<table class="table">
					
					<tr>
						<th>Datum: </th>
						<td><input type="text" v-model="datum" class="form-control" disabled></td>
					</tr>
				
					<tr>
						<th scope="row">Tip posete: </th>
						<td><input type="text" v-model="selectedZahtev.tipPosete" class="form-control" disabled></td>
					</tr>
			
					<tr>
						<th scope="row">Naziv posete: </th>
						<td><input type="text" v-model="selectedZahtev.nazivPosete" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Lekar: </th>
						<td><input type="text" v-model="selectedZahtev.lekar" class="form-control" disabled></td>
					</tr>
						
					<tr>
						<th scope="row">Trajanje posete: </th>
						<td><input type="text" v-model="selectedZahtev.trajanje" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Cena posete: </th>
						<td><input type="text" v-model="selectedZahtev.cena" class="form-control" disabled></td>
					</tr>
			
				</table>
				
				</div>
				
				<div slot="footer">
					<button style="margin:5px;" class="btn btn-secondary" @click="selected=false">Zatvori</button>												
				</div>
			
			</modal>
		
		</div>
		
		</div>
				
		<div class="container" id="cosak">
		
			<h2>Zahtevi za posete</h2><br>
			
			<table v-if="zahtevi.length>0" class="table table-hover">
			
				<thead>
				
					<tr>
						<th scope="col">Datum</th>
						<th scope="col">Tip posete</th>
						<th scope="col">Naziv posete</th>
						<th scope="col">Lekar</th>
					
					</tr>
				
				</thead>
				
				<tbody>
				
					<tr v-for="z in zahtevi" v-on:click="selectZahtev(z)">
						
						<td>{{formatiraj(z.datum)}}</td>
						<td>{{z.tipPosete}}</td>
						<td>{{z.nazivPosete}}</td>
						<td>{{z.lekar}}</td>
					</tr>
				
				</tbody>
			
			</table>
			
			<h3 v-if="zahtevi.length==0" style="color: #00CED1;">
				Nema rezultata pretrage.
			</h3>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/zahtevTermini")
		.then(response => {
			this.zahtevi = response.data;
			this.zahteviBackup = response.data;
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
		
		selectZahtev: function(zahtev){
			this.selectedZahtev = zahtev;
			this.selected = true;
			this.datum = this.formatiraj(this.selectedZahtev.datum);
		}, 
		
		refresh: function(){
			location.reload();
		}, 
		
		search: function(){
			
			this.zahtevi = [];
			let lowerPretraga = this.pretraga.toLowerCase();
			
			for (let z of this.zahteviBackup){
				let tipPosetePassed = lowerPretraga != '' ? z.tipPosete.toLowerCase().includes(lowerPretraga) : true;
				let nazivPosetePassed = lowerPretraga != '' ? z.nazivPosete.toLowerCase().includes(lowerPretraga) : true;
				let lekarPassed = lowerPretraga != '' ? z.lekar.toLowerCase().includes(lowerPretraga) : true;
				if (tipPosetePassed || nazivPosetePassed || lekarPassed) this.zahtevi.push(z);
			}
			
		}
	}
	
});