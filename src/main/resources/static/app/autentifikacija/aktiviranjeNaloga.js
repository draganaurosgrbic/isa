Vue.component("aktiviranjeNaloga", {
	
	template: `
	
		<div class="card" style="text-align:center; font-size: 15px;">
			
			<br>
			<h1>Nalog aktiviran</h1><br>
			Vas nalog je uspesno aktiviran!
			Kliknite na sledeci link da bi ste se ulogovali: <br><br>
			<router-link to="/">PRIJAVA</router-link><br><br>
		
		</div>	
	
	`,
	
	mounted() {
		
		let id = this.$route.query.id;

		if (id === "") {
			alert("ID NOT FOUND!\nPLEASE CONTACT SERVER SUPPORT.");
			return;
		}
				
		axios.post("/pacijent/aktiviranje/", {"param": id})
		.catch(error => {
			if (error.response.status === 409)
				alert("Vas nalog je vec aktiviran!");
			else
				alert("SERVER ERROR!!");
		});

	},

});
