Vue.component("dodajAdmina", {

	data: function(){
		return {
			admin: {
				'id': null,
				'email': '', 
				'lozinka': '', 
				'ime': '', 
				'prezime': '', 
				'telefon': '',  
				'drzava': '', 
				'grad': '', 
				'adresa': '', 
				'klinika': '', 
				'aktivan': true, 
				'promenjenaSifra': false
			}, 
			novaLozinka: '',
			ponovljenaLozinka: '', 
			greskaEmail: '', 
			greskaNovaLozinka: '', 
			greskaPonovljenaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '', 
			greskaDrzava: '', 
			greskaGrad: '',
			greskaAdresa: '', 
			greskaKlinika: '',
			greska: false, 
			klinike: [], 
			nazivKlinike: ''
		}
	}, 
	
	template: `
		<div>
		<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#/dodajAdmina">DODAVANJE ADMINA</a>
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
		
				<h2 class="row justify-content-center">Registracija novog administratora</h2>
				
				<br>
			
			
				<table>
					<tbody>
					<tr><th scope="row">Email: </th><td><input type="text" v-model="admin.email" class="form-control"></td><td>{{greskaEmail}}</td></tr>
					<tr><th scope="row">Ime: </th><td><input type="text" v-model="admin.ime" class="form-control"></td><td>{{greskaIme}}</td></tr>
					<tr><th scope="row">Prezime: </th><td><input type="text" v-model="admin.prezime" class="form-control"></td><td>{{greskaPrezime}}</td></tr>
					<tr><th scope="row">Telefon: </th><td><input type="text" v-model="admin.telefon" class="form-control"></td><td>{{greskaTelefon}}</td></tr>
					<tr><th scope="row">Drzava: </th><td><input type="text" v-model="admin.drzava" class="form-control"></td><td>{{greskaDrzava}}</td></tr>
					<tr><th scope="row">Grad: </th><td><input type="text" v-model="admin.grad" class="form-control"></td><td>{{greskaGrad}}</td></tr>
					<tr><th scope="row">Adresa: </th><td><input type="text" v-model="admin.adresa" class="form-control"></td><td>{{greskaAdresa}}</td></tr>
					<tr><th scope="row">Lozinka: </th><td><input type="password" v-model="novaLozinka" class="form-control"></td><td>{{greskaNovaLozinka}}</td></tr>
					<tr><th scope="row">Ponovljena lozinka: </th><td><input type="password" v-model="ponovljenaLozinka" class="form-control"></td><td>{{greskaPonovljenaLozinka}}</td></tr>

					<tr><td class="left">Klinika: </td><td class="right"><select v-model="nazivKlinike">
						<option v-for="k in klinike">{{k.naziv}}</option>
					</select></td><td>{{greskaKlinika}}</td></tr>
					
					</tbody>
					
				</table>
		
				<br>
					
				<div class="row justify-content-center">
					<button v-on:click="dodajAdmina()" class="btn btn-success">KREIRAJ PROFIL</button>
				</div>
			
			</div>
		
		</div>
	
	`, 
	
	mounted () {
		
		axios
        .get("/klinika/pregled")
        .then(response => {
        	this.klinike = response.data
        })
		.catch(reponse => {
			this.$router.push("/");
		});

	},
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}, 
		
		nazivKlinike: function(){
			for (let k of this.klinike){
				if (k.naziv == this.nazivKlinike)
					this.admin.klinika = k.id;
			}
		}
		
	}, 
	
	methods: {
		
		emailProvera: function(email){
    		return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
		}, 
		
		osvezi: function(){
			this.greskaEmail = '';
			this.greskaNovaLozinka = '';
			this.greskaPonovljenaLozinka = '';
			this.greskaIme = '';
			this.greskaPrezime = '';
			this.greskaTelefon = '';
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greskaKlinika = '';
			this.greska = false;
		}, 
		
		dodajAdmina: function(){
			
			this.osvezi();
			this.admin.lozinka = this.novaLozinka;
			
			if (!this.emailProvera(this.admin.email)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.admin.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.admin.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.admin.telefon == ''){
				this.greskaTelefon = "Telefon ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.admin.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.admin.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.admin.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.novaLozinka == ''){
				this.greskaNovaLozinka = "Lozinka ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.ponovljenaLozinka != this.novaLozinka){
				this.greskaPonovljenaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.admin.klinika == ''){
				this.greskaKlinika = "Klinika ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/admin/kreiranje", this.admin)
			.then(response => {
				alert("Admin uspesno kreiran!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaEmail = "Unet email nije jedinstven.";
			});
			
		}
	}
	
});
