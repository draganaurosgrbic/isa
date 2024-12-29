Vue.component("zahtevRegistracijaObrada", {
	
	data: function(){
		return {
			zahtevi: [],
			selected: {
				id: '',
				razlog: ''
			}, 
			error: false
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/zahtevRegistracijaObrada">ZAHTEVI ZA <br> REGISTRACIJU PACIJENATA</a>
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
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <a class="navbar-brand" href="#" disabled>Odaberite zahtev: </a>
	  <select class="form-control mr-sm-2" v-model="selected.id">
       	<option v-for="z in zahtevi">{{z.id}}</option>
	  </select>
	  <input class="form-control mr-sm-2" type="text" v-model="selected.razlog" placeholder="Razlog Odbijanja" aria-label="Razlog Odbijanja">
      <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="potvrdi()">Potvrdi</button>
      <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="odbij()">Odbij</button>
    </form>
  </div>
</nav>

	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> ID </th>
			<th> Email </th>
			<th> Ime </th>
			<th> Prezime </th>
			<th> Broj Osiguranika </th>
			<th> Telefon </th>
			<th> Drzava </th>
			<th> Grad </th>
			<th> Adresa </th>
		</tr>
		
		<tr v-for="z in zahtevi">
		    <td>{{z.id}}</td>
			<td>{{z.email}}</td>
			<td>{{z.ime}}</td>
			<td>{{z.prezime}}</td>
			<td>{{z.brojOsiguranika}}</td>
			<td>{{z.telefon}}</td>
			<td>{{z.drzava}}</td>
			<td>{{z.grad}}</td>
			<td>{{z.adresa}}</td>
		</tr>
	</table>	
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/zahtevRegistracija/pregled")
		.then(response => {
			this.zahtevi = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		potvrdi: function() {
			
			if (this.selected.id === "") {
				alert("Morate odabrati zahtev!");
				return;
			}
			
			axios.post("/zahtevRegistracija/potvrda", this.selected)
			.then(response => {
				alert("Zahtev uspesno odobren!");
				location.reload();
			})
			.catch(error => {
				alert("Zahtev nije moguce odobriti! Email ili broj osiguranika nije jedinstven!");
			});
		},
		
		odbij: function() {
			
			this.error = false;
			let errorMessage = '';
			
			if (this.selected.id === "") {
				errorMessage = "Morate odabrati zahtev!";
				this.error = true;
			}
			
			if (this.selected.razlog === "") {
				errorMessage += "\nMorate uneti razlog odbijanja!";
				this.error = true;
			}
			
			if (this.error) {
				alert(errorMessage);
				return;
			}
			
			axios.post("/zahtevRegistracija/odbijanje", this.selected)
			.then(response => {
				alert("Zahtev odbijen!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		}
	},
	
});