Vue.component("pacijentProfil", {
	
	data: function(){
		return{
			korisnik: {}, 
			novaLozinka: '', 
			ponovljenaLozinka: '', 
			greskaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '',
			greskaDrzava: '', 
			greskaGrad: '', 
			greskaAdresa: '', 
			greska: false
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
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentProfil" v-on:click="refresh()">
          <i class="fa fa-user"></i>
          Profil 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>	
	
		</div>
	
		<div class="card" id="tableBox">
							
			<h2>Podaci o korisniku</h2><br>
									
				<table>
			
					<tr>
						<th>Email: </th>
						<td><input type="text" v-model="korisnik.email" class="form-control" disabled></td>
						<td style="width: 33%;"></td>
					</tr>
					
					<tr>
						<th>Ime: </th>
						<td><input type="text" v-model="korisnik.ime" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaIme}}</td>
					</tr>
					
					<tr>
						<th>Prezime: </th>
						<td><input type="text" v-model="korisnik.prezime" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaPrezime}}</td>
					</tr>
					
					<tr>
						<th scope="row">Telefon: </th>
						<td><input type="text" v-model="korisnik.telefon" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaTelefon}}</td>
					</tr>
					
					<tr>
						<th>Drzava: </th>
						<td><input type="text" v-model="korisnik.drzava" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaDrzava}}</td>
					</tr>
					
					<tr>
						<th>Grad: </th>
						<td><input type="text" v-model="korisnik.grad" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaGrad}}</td>
					</tr>
					
					<tr>
						<th>Adresa: </th>
						<td><input type="text" v-model="korisnik.adresa" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaAdresa}}</td>
					</tr>
					
					<tr>
						<th>Nova lozinka: </th>
						<td><input type="password" v-model="novaLozinka" class="form-control"></td>
						<td></td>
					</tr>
					
					<tr>
						<th>Ponovljena lozinka: </th>
						<td><input type="password" v-model="ponovljenaLozinka" v-bind:disabled="novaLozinka==''" class="form-control"></td>
						<td>&nbsp&nbsp&nbsp{{greskaLozinka}}</td>
					</tr>
					<br><br>
					<tr>
						<td><button v-on:click="izmeni()" class="btn btn-outline-secondary">IZMENI</button></td>
						<td></td>
						<td><button v-on:click="odustani()" class="btn btn-outline-secondary pull-right">ODUSTANI</button></td>
					</tr>
				
			</table>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/profil")
		.then(response => {
			this.korisnik = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}
	}, 
	
	methods: {
		
		osvezi: function(){
			this.greskaLozinka = '';
			this.greskaIme = '';
			this.greskaPrezime = '';
			this.greskaTelefon = '';
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		refresh: function(){
			location.reload();
		},
		
		odustani: function(){
			this.$router.push("/pacijentHome");
		},
		
		izmeni: function(){
			
			this.osvezi();
			
			if (this.korisnik.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.korisnik.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.korisnik.telefon == ''){
				this.greskaTelefon = "Telefon ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.korisnik.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.korisnik.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.korisnik.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.novaLozinka != '' && this.ponovljenaLozinka != this.novaLozinka){
				this.greskaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			this.korisnik.lozinka = this.novaLozinka != '' ? this.novaLozinka : this.korisnik.lozinka;
			
			axios.post("/pacijent/izmena", this.korisnik)
			.then(response => {
				this.$router.push("/pacijentHome");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}
		
	}
	
});