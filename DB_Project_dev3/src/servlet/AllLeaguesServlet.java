package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.json.JsonMessage;

import dao.LeagueDAO;
import dao.NationDAO;
import entity.League;

import biz.LeagueBIZ;

public class AllLeaguesServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AllLeaguesServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JsonMessage jm = new JsonMessage();

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		LeagueDAO leagueDAO = new LeagueDAO();
		NationDAO nationDAO = new NationDAO();
		LeagueBIZ leagueBIZ = new LeagueBIZ(leagueDAO, nationDAO);

		List<League> resultList = leagueBIZ.findAllLeagues();
		List<Map<String, Object>> resultArray = new ArrayList<Map<String, Object>>();

		for (League league : resultList) {
			resultArray.add(league.toMap());
		}

		jm.addData("leagues", resultArray);
		jm.setSucceed(true);
		jm.setMessage(JsonMessage.SUCCEED_MSG);

		out.println(jm.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
