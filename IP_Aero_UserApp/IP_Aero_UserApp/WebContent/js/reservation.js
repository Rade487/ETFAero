function deleteDuplicate(comboBox) {
	var x = document.getElementById(comboBox);
	//count the lenth of select optons
	var listLength = x.length;
	for (var i = 0; i < listLength; i++) {
		for (var j = 0; j < listLength; j++) {
			//i != j This prevents the actual index from being deleted
			//x.options[i].value == x.options[j].value => it finds the duplicate index.
			if (x.options[i].value == x.options[j].value && i != j) {
				//Remove duplicate option element
				x.remove(j);
				//Refresh the list Length
				listLength--;
			}
		}
	}
}
var ajax_call_arrivals = function() {
	var req = $.ajax({
        type:"post",
        url:"FlightServlet?action=allflightdep",
        data:{data:"Hello World"},
        success: function (response){
        	var respons = JSON.parse(JSON.stringify(response));
            $("#table > tbody").empty();
			$("#yesterdaytable > tbody").empty();
			$("#tomorrowtable > tbody").empty();			
            $.each(respons, function(i, flight) {
            	var type;
            	var status;
				
            	if(flight.passengers == 0){
            		type = "Teretni"
            	}else{
            		type = "Putnicki"
            	}
            	var d1 = Date.parse(flight.return_);
            	var d2 = new Date();
				var d = new Date();
				var d3 = new Date(d1);
				var datestring1 =  d3.getFullYear()  + "-" + d3.getDate() + "-" + (d3.getMonth()+1);
				var today = d.getFullYear()  + "-" + d.getDate() + "-" + (d.getMonth()+1);
				var yesterday = d.getFullYear()  + "-" + (d.getDate()-1) + "-" + (d.getMonth()+1);
				var tommorrow = d.getFullYear()  + "-" + (d.getDate()+1) + "-" + (d.getMonth()+1);
				var flightDate = d3.getFullYear()  + "-" + d3.getDate() + "-" + (d3.getMonth()+1);
				console.log(" Danas " + today);
				console.log(flightDate);
				//console.log(yesterday);
				//console.log(tommorrow);
            	if (d1 < d2) {
            	    status = "Sletio";
            	}else{
            		status = "Ceka";
            	}
    			var newRowContent = "<tr><td>"+flight.route.originCountry+ " (" + flight.route.originTown + ")" +"</td><td>"+flight.route.destinationCountry+ " (" + flight.route.destinationTown + ")" +"</td><td>"+flight.return_ +"</td><td>"+status +"</td><td>"+type +"</td></tr>";			
				if ( today ==flightDate ){
					$("#table tbody").append(newRowContent);
				}
				if ( yesterday == flightDate){
					$("#yesterdaytable tbody").append(newRowContent);
				}
				if (tommorrow == flightDate){
					$("#tomorrowtable tbody").append(newRowContent);
				}        
     });
          },
          error: function(e) {
            alert('Error: ' + e);
          }
        });

    req.done(function(){
        console.log("Request successful!");

        // This makes it able to send new request on the next interval
        response = true;
    });
}
var ajax_call_departure = function() {
	var req = $.ajax({
        type:"post",
        url:"FlightServlet?action=allflightdep",
        data:{data:"Hello World"},
        success: function (response){
        	var respons = JSON.parse(JSON.stringify(response));
            $("#table > tbody").empty();
			$("#yesterdaytable > tbody").empty();
			$("#tomorrowtable > tbody").empty();			
            $.each(respons, function(i, flight) {
            	var type;
            	var status;
				
            	if(flight.passengers == 0){
            		type = "Teretni"
            	}else{
            		type = "Putnicki"
            	}
            	var d1 = Date.parse(flight.departure);
            	var d2 = new Date();
				var d = new Date();
				var d3 = new Date(d1);
				var datestring1 =  d3.getFullYear()  + "-" + d3.getDate() + "-" + (d3.getMonth()+1);
				var today = d.getFullYear()  + "-" + d.getDate() + "-" + (d.getMonth()+1);
				var yesterday = d.getFullYear()  + "-" + (d.getDate()-1) + "-" + (d.getMonth()+1);
				var tommorrow = d.getFullYear()  + "-" + (d.getDate()+1) + "-" + (d.getMonth()+1);
				var flightDate = d3.getFullYear()  + "-" + d3.getDate() + "-" + (d3.getMonth()+1);
				console.log(" Danas " + today);
				console.log(flightDate);
				//console.log(yesterday);
				//console.log(tommorrow);
            	if (d1 < d2) {
            	    status = "Poletio";
            	}else{
            		status = "Ceka";
            	}
    			var newRowContent = "<tr><td>"+flight.route.originCountry+ " (" + flight.route.originTown + ")" +"</td><td>"+flight.route.destinationCountry+ " (" + flight.route.destinationTown + ")" +"</td><td>"+flight.departure +"</td><td>"+status +"</td><td>"+type +"</td></tr>";			
				if ( today ==flightDate ){
					$("#table tbody").append(newRowContent);
				}
				if ( yesterday == flightDate){
					$("#yesterdaytable tbody").append(newRowContent);
				}
				if (tommorrow == flightDate){
					$("#tomorrowtable tbody").append(newRowContent);
				}        
     });
          },
          error: function(e) {
            alert('Error: ' + e);
          }
        });

    req.done(function(){
        console.log("Request successful!");

        // This makes it able to send new request on the next interval
        response = true;
    });
}
var ajax_call = function() {
	var req = $.ajax({
        type:"post",
        url:"FlightServlet?action=allflight",
        data:{data:"Hello World"},
        success: function (response){
            
        	 

        	var respons = JSON.parse(JSON.stringify(response));
            $("#arrivals > tbody").empty();
            $.each(respons, function(i, flight) {
            	var type;
            	var status;
            	if(flight.passengers == 0){
            		type = "Teretni"
            	}else{
            		type = "Putnicki"
            	}
            	var d1 = Date.parse(flight.return_);
            	var d2 = new Date();
            	if (d1 < d2) {
            	    status = "Sletio";
            	}else{
            		status = "Ceka";
            	}
    			var newRowContent = "<tr><td>"+flight.route.originCountry+ " (" + flight.route.originTown + ")" +"</td><td>"+flight.route.destinationCountry+ " (" + flight.route.destinationTown + ")" +"</td><td>"+flight.return_ +"</td><td>"+status +"</td><td>"+type +"</td></tr>";			
				$("#arrivals tbody").append(newRowContent);
             });
          },
          error: function(e) {
            alert('Error: ' + e);
          }
        });

    req.done(function(){
        console.log("Request successful!");

        // This makes it able to send new request on the next interval
        response = true;
    });
var req = $.ajax({
        type:"post",
        url:"FlightServlet?action=allflight2",
        data:{data:"Hello World"},
        success: function (response){
            
        	 

        	var respons = JSON.parse(JSON.stringify(response));
			$("#departure > tbody").empty();
            $.each(respons, function(i, flight) {
            	var type;
            	var status;
            	if(flight.passengers == 0){
            		type = "Teretni"
            	}else{
            		type = "Putnicki"
            	}
            	var d1 = Date.parse(flight.departure);
            	var d2 = new Date();
            	if (d1 < d2) {
            	    status = "Poletio";
            	}else{
            		status = "Ceka";
            	}
    			var newRowContent1 = "<tr><td>"+flight.route.originCountry+ " (" + flight.route.originTown + ")" +"</td><td>"+flight.route.destinationCountry+ " (" + flight.route.destinationTown + ")" +"</td><td>"+flight.departure +"</td><td>"+status +"</td><td>"+type +"</td></tr>";
				$("#departure tbody").append(newRowContent1);
             });
          },
          error: function(e) {
            alert('Error: ' + e);
          }
        });

    req.done(function(){
        console.log("Request successful!");

        // This makes it able to send new request on the next interval
        response = true;
    });
}

