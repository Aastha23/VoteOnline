import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		
			pw.println(" <!DOCTYPE html><html lang='zxx'><head><title>Login Failed</title>");
			pw.println("<meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
			pw.println("<meta name='keywords' content='Free elections for your school, Free elections for your organisation, Very less time required, Held online elections'/>");
			pw.println("<script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false);function hideURLbar(){ window.scrollTo(0,1); } </script>");
			pw.println("<link href='../bootstrap.css' rel='stylesheet' type='text/css' media='all' />");
			pw.println("<link href='../style.css' rel='stylesheet' type='text/css' media='all' />");
			pw.println("<style type='text/css'>body{font-family: 'Source Sans Pro', sans-serif;}body {background-image: url(../banner.jpg);}.clr{ color:white;}</style>");
			pw.println("</head>");
			pw.println("<body><div class='banner-w3ls' id='home'><div class='container'><div class='header-inner'><h1 class='logo'><a href='../index.html'><span> Vote Online</span></a></h1><div class='header-right-w3ls'></div>");
			pw.println("<div class='clearfix'></div></div><div class='w3ls-info'><br><br>");
		
		int flag=0;
		String usertype="",no="";
		String s1=req.getParameter("Email");
		String s2=req.getParameter("Pass");
		
		try
		{
			Connection cn=DBInfo.con;
			String query="select * from login  where email=? and password=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,s1);
			ps.setString(2,s2);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				flag=1;
				usertype=rs.getString("Usertype");
				no=rs.getString("OrganisationNo");
				break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(flag==1)
		{
			HttpSession session=req.getSession();
			session.setAttribute("email",s1);
			session.setAttribute("usertype",usertype);
			session.setAttribute("no",no);
			res.sendRedirect("Schedule");
		}
	
		else
		{
		   pw.println("<h1 class='clr'>Login Failed</h1><br></div><div class='clearfix'></div><div style='text-align:center' class='sim-button button12'><a href='../login.html'>Retry</a></div>");

		}
		pw.println(" </div></div><div class='footer-w3layouts'><div class='container'><div class='agile-copy'><p> Developed and Maintained by <a href='https://www.linkedin.com/in/aastha-garg-461736b8/'>Aastha Garg</a></p></div></div></div>");
		pw.println("<script type='text/javascript' src='../jquery-2.2.3.min.js'></script><script type='text/javascript' src='../bootstrap-3.1.1.min.js'></script>");
		
	}
}