import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Result extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		
			pw.println(" <!DOCTYPE html><html lang='zxx'><head><title>Election Result</title>");
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
			pw.println("<li><a href='Nominees'>Nominees</a></li>");
			pw.println("<li><a href='Result' class='active'>Result</a></li></ul>");
			}
			else if(usertype.equalsIgnoreCase("Candidates"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='Schedule' >Schedule</a></li>");
			pw.println("<li><a href='Nominees'>Nominees</a></li>");
			pw.println("<li><a href='Result' class='active'>Result</a></li></ul>");
			}
			else if(usertype.equalsIgnoreCase("Voters"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='Schedule' >Schedule</a></li>");
			pw.println("<li><a href='Nominees'>Nominees</a></li>");
			pw.println("<li><a href='VoteNow' >Vote Now</a></li>");
			pw.println("<li><a href='Result' class='active'>Result</a></li></ul>");
			}
			
			pw.println("</div><div class='clearfix'> </div>");	
			pw.println("</nav><div class='header-right-w3ls'><a href='Logout'>LogOut</a></div><div class='clearfix'></div></div></div></div>");
			pw.println("<div class='w3l_agileits_breadcrumbs'><div class='container'><div class='w3l_agileits_breadcrumbs_inner'><ul><li><a href='Schedule'>Home</a><span>&#187;</span></li>");
			pw.println("<li>Election Result</li></ul></div></div></div>");
			pw.println("<div class='matches elite-app'><div class='container'><div class='tittle-agileinfo'><h3>Election Result</h3></div>");
	
			
		int i=0,flag=0,count=0;
		String pos="",name="";
		String no=(String)session.getAttribute("no");
		
		try
		{   
				Connection cn=DBInfo.con;
				String query="select distinct position from votes where OrganisationNo = ?";
				PreparedStatement ps=cn.prepareStatement(query);
				ps.setString(1,no);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{   flag=1;
					pos=rs.getString("position");
					query="select * from votes where OrganisationNo=? and position=? order by count desc";
					ps=cn.prepareStatement(query);
					ps.setString(1,no);
					ps.setString(2,pos);
					ResultSet rss=ps.executeQuery();
					pw.println("<h3>"+pos+":</h3><br><div class='clearfix'></div>");
					pw.println("<div class='matches-main-agileinfo'><div class=' person-info-agileits-w3layouts'><ul>");
					while(rss.next())
					{
						name=rss.getString("name");
						count=rss.getInt("count");
						
						pw.println("<li><span><h3>"+name+"</h3></span>");
						pw.println(":"+count+"</li>");
						
					}
				 pw.println("</ul></div><div class='clearfix'></div></div>");
				}	
			  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		if(flag==0)
		{  
		   pw.println("<h3>Election not started yet</h3><br><br><br><br><br><br><div class='clearfix'></div>");

		}	
		
		pw.println("</div></div>");
		pw.println("<div class='footer-w3layouts'><div class='container'><div class='agile-copy'><p> Developed and Maintained by <a href='https://www.linkedin.com/in/aastha-garg-461736b8/'>Aastha Garg</a></p></div></div></div>");
		pw.println(" <script type='text/javascript' src='../jquery-2.2.3.min.js'></script><script type='text/javascript' src='../bootstrap-3.1.1.min.js'></script> ");
		pw.println("</body></html>");
	}
}