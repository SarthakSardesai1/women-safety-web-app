let container = document.getElementById('container')

toggle = () => {
	container.classList.toggle('sign-in')
	container.classList.toggle('sign-up')
}

setTimeout(() => {
	container.classList.add('sign-in')
}, 200)


function registerbtn()
{
	$.ajax({
	    url: '/Women_Safety_v3/WSServlet',
	    type: 'POST',
	    data : {Name:$('#name_id').val(),
	    	   mob:$('#num_id').val(),
	    	   pass:$('#pass_id').val(),
	    	   num1:$('#num1_id').val(),
	    	   num2:$('#num2_id').val(),
	    	   num3:$('#num3_id').val(),
	    	   callFlag:"registerData"
	    	},
	    success: function(response){
	        if(response==='Existing')
	        	{
	        	alert("Already Exist!!!!");
	        	toggle();
	        	}else
	        		{
	        		alert("Registered Successfully!!");
	        		toggle();
	        		}
	     },
	    error: function(error){
	      console.log(error);
	    }
	     
	});

}


function verifyLoginData(){
	$.ajax({
	    url: '/Women_Safety_v3/WSServlet',
	    type: 'POST',
	    data : {cust_mob:$('#cust_mob').val(),
	    	cust_pwd:$('#cust_pwd').val(),
	    	  
	    	   callFlag:"loginVerifyData"
	    	},
	    success: function(response){
	    	alert(response);
	        if(response==="new")
	        	{
	        	alert("not registered!!!!");
	        	}else
	        		{
	        		alert("login successfully!!");
	        		localStorage.setItem("cust_mob", $('#cust_mob').val());
	        		window.location.href = 'sos.html';

	        		}
	     },
	    error: function(error){
	      console.log(error)
	    }
	     
	});

}
