/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Get ?user=XYZ parameter value
const urlParams = new URLSearchParams(window.location.search);
const parameterUsername = urlParams.get('user');

// URL must include ?user=XYZ parameter. If not, redirect to homepage.
if (!parameterUsername) {
  window.location.replace('/');
}

function submitProfileData() {
  console.log(document.getElementById("city"));
  document.getElementById("profile-form").submit();
}

/** Sets the page title based on the URL parameter username. */
function setPageTitle() {
  document.getElementById('profile-email').innerText = 'Email:  ' + parameterUsername;
  document.title = parameterUsername + ' - Profile Page';
}

function fetchProfile(){

  const url = '/profile?user=' + parameterUsername;
  fetch(url).then((response) => {
    return response.json();
  }).then((aboutMe) => {
    console.log("this is the aboutMe")
    console.log(aboutMe)
    const nameContainer = document.getElementById('name');
    const cityContainer = document.getElementById('city');
    const interestsContainer = document.getElementById('interests');

    console.log(nameContainer.value);

    nameContainer.value = aboutMe[0];
    cityContainer.value = aboutMe[1];
    interestsContainer.value = aboutMe[2];

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
            var Latitude = document.createElement("div");
              // and give it some content
            var lat_value = document.createTextNode(position.coords.latitude);
              // add the text node to the newly created div
            Latitude.appendChild(lat_value);

            var Longitude = document.createElement("div");
                          // and give it some content
            var lon_value = document.createTextNode(position.coords.longitude);
                          // add the text node to the newly created div
            Longitude.appendChild(lon_value);

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


    var x = document.getElementById("map");
    function getLocation() {
        if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition);
    } else {
         x.innerHTML = "Geolocation is not supported by this browser.";
        }
    }
    function showPosition(position) {
        x.innerHTML = "Latitude: " + position.coords.latitude +
        "<br>Longitude: " + position.coords.longitude;
    }



/** Fetches data and populates the UI of the page. */
function buildUI() {
  setPageTitle();
  fetchProfile();
  initMap();
  getLocation();

}
