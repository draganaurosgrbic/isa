Vue.component("izvestaj", {
	data: function(){
		return{
			parametar: 'nedeljni',
			graf: {},
			podaci: {},
			ocena: {},
			klinika: '',
			pocetak: '',
			kraj: '',
			profit: '',
			period: {
				pocetak: '',
				kraj: ''
			}
		}

	},
	template: `
	
	<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/izvestaj">IZVESTAJ O POSLOVANJU</a>
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
		<div class="container" id="red"> 
		<div class="row">
		<div>
			<h2 class="row justify-content-center">Klinika "{{klinika}}"</h2><br>
		<table class="table ">
			<tbody>
				<tr><th scope="row" >Prihodi klinike: </th></tr>
				<br>
				<tr>
					<td><input type="date" v-model="period.pocetak"></td>
					<td><input type="date" v-model="period.kraj"></td>
					<td><button v-on:click="nadjiPrihode()" class="btn btn-primary">PRIKAZI</button></td>
				</tr>
				<br>	
				<tr>
					<th scope="row">Profit: </th>
					<td>{{profit}}</td>
				</tr>			
				<tr>
							<th scope="row">Prosecna ocena:</th>
							<td>{{ocena}}</td>
						</tr>		
			</tbody>
			</table>
		</div>
		
		<div class="col-md-5 ">
		<h2 class="row justify-content-center">Grafikon pregleda</h2><br>
				<table >
				<tr><th>&nbsp &nbsp Odaberite nivo:&nbsp </th>
				<td><select v-model="parametar" class="browser-default custom-select">
						<option>dnevni</option>
						<option>nedeljni</option>
						<option>mesecni</option>
						<option>godisnji</option>
					</select></td></tr>
				</table>
			<column-chart :data="podaci"></column-chart>	
		</div>
	</div>
	</div>
	</div>
	`
	,
	watch: {
		parametar: function(){
			if (this.parametar != '') {
				axios.get("/klinika/admin/graf/" + this.parametar)
				.then(response => {
					this.podaci = response.data;
					
				})
				.catch(response => {
					this.$router.push("/");
				});	
			}
				
		},

	},
	mounted() {
		axios.get("/klinika/admin/ocena")
		.then(response => {
			this.ocena = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/klinika/admin/graf/" + this.parametar)
		.then(response => {
			this.podaci = response.data;
			
		})
		
		axios.get("/klinika/admin/naziv")
		.then(response => {
			this.klinika = response.data;
			
		})
		
		.catch(response => {
			this.$router.push("/");
		});	
	}, 
	methods: {
		nadjiPrihode:function() {
			axios.post("/klinika/admin/profit", this.period)
			.then(response => {
				this.profit = response.data;
				console.log(this.profit);
			})
			.catch(response => {
				alert("Datum nije validan!");
			});
		}
	},
	
});