Vue.component("sestrePretraga", {
	
	data: function(){
		return{
			sestre: {},
			backup: {},
			pretraga: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/sestrePretraga">PRETRAGA MEDICINSKOG OSOBLJA</a>
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
		<tr bgcolor="#f2f2f2">
			<th> Ime </th>
			<th> Prezime </th>
		</tr>
		
		<tr v-for="s in sestre">
			<td>{{s.ime}}</td>
			<td>{{s.prezime}}</td>
			<td><button v-on:click="deleteS(s.id)" class="btn btn-danger"><i class="fa fa-trash "></i>Obrisi</button></td></tr>
	</table>	
		
		<h3>{{nemaRezultata}}</h3>
	
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/sestra/admin/pregled")
		.then(response => {
			this.sestre = response.data;
			this.backup= response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		
		search: function(){
			
			this.sestre = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let l of this.backup){
            	let imePrezime = (l.ime.concat(" ",l.prezime)).toLowerCase();
            	let prezimeIme = (l.prezime.concat(" ", l.ime)).toLowerCase();
            	if (lowerPretraga.includes(" ")) { //ime i prezime
            		let passedImePrezime = (this.pretraga != '') ? (imePrezime.includes(lowerPretraga) || imePrezime === lowerPretraga) : true;
                    let passedPrezimeIme = (this.pretraga != '') ? (prezimeIme.includes(lowerPretraga) || prezimeIme === lowerPretraga) : true;
                    if (passedImePrezime || passedPrezimeIme ) this.sestre.push(l);
            	}
            	else { //ili ime ili prezime
            		let passedIme = (this.pretraga != '') ? (l.ime.toLowerCase().includes(lowerPretraga)) : true;
                    let passedPrezime = (this.pretraga != '') ? (l.prezime.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedIme  || passedPrezime) this.sestre.push(l);
            	}
                                
            }
            
            if (this.sestre.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteS: function(id) {
			axios.delete("/sestra/brisanje/" + id)
			.then(response => {
				alert("Sestra uspesno obrisana!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!!");
			});

		}
		
	},
	
});