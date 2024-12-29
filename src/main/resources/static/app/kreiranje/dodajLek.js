Vue.component("dodajLek", {

	data: function(){
		return {
			lek: {
				'id': null,
				'sifra': '', 
				'naziv': ''
			}, 
			greskaSifra: '', 
			greskaNaziv: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div>
			<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
			  <a class="navbar-brand" href="#/dodajLek">DODAVANJE LEKA</a>
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
				
				<h2 class="row justify-content-center">Dodavanje novog leka</h2>
				
				<br>
			
				<table>
					<tbody>
					<tr><th scope="row">Sifra: </th><td><input type="text" v-model="lek.sifra" class="form-control"></td><td>{{greskaSifra}}</td></tr>				
					<tr><th scope="row">Naziv: </th><td><input type="text" v-model="lek.naziv" class="form-control"></td><td>{{greskaNaziv}}</td></tr>
					</tbody>
				</table>
				
				<br>
						
				<div class="row justify-content-center">
					<button v-on:click="dodajLek()" class="btn btn-success">DODAJ LEK</button>
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
			this.greskaSifra = '';
			this.greskaNaziv = '';
			this.greska = false;
		}, 
		
		dodajLek: function(){
			
			this.osvezi();

			if (this.lek.sifra == ''){
				this.greskaSifra = "Sifra ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.lek.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/lek/kreiranje", this.lek)
			.then(response => {
				alert("Lek uspesno kreiran!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaSifra = "Uneta sifra nije jedinstvena.";
			});
			
		}
	}
	
});
