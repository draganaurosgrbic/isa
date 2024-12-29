Vue.component("radniKalendar", {	
	data: function(){
		return {
			obaveze: [],
			trenutni: [],
			dnevni: {},
			nedeljni: {},
			mesecni: {},
			godisnji: {},
			selectedObaveze: [],
			trenutnaPostoji: false
		}
	}, 
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/radniKalendar">RADNI KALENDAR</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/radniKalendar" v-on:click="prikaziDnevni()">
          <i class="fa fa-calendar"></i>
          Dnevni
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/radniKalendar" v-on:click="prikaziNedeljni()">
          <i class="fa fa-calendar"></i>
          Nedeljni
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/radniKalendar" v-on:click="prikaziMesecni()">
          <i class="fa fa-calendar"></i>
          Mesecni
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/radniKalendar" v-on:click="prikaziGodisnji()">
          <i class="fa fa-calendar"></i>
          Godisnji
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul v-if="trenutnaPostoji" class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/zapocetPregled">
          <i class="fa fa-stethoscope"></i>
          Trenutni Pregled
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
  </div>
</nav>
		
	<div>
		<div class="radniKalendarLevaTabelaDiv">
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th> Period </th>
					<th> Br. Pregleda </th>
					<th> Br. Operacija </th>
				</tr>
				
				<tr v-for="(value, key) in trenutniSorted" v-on:click="prikaziDetalje(value.obaveze)" bgcolor="white">
				    <td>{{value.period}}</td>
					<td>{{value.brPregleda}}</td>
					<td>{{value.brOperacija}}</td>
				</tr>
			</table>
		</div>
		
		<div class="radniKalendarDesnaTabelaDiv">
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th> Pocetak </th>
					<th> Trajanje </th>
					<th> Tip </th>
					<th> Pacijent </th>
					<th> Akcija </th>
				</tr>
				
				<tr v-for="obaveza in selectedObavezeSorted" bgcolor="white">
				    <td>{{obaveza.pocetak}}</td>
					<td>{{obaveza.trajanje}}</td>
					<td>{{obaveza.tip}}</td>
					<td>{{obaveza.pacijent}}</td>
					<td>
						<button v-if="can_start(obaveza.pocetak) && !trenutnaPostoji" class="btn btn-outline-success my-2 my-sm-0" v-on:click="zapocni(obaveza.id)">Zapocni</button>
					    <button v-if="can_cancel(obaveza.pocetak)" class="btn btn-outline-success my-2 my-sm-0" v-on:click="otkazi(obaveza.id)">Otkazi</button>
					</td>
				</tr>
			</table>
		</div>
	
	</div>
	
		</div>
	
	`, 
	
	computed: {
		trenutniSorted: function () {
			return _.orderBy(this.trenutni, 'period');
		},
	
		selectedObavezeSorted: function () {
			return _.orderBy(this.selectedObaveze, 'datum');
		}
	},
	
	mounted(){
		
		axios.get("/poseta/zapoceto")
		.then(response => {
			this.trenutnaPostoji = true;
		})
		.catch(response => {
			this.trenutnaPostoji = false;
		});
		
		axios.get("/lekar/obaveze")
		.then(response => {
			this.obaveze = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	},
	
	methods: {
		can_start: function(pocetak) {
			let datum = Date.parse(pocetak);
						
			if (((Date.now() - datum) / 1000 / 60) < -10)
				return false;
			
			if (((Date.now() - datum) / 1000 / 60) > 20)
				return false;

			return true;
		},
		
		can_cancel: function(pocetak) {
			let datum = Date.parse(pocetak);
			
			if (((datum - Date.now()) / 1000 / 60 / 60) >= 24)
				return true;
			
			return false;
		},
		
		zapocni: function(id) {
			if (id === "")
				return;
			
			axios.get("/poseta/zapocni/" + id)
			.then(response => {
				this.$router.push("/lekarHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		},
		
		otkazi: function(id) {
			if (id === "")
				return;
			
			axios.delete("/poseta/otkazi/" + id)
			.then(response => {
				alert("Poseta uspesno otkazana!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		},
		
		prikaziDetalje: function(obaveze) {
			this.selectedObaveze = obaveze;
		},
		
		compare: function(a, b) {
			  const datumA = a.datumSortiranje;
			  const datumB = b.datumSortiranje;

			  let comparison = 0;
			  if (datumA > datumB) {
			    comparison = 1;
			  } else if (datumA < datumB) {
			    comparison = -1;
			  }
			  return comparison;
		},
		
		prikaziDnevni: function() {
			this.selectedObaveze = [];
			
			this.dnevni = this.obaveze.reduce(function (acc, obaveza) {
				let period = obaveza.datum;
				
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				
				acc[period].obaveze.push(obaveza);
				
				return acc;

			}, {});
						
			this.trenutni = this.dnevni;
		},
	
		prikaziNedeljni: function() {
			this.selectedObaveze = [];

			this.nedeljni = this.obaveze.reduce(function (acc, obaveza) {
				let period = moment(obaveza.datum).startOf('isoWeek').format("YYYY-MM-DD") + ' - ' + moment(obaveza.datum).endOf('isoWeek').format("YYYY-MM-DD");
				
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				  
				acc[period].obaveze.push(obaveza);
				
				return acc;

			}, {});
			
			this.trenutni = this.nedeljni;
		},
		
		prikaziMesecni: function() {
			this.selectedObaveze = [];

			this.mesecni = this.obaveze.reduce(function (acc, obaveza) {
				let period = moment(obaveza.datum).format("YYYY-MM");
				
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				
				acc[period].obaveze.push(obaveza);
				acc[period].obaveze.sort(this.compare);

				return acc;

			}, {});
						
			this.trenutni = this.mesecni;
		},
		
		prikaziGodisnji: function() {
			this.selectedObaveze = [];

			this.godisnji = this.obaveze.reduce(function (acc, obaveza) {
				let period = moment(obaveza.datum).format("YYYY");
				
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				
				acc[period].obaveze.push(obaveza);
				acc[period].obaveze.sort(this.compare);

				return acc;

			}, {});
			
			this.trenutni = this.godisnji;
		}
	}
});