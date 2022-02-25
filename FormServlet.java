//Importing necessary packages and classes.

package com.ff; //package football form
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
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
	
    private static Connection con = null;
    boolean user_exist,data_exist;
    public HttpServletResponse res;

    //This method will use HttpServlet Request and Response To generate a dynamic page in response to what is requested. 
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
    {
    	firstname = req.getParameter("firstname");
    	connect();
    	JSONArray array = new JSONArray();
    	JSONObject record = new JSONObject();  	
    	fetch();
    	System.out.println(user_exist);
    	if(user_exist == true)
    	{
    		try 
    		{ 		
    			record.put("fname",firstname_s);
    			record.put("lname",lastname_s);
    			record.put("phone",phonenumber_s);
    			record.put("email",email_s);
    			record.put("age",agegroup_s);
    			record.put("team",desiredteam_s);
    			record.put("position",desiredposition_s);
    			record.put("address",address_s);
    			record.put("pin",pincode_s);
    			record.put("country",country_s);
    			record.put("state",state_s);
    			record.put("city",city_s);
    			array.put(record);
    			res.setContentType("application/json");
    			//response.getWriter().write(array.toString());
    			ServletOutputStream out = res.getOutputStream();
    			//String response = gson.toJson(postRes);
    			out.write(record.toString().getBytes());
    			out.flush();
    			out.close();
    			System.out.println("done");
    			update();
	        } 
		   
    	        catch (Exception e) 
    	        {
    		        e.printStackTrace();
		}
    	      
    	}
    }
    
    @Override
    protected void doPut(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
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
		 update();	 
	}
 
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
		 res.sendRedirect("footballformm_1.html"); 
		 		 
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
			String sql = "insert into user_tb1(FirstName,LastName,PhoneNumber,Email,AgeGroup,DesiredTeam,DesiredPosition,Address,Pincode,Country,State,City) values(?,?,?,?,?,?,?,?,?,?,?,?)";	
			PreparedStatement pstm = con.prepareStatement(sql);
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
	
	 //this method will extract data from the database.
	
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
			String i = "UPDATE user_tb1 SET FirstName = ?,LastName = ?,PhoneNumber = ?,Email = ?,AgeGroup = ?,DesiredTeam = ?,DesiredPosition = ?,Address = ?,Pincode = ?,Country = ?,State = ?,City = ? WHERE FirstName = ?";	
			PreparedStatement pstm = con.prepareStatement(i);
			
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

