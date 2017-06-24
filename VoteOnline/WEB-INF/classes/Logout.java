import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Logout extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{

		HttpSession session=req.getSession();
		session.invalidate();
		res.sendRedirect("../index.html");
	}
}