function handleClick(myRadio) {
    var tTable = document.getElementById("table");
	var yTable = document.getElementById("yesterdaytable");
	var tomTable = document.getElementById("tomorrowtable");
	
	if (myRadio.value === "today"){
		yTable.style.display = "none";
		tTable.style.display = "table";
		tomTable.style.display = "none";
	}else if (myRadio.value ==="yesterday"){
		yTable.style.display = "table";
		tTable.style.display = "none";
		tomTable.style.display = "none";
	}else{
		yTable.style.display = "none";
		tTable.style.display = "none";
		tomTable.style.display = "table";
	}
}
function greeting() {
	confirm("Da li ste sigurni da zelite ponistiti rezervaciju?");
}
	function reservation() {
 	 let allAreFilled = true;
 	 document.getElementById("myForm").querySelectorAll("[required]").forEach(function(i) {
    if (!allAreFilled) return;
    if (!i.value) allAreFilled = false;
    if (i.type === "radio") {
      let radioValueCheck = false;
      document.getElementById("myForm").querySelectorAll(`[name=${i.name}]`).forEach(function(r) {
        if (r.checked) radioValueCheck = true;
      })
      allAreFilled = radioValueCheck;
    }
  })
  if (!allAreFilled) {
    alert('Molim vas popunite sva polja');
  } else{
	alert('Uspjesno rezervisano')
}
}
function setDateAgain() {
	document.getElementById("datepicker").disabled = false;

	document.getElementById("datepickerreturn").disabled = false;

	var a = document.getElementById("origin_country");
	var text = a.options[a.selectedIndex].text;
	var b = document.getElementById("origin_town");
	var text1 = b.options[b.selectedIndex].text;
	var c = document.getElementById("destination_country");
	var text2 = c.options[c.selectedIndex].text;
	var d = document.getElementById("destination_town");
	var text3 = d.options[d.selectedIndex].text;

	var url = 'ReservationServlet?p=' + text + "-" + text1 + "-" + text2 + "-" + text3 + '&cb=' + "days";

	let elements = new Array();
	var elementsReturn = new Array();

	fetch(url)
		.then(
			function(response) {
				if (response.status !== 200) {
					console.warn('Looks like there was a problem. Status Code: ' + response.status);
					return;
				}

				// Examine the text in the response  
				response.json().then(function(data) {

					for (let i = 0; i < data.length; i++) {
						var javaScriptRelease = Date.parse(data[i].departure);
						var javaScriptRelease1 = Date.parse(data[i].return_);
						var date = new Date(javaScriptRelease);
						var date1 = new Date(javaScriptRelease1);
						elements.push(date.toLocaleDateString());
						elementsReturn.push(date1.toLocaleDateString());
					}
				});
			}
		)
		.catch(function(err) {
			console.error('Fetch Error -', err);
		});

	setAll(elements);
	setReturn(elementsReturn);
}


