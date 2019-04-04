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

/** Sets the page title based on the URL parameter username. */
function setPageTitle() {
  document.getElementById('page-title').innerText = parameterUsername;
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





/** Fetches data and populates the UI of the page. */
function buildUI() {
  setPageTitle();
  fetchProfile();
  
  
}