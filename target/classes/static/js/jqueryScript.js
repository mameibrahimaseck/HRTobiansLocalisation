$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "api/localisation/employee",
		success: function(result){
			$.each(result, function(i, employee){
				
				var customerRow = '<tr>' +
									'<td>' + employee.fullName + '</td>' +
                    				'<td>' + employee.firstName + '</td>' +
									'<td>' + employee.lastName.toUpperCase() + '</td>' +
									'<td>' + employee.technology + '</td>' +
									'<td>' + employee.client + '</td>' +
									'<td>' + employee.placeOfProject + '</td>' +
                    				'<td>' + employee.region + '</td>' +
								  '</tr>';
				
				$('#customerTable tbody').append(customerRow);
				
	        });
			
			$( "#customerTable tbody tr:odd" ).addClass("info");
			$( "#customerTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#customerTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
})