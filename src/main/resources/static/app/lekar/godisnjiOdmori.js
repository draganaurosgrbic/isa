Vue.component("godisnjiOdmori", {
	
	data: function(){
		return {
			godisnji: []
		}
	}, 
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/godisnjiOdmori">PREGLED GODISNJIH ODMORA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">

  </div>
</nav>
		
	<div>
		<div class="card" id="tableBox">
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th>Datum Pocetka</th>
					<th>Datum Zavrsetka</th>
					<th>Dani</th>
					<th>Odobren</th>
				</tr>
				
				<tr v-for="g in godisnji" bgcolor="white">
				    <td>{{formatiraj(g.pocetak)}}</td>
					<td>{{formatiraj(g.kraj)}}</td>
					<td>{{g.trajanje}}</td>
					<td>{{g.odobren}}</td>
				</tr>
			</table>
		</div>
	
	</div>
	
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/zaposleni/zahtevOdmor/pregled")
		.then(response => {
			this.godisnji = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		formatiraj: function (date) {
			
			  date = new Date(date);
			  let year = date.getFullYear();
			  let month = (1 + date.getMonth()).toString();
			  month = month.length > 1 ? month : '0' + month;
			  let day = date.getDate().toString();
			  day = day.length > 1 ? day : '0' + day;
			  let hours = date.getHours().toString();
			  hours = hours.length > 1 ? hours : '0' + hours;
			  let minutes = date.getMinutes().toString();
			  minutes = minutes.length > 1 ? minutes : '0' + minutes;
			  return day + '/' + month + '/' + year + " " + hours + ":" + minutes;
			  
		}
	}
	
});