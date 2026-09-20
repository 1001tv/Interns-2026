<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="style.css">

</head>





<body>

<h1> Hello! </h1>
<h2> Sign Up to Get Started </h2>
<input type = "text" id = emailID placeholder = "Email"> </input>
<input type = "text" id = passwordID placeholder = "Password"> </input>
<button type = "submit" onclick="sbmt()"> Submit </button>
<h2> Sign In If You Already Have an Account </h2>

<input type = "text" id = "emailID2" placeholder = "Email"> </input>
<input type = "text"  id = "passwordID2" placeholder = "Password"> </input>
<button type = "submit" onclick="sbmt2()"> Submit </button>

<script>

function sbmt(){email = document.getElementById("emailID").value
password = document.getElementById("passwordID").value
let users = JSON.parse(localStorage.getItem("users")) || [];
users.push({email: email, password: password})
localStorage.setItem("users", JSON.stringify(users));
alert("Account Created!");
}

function sbmt2(){
email = document.getElementById("emailID2").value;
password = document.getElementById("passwordID2").value;
if(exists(email , password)){ alert("welcome back");}
else {alert("Account not found");}
}

function exists(email , password){
users = JSON.parse(localStorage.getItem("users"));
for (let i = 0; i < users.length; i++) {
if (users[i].email == email && users[i].password == password) {return true;}}

return false;

}

</script>

</body>



</html>

CSS

body {
  font-family: sans-serif;
  background-color: #f4f4f4;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
}

.card {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 280px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  text-align: center;
}

input {
  width: 100%;
  padding: 10px;
  margin: 8px 0;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

a {
  color: #007bff;
  text-decoration: none;
}
