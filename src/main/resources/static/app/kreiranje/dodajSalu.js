Vue.component("dodajSalu", {

	data: function(){
		return {
			sala: {
				'id': null,
				'broj': '', 
				'naziv': '', 
				'klinika': null, 
				'aktivan': true
			}, 
			greskaBroj: '', 
			greskaNaziv: '', 
			greska: false,  
			klinika: null
		}
	}, 
	
	template: `
	
	<div>
	<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#/dodajSalu">KREIRANJE SALE</a>
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
	
	<div class = "container">
		<div class="card" id="box">
		
			<h2 class="row justify-content-center">Kreiranje sale</h2><br>
		
			<table class="table">
				
					<tr><th scope="row">Broj: </th>
					<td><input class="form-control" type="text" v-model="sala.broj"></td><td>{{greskaBroj}}</td></tr>
					
					<tr><th scope="row">Naziv: </th>
					<td><input class="form-control" type="text" v-model="sala.naziv"></td><td>{{greskaNaziv}}</td></tr>					
				</table>
				<br>
			<div class="row justify-content-center">
				<button v-on:click="dodajSalu()" class="btn btn-success">KREIRAJ</button>
			</div>
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
			this.greskaNaziv = '';
			this.greskaBroj = '';
			this.greska = false;
		}, 
		
		dodajSalu: function(){
			
			this.osvezi();
			this.sala.klinika = this.klinika.id;

			if (this.sala.broj == ''){
				this.greskaBroj= "Broj ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.sala.naziv=='') {
				this.greskaNaziv = "Naziv ne sme biti prazan.";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/sala/kreiranje", this.sala)
			.then(response => {
				alert("Sala uspesno kreirana!");
				this.$router.push("/adminHome");
			})
			.catch(error => {
				this.greskaBroj = "Unet broj nije jedinstven. ";
			});
			
		}
	}
	
});
