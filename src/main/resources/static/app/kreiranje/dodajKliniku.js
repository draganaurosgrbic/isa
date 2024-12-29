Vue.component("dodajKliniku", {

	data: function(){
		return {
			klinika: {
				'id': null,
				'naziv': '', 
				'opis': '', 
				'adresa': '', 
			}, 
			greskaNaziv: '', 
			greskaAdresa: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div>
			<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
			  <a class="navbar-brand" href="#/dodajKliniku">DODAVANJE KLINIKE</a>
			  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			  </button>
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
			      <li class="nav-item active">
			        <a class="nav-link" href="#/superAdminHome">
			          <i class="fa fa-home"></i>
			          Home
			          <span class="sr-only">(current)</span>
			          </a>
			      </li>
			    </ul>
			  </div>
			</nav>

			<div class="card" id="box">
					
				<h2 class="row justify-content-center">Registracija nove klinike</h2>
				
				<br>
			
				<table>
					<tbody>
					<tr><th scope="row">Naziv: </th><td><input type="text" v-model="klinika.naziv" class="form-control"></td><td>{{greskaNaziv}}</td></tr>
					<tr><th scope="row">Opis: </th><td><input type="text" v-model="klinika.opis" class="form-control"></td><td></td></tr>
					<tr><th scope="row">Adresa: </th><td><input type="text" placeholder="Ulica i broj, grad" v-model="klinika.adresa" class="form-control"></td><td>{{greskaAdresa}}</td></tr>
					</tbody>
				</table>
				
				<br>
						
				<div class="row justify-content-center">
					<button v-on:click="dodajKliniku()" class="btn btn-success">KREIRAJ PROFIL KLINIKE</button>
				</div>
			
			</div>
		
		</div>
	
	`, 
	
	mounted(){
		axios.get("/user/check/superadmin")
		.catch(reponse => {
			this.$router.push("/");
		});
	},
	
	methods: {
		
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		dodajKliniku: function(){
			
			this.osvezi();
			
			if (this.klinika.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.klinika.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/klinika/kreiranje", this.klinika)
			.then(response => {
				alert("Klinika uspesno kreirana!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaNaziv = "Unet naziv nije jedinstven.";
			});
			
		}
	}
	
});
