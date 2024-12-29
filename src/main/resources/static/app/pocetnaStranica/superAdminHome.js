Vue.component("superAdminHome", {
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/superAdminHome">POCETNA STRANICA</a>
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
      <li class="nav-item active">
        <a class="nav-link" href="#/dodajKliniku">
          <i class="fa fa-line-chart"></i>
          Dodaj Kliniku
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-address-book">
          </i>
          Administratori
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajSuperAdmina">Dodaj Administratora Klinickog Centra</a>
          <a class="dropdown-item" href="#/dodajAdmina">Dodaj Administratora Klinike</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Dijagnoze
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajDijagnozu">Dodaj Dijagnozu</a>
          <a class="dropdown-item" href="#/dijagnozePretraga">Pretraga Dijagnoza</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Lekovi
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajLek">Dodaj Lek</a>
          <a class="dropdown-item" href="#/lekoviPretraga">Pretraga Lekova</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/zahtevRegistracijaObrada">
          <i class="fa fa-line-chart"></i>
          Zahtevi za registraciju\n
          pacijenata
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/superAdminProfil">
          <i class="fa fa-user-md"></i>
          Profil
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
  </div>
</nav>
		
		</div>
	
	`, 
	
	mounted(){
		axios.get("/user/check/superadmin")
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});