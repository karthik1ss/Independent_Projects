
// Multiple Functions to handle element OnFocus event
function UnameFocus() 
 {
   document.getElementById('username').style.backgroundColor = '#FFF9EC'; //Make the textbox color lightyellow
   document.getElementById('lbluname').style.color = '#000000'; //Make the label text color black
   document.getElementById('lbluname').innerHTML = 'Username:'; //On error raised textbox focus, assign label default text 
   document.getElementById('check').style.display='block';
}
function PassWordFocus() 
 {
   document.getElementById('password').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblpass').style.color = '#000000';
   document.getElementById('lblpass').innerHTML = 'Password:';
}
function RetypePassFocus() 
 {
   document.getElementById('password').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblretype').style.color = '#000000';
   document.getElementById('lblretype').innerHTML = 'Re-Type Pass:';
   document.getElementById('lblpass').style.color = '#000000';
   document.getElementById('lblpass').innerHTML = 'Password:';
}
function FUnameFocus() 
 {
   document.getElementById('fname').style.backgroundColor='#FFF9EC';
   document.getElementById('ufname').style.color = '#000000';
   document.getElementById('ufname').innerHTML = 'First name:';
}

function FUageFocus() 
 {
   document.getElementById('age').style.backgroundColor='#FFF9EC';
   document.getElementById('ulage').style.color = '#000000';
   document.getElementById('ulage').innerHTML = 'Age:';
}
function UaddressFocus() 
 {
   document.getElementById('address').style.backgroundColor='#FFF9EC';
   document.getElementById('staddress').style.color = '#000000';
   document.getElementById('staddress').innerHTML = 'Address:';
}
function UCityFocus() 
 {
   document.getElementById('city').style.backgroundColor='#FFF9EC';
   document.getElementById('usercity').style.color = '#000000';
   document.getElementById('usercity').innerHTML = 'City:';
}
function UzipFocus() 
 {
   document.getElementById('zipcode').style.backgroundColor='#FFF9EC';
   document.getElementById('userzip').style.color = '#000000';
   document.getElementById('userzip').innerHTML = 'Zip/Postal code:';
}
function UemailFocus() 
 {
   document.getElementById('email').style.backgroundColor='#FFF9EC';
   document.getElementById('ulemail').style.color = '#000000';
   document.getElementById('ulemail').innerHTML = 'Email:';
}

function combAddress()
{
	var addr = document.forms[1].address.value;
	var city = document.forms[1].city.value;
	var state= document.forms[1].state.value;
	var country= document.forms[1].country.value;
	var zipcode= document.forms[1].zipcode.value;
	if(state.length!=0)
	{
		document.forms[1].address.value = addr + city + state + country + zipcode;
	}
	else
	{
		document.forms[1].address.value = addr + city + country + zipcode;
	}
}
