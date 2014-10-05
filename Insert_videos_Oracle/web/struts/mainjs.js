

// Multiple Functions to handle element OnFocus event on Login form
function UnameFocus() 
 {
   document.getElementById('username').style.backgroundColor = '#FFF9EC'; //Make the textbox color lightyellow
   document.getElementById('lbluname').style.color = '#000000'; //Make the label text color black
   document.getElementById('lbluname').innerHTML = 'Username:'; //On error raised textbox focus, assign label default text 
}
function PassWordFocus() 
 {
   document.getElementById('password').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblpass').style.color = '#000000';
   document.getElementById('lblpass').innerHTML = 'Password:';
}

// Multiple Functions to handle element OnFocus event on Contact form
function UnameContactFocus() 
 {
   document.getElementById('fname').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblconuname').style.color = '#000000';
   document.getElementById('lblconuname').innerHTML = 'Your name:'; 
}
function UemailContactFocus() 
 {
   document.getElementById('email').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblconemail').style.color = '#000000';
   document.getElementById('lblconemail').innerHTML = 'Email:';
}
function UcommentContactFocus() 
 {
   document.getElementById('comments').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblcomment').style.color = '#000000';
   document.getElementById('lblcomment').innerHTML = 'Comments:';
}
