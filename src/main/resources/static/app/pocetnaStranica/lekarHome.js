Vue.component("lekarHome", {
	
	data: function(){
		return{
			izvestaj: {
				'id': null,
				'opis': '',
				'dijagnoze': [],
				'lekovi': []
			},
			zapoceto: false,
			posetaUtoku: '',
			dijagnoze: [],
			lekovi: []
		}
	}, 
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarPacijenti">
          <i class="fa fa-list"></i>
          Lista pacijenata
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/radniKalendar">
          <i class="fa fa-calendar"></i>
          Radni kalendar
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/godisnjiOdmori">
          <i class="fa fa-calendar"></i>
          Godisnji Odmori
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/zahtevOdmorSlanje">
          <i class="fa fa-plane"></i>
          Zahtev za odmor
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/lekarProfil">
          <i class="fa fa-user-md"></i>
          Profil
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>
		<h2>{{posetaUtoku}}</h2>
		<div class="card" id="tableBox" v-if="zapoceto">
			
			<h2 class="row justify-content-center">Unos izvestaja o poseti</h2><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Opis: </th>
						<td><input type="text" v-model="izvestaj.opis" class="form-control"></td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Dijagnoze: </th>
						<td>
							<select v-model="izvestaj.dijagnoze" multiple style="min-width: 300px;">
				                <option v-for="d in dijagnoze" :value="d.id">
				                    {{d.naziv}}
				                </option>
			                </select>
			            </td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Lekovi: </th>
						<td>
							<select v-model="izvestaj.lekovi" multiple style="min-width: 300px;">
				                <option v-for="l in lekovi" :value="l.id">
				                    {{l.naziv}}
				                </option>
			                </select>
			            </td>
						<td></td>
					</tr>

					<tr>
						<td><button v-on:click="zakaziNovuPosetu()" class="btn btn-success float-left">ZAKAZI NOVU POSETU</button></td>
						<td><button v-on:click="zavrsi()" class="btn btn-success float-right">ZAVRSI POSETU</button></td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
		</div>
	
	`, 
	
	mounted(){

		axios.get("/user/check/lekar")
		.catch(reponse => {
			this.$router.push("/sestraHome");
		});
	
		
		axios.get("/poseta/zapoceto")
		.then(response => {
			this.zapoceto = true;
			this.posetaUtoku = "";
		})
		.catch(response => {
			this.posetaUtoku = "Nemate zapocetu posetu.";
			this.zapoceto = false;
			this.$router.push("/lekarHome");
		});
		
		axios.get("/dijagnoza/pregled")
		.then(response => {
			this.dijagnoze = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/lek/pregled")
		.then(response => {
			this.lekovi = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
	},

	methods: {
		zakaziNovuPosetu: function() {
			this.$router.push("/zahtevPosetaSlanje");
		},
		
		zavrsi: function() {
			axios.post("/poseta/zavrsi", this.izvestaj)
			.then(response => {
				this.zapoceto = false;
				alert('Izvestaj uspesno unesen!');
				this.$router.push("/lekarHome");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}
		
	}
	
});