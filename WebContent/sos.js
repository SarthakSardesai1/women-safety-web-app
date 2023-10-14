let container = document.getElementById('container');
var custmob=localStorage.getItem("cust_mob");

toggle = () => {
	container.classList.toggle('sign-in')
	container.classList.toggle('sign-up')
}

setTimeout(() => {
	container.classList.add('sign-in')
}, 200)
var locationElement = document.getElementById("someElementId");
   
  

        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                alert("Geolocation is not supported by this browser!!");
            }
        }

        function showPosition(position) {
            /*locationElement.innerHTML = "Latitude: " + position.coords.latitude +
                "<br>Longitude: " + position.coords.longitude;
*/
            sendSOS(position.coords.latitude, position.coords.longitude);
        }

        function sendSOS(latitude, longitude) {
        	 const accountSid = 'AC85c81ab8be1176127086b586536302aa';
             const authToken = 'faed8a5593d755a40da9bc95ebc5c0f0';
//             const twilioPhoneNumber = '+919763208471';
//             const recipientPhoneNumber = '+919892108926';

             const sosButton = document.getElementById('sosButton');
             const locationElement = document.getElementById('location');
             $.ajax({
         	    url: '/Women_Safety_v3/WSServlet',
         	    type: 'POST',
         	    data : {cust_mob:custmob,
         	    	   callFlag:"getReceiptNumbers"
         	    	},
         	    success: function(response){
         	    
         	    //	after split
         	    	var temp = new Array();
         	    // This will return an array with strings "1", "2", etc.
         	    temp = response.split(",");
//         	        array a[] = response.split(',');
         	    	for(var i=0;i<3;i++)
         	    		{
         	    		alert(temp[i]);
         	            const message = 'SOS! This is an emergency! Current location: https://www.google.com/maps?q=' + latitude + ',' + longitude;

         	            axios.post(`https://api.twilio.com/2010-04-01/Accounts/${accountSid}/Messages.json`,
         	                `Body=${message}&From=+16185906198&To=+91`+temp[i],
         	                {
         	                    headers: {
         	                        'Authorization': 'Basic ' + btoa(`${accountSid}:${authToken}`),
         	                        'Content-Type': 'application/x-www-form-urlencoded'
         	                    }
         	                })
         	    		
         	                .then(twilioResponse => {
         	                    if (twilioResponse.status === 201) {
         	                        console.log('SOS message sent successfully!');
         	                        alert('SOS message sent successfully!');
         	                    } else {
         	                        console.error('Error sending SOS message:', twilioResponse.statusText);
         	                        alert('Error sending SOS message.');
         	                    }
         	                })
         	                .catch(error => {
         	                    console.error('Error sending SOS message:', error);
         	                     
         	                    alert('Error sending SOS message.');
         	                });
         	    		
         	    		}
         	      
         	     },
         	    error: function(error){
         	      console.log(error);
         	    }
         	     
         	});
        
            // const message = `SOS! This is an emergency! Current location: Latitude ${latitude}, Longitude ${longitude}`;

        }
    
