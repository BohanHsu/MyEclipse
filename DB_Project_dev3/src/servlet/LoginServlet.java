package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import dao.AdminDAO;
import entity.Admin;

import biz.AdminBIZ;

import utility.json.JsonMessage;
import utility.json.MyJSONParser;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		AdminDAO adminDAO = new AdminDAO();
		AdminBIZ adminBIZ = new AdminBIZ(adminDAO);
		Admin admin = null;
		String msgStr = null;
		JsonMessage jm = new JsonMessage();
		
//		String str = (String) request.getParameter("postString");
		
		
//		System.out.println(str);
		

//		if(str == null){
//			jm.setMessage("no data");
//			jm.setSucceed(false);
//			out.println(jm.toJSONString());
//			out.flush();
//			out.close();
//			return;
//		}
		
//		Map<String, String> jsonMap = MyJSONParser.jSonEntityToMap(str);
//				
//		String userName = jsonMap.get("username");
//		String passWord = jsonMap.get("password");
		
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		
		if(userName == null){
			jm.setMessage("empty username");
			jm.setSucceed(false);
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		
		if(passWord == null){
			jm.setMessage("empty password");
			jm.setSucceed(false);
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		
		try {
			admin = adminBIZ.adminLogin(userName, passWord);
			msgStr = "succeed";
		} catch (Exception e) {
			msgStr = e.getMessage();
		}finally{
		
			request.getSession().setAttribute("currentAdmin", admin);
			
			if(admin == null){
				jm.setSucceed(false);
				jm.setMessage(msgStr);
			}else{
				
				
				jm.addData("username", admin.getUsername());
				jm.addData("email", admin.getEmail());
				jm.setSucceed(true);
				jm.setMessage(JsonMessage.SUCCEED_MSG);
				System.out.println(this.getClass().getName()+":"+admin.getUsername() + " login!");
			}
			String jStr = jm.toJSONString();
			out.println(jStr);
			
			out.flush();
			out.close();			
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
