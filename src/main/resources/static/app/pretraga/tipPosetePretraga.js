Vue.component("tipPosetePretraga", {
	
	data: function(){
		return{
			tipoviPregleda: {},
			backup: {},
			tipSelected: {}, 
			selectedCenovnik: false,
			showModal: false,
			brisanjeSelected: false,
			operacija: 'Operacija',
			pregled: 'Pregled',
			pretraga: '',
			greskaSati: '',
			greskaMinuti: '',
			greskaCena: '',
			nemaRezultata: '',
			sati: '',
			minuti: '',
			cena: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/tipPosetePretraga">PRETRAGA TIPOVA PREGLEDA</a>
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
      <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" @click.prevent="selektovanCenovnik()" href="#">
          <i class="fa fa-money"></i>
          Cenovnik
          <span class="sr-only">(current)</span>
          </a>
      </li>
      </ul>
       <form  class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Unesite naziv tipa pregleda" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretrazi</button>
    </form>
  </div>
</nav>
	<table class="table">
		<tr >
			<th> Naziv </th>
			<th> Tip </th>
		</tr>
		
		<tr v-for="t in tipoviPregleda" >
			<td >{{t.naziv}}</td>
			<td v-if="t.pregled">pregled</td>
			<td v-else>operacija</td>
			<td v-if="selectedCenovnik" > {{t.cena}}</td>
			<td><button  @click="showModal = true" v-on:click="selektovanTip(t)" class="btn btn-success"><i class="fa fa-pencil "></i>Izmeni</button></td>
			<td><button v-on:click="deleteTipPosete(t.id)" class="btn btn-danger"><i class="fa fa-trash "></i>Obrisi</button></td></tr>
	
	</table>	
		<h3>{{nemaRezultata}}</h3>
		
	
	<div>
		<modal v-if="showModal" @close="showModal = false">
        	<h3 slot="header">Izmena tipa posete</h3>
			<div slot="body">
				
				<table class="table">
				
				<tbody>
					<tr>
						<th scope="row">Naziv: </th>
						<td colspan="2"><input type="text" v-model="tipSelected.naziv" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Tip: </th>
						<td v-if="tipSelected.pregled"><input type="text" v-model="pregled" class="form-control" disabled></td>
						<td v-else> <input type="text" v-model="operacija" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Sati: </th>
						<td><input type="number" min="0" v-model="tipSelected.sati" class="form-control"></td>
						<td>{{greskaSati}}</td>
					</tr>
					
					<tr>
						<th scope="row">Minuti: </th>
						<td><input type="number" min="30" v-model="tipSelected.minute" class="form-control"></td>
						<td>{{greskaMinuti}}</td>
					</tr>
					
					<tr>
						<th scope="row">Cena: </th>
						<td><input type="number" min="1000" v-model="tipSelected.cena" class="form-control"></td>
						<td>{{greskaCena}}</td>
					</tr>
					<tr>
					
					</td>
					</tr>
				</tbody>
			</table>		

			</div>
        					
        		<div slot="footer">
        		<button @click="showModal=false" style="margin:5px;" class="btn btn-dark" v-on:click="izmeni()"> Sacuvaj </button>       						
				<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="backupData()" > Nazad </button>								
				</div>
		</modal>
		
	</div>
	</div>
	
	`, 
	
	mounted(){
		
		axios.get("/tipPosete/admin/pregled")
		.then(response => {
			this.tipoviPregleda = response.data;
			this.backup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		backupData : function() {
			this.tipSelected.sati = this.sati;
			this.tipSelected.minute = this.minuti;
			this.tipSelected.cena = this.cena;
		},
		osvezi: function() {
			greskaSati = '',
			greskaMinuti = '',
			greskaCena = '',
			this.greska = false;
		},
		izmeni: function() {
			this.osvezi();
			this.showModal = false;
			if (this.tipSelected.sati===''){
				this.greskaSati = "Unesite broj sati";
				this.greska = true;
			}
			if (this.tipSelected.minute===''){
				this.greskaMinuti = "Unesite broj minuta";
				this.greska = true;
			}
			if (this.tipSelected.cena ==''){
				this.greskaCena= "Unesite cenu";
				this.greska = true;
			}
			console.log(this.tipSelected);
			if (this.greska){return;}

			axios.post("/tipPosete/kreiranje", this.tipSelected)
			.then(response => {
				alert('Izmene uspesno sacuvane!');
				location.reload();
			})
			.catch(response => {
				alert("Postoje zakazane posete sa odabranim tipom i nija moguca izmena!");
			});
		},
		selektovanTip: function(t) {
			this.tipSelected = t;
			this.sati = t.sati;
			this.minuti = t.minute;
			this.cena = t.cena;
		},
		
		selektovanCenovnik: function() {
			if (this.selectedCenovnik ){
				this.selectedCenovnik=false;
			}
			else {
				this.selectedCenovnik = true;
			}
			
		},
		
		
		search: function(){
			
			this.tipoviPregleda = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let l of this.backup){
        		let passed = (this.pretraga != '') ? (l.naziv.toLowerCase().includes(lowerPretraga)) : true;
                if (passed) this.tipoviPregleda.push(l);            	
            }
            
            if (this.tipoviPregleda.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteTipPosete: function(id) {
			axios.delete("/tipPosete/brisanje/" + id)
			.then(response => {
				alert("Tip posete uspesno obrisan!");
				location.reload();
			})
			.catch(error => {
				alert("Postoje zakazane posete sa odabranim tipom i nije moguce brisanje!");
			});
		}
		
	},
	
});