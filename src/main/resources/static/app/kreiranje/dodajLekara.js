Vue.component("dodajLekara", {

	data: function(){
		return {
			lekar: {
				'id': null,
				'email': '', 
				'lozinka': '', 
				'ime': '', 
				'prezime': '', 
				'telefon': '',  
				'drzava': '', 
				'grad': '', 
				'adresa': '', 
				'pocetnoVreme': '',
				'krajnjeVreme': '',
				'klinika': '',
				'specijalizacija': '',
				"aktivan": true, 
				"promenjenaSifra": false
			}, 
			
			tip: 'lekar',
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
			greskaPocetak: '',
			greskaKraj: '',
			greskaSpecijalizacija: '',
			greska: false, 
			pocetak: '',
			kraj: '',
			specijalizacije: [], 
			nazivSpecijalizacije: '',
			klinika: null
		}
	}, 
	
	template: `
	
		<div>
		<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#/dodajLekara">REGISTRACIJA MEDICINSKIH RADNIKA</a>
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
		
			<h2 class="row justify-content-center">Registracija medicinskog radnika</h2><br>
			
			<table>
			
				<tbody>
					
					<tr>
					<th scope="row">Profesija: </th>
					<td><select v-model="tip" class="form-control">
						<option>lekar</option>
						<option>medicinska sestra</option>
					</select></td><td></td></tr>

					<tr>
					<th scope="row">Email: </th>
					<td><input class="form-control" type="text" v-model="lekar.email"></td><td>{{greskaEmail}}</td></tr>
					
					<tr>
					<th scope="row">Ime: </th>
					<td ><input type="text" class="form-control" v-model="lekar.ime"></td><td>{{greskaIme}}</td></tr>
					
					<tr><th scope="row">Prezime: </th>
					<td ><input type="text" class="form-control" v-model="lekar.prezime"></td><td>{{greskaPrezime}}</td></tr>
					
					<tr><th scope="row">Telefon: </th>
					<td><input type="text" class="form-control" v-model="lekar.telefon"></td><td>{{greskaTelefon}}</td></tr>
					
					<tr><th scope="row">Drzava: </th>
					<td><input type="text" class="form-control" v-model="lekar.drzava"></td><td>{{greskaDrzava}}</td></tr>
					
					<tr><th scope="row">Grad: </th>
					<td><input type="text" class="form-control" v-model="lekar.grad"></td><td>{{greskaGrad}}</td></tr>
					
					<tr><th scope="row">Adresa: </th>
					<td><input type="text" class="form-control" v-model="lekar.adresa"></td><td>{{greskaAdresa}}</td></tr>
					
					<tr><th scope="row">Pocetak smene: </th>
					<td><input type="text" class="form-control" v-model="pocetak"></td><td>{{greskaPocetak}}</td></tr>
					
					<tr><th scope="row">Kraj smene: </th>
					<td><input type="text" class="form-control" v-model="kraj"></td><td>{{greskaKraj}}</td></tr>
					
					<tr><th scope="row">Lozinka: </th>
					<td ><input type="password" class="form-control" v-model="novaLozinka"></td><td>{{greskaNovaLozinka}}</td></tr>
					
					<tr><th scope="row">Ponovljena lozinka: </th>
					<td><input type="password" class="form-control" v-model="ponovljenaLozinka"></td><td>{{greskaPonovljenaLozinka}}</td></tr>
					
					<tr v-if="this.tip==='lekar'"><th scope="row">Specijalizacija: </th>
					<td><select v-model="nazivSpecijalizacije" class="form-control">
						<option v-for="s in specijalizacije">{{s.naziv}}</option>
					</select></td><td>{{greskaSpecijalizacija}}</td></tr>
					
					
					
				</tbody>	
			</table>
			<br>
			<div class="row justify-content-center">
				<button v-on:click="dodajLekara()" class="btn btn-success">KREIRAJ PROFIL</button>
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
		
		axios 
		.get("/tipPosete/admin/pregled")
		.then(response => {
			this.specijalizacije = response.data
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
		
		nazivSpecijalizacije: function(){
			for (let s of this.specijalizacije){
				if (s.naziv === this.nazivSpecijalizacije)
					this.lekar.specijalizacija = s.id;
			}
		}
	}, 
	
	methods: {
		
		pocetakF: function() {

			if (!this.pocetak.includes(':') && (this.pocetak.length==2)) {
				this.lekar.pocetnoVreme = this.pocetak.concat(":00"); 
			}
			if (!this.pocetak.includes(':') && (this.pocetak.length==1)) {
				this.lekar.pocetnoVreme = '0'.concat(this.pocetak,":00"); 
			}
			if (this.pocetak.includes(':') && (this.pocetak.length==5)) {
				this.lekar.pocetnoVreme=this.pocetak;
			}

			return this.lekar.pocetnoVreme; 

		},
			
		krajF: function() {
			
			if (!this.kraj.includes(':') && (this.kraj.length==2)) {
				this.lekar.krajnjeVreme = this.kraj.concat(":00"); 
			}
			if (!this.kraj.includes(':') && (this.kraj.length==1)) {
				this.lekar.krajnjeVreme = '0'.concat(this.kraj,":00");
			}
			if (this.kraj.includes(':') && (this.kraj.length==5)) {
				this.lekar.krajnjeVreme=this.kraj;
			}

			return this.lekar.krajnjeVreme;

		},
		
		emailProvera: function (email){
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
			this.greskaPocetak = '';
			this.greskaKraj = '';
			this.greskaSpecijalizacija = '';
			this.greska = false;
		}, 
		
		registrujSestru() {
			axios.post("/sestra/kreiranje", this.lekar)
			.then(response => {
				alert("Medicinska sestra uspesno kreirana!");
				this.$router.push("/adminHome");
			})
			.catch((error) => {
				this.greskaEmail = "Email mora biti jedinstven. ";
			});
		}, 
		
		registrujLekara() {
			axios.post("/lekar/kreiranje", this.lekar)
			.then(response => {
				alert("Lekar uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch((error) => {
				this.greskaEmail = "Email mora biti jedinstven. ";
			});
		},
		
		dodajLekara: function(){
			
			this.osvezi();
			this.pocetakF();
			this.krajF();
			
			this.lekar.lozinka = this.novaLozinka;
			this.lekar.klinika = this.klinika.id;

			if (!this.pocetak.includes(':') && ((this.pocetak.length>2 || this.pocetak.length<1) || parseInt(this.pocetak)>25)) {
				this.greskaPocetak = "Nespravan format.";
				this.greska = true;
			}
			if (this.pocetak.includes(':') && (this.pocetak.length != 5)) {
				this.greskaPocetak = "Nespravan format.";
				this.greska = true;
			}
			
			if (!this.kraj.includes(':') && ((this.kraj.length > 2 || this.kraj.length < 1) || parseInt(this.kraj)>25)) {
				this.greskaKraj = "Nespravan format.";
				this.greska = true;
			}
			if (this.kraj.includes(':') && (this.kraj.length != 5)) {
				this.greskaKraj = "Nespravan format.";
				this.greska = true;
			}
			if (parseInt(this.kraj)<parseInt(this.pocetak)) {
				this.greskaKraj = "Nespravan format.";
				this.greskaPocetak = "Nespravan format.";
				this.greska = true;
			}
			
			if (!this.emailProvera(this.lekar.email)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.lekar.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.lekar.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.lekar.telefon == ''){
				this.greskaTelefon = "Telefon ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.lekar.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.lekar.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.lekar.adresa == ''){
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
			
			if (this.tip === 'lekar' && this.lekar.specijalizacija == '') {
				this.greskaSpecijalizacija = "Specijalizacija na sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			if (this.tip === "lekar")
				this.registrujLekara();
			else 
				this.registrujSestru();
			
		}
	}
	
});
