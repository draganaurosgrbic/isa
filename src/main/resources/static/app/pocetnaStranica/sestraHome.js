Vue.component("sestraHome", {

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/sestraHome">POCETNA STRANICA</a>
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
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/sestraPacijenti">
          <i class="fa fa-list"></i>
          Lista pacijenata klinike
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/overaRecepta">
          <i class="fa fa-home"></i>
          Overa Recepata
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
      <li class="nav-item active">
        <a class="nav-link" href="#/zahtevOdmorSlanje">
          <i class="fa fa-plane"></i>
          Zahtev za odmor
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/sestraProfil">
          <i class="fa fa-home"></i>
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
		axios.get("/user/check/sestra")
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});