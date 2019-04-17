function fetchUsers(){
  const url = '/users';
  fetch(url).then((response) => {
    return response.json();
  }).then((users) => {
    const userContainer = document.getElementById('user-container');
    if(users.length == 0){
     userContainer.innerHTML = '<p>No users to display.</p>';
    }
    else{
     userContainer.innerHTML = '';
    }
    users.forEach((user) => {
     const userDiv = buildUserDiv(user);
     userContainer.appendChild(userDiv);
    });
  });
}

function buildUserDiv(user){
 const usernameDiv = document.createElement('div');
 usernameDiv.appendChild(document.createTextNode(user.name));

 const emailDiv = document.createElement('div');
 emailDiv.appendChild(document.createTextNode(user.email));

 const cityDiv = document.createElement('div');
 cityDiv.appendChild(document.createTextNode(user.city));

 const interestsDiv = document.createElement('div');
 interestsDiv.appendChild(document.createTextNode(user.interests));

 const lonDiv = document.createElement('lon');
 lonDiv.appendChild(document.createTextNode(user.lon));

 const latDiv = document.createElement('lat');
 latDiv.appendChild(document.createTextNode(user.lat));

 const userDiv= document.createElement('div');
 userDiv.classList.add('user-header');
 userDiv.appendChild(usernameDiv);
 userDiv.appendChild(emailDiv);
 userDiv.appendChild(cityDiv);
 userDiv.appendChild(interestsDiv);
 userDiv.appendChild(lonDiv);
 userDiv.appendChild(latDiv);

 return userDiv;
}

function buildUI(){
 fetchUsers();
}
