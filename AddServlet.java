//Importing necessary packages and classes.

package com.ff;   //package football form
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet
{

	//Declaring static variables to store all the data received from input fields of the form.  
	
	static String firstname;
	static String lastname;
	static String phonenumber;
	static String email;
	static String agegroup;
	static String desiredteam;
	static String desiredposition;
	static String address;
	static String pincode;
	static String country;
	static String state;
	static String city;
	
	//Declaring static variables to stored the fetched data of an already existing user. 
	// _s is behind the variables is indicating the stored data.
	
	static String firstname_s;
	static String lastname_s;
	static String phonenumber_s;
	static String email_s;
	static String agegroup_s;
	static String desiredteam_s;
	static String desiredposition_s;
	static String address_s;
	static String pincode_s;
	static String country_s;
	static String state_s;
	static String city_s;
	
	private static Connection con=null;
        boolean user_exist,data_exist;
    
        //This method will use HttpServlet Request and Response To generate a dynamic page in response to what is requested. 
    
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{		
		
		//connecting to the database.
		
		 connect();
		 
		//Receiving Data From the input Fields of the Form .
		 
		 firstname = req.getParameter("firstname");	 
		 lastname = req.getParameter("lastname");
		 phonenumber = req.getParameter("phonenumber");
		 email = req.getParameter("email");
		 agegroup = req.getParameter("agegroup");
		 desiredteam = req.getParameter("fav_language");
		 desiredposition = req.getParameter("desiredposition");
		 address = req.getParameter("address");
		 pincode = req.getParameter("pincode");
		 country = req.getParameter("country");
		 state = req.getParameter("state");
		 city = req.getParameter("city");	
		 	 
		 //fetch method will check weather the user already exists or not. 
		 
		 fetch();	 
		 
		 PrintWriter out = res.getWriter();	
		 
		 /* if user does not exists in database the page will refresh to enter as a new user and after filling mandatory fields 
		    on clicking submit button data will be stored in database.*/
		 
		 if(user_exist == false)
		 {
			 insert();	
			 
			 if(data_exist == true)         //if data exists while clicking retrieve button update page will open.
			 {
				 fetch();
			 }
			 
			 else 				//else page will refresh and you can enter new data. 
			 {
				 RequestDispatcher dispatcher = req.getRequestDispatcher("/footballform_1.html");
				 dispatcher.forward(req, res);	
			 }
			 
		 }
		 
		 //If user already exists then on clicking retrieve button update page will open.
		 
		 if(user_exist == true)
		 {		
     
			 update();	
			 fetch();	 
			 out.println("<html>\r\n"
				 		+ "<head>\r\n"
				 		+ "  <link rel=\"stylesheet\" href=\"myStyle.css\">\r\n"
				 		+ "  <script src=\"myscript_1.js\"></script>\r\n"
				 		+ "</head>\r\n"
				 		+ "<body style=\"padding:15px;\">\r\n"
				 		+ "<form action=\"save\" method=\"post\" id=\"form\">\r\n"
				 		+ "<div class=\"container\">\r\n"
				 		+ "<h2 style=\"margin: 9px;margin-bottom:30px; text-align:center;padding:5px 0px 10px;font-size:32px;\"><b>Football Registration Form</b></h2>\r\n"
				 		+ "<table class=\"styl1\">\r\n"
				 		+ "  <tr>\r\n"
				 		+ " <!--FIRST NAME-->\r\n"
				 		+ "    <td style=\"padding-top:20px;padding-bottom:20px;width:50%\">\r\n"
				 		+ "    <label  for=\"fname\">FIRST NAME</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <button type=\"button\" disabled style=\"float:right\">Retrieve</button>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <input value="+firstname_s+" onchange=\"\" autocomplete=\"off\" autofocus style=\"width:100%\" type=\"text\" id=\"fname\" name=\"firstname\" placeholder=\"What is your first name?\" onkeypress=\"return (event.charCode > 64 && \r\n"
				 		+ "	event.charCode < 91) || (event.charCode > 96 && event.charCode < 123)\"onkeydown=\"checkKeyfn(event)\">\r\n"
				 		+ "    <p style=\"margin:0px;color:red;\"id=\"fn\"></p> \r\n"
				 		+ "    </td>\r\n"
				 		+ "    \r\n"
				 		+ "   <!--LAST NAME-->\r\n"
				 		+ "  \r\n"
				 		+ "    <td style=\"padding-top:23px;padding-bottom:20px;width:50%\">\r\n"
				 		+ "    <label for=\"lname\">LAST NAME</label><br>\r\n"
				 		+ "    <input style=\"width:100%\" type=\"text\" id=\"lname\" name=\"lastname\" placeholder=\"What is your last name?\"autocomplete=\"off\" onkeypress=\"checkKey(); return (event.charCode > 64 && \r\n"
				 		+ "	event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || (event.charCode ==32)\"value="+lastname_s+" >\r\n"
				 		+ "    <p style=\"margin:0px;color:red;font-weight:bold;\r\n"
				 		+ "  font-size:15px;\"id=\"ln\"></p> \r\n"
				 		+ "    </td>\r\n"
				 		+ "  </tr>\r\n"
				 		+ "  </table>\r\n"
				 		+ "  \r\n"
				 		+ "  <!--PHONE NUMBER-->\r\n"
				 		+ "  \r\n"
				 		+ "  <table class=\"styl1\">\r\n"
				 		+ "  <tr>\r\n"
				 		+ "    <td style=\"padding-top:2px;\">\r\n"
				 		+ "    <label for=\"fname\">PHONE NUMBER</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    \r\n"
				 		+ "	<divp>\r\n"
				 		+ "    <select style=\"padding:4.5px;\" id=\"code\" name=\"ccode\">\r\n"
				 		+ "      <option>+91</option>\r\n"
				 		+ "      <option>+46</option>\r\n"
				 		+ "      <option>+49</option>\r\n"
				 		+ "    </select>\r\n"
				 		+ "    <input value="+phonenumber_s+" onchange=\"\"autocomplete=\"off\"  style=\"margin-left:2px;width:88%\" type=\"text\" id=\"phone\" name=\"phonenumber\"  placeholder=\"What is the best # to reach you?\"onkeypress=\"return(event.charCode > 47 && event.charCode < 58)\"onkeydown=\"checkKeyph(event)\">\r\n"
				 		+ "\r\n"
				 		+ "    <p style=\"margin-top:0px;color:red;\"id=\"ph\"></p>\r\n"
				 		+ "    </divp>\r\n"
				 		+ "    </td>\r\n"
				 		+ "    \r\n"
				 		+ "    <!--E-MAIL-->\r\n"
				 		+ "    \r\n"
				 		+ "    <td style=\"padding-top:2px;width:33.3%;\">\r\n"
				 		+ "    <label>EMAIL</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <label style=\"float:right;\"class=\"switch\">\r\n"
				 		+ "    <input onclick=\"myFunction()\" id=\"email\"type=\"checkbox\" unchecked>\r\n"
				 		+ "    <span class=\"slider round\"></span>\r\n"
				 		+ "    </label>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <input value="+email_s+" onchange=\"\"autocomplete=\"off\"  disabled style=\"width:100%\" type=\"email\" id=\"em\" name=\"email\"  placeholder=\"What is your e-mail address?\" onkeypress=\"return(event.charCode)\"onkeydown=\"checkEm(event)\">\r\n"
				 		+ "    </td>\r\n"
				 		+ "        \r\n"
				 		+ "    <!--AGE GROUP-->\r\n"
				 		+ "    \r\n"
				 		+ "    <td style=\"width:33%;padding-top:1.5px;\">\r\n"
				 		+ "    <label for=\"AGE GROUP\">AGE GROUP</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <select onchange=\"ag1=1;\" name=\"agegroup\" id=\"age group\"  style=\"width:100%\" >\r\n"
				 		+ "      <option selected disabled hidden value=\"\">"+agegroup_s+"</option>\r\n"
				 		+ "      <option value=\"20-24\">20-24 years</option>\r\n"
				 		+ "      <option value=\"25-29\">25-29 years</option>\r\n"
				 		+ "      <option value=\"30-34\">30-34 years</option>\r\n"
				 		+ "    </select>\r\n"
				 		+ "    </td>\r\n"
				 		+ "  </tr>\r\n"
				 		+ "  </table>\r\n"
				 		+ "  <table class=\"styl2\">\r\n"
				 		+ "  \r\n"
				 		+ "    <!--DESIRED TEAM-->\r\n"
				 		+ "  \r\n"
				 		+ "  <tr style=\"border-bottom-style:none;\">\r\n"
				 		+ "    <td style=\"vertical-align:top;width:25%;padding-top:15px;\">     \r\n"
				 		+ "    <label FOR=\"CHELSEA\">DESIRED TEAM</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <BR>\r\n"
				 		+ "    <input onchange=\"dt=1;\" type=\"radio\" id=\"CHELSEA\" name=\"fav_language\" value=\"CHELSEA\">\r\n"
				 		+ "    <label for=\"CHELSEA\">CHELSEA</label><br>\r\n"
				 		+ "    <input onchange=\"dt=1;\" type=\"radio\" id=\"MANCHESTER UNITED\"name=\"fav_language\" value=\"MANCHESTER UNITED\">\r\n"
				 		+ "    <label for=\"MANCHESTER UNITED\">MANCHESTER UNITED</label><br>\r\n"
				 		+ "    <input onchange=\"dt=1;\" type=\"radio\" id=\"LIVERPOOL\" name=\"fav_language\" value=\"LIVERPOOL\">\r\n"
				 		+ "    <label for=\"LIVERPOOL\">LIVERPOOL</label><br>\r\n"
				 		+ "    <input onchange=\"dt=1;\" type=\"radio\" id=\"BARCELONA\" name=\"fav_language\" value=\"BARCELONA\">\r\n"
				 		+ "    <label for=\"BARCELONA\">BARCELONA</label> \r\n"
				 		+ "    </td>\r\n"
				 		+ "   \r\n"
				 		+ "     <!--DESIRED POSITION-->\r\n"
				 		+ "    \r\n"
				 		+ "    <td style=\"width:25%;vertical-align:top;padding-top:15px;\">\r\n"
				 		+ "    <label for=\"GOAL KEPPER\">DESIRED POSITION</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <input onchange=\"despos(this.id);\" name=\"desiredposition\" type=\"checkbox\"  id=\"GOAL KEPPER\" value=\"GOAL KEPPER\">\r\n"
				 		+ "    <label for=\"GOAL KEPPER\">GOAL KEPPER</label><br>\r\n"
				 		+ "    <input onchange=\"despos(this.id);\" name=\"desiredposition\" type=\"checkbox\"  id=\"OFFENSIVE\" value=\"OFFENSIVE\">\r\n"
				 		+ "    <label for=\"OFFENSIVE\">OFFENSIVE</label> <br>\r\n"
				 		+ "    <input onchange=\"despos(this.id);\" name=\"desiredposition\" type=\"checkbox\"  id=\"DEFENSE\" value=\"DEFENSE\">\r\n"
				 		+ "    <label for=\"DEFENSE\">DEFENSE</label><br>\r\n"
				 		+ "    <input onchange=\"despos(this.id);\" name=\"desiredposition\" type=\"checkbox\"  id=\"RECEIVER\" value=\"RECEIVER\">\r\n"
				 		+ "    <label for=\"RECEIVER\">RECEIVER</label>      \r\n"
				 		+ "    </td>\r\n"
				 		+ "    \r\n"
				 		+ "     <!--ADDRESS-->\r\n"
				 		+ "    \r\n"
				 		+ "    <td class=\"textarea\" style=\"width:25%;vertical-align:top;padding-top:15px;\">  \r\n"
				 		+ "    <label for=\"tarea\">ADDRESS</label>\r\n"
				 		+ "    <textarea id=\"tarea\"style=\"height:130px\" name=\"address\">"+address_s+"</textarea>\r\n"
				 		+ "    </td>\r\n"
				 		+ "    \r\n"
				 		+ "     <!--PINCODE-->\r\n"
				 		+ "    \r\n"
				 		+ "    <td class=\"pcode\"style=\"width:25%;vertical-align:top;padding-top:15px;\">\r\n"
				 		+ "    <label for=\"PINCODE\">PINCODE</label><br>\r\n"
				 		+ "    <input  name=\"pincode\" onchange=\"\" name=\"pincode\"style=\"width:100%\" type=\"text\" id=\"PINCODE\" placeholder=\"Enter your Pin code\"onkeypress=\"return(event.charCode > 47 && event.charCode < 58)\"autocomplete=\"off\" onkeydown=\"checkKeypi(event)\" value="+pincode_s+">\r\n"
				 		+ "\r\n"
				 		+ "    <p style=\"margin-top:-6px;margin-left:-5px;color:red;border-style:none;\"id=\"pi\"></p>\r\n"
				 		+ "   </tr> \r\n"
				 		+ "   </table>\r\n"
				 		+ "   \r\n"
				 		+ "    <!--COUNTRY-->\r\n"
				 		+ "    \r\n"
				 		+ "    <table class=\"location\">\r\n"
				 		+ " <tr>\r\n"
				 		+ "  <td>\r\n"
				 		+ "    <label for=\"COUNTRY\">COUNTRY</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <select onchange=\"c=1;myLocation();\" id=\"COUNTRY\" name=\"country\"style=\"width:100%\"\r\n"
				 		+ "    >\r\n"
				 		+ "       <option selected disabled hidden value=\"\">"+country_s+"</option>\r\n"
				 		+ "       <option value=\"INDIA\">INDIA</option>\r\n"
				 		+ "       <option value=\"SWEDEN\">SWEDEN</option>\r\n"
				 		+ "       <option value=\"GERMANY\">GERMANY</option>\r\n"
				 		+ "    </select><br>\r\n"
				 		+ "  \r\n"
				 		+ "    <!--state-->\r\n"
				 		+ "    \r\n"
				 		+ "    <label for=\"STATE\">STATE</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <select onchange=\"s=1;myLocation();\" id=\"STATE\" name=\"state\" style=\"width:100%\">\r\n"
				 		+ "      <option selected disabled hidden value=\"\">"+state_s+"</option>\r\n"
				 		+ "      <option id=\"S1\" value=\"PUNJAB\">PUNJAB</option>\r\n"
				 		+ "      <option id=\"S2\" value=\"RAJASTHAN\">RAJASTHAN</option>\r\n"
				 		+ "      <option id=\"S3\" value=\"HIMACHAL\">HIMACHAL</option>\r\n"
				 		+ "    </select><br>\r\n"
				 		+ "    <!--city-->\r\n"
				 		+ "    \r\n"
				 		+ "    <label for=\"CITY\">CITY</label>\r\n"
				 		+ "    <span style=\"color:red;\"><sup>  *</sup></span>\r\n"
				 		+ "    <br>\r\n"
				 		+ "    <select onchange=\"ci=1;\" id=\"CITY\" name=\"city\" style=\"width:100%\" >\r\n"
				 		+ "    <option selected disabled hidden value=\"\">"+city_s+"</option>\r\n"
				 		+ "    <option id=\"C1\" value=\"FAZILKA\">FAZILKA</option>\r\n"
				 		+ "    <option id=\"C2\" value=\"KHARAR\">KHARAR</option>\r\n"
				 		+ "    <option id=\"C3\" value=\"MOHALI\">MOHALI</option>\r\n"
				 		+ "    </select>\r\n"
				 		+ "    \r\n"
				 		+ "    <!--button-->\r\n"
				 		+ "    <div style=\"text-align:center;margin: 90px 185px -25px 185px;\">\r\n"
				 		+ "    <button onclick=\"alert('Form Updated Successfully!');\" style=\"background-color:#8cc751;\"type=\"submit\" id=\"btnn\" class=\"button\">Phewww! Let's submit it</button>\r\n"
				 		+ "    </div>\r\n"
				 		+ "    </td>\r\n"
				 		+ "  </tr>\r\n"
				 		+ "</table>\r\n"
				 		+ "</div>\r\n"
				 		+ "</form>\r\n"
				 		+ "<script>document.getElementById(\""+desiredteam_s+"\").checked=true;document.getElementById(\""+desiredposition_s+"\").checked=true;"
				 	    + "document.getElementById(\"btnn\").disabled=false;</script>;"
				 		+ "</body>\r\n"
				 		+ "</html>");	 			
		  }
	 }
	
	//This method will connect us to our database.

	public void connect()
	{
  
		AddServlet as = new AddServlet();
    
		try 
		{	
			String url = "jdbc:sqlserver://INDIA-1PHMPC2;databaseName=footballform";
			String user = "sa";
			String pass = "Despicable@1";
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("connected!");
		}
		
		catch(Exception e)
		{
			System.out.println("Something went Wrong!");
		}
		
		
	}
	
	//This method will insert the data into our database.
	
	public void insert()
	{		
  
		try
		{			
			String i = "insert into user_tb1(FirstName,LastName,PhoneNumber,Email,AgeGroup,DesiredTeam,DesiredPosition,Address,Pincode,Country,State,City) values(?,?,?,?,?,?,?,?,?,?,?,?)";	
			PreparedStatement pstm = con.prepareStatement(i);
			pstm.setString(1,firstname);
			pstm.setString(2,lastname);
			pstm.setString(3,phonenumber);
			pstm.setString(4,email);
			pstm.setString(5,agegroup);
			pstm.setString(6,desiredteam);
			pstm.setString(7,desiredposition);
			pstm.setString(8,address);
			pstm.setString(9,pincode);
			pstm.setString(10,country);
			pstm.setString(11,state);
			pstm.setString(12,city);		
			int rows = pstm.executeUpdate();			
			
			if(rows > 0)
			{
				System.out.println("Inserted!");	
				data_exist = true;
			}				
			
		}
		
		catch(Exception e)
		{
			data_exist = false;
			System.out.println("Unable to insert !");		
                }
    
         }
	
	 //This method will extract data from the database.
	
         public void fetch()
	 {				
		 try
		 {
			String sql = "Select * from user_tb1 where FirstName='"+firstname+"'";
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(sql);
			while(result.next())
			{
				 firstname_s = result.getString("FirstName");
				 lastname_s = result.getString("LastName");
				 phonenumber_s = result.getString("PhoneNumber");
				 email_s = result.getString("Email");
				 agegroup_s = result.getString("AgeGroup");
				 desiredteam_s = result.getString("DesiredTeam");
				 desiredposition_s = result.getString("DesiredPosition");
				 address_s = result.getString("Address");
				 pincode_s = result.getString("Pincode");
				 country_s = result.getString("Country");
				 state_s = result.getString("State");
				 city_s = result.getString("City");
			}
			 System.out.println("done Fetching!");	
			 match();	
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println("Unable to Fetch!");			
		 }	
		 
        }
	 
	//This method will check weather a user already Exists or not by matching it with the FirstName column of our database. 
	 
	public void match()
	{		
  
		if(firstname.equals(firstname_s))
		{
    
			user_exist = true;
			System.out.println("Found!");
			
		}
		
		else
		{
    
			user_exist = false;			
			System.out.println("not Found!");
      
		}
		
	}
	
	//This method will allow us to update the existing values stored in our database.
	
	public void update()
	{		
  
		try
		{
    
			String sql = "UPDATE user_tb1 SET FirstName=?,LastName=?,PhoneNumber=?,Email=?,AgeGroup=?,DesiredTeam=?,DesiredPosition=?,Address=?,Pincode=?,Country=?,State=?,City=? WHERE FirstName=?";	
			PreparedStatement pstm=con.prepareStatement(sql);
			
			//while updating the data if user does not change some fields then those fields will remain same.
			
			if(pincode == null)
			{
				pincode = pincode_s;
			}
			
			if(email == null)		
			{
				email = email_s;
			}
			
			if(agegroup == null)
			{
				agegroup = agegroup_s;
			}
			
			if(country == null)
			{
				country=country_s;			
			}
			
			if(state == null)
			{
				state = state_s;			
			}
			
			if(city == null)
			{
				city = city_s;			
			}	
			
			pstm.setString(1,firstname);
			pstm.setString(2,lastname);
			pstm.setString(3,phonenumber);
			pstm.setString(4,email);
			pstm.setString(5,agegroup);
			pstm.setString(6,desiredteam);
			pstm.setString(7,desiredposition);
			pstm.setString(8,address);
			pstm.setString(9,pincode);
			pstm.setString(10,country);
			pstm.setString(11,state);
			pstm.setString(12,city);
			pstm.setString(13,firstname_s);
			
			int rows = pstm.executeUpdate();			
			if(rows > 0)
			{
				System.out.println("Updated Successfully!!");
			}	
			
		}
		
		catch(Exception e)
		{
			System.out.println("unable to update!");			
		}	
		
	}
	
}

