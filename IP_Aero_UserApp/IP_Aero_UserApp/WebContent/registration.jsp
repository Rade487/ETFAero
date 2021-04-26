<jsp:include flush="true" page="WEB-INF/header.jsp"/>
<!-- content -->
<div class="registration-container">
<div class="errors">
${errors }
</div>
  <form action="login?action=registration" method="post">
   <div class="row">
      <div class="col-25">
        <label for="fname"><b>Email</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="text" name="email" placeholder="Unesite email.." required> 
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname"><b>Korisnicko ime</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="text" name="username" placeholder="Vase korisnicko ime.." required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname"><b>Lozinka</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="password" name="password" placeholder="Vasa lozinka.." required>
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="fname"><b>Ponovo lozinka</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="password" name="password1" placeholder="Vasa lozinka.." required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname"><b>Ime</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="text" name="firstname" placeholder="Vase ime.." required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname"><b>Prezime</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="text" name="lastname" placeholder="Vase prezime.." required>
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="lname"><b>Adresa</b></label>
      </div>
      <div class="col-75">
        <input class="registration" type="text" name="address" placeholder="Vasa adresa.." required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="country"><b>Drzava</b></label>
      </div>
      <div class="col-75">
        <select class="registration-select" id="country" name="country" required>
        </select>
      </div>
    </div>
    <div class="col-25">
        <label for="country"><b>Izbor naloga</b></label>
      </div>
    <div class="col-75">
    	<input type="radio" id="male" name="type" value="putnicki" checked="checked">
		<label for="male">Putnicki</label><br>
		<input type="radio" id="female" name="type" value="teretni">
		<label for="female">Teretni</label><br>
	</div>
    <div class="row">
      <input class ="myButton" type="submit" value="Registruj se">
    </div>
  </form>
</div>
<script>
let dropdown = document.getElementById('country');
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Izaberite drzavu';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = 'http://restcountries.eu/rest/v2/region/europe';

fetch(url)  
  .then(  
    function(response) {  
      if (response.status !== 200) {  
        console.warn('Looks like there was a problem. Status Code: ' + 
          response.status);  
        return;  
      }

      // Examine the text in the response  
      response.json().then(function(data) {  
        let option;
    
    	for (let i = 0; i < data.length; i++) {
    		if(data[i].name != "Republic of Kosovo"){
          option = document.createElement('option');
      	  option.text = data[i].name;
      	  option.value = data[i].name;
      	  dropdown.add(option);
    		}
    	}    
      });  
    }  
  )  
  .catch(function(err) {  
    console.error('Fetch Error -', err);  
  });
</script>

<jsp:include flush="true" page="WEB-INF/footer.jsp"/>