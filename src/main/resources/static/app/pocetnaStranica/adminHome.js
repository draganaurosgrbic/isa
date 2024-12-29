Vue.component("adminHome", {
	data: function(){
		return{
			klinika: {},
			zahteviBroj: '',
			zahteviPosetaBroj: '',
			zahteviOperacijaBroj: '',
			greskaNaziv: '', 
			greskaAdresa: '', 
			ulica: '',
			grad: '',
			url: '',
			defaultURL: 'http://www.mapquestapi.com/geocoding/v1/address?key=RsieL5Qcb2EAtLOSE3fmKCkWxGetBnzX&street=Bate+Brkica+8&city=Novi+Sad',
			nemaRezultata: '',
			greska: false
		}
	}, 
	
	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/adminHome">{{klinika.naziv}}</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-address-book">
          </i>
          Medicinsko osoblje
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajLekara">Dodaj medicinskog radnika</a>
          <a class="dropdown-item" href="#/lekariPretraga">Pretraga lekara</a>
          <a class="dropdown-item" href="#/sestrePretraga">Pretraga medicinara</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Tipovi pregleda
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajTipPosete">Dodaj tip pregleda</a>
          <a class="dropdown-item" href="#/tipPosetePretraga">Pretraga tipova</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-hotel">
          </i>
          Sale
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajSalu">Dodaj salu</a>
          <a class="dropdown-item" href="#/salaPretraga">Pretraga sala</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/dodajPregled">
          <i class="fa fa-newspaper-o"></i>
          Kreiraj pregled
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/izvestaj">
          <i class="fa fa-bar-chart"></i>
          Izvestaj
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav ">
      <li class="nav-item">
        <a class="nav-link" href="#/adminProfil">
          <i class="fa fa-user">
          </i>
          Profil
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#/zahtevOdmorObrada">
          <i class="fa fa-globe">
            <span class="badge badge-success">{{zahteviBroj}}</span>
          </i>
          Zahtevi odmor
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#/zahtevPregledObrada">
          <i class="fa fa-globe">
            <span class="badge badge-success">{{zahteviPosetaBroj}}</span>
          </i>
          Zahtevi poseta
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#/zahtevOperacijaObrada">
          <i class="fa fa-globe">
            <span class="badge badge-success">{{zahteviOperacijaBroj}}</span>
          </i>
          Zahtevi Operacije
        </a>
      </li>
    </ul>
  </div>
</nav>
		
		
	<div class="container"> 
		
		<div class="row">
		
			<div class="col card" id="left">
		
			<h2 class="row justify-content-center">Osnovne informacije o klinici</h2><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Naziv klinike: </th>
						<td><input type="text" v-model="klinika.naziv" class="form-control"></td>
						<td>{{greskaNaziv}}</td>
					</tr>
					
					<tr>
						<th scope="row">Opis: </th>
						<td> <textarea v-model="klinika.opis"></textarea> </td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="klinika.adresa" class="form-control"></td>
						<td>{{greskaAdresa}}</td>
					</tr>
				</tbody>
			</table>
			
			<div class="row justify-content-center">
				<button v-on:click="izmeni()" class="btn btn-success">SACUVAJ IZMENE</button>
			</div>
		</div>
		<div class="col card" id="right" style="width: 700px; height: 600px">
		<h2 h2 class="row justify-content-center">Prikaz na mapi</h2><br>
		<h3>{{nemaRezultata}}</h3>
		<div id="map" style="width: 500px; height: 400px"></div>
		</div>
		</div>
		</div>
		
		</div>
	`, 	
	
	mounted(){
		
		axios.get("/klinika/admin/pregled")
		.then(response => {
			this.klinika = response.data
		})
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/zahtevOdmor/klinika/pregled")
		.then(response => {this.zahteviBroj = response.data.length})
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/zahtevPoseta/klinika/pregledi")
		.then(response => {this.zahteviPosetaBroj = response.data.length})
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/zahtevPoseta/klinika/operacije")
		.then(response => {this.zahteviOperacijaBroj = response.data.length})
		.catch(reponse => {
			this.$router.push("/");
		});

		
		ymaps.ready(function () {
			axios.get("/klinika/admin/pregled")
			.then(response => {
				this.klinika = response.data;
				console.log(this.klinika);
				if (this.klinika.adresa.includes(",")) {
					var podaci = this.klinika.adresa.split(",");
					if (podaci[1].includes(" ")){
						this.grad = podaci[1].trim();
						this.grad = this.grad.replace(" ","+");
					}
					else {
						this.grad = podaci[1];
					}
					if (podaci[0].includes(" ")){
						this.ulica = podaci[0].split(" ").join("+");

						console.log(this.ulica);
					}
				}
				axios 
			      .get('http://www.mapquestapi.com/geocoding/v1/address?key=RsieL5Qcb2EAtLOSE3fmKCkWxGetBnzX&street='.concat(this.ulica,'&city=',this.grad))
			      .then(res => {
			    	  console.log(res.data.results[0].locations[0].displayLatLng.lat, res.data.results[0].locations[0].displayLatLng.lng);
					    	var myMap = new ymaps.Map('map', {
					    	center: [res.data.results[0].locations[0].displayLatLng.lat, res.data.results[0].locations[0].displayLatLng.lng],
					    	zoom: 9
				        }, {
				            searchControlProvider: 'yandex#search'
				        }),
				        MyIconContentLayout = ymaps.templateLayoutFactory.createClass(
				            '<div style="color: #FFFFFF; font-weight: bold;">$[properties.iconContent]</div>'
				        ),
				        myPlacemark = new ymaps.Placemark(myMap.getCenter(), {
				            hintContent: this.klinika.adresa,
				            balloonContent: this.klinika.adresa
				        }, {
				            iconLayout: 'default#image',
				            iconImageHref: 'https://findicons.com/files/icons/2796/metro_uinvert_dock/256/google_maps.png',
				            iconImageSize: [48, 48],
				            iconImageOffset: [-5, -38]
				        }),

				        myPlacemarkWithContent = new ymaps.Placemark([res.data.results[0].locations[0].displayLatLng.lat, res.data.results[0].locations[0].displayLatLng.lng], {
				            hintContent: 'A custom placemark icon with contents',
				            balloonContent: 'This one — for Christmas',
				            iconImageSize: [48, 48],
				            iconContent: '12'
				        }, {
				            iconLayout: 'default#imageWithContent',
				            
				            iconImageHref: '',
				            
				            iconImageSize: [48, 48],
				            
				            iconImageOffset: [-24, -24],
				            
				            iconContentOffset: [15, 15],
				            
				            iconContentLayout: MyIconContentLayout
				        });

				    myMap.geoObjects
				        .add(myPlacemark)
				        .add(myPlacemarkWithContent);

					    }

					);
				
			});
			})
			.catch(reponse => {
				this.nemaRezultata = "Lokacija nije validna!";
			});
			
			
		

	},
	
	methods: {
		
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaAdresa= '';
			this.nemaRezultata = '';
			this.greska = false;
		}, 
		
		izmeni: function(){
			
			this.osvezi();
			
			if (this.klinika.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.klinika.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/klinika/izmena", this.klinika)
			.then(response => {
				alert("Podaci uspesno izmenjeni!");
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});	
		}
	},
	
});