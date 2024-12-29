Vue.component("promenaSifre", {
	
	data: function(){
		return{
			novaLozinka: '', 
			ponovljenaLozinka: '', 
			greskaNovaLozinka: '', 
			greskaPonovljenaLozinka: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div class="card" id="tableBox" style="margin-top: 100px;">
		
			<h2>Promena sifre</h2><br>
			
			<table class="table">
			
					<tr>
						<th scope="row">Lozinka: </th>
						<td><input type="password" v-model="novaLozinka" class="form-control"></td>
						<td style="width: 33%;">{{greskaNovaLozinka}}</td>
					</tr>
					
					<tr>
						<th scope="row">Ponovljena lozinka: </th>
						<td><input type="password" v-model="ponovljenaLozinka" class="form-control"></td>
						<td>{{greskaPonovljenaLozinka}}</td>
					</tr>

					<tr>
						<td><button v-on:click="promenaSifre()" class="btn btn-outline-secondary">IZMENI LOZINKU</button></td>
					</tr>
			
			</table>
		
		</div>
	
	`, 
	
	mounted(){
		axios.get("/user/check/sifra")
		.catch(reponse => {
			this.$router.push("/");
		});
	}, 
	
	methods: {
		
		osvezi: function(){
			
			this.greskaNovaLozinka = '';
			this.greskaPonovljenaLozinka = '';
			this.greska = false;
			
		}, 
		
		promenaSifre(){
			
			this.osvezi();
			
			if (this.novaLozinka == ''){
				this.greskaNovaLozinka = "Lozinka ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.novaLozinka != this.ponovljenaLozinka){
				this.greskaPonovljenaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/user/lozinka", {"param": this.novaLozinka})
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
				alert("SERVER ERROR!!");
			});
			
		}
	}
	
});