function setAll(elements) {
	console.log(elements);
	jQuery(function() {
		$('#datepicker').datepicker({
			dateFormat: 'dd-mm-yy', beforeShowDay: enableAllTheseDays
		});
	}
	);


	function enableAllTheseDays(date) {
		var sdate = $.datepicker.formatDate('m/d/yy', date)
		if ($.inArray(sdate, elements) != -1) {
			return [true];
		}
		return [false];
	}

}

function setReturn(elements) {
	console.log(elements);
	jQuery(function() {
		$('#datepickerreturn').datepicker({
			dateFormat: 'dd-mm-yy', beforeShowDay: enableAllTheseDays
		});
	}
	);
	function enableAllTheseDays(date) {
		var sdate = $.datepicker.formatDate('m/d/yy', date)
		if ($.inArray(sdate, elements) != -1) {
			return [true];
		}
		return [false];
	}

}


function setDate() {
	document.getElementById("date").disabled = false;
	let datalist = document.getElementById("days");
	datalist.length = 0;

	var x = datalist.options.length;
	for (var i = x - 1; i >= 0; i--) {
		console.log(datalist.children[i]);
		console.log(i);
		datalist.children[i].remove();
	}

	//console.log(x);
	var a = document.getElementById("origin_country");
	var text = a.options[a.selectedIndex].text;
	var b = document.getElementById("origin_town");
	var text1 = b.options[b.selectedIndex].text;
	var c = document.getElementById("destination_country");
	var text2 = c.options[c.selectedIndex].text;
	var d = document.getElementById("destination_town");
	var text3 = d.options[d.selectedIndex].text;

	var url = 'ReservationServlet?p=' + text + "-" + text1 + "-" + text2 + "-" + text3 + '&cb=' + "days";

	var elements;

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
						option = document.createElement('option');
						option.text = data[i].departure;
						option.value = data[i].departure;
						elements.push(data[i]).departure;
						datalist.appendChild(option);
					}
				});
			}
		)
		.catch(function(err) {
			console.error('Fetch Error -', err);
		});

	console.log(elements);
	//document.getElementById("date").min = new Date().toISOString().split("T")[0];
}
function setReturnDate() {
	document.getElementById("returndate").disabled = false;
	console.log(document.getElementById("date").value);
	document.getElementById("returndate").min = document.getElementById("date").value;
}

