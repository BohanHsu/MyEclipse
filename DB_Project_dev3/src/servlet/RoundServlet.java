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

import dao.CityDAO;
import dao.ClubDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Match;

import biz.MatchBIZ;

import utility.json.JsonMessage;

public class RoundServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RoundServlet() {
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
		String msgStr = JsonMessage.SUCCEED_MSG;

		MatchDAO matchDAO = null;
		ClubDAO clubDAO = null;
		CityDAO cityDAO = null;
		LeagueDAO leagueDAO = null;
		SeasonDAO seasonDAO = null;
		PlayingDAO playingdDao = null;
		PlayerDAO playerDAO = null;
		RoundDAO roundDAO = null;

		MatchBIZ matchBIZ = null;

		List<Match> resultMatchList = null;

		String leagueName = request.getParameter("league");
		String strSeasonStartYear = request.getParameter("season");
		String strRound = request.getParameter("round");

		if (leagueName == null || strSeasonStartYear == null
				|| strRound == null) {
			jm.setMessage("missing arguments");
			jm.setSucceed(false);

			out.println(jm.toJSONString());
			out.flush();
			out.close();
		} else {

			matchDAO = new MatchDAO();
			clubDAO = new ClubDAO();
			cityDAO = new CityDAO();
			leagueDAO = new LeagueDAO();
			seasonDAO = new SeasonDAO();
			playingdDao = new PlayingDAO();
			playerDAO = new PlayerDAO();
			roundDAO = new RoundDAO();

			matchBIZ = new MatchBIZ(matchDAO, clubDAO, cityDAO, leagueDAO,
					seasonDAO, playingdDao, playerDAO, roundDAO);

			Integer seasonStartYear = Integer.parseInt(strSeasonStartYear);
			Integer roundNumber = Integer.parseInt(strRound);
			try {
				resultMatchList = matchBIZ.findMatchsByLeagueAndSeasonAndRound(
						leagueName, seasonStartYear, roundNumber);
				ArrayList<Map<String, Object>> matchArray = new ArrayList<Map<String,Object>>();
				
				for (Match match : resultMatchList) {
					matchArray.add(match.toMap());
				}
				
				jm.addData("league", leagueName);
				jm.addData("season", seasonStartYear);
				jm.addData("round", roundNumber);
				jm.addData("matches", matchArray);
				
				jm.setSucceed(true);
				jm.setMessage(msgStr);
				
			} catch (Exception e) {
				jm.setSucceed(false);
				jm.setMessage(e.getMessage());
			} finally {
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

		response.setContentType("application/json");
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
