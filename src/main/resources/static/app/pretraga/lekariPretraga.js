Vue.component("lekariPretraga", {
	
	data: function(){
		return{
			lekari: {},
			backupLekari: {},
			pretraga: '',
			nemaRezultata: ''
		}
	},
	template: `
	
	<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/lekariPretraga">PRETRAGA LEKARSKOG OSOBLJA</a>
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
			<th> Ime </th>
			<th> Prezime </th>
			<th> Ocena </th>
		</tr>
		
		<tr v-for="l in lekari">
			<td>{{l.ime}}</td>
			<td>{{l.prezime}}</td>
			<td>{{l.prosecnaOcena}}</td>
			<td><button v-on:click="deleteLekar(l.id)" class="btn btn-danger"><i class="fa fa-trash "></i>Obrisi</button></td></tr>
	</table>	
	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	`, 
	
	mounted() {
		
		axios.get("/lekar/admin/pregled")
		.then(response => {
			this.lekari = response.data;
			this.backupLekari = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		
		search: function(){
			
			this.lekari = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let l of this.backupLekari){
            	let imePrezime = (l.ime.concat(" ",l.prezime)).toLowerCase();
            	let prezimeIme = (l.prezime.concat(" ", l.ime)).toLowerCase();
            	if (lowerPretraga.includes(" ")) { //ime i prezime
            		let passedImePrezime = (this.pretraga != '') ? (imePrezime.includes(lowerPretraga) || imePrezime === lowerPretraga) : true;
                    let passedPrezimeIme = (this.pretraga != '') ? (prezimeIme.includes(lowerPretraga) || prezimeIme === lowerPretraga) : true;
                    if (passedImePrezime || passedPrezimeIme ) this.lekari.push(l);
            	}
            	else { //ili ime ili prezime
            		let passedIme = (this.pretraga != '') ? (l.ime.toLowerCase().includes(lowerPretraga)) : true;
                    let passedPrezime = (this.pretraga != '') ? (l.prezime.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedIme  || passedPrezime) this.lekari.push(l);
            	}
                                
            }
            
            if (this.lekari.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteLekar: function(id) {
			axios.delete("/lekar/brisanje/" + id)
			.then(response => {
				alert("Lekar uspesno obrisan!");
				location.reload();
			})
			.catch(error => {
				alert("Lekar ima zakazane posete i ne moze biti obrisan!");
			});

		}
		
	},
	
});