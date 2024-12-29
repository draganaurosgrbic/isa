Vue.component("salaPretraga", {
	
	data: function(){
		return{
			sale: {},
			backup: {},
			pretraga: '',
			showModal: false,
			greskaNaziv: '',
			greska: false,
			salaSelected : '',
			nemaRezultata: '',
			naziv: ''
		}
	}, 

	template: `
	
	<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/salaPretraga">PRETRAGA SALA</a>
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
       <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretrazite" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretrazi</button>
    </form>
  </div>
</nav>
	<table class="table">
		<tr >
			<th>Naziv </th>
			<th> Broj </th>
		</tr>
		
		<tr v-for="s in sale">
			<td>{{s.naziv}}</td>
			<td>{{s.broj}}</td>
			<td><button  @click="showModal = true" v-on:click="selektovanaSala(s)" class="btn btn-success"><i class="fa fa-pencil "></i>Izmeni</button></td>
			<td><button v-on:click="deleteSala(s.id)" class="btn btn-danger"><i class="fa fa-trash"></i>Obrisi</button></td></tr>
	</table>	
	
		<h3>{{nemaRezultata}}</h3>
	
	<div>
		<modal v-if="showModal" @close="showModal = false">
        	<h3 slot="header">Izmena sale</h3>
			<div slot="body">
				
				<table class="table">
				
				<tbody>
					<tr>
						<th scope="row">Naziv: </th>
						<td colspan="2"><input type="text" v-model="salaSelected.naziv" class="form-control" ></td>
						<td>{{greskaNaziv}}</td>
					</tr>
					
					<tr>
						<th scope="row">Broj: </th>
						<td ><input type="text" v-model="salaSelected.broj" class="form-control" disabled></td>
					</tr>					
				</tbody>
			</table>		

			</div>
        					
        		<div slot="footer">
        		<button @click="showModal=false" style="margin:5px;" class="btn btn-dark" v-on:click="izmeni()"> Sacuvaj </button>       						
				<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="backupData()"> Nazad </button>								
				</div>
		</modal>
		
	</div>
	</div>
	
	`, 
	
	mounted(){
		
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.sale = response.data;
			this.backup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		backupData : function() {
			this.salaSelected.naziv = this.naziv;
		},
		osvezi: function() {
			this.greskaNaziv = '';
			this.greska = false;
		},
		izmeni: function() {
			this.osvezi();
			this.showModal = false;
			if (this.salaSelected.naziv==''){
				this.greskaNaziv = "Unesite naziv sale.";
				this.greska = true;
			}
			if (this.greska){return;}
			
			axios.post("/sala/kreiranje", this.salaSelected)
			.then(response => {
				alert('Izmene uspesno sacuvane!');
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		},

		selektovanaSala: function(s) {
			this.salaSelected = s;
			this.naziv = this.salaSelected.naziv;
		},
		
		search: function(){
			
			this.sale = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let s of this.backup){
            	let passedNaziv = (this.pretraga != '') ? (s.naziv.toLowerCase().includes(lowerPretraga)) : true;
                let passedBroj = (this.pretraga != '') ? (s.broj.toLowerCase().includes(lowerPretraga)) : true;                    
                if (passedNaziv || passedBroj) this.sale.push(s);                    
            }
            
            if (this.sale.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteSala: function(id) {
			axios.delete("/sala/brisanje/" + id)
			.then(response => {
				alert("Sala uspesno obrisana!");
				location.reload();
			})
			.catch(error => {
				alert("Sala je zakazana za posete i ne moze biti obrisana!");
			});

		}
		
	},
	
});