const urlParams = new URLSearchParams(window.location.search);
const parameterUsername = urlParams.get('user');

function showMessageFormIfLoggedIn() {
    fetch('/login-status')
        .then((response) => {
            return response.json();
        })
        .then((loginStatus) => {
            if (loginStatus.isLoggedIn){
                const messageForm = document.getElementById('message-form');
                messageForm.action = '/messages?recipient=' + parameterUsername;
                messageForm.classList.remove('hidden');
            }
        });
}

// Fetch messages and add them to the page.
function fetchMessages(){
  const url = '/messages?user=' + parameterUsername;
  fetch(url).then((response) => {
    return response.json();
  }).then((messages) => {
    const messageContainer = document.getElementById('message-container');
    if(messages.length == 0){
     messageContainer.innerHTML = '<p>There are no posts yet.</p>';
    }
    else{
     messageContainer.innerHTML = '';
    }
    messages.forEach((message) => {
     const messageDiv = buildMessageDiv(message);
     messageContainer.appendChild(messageDiv);
    });
  });
}

function buildMessageDiv(message){
 const usernameDiv = document.createElement('h1');
 usernameDiv.appendChild(document.createTextNode(message.user));

 const timeDiv = document.createElement('p');
 timeDiv.appendChild(document.createTextNode(new Date(message.timestamp)));

 const headerDiv = document.createElement('div');
 headerDiv.classList.add('message-header');
 headerDiv.appendChild(usernameDiv);
 headerDiv.appendChild(timeDiv);

 const bodyDiv = document.createElement('p');
 bodyDiv.classList.add('message-body');
 bodyDiv.appendChild(document.createTextNode(message.text));

 const messageDiv = document.createElement('div');
 messageDiv.classList.add("message-div");
 messageDiv.appendChild(headerDiv);
 messageDiv.appendChild(bodyDiv);

 return messageDiv;
}

// Fetch data and populate the UI of the page.
function buildUI(){
  showMessageFormIfLoggedIn()
 fetchMessages();
}