function setCities(selectObject) {


	let dropdown = document.getElementById(selectObject);
	dropdown.length = 0;

	if (selectObject === "origin_town") {
		let defaultOption = document.createElement('option');
		defaultOption.text = 'Izaberite grad';
		dropdown.add(defaultOption);
		dropdown.selectedIndex = 0;
		var e = document.getElementById("origin_country");
		var text = e.options[e.selectedIndex].text;
		var url = 'ReservationServlet?p=' + text + '&cb=' + selectObject;

	} else if (selectObject === "destination_country") {
		let defaultOption = document.createElement('option');
		defaultOption.text = 'Izaberite odredisnu drzavu';
		dropdown.add(defaultOption);
		dropdown.selectedIndex = 0;
		var a = document.getElementById("origin_country");
		var text = a.options[a.selectedIndex].text;
		var b = document.getElementById("origin_town");
		var text1 = b.options[b.selectedIndex].text;
		var url = 'ReservationServlet?p=' + text + "-" + text1 + '&cb=' + selectObject;
	} else {
		let defaultOption = document.createElement('option');
		defaultOption.text = 'Izaberite odredisni grad';
		dropdown.add(defaultOption);
		dropdown.selectedIndex = 0;
		var a = document.getElementById("origin_country");
		var text = a.options[a.selectedIndex].text;
		var b = document.getElementById("origin_town");
		var text1 = b.options[b.selectedIndex].text;
		var c = document.getElementById("destination_country");
		var text2 = c.options[c.selectedIndex].text;
		var url = 'ReservationServlet?p=' + text + "-" + text1 + "-" + text2 + '&cb=' + selectObject;
	}


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
						option = document.createElement('option');
						if (selectObject === "origin_town") {
							if (!selectHasValue(selectObject, data[i].originTown)) {
								option.text = data[i].originTown;
								option.value = data[i].originTown;
								dropdown.add(option);
							}
						} else if (selectObject === "destination_country") {
							if (!selectHasValue(selectObject, data[i].destinationCountry)) {
								option.text = data[i].destinationCountry;
								option.value = data[i].destinationCountry;
								dropdown.add(option);
							}
						} else {
							if (!selectHasValue(selectObject, data[i].destinationTown)) {
								option.text = data[i].destinationTown;
								option.value = data[i].destinationTown;
								dropdown.add(option);
							}
						}
					}
				});
			}
		)
		.catch(function(err) {
			console.error('Fetch Error -', err);
		});
}

function selectHasValue(select, value) {
	obj = document.getElementById(select);

	if (obj !== null) {
		return (obj.innerHTML.indexOf('value="' + value + '"') > -1);
	} else {
		return false;
	}
}

function getFlightsEveryMinut(){
	console.log("rade");
}
