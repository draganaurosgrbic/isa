Vue.component("dijagnozePretraga", {
	
	data: function(){
		return {
			dijagnoze: [],
			backupDijagnoze: [],
			pretraga: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/dijagnozePretraga">PRETRAGA DIJAGNOZA</a>
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
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-sort"></i>
          Sortiraj po
          <span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" @click.prevent="sifra_sort()" href="#">Sifri</a>
          <a class="dropdown-item" @click.prevent="naziv_sort()" href="#">Nazivu</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
    </ul>
       <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Search</button>
    </form>
  </div>
</nav>

	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Sifra </th>
			<th> Naziv </th>
		</tr>
		
		<tr v-for="d in dijagnoze">
			<td>{{d.sifra}}</td>
			<td>{{d.naziv}}</td>
			<td><button v-on:click="deleteDijagnoza(d.id)" class="btn"><i class="fa fa-trash fa-2x"></i>Obrisi</button></td></tr>
	
	</table>	
	
		<h3>{{nemaRezultata}}</h3>
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/dijagnoza/pregled")
		.then(response => {
			this.dijagnoze = response.data;
			this.backupDijagnoze = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		
		sifra_sort: function(){
			let lista = this.dijagnoze;
			this.dijagnoze = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].sifra > lista[i].sifra) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.dijagnoze = lista;
		},
		
		naziv_sort: function(){
			let lista = this.dijagnoze;
			this.dijagnoze = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].naziv > lista[i].naziv) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.dijagnoze = lista;
		},
		
		search: function(){

			this.dijagnoze = [];
			this.nemaRezultata = '';
            let lowerPretraga = this.pretraga.toLowerCase();
            
            for (let d of this.backupDijagnoze){
            	
            	let sifraNaziv = (d.sifra.concat(" ",d.naziv)).toLowerCase();
            	let nazivSifra = (d.naziv.concat(" ", d.sifra)).toLowerCase();
            	
            	if (lowerPretraga.includes(" ")) { //sifra i naziv
            		let passedSifraNaziv = (this.pretraga != '') ? (sifraNaziv.includes(lowerPretraga) || sifraNaziv === lowerPretraga) : true;
                    let passedNazivSifra = (this.pretraga != '') ? (nazivSifra.includes(lowerPretraga) || nazivSifra === lowerPretraga) : true;
                    if (passedSifraNaziv || passedNazivSifra ) this.dijagnoze.push(d);
            	}
            	
            	else { //ili sifra ili naziv
            		let passedSifra = (this.pretraga != '') ? (d.sifra.toLowerCase().includes(lowerPretraga)) : true;
                    let passedNaziv = (this.pretraga != '') ? (d.naziv.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedSifra  || passedNaziv) this.dijagnoze.push(d);
            	}
                                
            }
            
            if (this.dijagnoze.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
            
		},
		
		deleteDijagnoza: function(id) {
			
			axios.delete("/dijagnoza/brisanje/" + id)
			.then(response => {
				alert("Dijagnoza uspesno obrisana!");
				location.reload();
			})
			.catch(error => {
				alert("Dijagnoza je u upotrebi i ne moze biti obrisana!");
			});

		}
		
	},
	
});