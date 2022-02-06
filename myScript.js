let fn1=0,ph1=0,pi1=0,em1=0,ag1=0,dt=0,dp=0,c=0,s=0,ci=0;
function checkKeyfn(event)
{  
	var k=0;
    k=document.getElementById("fname").value.length;   
	if(event.keyCode < 65 || event.keyCode > 90 && event.keyCode 		<97 ||event.keyCode>122) 
	{
		document.getElementById("fn").innerHTML="[Only alphabets are allowed without any spaces!]";
        if(event.keyCode==8)
        {
        	k=k-1;
            document.getElementById("fn").innerHTML="";
        }
        else if(event.keyCode==46)
        {
        k=0;
        }        
	}
	 else
	{
		document.getElementById("fn").innerHTML="";
        k=k+1;
	}
    fn1=k;
     
}
 function despos(id){
 var checkBox = document.getElementById(id);
  
    if (checkBox.checked == 1){
      dp=1;
    } else {
      dp=0;
    }
  }
 function checkEm(event)
{  
	var k=0;
    k=document.getElementById("em").value.length;  
    if(event.keyCode==8)
    {
    k=k-1
    }
    else if(event.keyCode==46)
        {
        k=0;
        }    
    else
    {
    k=k+1;
    }
    em1=k;
}

function checkKey()
{
if(event.charCode <32 ||event.charCode >32 && event.charCode < 65 || 
	event.charCode > 90 && event.charCode <97 ||event.charCode>122) 
{
	document.getElementById("ln").innerHTML="[Only alphabets are allowed with spaces!]";
}
else
{
document.getElementById("ln").innerHTML="";
}
}

function checkKeyph(event)
{var n,k=0,q;
  n=document.getElementById("phone").value.length;
  k=1+n;
  q=event.keyCode;
  if(event.keyCode==46)
        {
        k=0;
        }    
  if(q==8)
  {
   n=document.getElementById("phone").value.length-1;
   k=n;
  }
 if(k!=10)
  {
document.getElementById("ph").innerHTML="[Mobile number must have 10 digits!]";
  }
  if(k==10)
  {
   document.getElementById("ph").innerHTML="";
  }
 ph1=k;
}

function checkKeypi(event)
{var n,k=0,q;
  n=document.getElementById("PINCODE").value.length;
  k=1+n;
  q=event.keyCode;
  if(event.keyCode==46)
        {
        k=0;
        }    
  if(q==8)
  {
   n=document.getElementById("PINCODE").value.length-1;
   k=n;
  }
 if(k!=6)
  {
document.getElementById("pi").innerHTML="[Pincode must have 6 digits!]";
  }
  if(k==6)
  {
   document.getElementById("pi").innerHTML="";
  }
  pi1=k;
  
}

var t=0;
function myFunction() {
  if(t==0)
  {
    document.getElementById("em").disabled = false;
    t=1;
  }
  else
  {
  document.getElementById("em").disabled = true;  
  t=0;
  }

}
function myLocation() {
  var x = document.getElementById("COUNTRY").value;
  if(x=="INDIA")
  {
   Y=document.getElementById("S1").value = "PUNJAB";
   document.getElementById("S2").value = "RAJASTHAN";
   document.getElementById("S3").value = "HIMACHAL"
   document.getElementById("S1").innerHTML = "PUNJAB";
   document.getElementById("S2").innerHTML = "RAJASTHAN";
   document.getElementById("S3").innerHTML = "HIMACHAL";
   var y =  document.getElementById("STATE").value;
   document.getElementById("STATE").disabled=false;
}
  else if(x=="SWEDEN")
  {document.getElementById("S1").value="BLEKINGE";
   document.getElementById("S2").value="DALSLAND";
   document.getElementById("S3").value="GOTLAND";
   document.getElementById("S1").innerHTML = "BLEKINGE";
   document.getElementById("S2").innerHTML = "DALSLAND";
   document.getElementById("S3").innerHTML = "GOTLAND"
   var y =  document.getElementById("STATE").value;
   document.getElementById("STATE").disabled=false;
  }
  else if(x=="GERMANY")
  {
   document.getElementById("S1").value = "HAMBURG";
   document.getElementById("S2").value = "BAVARIA";
   document.getElementById("S3").value = "HESSE";
   document.getElementById("S1").innerHTML = "HAMBURG";
   document.getElementById("S2").innerHTML = "BAVARIA";
   document.getElementById("S3").innerHTML = "HESSE";
   var y =  document.getElementById("STATE").value;
   document.getElementById("STATE").disabled=false;
  }
  if(y=="PUNJAB")
  {
   document.getElementById("C1").innerHTML = "FAZILKA";
   document.getElementById("C2").innerHTML = "KHARAR";
   document.getElementById("C3").innerHTML = "MOHALI";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="RAJASTHAN")
  {
   document.getElementById("C1").innerHTML = "JAIPUR";
   document.getElementById("C2").innerHTML = "AJMER";
   document.getElementById("C3").innerHTML = "KOTA";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="HIMACHAL")
  {
   document.getElementById("C1").innerHTML = "SHIMLA";
   document.getElementById("C2").innerHTML = "MANDI";
   document.getElementById("C3").innerHTML = "KULLU";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="BLEKINGE")
  {
   document.getElementById("C1").innerHTML = "KARLSKRONA";
   document.getElementById("C2").innerHTML = "ROONEBY";
   document.getElementById("C3").innerHTML = "KARLSHAMAN";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="DALSLAND")
  {
   document.getElementById("C1").innerHTML = "MUNKEDAL";
   document.getElementById("C2").innerHTML = "VANERSBORG";
   document.getElementById("C3").innerHTML = "ARJANG";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="GOTLAND")
  {
   document.getElementById("C1").innerHTML = "VISBY";
   document.getElementById("C2").innerHTML = "SLITE";
   document.getElementById("C3").innerHTML = "HEMSE";
   document.getElementById("CITY").disabled=false;
  
  }
  else if(y=="HAMBURG")
  {
   document.getElementById("C1").innerHTML = "JENA";
   document.getElementById("C2").innerHTML = "KIEL";
   document.getElementById("C3").innerHTML = "ERFURT";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="BAVARIA")
  {
   document.getElementById("C1").innerHTML = "MUNICH";
   document.getElementById("C2").innerHTML = "WURZ";
   document.getElementById("C3").innerHTML = "AUGSBERG";
   document.getElementById("CITY").disabled=false;
  }
  else if(y=="HESSE")
  {
   document.getElementById("C1").innerHTML = "KASSEL";
   document.getElementById("C2").innerHTML = "DARMST";
   document.getElementById("C3").innerHTML = "WIESBADEN";
   document.getElementById("CITY").disabled=false;
  }
}

function checkComplete()
{
    if(fn1>0 && ph1==10 && em1>0 && ag1==1 && dt==1 && dp==1 && c==1 && s==1 && ci==1 && (pi1==6 || pi1<=0))
    {  
    document.getElementById("btnn").disabled=false;
    document.getElementById("btnn").style.backgroundColor="#8cc751";
    }
    else
    {
    document.getElementById("btnn").disabled=true;
    document.getElementById("btnn").style.backgroundColor="grey";
    } 
}