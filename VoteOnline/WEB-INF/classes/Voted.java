import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Voted extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		int flag=0,i=0,j=0,count=0;
		String pos="",name="";
		HttpSession session=req.getSession();
		String no=(String)session.getAttribute("no");
		String email=(String)session.getAttribute("email");
		
		try
		{   
			Connection cn=DBInfo.con;
			String query="select distinct position from candidates where OrganisationNo=? ";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,no);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{  flag=0;  
				i++;
				name=(String)req.getParameter("radio"+i);
				pos=rs.getString("position");
				//System.out.println("position is: "+pos);
			    //System.out.println("name is: "+name); 
				//counting the number of votes(if same entry exist add count by 1 else add new entry) 
				query="select * from votes where OrganisationNo=? and Position=? and Name=?";
				ps=cn.prepareStatement(query);
				ps.setString(1,no);
				ps.setString(2,pos);
				ps.setString(3,name);
				ResultSet rss=ps.executeQuery();
				while(rss.next())
				{
					flag=1;
					count=rss.getInt("count");
					query="update votes set count=? where OrganisationNo=? and Position=? and Name=?";
					ps=cn.prepareStatement(query);
					ps.setInt(1,count+1);
					ps.setString(2,no);
					ps.setString(3,pos);
					ps.setString(4,name);
					j=ps.executeUpdate();
				}
				
				if(flag==0)
				{	query="insert into votes values(?,?,?,?)";
					ps=cn.prepareStatement(query);
					ps.setString(1,no);
					ps.setString(2,pos);
					ps.setString(3,name);
					ps.setInt(4,1);
					j=ps.executeUpdate();	
				}
			}
			
			query="insert into havevoted values(?,?)";
			ps=cn.prepareStatement(query);
			ps.setString(1,no);
			ps.setString(2,email);
			int k=ps.executeUpdate();
			
			
		   res.sendRedirect("Result");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}