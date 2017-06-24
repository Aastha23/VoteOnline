import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;

public class Register extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{	int i=0,j=0;
		String s1=req.getParameter("No");
		String s2=req.getParameter("Name");
		String s3=req.getParameter("Email");
		String s4=req.getParameter("Address");
		String s5=req.getParameter("Phone");
		String s6=req.getParameter("Pass");
		
		PrintWriter pw=res.getWriter();
		
		
			pw.println(" <!DOCTYPE html><html lang='zxx'><head><title>Organisation Registration</title>");
			pw.println("<meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
			pw.println("<meta name='keywords' content='Free elections for your school, Free elections for your organisation, Very less time required, Held online elections'/>");
			pw.println("<script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false);function hideURLbar(){ window.scrollTo(0,1); } </script>");
			pw.println("<link href='../bootstrap.css' rel='stylesheet' type='text/css' media='all' />");
			pw.println("<link href='../style.css' rel='stylesheet' type='text/css' media='all' />");
			pw.println("<style type='text/css'>body{font-family: 'Source Sans Pro', sans-serif;}body {background-image: url(../banner.jpg);}.clr{ color:white;}</style>");
			pw.println("</head>");
			pw.println("<body><div class='banner-w3ls' id='home'><div class='container'><div class='header-inner'><h1 class='logo'><a href='../index.html'><span> Vote Online</span></a></h1><div class='header-right-w3ls'></div>");
			pw.println("<div class='clearfix'></div></div><div class='w3ls-info'><br><br>");
		
		try
		{
		Connection cn=DBInfo.con;
		String query="Insert into organisation values(?,?,?,?,?,?,?)";
		PreparedStatement ps=cn.prepareStatement(query);
		ps.setString(1,s1);
		ps.setString(2,s2);
		ps.setString(3,s3);
		ps.setString(4,s4);
		ps.setString(5,s5);
		ps.setString(6,s6);
		ps.setString(7,"organisation");
		i=ps.executeUpdate();
		
	    query="Insert into login values(?,?,?,?)";
		ps=cn.prepareStatement(query);
		ps.setString(1,s1);
		ps.setString(2,s3);
		ps.setString(3,s6);
		ps.setString(4,"organisation");
		j=ps.executeUpdate();
		
		ps.close();
		cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(i!=0 && j!=0)
		{
			pw.println("<h1 class='clr'>Registration Successful</h1><br></div><div class='clearfix'></div><div style='text-align:center' class='sim-button button12'><a href='../login.html'>Login Now</a></div> ");
			
		}
		else
		{
			pw.println("<h1 class='clr'>Registration Failed</h1><br></div><div class='clearfix'></div><div style='text-align:center' class='sim-button button12'><a href='../register.html'>Retry</a></div>");
		}
		
		pw.println(" </div></div><div class='footer-w3layouts'><div class='container'><div class='agile-copy'><p> Developed and Maintained by <a href='https://www.linkedin.com/in/aastha-garg-461736b8/'>Aastha Garg</a></p></div></div></div>");
		pw.println("<script type='text/javascript' src='../jquery-2.2.3.min.js'></script><script type='text/javascript' src='../bootstrap-3.1.1.min.js'></script>");

	}
}