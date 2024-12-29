Vue.component("dodajTipPosete", {

	data: function(){
		return {
			tipPosete: {
				'id': null,
				'pregled': '', 
				'naziv': '', 
				'sati': 0,  
				'minute': 0,
				'cena': 0,
				'klinika': null, 
				'aktivan': true
			}, 
			greskaPregled: '',
			greskaNaziv: '', 
			greskaSati: '', 
			greskaMinute: '', 
			greskaCena: '', 
			greska: false,  
			klinika: null
		}
	}, 
	
	template: `
	
	<div>
	<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#/dodajTipPosete">KREIRANJE TIPA POSETE</a>
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
       
		</div>
		</nav>
		<div class="card" id="box">
		
			<h2 class="row justify-content-center">Kreiranje tipa posete</h2><br>

				<table>
			
				<tbody>
				
					<tr><th scope="row">Naziv: </th>
					<td><input type="text" class="form-control" v-model="tipPosete.naziv"></td><td>{{greskaNaziv}}</td></tr>
					
					<tr> <th scope="row">Pregled: </th>
					<td><select v-model="tipPosete.pregled" class="form-control">
						<option value="true">pregled</option>
						<option value="false">operacija</option>
					</select></td><td>{{greskaPregled}}</td></tr>
					
					<tr> <th scope="row">Sati: </th>
					<td><input type="text" v-model="tipPosete.sati" class="form-control"></td><td>{{greskaSati}}</td></tr>
					
					<tr><th scope="row">Minute: </th>
					<td><input type="text" v-model="tipPosete.minute" class="form-control"></td><td>{{greskaMinute}}</td></tr>
					
					<tr><th scope="row">Cena: </th>
					<td><input type="text" v-model="tipPosete.cena" class="form-control"></td><td>{{greskaCena}}</td></tr>

				</tbody>
				</table>
				<br>
			<div class="row justify-content-center">
				<button v-on:click="dodajTipPosete()" class="btn btn-success">KREIRAJ</button>
			</div>
		</div>
		</div>
	
	`, 
	
	mounted () {
		axios
        .get("/klinika/admin/pregled")
		.then(response => {
			this.klinika = response.data
		})
		.catch(reponse => {
			this.$router.push("/");
		});
	},
	
	methods: {
	
		osvezi: function(){
			this.greskaPregled = '';
			this.greskaNaziv = '';
			this.greskaCena = '';
			this.greskaMinute = '';
			this.greskaSati = '';
			this.greska = false;
		}, 
		
		dodajTipPosete: function(){
			
			this.osvezi();
			this.tipPosete.klinika = this.klinika.id;

			if (this.tipPosete.pregled == ''){
				this.greskaPregled = "Pregled ne sme biti prazan.";
				this.greska = true;
			}
			
			if (this.tipPosete.naziv=='') {
				this.greskaNaziv = "Naziv ne sme biti prazan.";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.tipPosete.cena)) || parseInt(this.tipPosete.cena) <= 0){
				this.greskaCena = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.tipPosete.minute)) || parseInt(this.tipPosete.minute) <= 0){
				this.greskaMinute = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.tipPosete.sati)) || parseInt(this.tipPosete.sati) < 0){
				this.greskaSati = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/tipPosete/kreiranje", this.tipPosete)
			.then(response => {
				alert("Tip posete uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch(error => {
				this.greskaNaziv = "Unet naziv nije jedinstven. ";
			});
			
		}
	},
	
});
