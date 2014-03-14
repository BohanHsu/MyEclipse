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
import utility.json.MyJSONParser;

import biz.LeagueBIZ;
import biz.SeasonBIZ;
import dao.CityDAO;
import dao.ClubDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.PlaysinDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Admin;
import entity.League;
import entity.derived.StandingRow;

public class LeagueServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LeagueServlet() {
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

		LeagueDAO leagueDAO = new LeagueDAO();
		MatchDAO matchDAO = new MatchDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		PlayingDAO playingdDao = new PlayingDAO();
		PlayerDAO playerDAO = new PlayerDAO();
		PlaysinDAO playsinDAO = new PlaysinDAO();
		RoundDAO roundDAO = new RoundDAO();
		LeagueBIZ leagueBIZ = new LeagueBIZ(leagueDAO, matchDAO, clubDAO,
				cityDAO, seasonDAO, playingdDao, playerDAO, playsinDAO,
				roundDAO);
		JsonMessage jm = new JsonMessage();
		String msgStr = jm.SUCCEED_MSG;

		String leagueName = request.getParameter("league");
		String strSeasonStartyear = request.getParameter("season");
		
		if(strSeasonStartyear == null){
			SeasonBIZ seasonBIZ = new SeasonBIZ(seasonDAO);
			Integer currentSeason = seasonBIZ.findCurrentSeason();
			strSeasonStartyear = currentSeason.toString();
		}

		if (leagueName == null || strSeasonStartyear == null) {
			jm.setSucceed(false);
			jm.setMessage("missing arguments");
		} else {

			Integer seasonStartYear = Integer.parseInt(strSeasonStartyear);
			List<StandingRow> resultList = leagueBIZ
					.queryStatisicByLeagueNameAndSeasonStartYear(leagueName,
							seasonStartYear);
			List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();

			for (StandingRow standingRow : resultList) {
				array.add(standingRow.toMap());
			}

			jm.addData("ranks", array);
			jm.setMessage(msgStr);
			jm.setSucceed(true);
		}

		String jStr = jm.toJSONString();

		out.println(jStr);

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

		JsonMessage jm = new JsonMessage();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

//		String jsonExp = request.getParameter("postMsg");

//		String leagueName = MyJSONParser.getValueFromEntity("name", jsonExp);
//		String nationName = MyJSONParser.getValueFromEntity("nation", jsonExp);
		
		Admin admin = (Admin) request.getSession().getAttribute("currentAdmin");

		if (admin == null) {
			jm.setSucceed(false);
			jm.setMessage("please login!");
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}else{
			System.out.println(this.getClass().getName()+":"+admin.getUsername() + " is calling");
		}
		
		String leagueName = request.getParameter("name");
		String nationName = request.getParameter("nation");

		if (leagueName == null) {
			jm.setSucceed(false);
			jm.setMessage("missing name");
			out.print(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}

		System.out.println("adding league: name: "+leagueName+" nation: "+nationName);
		
		LeagueDAO leagueDAO = new LeagueDAO();
		NationDAO nationDAO = new NationDAO();
		LeagueBIZ leagueBIZ = new LeagueBIZ(leagueDAO, nationDAO);

		League newLeague = null;

		try {
			newLeague = leagueBIZ.addLeague(leagueName, nationName);
			jm.appendMapToData(newLeague.toMap());
			jm.setSucceed(true);
			jm.setMessage(jm.SUCCEED_MSG);
		} catch (Exception e) {
			jm.setSucceed(false);
			jm.setMessage(e.getMessage());
		} finally {
			out.println(jm.toJSONString());
			out.flush();
			out.close();
		}
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
