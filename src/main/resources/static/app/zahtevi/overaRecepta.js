Vue.component("overaRecepta", {
	
	data: function(){
		return{
			recepti: []
		}
	},
	
	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/overaRecepta">OVERA RECEPATA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/sestraHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
  </div>
</nav>

	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> ID </th>
			<th> Broj Osiguranika </th>
			<th> Lekovi </th>
			<th> Dijagnoze </th>
			<th></th>
		</tr>
		
		<tr v-for="r in recepti">
		    <td>{{r.id}}</td>
			<td>{{r.brojOsiguranika}}</td>
			<td>
			<select>
				<option v-for="l in r.lekovi">{{l}}</option>
			</select>
			</td>
			<td>
			<select>
				<option v-for="d in r.dijagnoze">{{d}}</option>
			</select>
			</td>
			<td>
	        	<button class="btn btn-success" v-on:click="overi(r.id)">Overi</button>
			</td>
		</tr>
	</table>
	
		</div>
	
	`, 
	
	mounted(){
		axios.get("/terapija/neovereno")
		.then(response => {
			this.recepti = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
	},
	
	methods: {
		
		overi: function(rid) {
						
			if (rid === "") {
				alert("Morate odabrati recept!");
				return;
			}
			
			axios.get("/terapija/overi/" + rid)
			.then(response => {
				alert("Recept uspesno overen!");
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!");
			});
		}
	}
});