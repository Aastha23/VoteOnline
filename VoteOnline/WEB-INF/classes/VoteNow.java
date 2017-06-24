import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class VoteNow extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		
			pw.println(" <!DOCTYPE html><html lang='zxx'><head><title>Vote Now</title>");
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
			pw.println("<li><a href='Schedule' class='active'>Schedule</a></li>");
			pw.println("<li><a href='Nominees'>Nominees</a></li>");
			pw.println("<li><a href='Result'>Result</a></li></ul>");
			}
			else if(usertype.equalsIgnoreCase("Candidates"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='Schedule' class='active'>Schedule</a></li>");
			pw.println("<li><a href='Nominees'>Nominees</a></li>");
			pw.println("<li><a href='Result'>Result</a></li></ul>");
			}
			else if(usertype.equalsIgnoreCase("Voters"))
			{
			pw.println("<ul class='nav navbar-nav navbar-right'>");
			pw.println("<li><a href='Schedule' >Schedule</a></li>");
			pw.println("<li><a href='Nominees'>Nominees</a></li>");
			pw.println("<li><a href='VoteNow' class='active'>Vote Now</a></li>");
			pw.println("<li><a href='Result'>Result</a></li></ul>");
			}
			
			pw.println("</div><div class='clearfix'> </div>");	
			pw.println("</nav><div class='header-right-w3ls'><a href='Logout'>LogOut</a></div><div class='clearfix'></div></div></div></div>");
			pw.println("<div class='w3l_agileits_breadcrumbs'><div class='container'><div class='w3l_agileits_breadcrumbs_inner'><ul><li><a href='Schedule'>Home</a><span>&#187;</span></li>");
			pw.println("<li>Vote Now</li></ul></div></div></div>");
			pw.println("<div class='matches elite-app'><div class='container'><div class='tittle-agileinfo'><h3>Vote Now</h3></div>");
	
			
			
		int flag=0,i=0,flag2=0,flag3=0;
		String pos="",name="";
		String email=(String)session.getAttribute("email");
		String no=(String)session.getAttribute("no");
		//System.out.println("no is:"+no);
		//System.out.println("email is:"+email);
		
		
		try
		{   
			Connection cn=DBInfo.con;
			String query="select * from havevoted where Email = ?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{  // System.out.println("flag is:"+flag);
				flag=1;
			}
			
			if(flag==1)
			{  
				pw.println("<h3>You have already voted</h3><br><br><br><br><br><br><div class='clearfix'></div>");
			}
			else
			{
				 query="select distinct position from candidates where OrganisationNo=? ";
				 ps=cn.prepareStatement(query);
				 ps.setString(1,no);
				 rs=ps.executeQuery();
				 pw.println("<form action='Voted' method='post'>");
				 while(rs.next())
				 {  flag2=1;
					pos=rs.getString("position");
					//checking whether a voter is voting in scheduled date of an organisation position or not
					query="select * from schedule where OrganisationNo=? and Position=?";
					ps=cn.prepareStatement(query);
					ps.setString(1,no);
					ps.setString(2,pos);
					ResultSet rst=ps.executeQuery();
					rst.next();
					
					java.util.Date SDate = new java.util.Date(rst.getDate("StartDate").getTime());
					java.util.Date EDate = new java.util.Date(rst.getDate("EndDate").getTime());
					java.util.Date now= new java.util.Date();
					//System.out.println(SDate);
					//System.out.println(EDate);
					//System.out.println(now);
					
					if(now.after(SDate) && now.before(EDate))
					{	
						flag3=1;
						query="select distinct Name from candidates where OrganisationNo=? and position=?";
						ps=cn.prepareStatement(query);
						ps.setString(1,no);
						ps.setString(2,pos);
						ResultSet rss=ps.executeQuery();
						i++;
						pw.println("<div class='w3_form_body_grid'><span>"+pos);
						pw.println("<br></span><div class='w3_gender'>");
							while(rss.next())
							{ 
								name=rss.getString("name");
								pw.println("<input type='radio' name='radio" +i+ "'" +"value="+name+">"+name+"&nbsp;&nbsp;");
								
							}
						pw.println("</div><div class='clearfix'> </div></div><br><br>");
					}
					
				 }	
			    if(flag3!=1)
				{  
					pw.println("<h3>Election hasn't started yet or it has ended. Please refer schedule for the same.</h3><br><br><br><br><br><br><div class='clearfix'></div>");
				} 
				else if(flag2==1)	
				{	
					pw.println("<input type='submit' value='Submit'>");
				}
				else if(flag2!=1)
				{  
					pw.println("<h3>No one nominated from your organisation</h3><br><br><br><br><br><br><div class='clearfix'></div>");
				}
				
				 pw.println("</form>");
			}				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		pw.println("</div></div>");
		pw.println("<div class='footer-w3layouts'><div class='container'><div class='agile-copy'><p> Developed and Maintained by <a href='https://www.linkedin.com/in/aastha-garg-461736b8/'>Aastha Garg</a></p></div></div></div>");
		pw.println("<script type='text/javascript' src='../jquery-2.2.3.min.js'></script><script type='text/javascript' src='../bootstrap-3.1.1.min.js'></script> ");
		pw.println("</body></html>");
	}
}