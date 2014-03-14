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

import dao.LeagueDAO;
import dao.NationDAO;
import dao.SeasonDAO;
import entity.derived.TopScorePlayerRow;

import biz.LeagueBIZ;
import biz.SeasonBIZ;

import utility.json.JsonMessage;

public class LeaguePlayerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LeaguePlayerServlet() {
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

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JsonMessage jm = new JsonMessage();
		LeagueDAO leagueDAO = new LeagueDAO();
		NationDAO nationDAO = new NationDAO();
		LeagueBIZ leagueBIZ = new LeagueBIZ(leagueDAO, nationDAO);

		String leagueName = request.getParameter("league");
		String strSeasonStartyear = request.getParameter("season");

		if (strSeasonStartyear == null) {
			SeasonDAO seasonDAO = new SeasonDAO();
			SeasonBIZ seasonBIZ = new SeasonBIZ(seasonDAO);
			Integer currentSeason = seasonBIZ.findCurrentSeason();
			strSeasonStartyear = currentSeason.toString();
		}

		if (leagueName == null || strSeasonStartyear == null) {
			jm.setSucceed(false);
			jm.setMessage("missing arguments");
			out.println(jm.toJSONString());
			out.flush();
			out.close();
		} else {
			Integer seasonStartYear = Integer.parseInt(strSeasonStartyear);
			try {
				List<TopScorePlayerRow> list = leagueBIZ.findTopScorePlayerStatisticByLeagueAndSeasonStartYear(
						leagueName, seasonStartYear);
				
				ArrayList<Map<String, Object>> rankArray = new ArrayList<Map<String,Object>>();
				for (TopScorePlayerRow topScorePlayerRow : list) {
					rankArray.add(topScorePlayerRow.toMap());
				}
				
				jm.addData("ranks", rankArray);
				jm.setMessage(JsonMessage.SUCCEED_MSG);
				jm.setSucceed(true);
			} catch (Exception e) {
				jm.setSucceed(false);
				jm.setMessage(e.getMessage());
			}finally{
				out.println(jm.toJSONString());
				out.flush();
				out.close();
			}
		}

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
