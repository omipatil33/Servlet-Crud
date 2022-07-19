import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Add extends HttpServlet 
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       Connection con=null;
       int sno=0;

       try
         {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "javapatil"); 

           String query="Select max(sno) maxnum from student";

           Statement stmt=con.createStatement();
           ResultSet rs=stmt.executeQuery(query);

           if(rs.next()) 
             {
               sno=rs.getInt("maxnum");             
             }

           rs.close();
           stmt.close();
           con.close();
         }
       catch(Exception e)
         {
           System.out.println(e);
         }

        sno++; 

        out.println("<html>");
        out.println("<body>");

        out.println("<form action='Insert'>");        

        out.println("<table bgcolor='yellow' align='center'>");        

        out.println("<tr>");        
        out.println("<td>S. No: </td>");        
        out.println("<td>" + sno + "<input type='hidden' name='sno' value='" + sno + "'></td>");        
        out.println("</tr>");        

        out.println("<tr>");        
        out.println("<td>S. Name: </td>");        
        out.println("<td><input type='text' name='sname'></td>");        
        out.println("</tr>");       

        out.println("<tr>");        
        out.println("<td>Age: </td>");        
        out.println("<td><input type='text' name='age'></td>");        
        out.println("</tr>");       

        out.println("<tr>");        
        out.println("<th colspan='1'><input type='Submit' value='Submit'></th>");        
        out.println("</tr>");        

        out.println("</table>");        

        out.println("</form>");        

        out.println("</body>");
        out.println("</html>");
    }

}



