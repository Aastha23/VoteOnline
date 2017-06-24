import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Nominees extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		
			pw.println(" <!DOCTYPE html><html lang='zxx'><head><title>Nominees for Election</title>");
			pw.println("<meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
			pw.println("<meta name='keywords' content='Free elections for your school, Free elections for your organisation, Very less time required, Held online elections'/>");
			pw.println("<script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false);function hideURLbar(){ window.scrollTo(0,1); } </script>");
			pw.println("<link href='../bootstrap.css' rel='stylesheet' type='text/css' media='all' />");
			pw.println("<link href='../style.css' rel='stylesheet' type='text/css' media='all' />");
			pw.println("<style type='text/css'>body{font-family: 'Source Sans Pro', sans-serif;}  #home{background-image: url(../banner.jpg);}.clr{ color:white;}</style>");
			pw.println("</head>");
		    pw.println("<body><div class='banner-w3ls inner-banner-agileits' id='home'><div class='container'><div class='header-inner'>");
			pw.println("<h1 class='logo'><a href='../index.html'><span>Vote Online</span></a></h1><nav class='navbar navbar-default'>");
			pw.println("<div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1'>");
			pw.println("<span class='sr-only'>Toggle navigation</span><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span>");
			pw.println("</button></div><div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>");
			
			
			HttpSession session=req.getSession();
			String usertype=(String)session.getAttribute("usertype");
			if(usertype.equalsIgnoreCase("Organisation"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='../organisationhome.html' >Add Election</a></li>");
			pw.println("<li><a href='Schedule' >Schedule</a></li>");
			pw.println("<li><a href='Nominees' class='active'>Nominees</a></li>");
			pw.println("<li><a href='Result'>Result</a></li></ul>");
			}
			else if(usertype.equalsIgnoreCase("Candidates"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='Schedule' >Schedule</a></li>");
			pw.println("<li><a href='Nominees' class='active'>Nominees</a></li>");
			pw.println("<li><a href='Result'>Result</a></li></ul>");
			}
			else if(usertype.equalsIgnoreCase("Voters"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='Schedule' >Schedule</a></li>");
			pw.println("<li><a href='Nominees' class='active'>Nominees</a></li>");
			pw.println("<li><a href='VoteNow' >Vote Now</a></li>");
			pw.println("<li><a href='Result'>Result</a></li></ul>");
			}
			
			pw.println("</div><div class='clearfix'> </div>");	
			pw.println("</nav><div class='header-right-w3ls'><a href='Logout'>LogOut</a></div><div class='clearfix'></div></div></div></div>");
			pw.println("<div class='w3l_agileits_breadcrumbs'><div class='container'><div class='w3l_agileits_breadcrumbs_inner'><ul><li><a href='Schedule'>Home</a><span>&#187;</span></li>");
			pw.println("<li>Election Nominees</li></ul></div></div></div>");
			pw.println("<div class='matches elite-app'><div class='container'><div class='tittle-agileinfo'><h3>Nominees for election</h3></div>");
	
			
			
		int flag=0;
		String pos="",name="",motto="",comment="";
		String no=(String)session.getAttribute("no");
		
		try
		{   
			Connection cn=DBInfo.con;
			String query="select * from candidates where OrganisationNo = ?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,no);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{  
			flag=1;
			pw.println("<div class='matches-main-agileinfo'><div class=' person-info-agileits-w3layouts'><ul>");
			pw.println("<li><span><h3>Position</h3></span>");
			pos=rs.getString("Position");
			pw.println(":"+pos+"</li>");
			pw.println("<li><span>Name</span>");
			name=rs.getString("Name");
			pw.println(":"+name+"</li>");
			pw.println("<li><span>Motto</span>");
			motto=rs.getString("Motto");
			pw.println(":"+motto+"</li>");
			pw.println("<li><span>Additional Info</span>");
			comment=rs.getString("Comment");
			pw.println(":"+comment+"</li>");
			pw.println("</ul></div><div class='clearfix'></div></div>");
				
			}
			
		//	System.out.println("flag is: "+flag);
			  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		if(flag==0)
		{  
		   pw.println("<h3>No one has nominated yet</h3><br><br><br><br><br><br><div class='clearfix'></div>");

		}	
		
		pw.println("</div></div>");
		pw.println("<div class='footer-w3layouts'><div class='container'><div class='agile-copy'><p> Developed and Maintained by <a href='https://www.linkedin.com/in/aastha-garg-461736b8/'>Aastha Garg</a></p></div></div></div>");
		pw.println(" <script type='text/javascript' src='../jquery-2.2.3.min.js'></script><script type='text/javascript' src='../bootstrap-3.1.1.min.js'></script> ");
		pw.println("</body></html>");
	}
}