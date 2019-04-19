 /**    function fetchUsers(map){
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
         }

         users.forEach((user) => {
           latitude = user.getLat();
            longitude = user.getLon();
            interest = user.getInterestsString();
            name = user.getName();
            addLandMark(map, latitude, longitude, name, interest);
         });
       });
     }
*/


