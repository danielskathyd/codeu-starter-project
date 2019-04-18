     function fetchUsers(map){
       const urlParams = new URLSearchParams(window.location.search);
         //const parameterUsername = urlParams.get('user');
         //const parameterInterest= urlParams.get('interest');
       const url = '/users';
       fetch(url).then((response) => {
         return response.json();
       }).then((users) => {
         /**const userContainer = document.getElementById('user-container');
         if(users.length == 0){
          userContainer.innerHTML = '<p>No users to display.</p>';
         }
         else{
          userContainer.innerHTML = '';
         } */

         users.forEach((user) => {
            var latitude = user.latitude ;
            var longitude = user.longitude;
            var name     = user.name;
            var interest = user.interest;
            addLandMark(map, latitude, longitude, name, interest);
         });
       });
     }


     /** Creates a map that shows landmarks around Google. */


         /** Adds a marker that shows an InfoWindow when clicked. */

         function addLandmark(map, lat, lng, title, description){
           const marker = new google.maps.Marker({
             position: {lat: lat, lng: lng},
             map: map,
             title: title
           });

           var infoWindow = new google.maps.InfoWindow({
             content: description
           });
           marker.addListener('click', function() {
             infoWindow.open(map, marker);
           });
         }

     var map, infoWindow;
           function initMap() {
             map = new google.maps.Map(document.getElementById('map'), {
               center: {lat: -34.397, lng: 150.644},
               zoom: 6
             });
             infoWindow = new google.maps.InfoWindow;

             // Try HTML5 geolocation.
             if (navigator.geolocation) {
               navigator.geolocation.getCurrentPosition(function(position) {
                 var pos = {
                   lat: position.coords.latitude,
                   lng: position.coords.longitude
                 };

                 infoWindow.setPosition(pos);
                 infoWindow.setContent('Location found.');
                 infoWindow.open(map);
                 map.setCenter(pos);
               }, function() {
                 handleLocationError(true, infoWindow, map.getCenter());
               });
             } else {
               // Browser doesn't support Geolocation
               handleLocationError(false, infoWindow, map.getCenter());
             }
           }

           function handleLocationError(browserHasGeolocation, infoWindow, pos) {
             infoWindow.setPosition(pos);
             infoWindow.setContent(browserHasGeolocation ?
                                   'Error: The Geolocation service failed.' :
                                   'Error: Your browser doesn\'t support geolocation.');
             infoWindow.open(map);
           }


      function createMap(){

                 initMap();
                  fetchUsers(map);
      }
