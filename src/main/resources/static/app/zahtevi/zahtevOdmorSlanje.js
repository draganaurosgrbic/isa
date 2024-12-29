Vue.component("zahtevOdmorSlanje", {
	data: function(){
		return{
			zahtev: {
				'id': null,
				'pocetak': '', 
				'kraj': '', 
				'odobren': false
			}, 
			tip: '',

			zaposleni: '',
			greskaKraj: '', 
			greskaPocetak: '', 
			greska: false
		}
	}, 
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <a class="navbar-brand" href="#/zahtevOdmorSlanje">ZAHTEV GODISNJI ODMOR</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
		<li class="nav-item active">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          <span class="sr-only">(current)</span>
          </a>
		</li>
		</ul>
  </div>
  </div>

</nav>

	<div class="card" id="tableBox">
		
		<h2 class="row justify-content-center">Zahtev za godisnji odmor</h2><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Odsustvo/odmor: </th>
						<td><select v-model="tip" class="form-control">
						<option value="" disabled selected>Odaberite tip odsustva</option>
						<option>odsustvo</option>
						<option>odmor</option>
					</select></td><td></td></tr>
					</tr>

				
					<tr>
						<th scope="row">Datum pocetka: </th>
						<td><input type="date" v-model="zahtev.pocetak" class="form-control"></td>
						<td>{{greskaPocetak}}</td>
					</tr>
					
					<tr>
						<th scope="row">Datum kraja: </th>
						<td><input type="date" v-model="zahtev.kraj" class="form-control"></td>
						<td>{{greskaKraj}}</td>
					</tr>
				</tbody>
			</table>
			<div class="row justify-content-center">
				<td><button v-on:click="posalji()" class="btn btn-success">POSALJI</button></td>
			</div>
		
		</div>
		
	</div>
	
	`, 
	
	methods: {
		osvezi: function(){
			this.greskaPocetak = '';
			this.greskaKraj = '';
			this.greska = false;
		}, 
		
		posalji: function(){
			
			this.osvezi();
			
			if (this.zahtev.pocetak==='') {
				this.greskaPocetak = 'Odaberite pocetni datum';
				this.greska = true;
			}
			
			if (new Date(this.zahtev.pocetak) <= new Date()){
				this.greskaPocetak = 'Datum ne sme biti manji od trenutnog.';
				this.greska = true;
			}
			
			if (new Date(this.zahtev.kraj) <= new Date()){
				this.greskaKraj = 'Datum ne sme biti manji od trenutnog.';
				this.greska = true;
			}
			
			if (this.zahtev.kraj==='') {
				this.greskaKraj = 'Odaberite krajnji datum';
				this.greska = true;
			}
			
			if (this.zahtev.pocetak >= this.zahtev.kraj) {
				this.greska = true;
				alert("Pocetni datum mora biti pre krajnjeg!");
			}
			let datePocetak = new Date(this.zahtev.pocetak);
			let dateKraj = new Date(this.zahtev.kraj);
			let razlika = dateKraj.getTime() - datePocetak.getTime();

			let dani = (razlika/(1000*3600*24));
			if (this.tip=="odsustvo") {
				if (dani>3) {
					this.greska = true;
					alert("Odsustvo maksimalno moze trajati 3 dana!");
				}
			}
			if (this.greska) return;

			
			if (this.greska) return;
						
			axios.post("/zahtevOdmor/kreiranje", this.zahtev)
			.then(response => {
				alert("Zahtev poslat!");
			})
			.catch(error => {
				alert("TERMIN ZAUZET!!");
			});
		}
	},
	
	mounted(){
		
		axios.get("/user/check/zaposleni")
		.catch(response => {
			this.$router.push("/");
		});
		
	}
	
});