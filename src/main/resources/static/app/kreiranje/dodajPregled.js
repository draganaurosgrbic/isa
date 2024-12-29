Vue.component("dodajPregled", {
	
	data: function(){
		return {
			pregled: {
				"id": null,
				"datum": '', 
				"vreme": '', 
				"tipPregleda": '',
				"sala": '',
				"lekar": '',
				"popust": ''
			}, 
			vreme: '',
			nazivSale: '',
			nazivTipaPregleda: '',
			imePrezimeLekara: '',
			greskaDatum: '', 
			greskaVreme: '', 
			greskaTipPregleda: '',
			greskaSala: '',
			greskaLekar: '',
			greskaPopust: '',
			greska: false,  
			tipoviPregleda: {},
			sale: {},
			lekari: {}
		}
	}, 
	
	template: `
	
	
	<div>
	<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#/dodajPregled">KREIRANJE SLOBODNOG TERMINA PREGLEDA</a>
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
		<div class="container">
		<div class="card" id="tableBox">
		
			<h2 class="row justify-content-center">Kreiranje predefinisanog pregleda</h2><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Datum: </th>
						<td><input type="date" class="form-control" v-model="pregled.datum" name="name"></td>
						<td>{{greskaDatum}}</td>
					</tr>
					
					<tr>
						<th scope="row">Vreme: </th>
						<td><input type="text" class="form-control" v-model="vreme" name="name"></td>
						<td>{{greskaVreme}}</td>
					</tr>
					
					<tr><th scope="row">Tip pregleda: </th>
						<td><select class="form-control" v-model="nazivTipaPregleda">
						<option value="" disabled selected>Odaberite tip pregleda</option>
						<option v-for="p in tipoviPregleda" v-if="p.pregled">{{p.naziv}}</option>
					</select></td> <td>{{greskaTipPregleda}}</td> </tr>
					
					<tr><th scope="row">Sala: </th>
						<td><select class="form-control" v-model="nazivSale">
						<option value="" disabled selected>Odaberite salu</option>
						<option v-for="s in sale">{{s.naziv}}</option>
					</select></td> <td>{{greskaSala}}</td></tr>
					
					<tr><th scope="row">Lekar: </th>
						<td><select v-model="imePrezimeLekara" class="form-control">
						<option value="" disabled selected>Odaberite lekara</option>
						<option v-for="l in lekari">{{l.ime}} {{l.prezime}}</option>
					</select></td> <td>{{greskaLekar}}</td> </tr>
					
					<tr>
						<th scope="row">Popust: </th>
						<td><input type="text" v-model="pregled.popust" name="name" class="form-control"></td>
						<td>{{greskaPopust}}</td>
					</tr>
				</tbody>
			</table>
			<div class="row justify-content-center">
				<button v-on:click="dodajPregled()" class="btn btn-success">KREIRAJ</button>
			</div>
		</div>
		
		</div>
		</div>
	
	`,
	
	mounted(){
		
		axios.get("/tipPosete/admin/pregled")
		.then(response => {
			this.tipoviPregleda = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.sale = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/lekar/admin/pregled")
		.then(response => {
			this.lekari = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	},
	
	watch: {
		nazivTipaPregleda: function(){
			for (let t of this.tipoviPregleda){
				if (t.naziv === this.nazivTipaPregleda)
					this.pregled.tipPregleda = t.id;
			}
		},
		nazivSale: function(){
			for (let s of this.sale){
				if (s.naziv === this.nazivSale)
					this.pregled.sala = s.id;
			}
		},
		imePrezimeLekara: function(){
			for (let l of this.lekari){
				if (l.ime.concat(" ", l.prezime) === this.imePrezimeLekara)
					this.pregled.lekar = l.id;
			}
		}
	
	},
	
	methods: {
		
		vremePromena: function() {

			if (!this.vreme.includes(':') && (this.vreme.length==2)) {
				this.pregled.vreme = this.vreme.concat(":00"); 
			}
			if (!this.vreme.includes(':') && (this.vreme.length==1)) {
				this.pregled.vreme = '0'.concat(this.vreme,":00"); 
			}
			if (this.vreme.includes(':') && (this.vreme.length==5)) {
				this.pregled.vreme=this.vreme;
			}
			return this.pregled.vreme; 
		},
		
		osvezi: function(){
			this.greskaDatum = '';
			this.greskaVreme = '';
			this.greskaTipPregleda = '';
			this.greskaSala = '';
			this.greskaLekar = '';
			this.greskaPopust = '';
			this.greska = false;
		},
		
		dodajPregled : function() {
			
			this.osvezi();
			this.vremePromena();
			
			if (this.pregled.datum == '') {
				this.greskaDatum = "Morate uneti datum.";
				this.greska = true;
			}
			
			if (new Date(this.pregled.datum) <= new Date()){
				this.greskaDatum = "Datum ne sme biti manji od trenutnog.";
				this.greska = true;
			}

			if (this.pregled.vreme == '') {
				this.greskaVreme = "Morate uneti vreme.";
				this.greska = true;
			}
			if (this.pregled.tipPregleda == '') {
				this.greskaTipPregleda = "Obavezno polje!";
				this.greska = true;
			}
			if (this.pregled.sala == '') {
				this.greskaSala = "Obavezno polje!";
				this.greska = true;
			}
			
			if (this.pregled.lekar == '') {
				this.greskaLekar = "Obavezno polje!";
				this.greska = true;
			}
			if (isNaN(parseFloat(this.pregled.popust))) {
				this.greskaPopust = "Obavezno polje! ";
				this.greska = true;
			} 
			if (parseFloat(this.pregled.popust) < 0 || parseFloat(this.pregled.popust) > 1 ){
				this.greskaPopust = "Popust nije ispravan. ";
				this.greska = true;
			}
			if (!this.vreme.includes(':') && ((this.vreme.length>2 || this.vreme.length<1) || parseInt(this.vreme)>25)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			if (this.vreme.includes(':') && (this.vreme.length != 5)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			if (this.greska) {return;}
			
			axios.post("/poseta/kreiranje", this.pregled)
			.then(response => {
				alert("Pregled uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch((error) => {
				alert("LEKAR JE ZAUZET, ODABERITE DRUGI TERMIN!");
			});
		},
	}
	
});