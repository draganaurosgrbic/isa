Vue.component("prijava", {
	
	data: function(){
		return{
			user: {
				"email": '', 
				"lozinka": ''
			}, 
			greskaEmail: '', 
			greskaLozinka: '', 
			greskaPrijava: '',
			greska: false
		}
	}, 
	
	template: `
	<section class="forms-section">
	<br>
  <h2>Klinicki centar Vojvodine</h2>
  <div class="forms">
    <div class="form-wrapper is-active">
      <button type="button" class="switcher switcher-login">
        Prijava
        <span class="underline"></span>
      </button>
      <form class="form form-login">
        <fieldset>
          <legend>Unesite korisnicko ime i lozinku.</legend>
          <div class="input-block">
            <label for="login-email" style="color: black;">Email</label>
            <input id="login-email" type="email" v-model="user.email" required>
          </div>
          <div class="input-block">
            <label for="login-password" style="color: black;">Lozinka</label>
            <input id="login-password" type="password" v-model="user.lozinka" required>
          </div>
        </fieldset>
        <button v-on:click="prijava()" class="btn btn-success">PRIJAVA</button><br>{{greskaPrijava}}<br><br>
		Niste registrovani? <router-link to="/dodajPacijenta">REGISTRACIJA</router-link><br><br>
        
		
      </form>
    </div>
    <div class="form-wrapper">
      <button type="button" class="switcher switcher-signup">
        <span class="underline"></span>
      </button>
      <form class="form form-signup">
        <fieldset>
          <legend>Please, enter your email, password and password confirmation for sign up.</legend>
          <div class="input-block">
            <label for="signup-email">E-mail</label>
            <input id="signup-email" type="email" required>
          </div>
          <div class="input-block">
            <label for="signup-password">Password</label>
            <input id="signup-password" type="password" required>
          </div>
          <div class="input-block">
            <label for="signup-password-confirm">Confirm password</label>
            <input id="signup-password-confirm" type="password" required>
          </div>
        </fieldset>
        <button type="submit" class="btn-signup">Continue</button>
      </form>
    </div>
  </div>
</section>
	`, 
	
	methods: {
		
		osvezi: function(){
			this.greskaEmail = '';
			this.greskaLozinka = '';
			this.greskaPrijava = '';
			this.greska = false;
		}, 
		
		prijava: function(){
			
			this.osvezi();
			
			if (this.user.email == ''){
				this.greskaEmail = "Niste uneli email adresu. ";
				this.greska = true;
			}
			
			if (this.user.lozinka == ''){
				this.greskaLozinka = "Niste uneli lozinku. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/user/prijava", this.user)
			.then(response => {
				if (response.data == "pacijent")
					this.$router.push("/pacijentHome");
				else if (response.data == "lekar")
					this.$router.push("/lekarHome");
				else if (response.data == "sestra")
					this.$router.push("/sestraHome")
				else if (response.data == "admin")
					this.$router.push("/adminHome");
				else if (response.data == "superadmin")
					this.$router.push("/superAdminHome");
				else
					this.$router.push("/promenaSifre");
			})
			.catch(response => {
				this.greskaPrijava = "Unet korisnik ne postoji. ";
			});
			
		}
		
	}
	
});
