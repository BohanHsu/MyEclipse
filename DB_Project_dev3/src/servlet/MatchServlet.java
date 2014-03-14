package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.json.JsonMessage;

import dao.CityDAO;
import dao.ClubDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Match;
import entity.Playing;

import biz.MatchBIZ;
import biz.PlayingBIZ;

public class MatchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MatchServlet() {
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
		String msgStr = JsonMessage.SUCCEED_MSG;
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String strMatchId = request.getParameter("match");
		
		MatchDAO matchDAO = new MatchDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		LeagueDAO leagueDAO = new LeagueDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		PlayingDAO playingdDao = new PlayingDAO();
		PlayerDAO playerDAO = new PlayerDAO();
		RoundDAO roundDAO = new RoundDAO();

		MatchBIZ matchBIZ = new MatchBIZ(matchDAO, clubDAO, cityDAO,
				leagueDAO, seasonDAO, playingdDao, playerDAO, roundDAO);

		if (strMatchId == null) {
//			jm.setSucceed(false);
//			jm.setMessage("missing arguments");
//			out.println(jm.toJSONString());
//			out.flush();
//			out.close();
			List<Match> matchList = matchBIZ.getAllMatches();
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			for (Match match : matchList) {
				resultList.add(match.toMap());
			}
			
			jm.addData("matches", resultList);
			jm.setSucceed(true);
			jm.setMessage(JsonMessage.SUCCEED_MSG);
			out.print(jm.toJSONString());
			out.flush();
			out.close();
			return;
			
		} else {

			PlayingBIZ playingBIZ = new PlayingBIZ(playingdDao, playerDAO);

			int matchId = Integer.parseInt(strMatchId);

			try {
				Match match = matchBIZ.findMatchById(matchId);
				List<Playing> homePlayings = playingBIZ
						.findPlayerForHome(match);
				List<Playing> visitPlayings = playingBIZ
						.findPlayerForVisit(match);

				List<Map<String, Object>> homePlayingsJsonArray = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> visitPlayingsJsonArray = new ArrayList<Map<String, Object>>();

				for (Playing playing : homePlayings) {
					homePlayingsJsonArray.add(playing.toMap());
				}

				for (Playing playing : visitPlayings) {
					visitPlayingsJsonArray.add(playing.toMap());
				}

				jm.addData("match", match.toMap());
				jm.addData("homePlayers", homePlayingsJsonArray);
				jm.addData("visitPlayers", visitPlayingsJsonArray);

				jm.setSucceed(true);

			} catch (Exception e) {
				msgStr = e.getMessage();
			} finally {
				jm.setMessage(msgStr);

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

		JsonMessage jm = new JsonMessage();
		
		// add match
		String leagueNameStr = request.getParameter("leaguename");
		String seasonStartYearStr = request.getParameter("seasonStartYear");
		String homeIdStr = request.getParameter("homeId");
		String visitIdStr = request.getParameter("visitId");
		String roundNumberStr = request.getParameter("roundNumber");
		String dateStr = request.getParameter("date");
		String stadium = request.getParameter("stadium");
		String homeScoreStr = request.getParameter("homeScore");
		String visitScoreStr = request.getParameter("visitScore");

		if (leagueNameStr == null && seasonStartYearStr == null
				&& homeIdStr == null && visitIdStr == null
				&& roundNumberStr == null && dateStr == null && stadium == null
				&& homeScoreStr == null && visitScoreStr == null) {
			jm.setMessage("missing arguments");
			jm.setSucceed(false);
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}

		MatchDAO matchDAO = new MatchDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		LeagueDAO leagueDAO = new LeagueDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		PlayingDAO playingdDao = new PlayingDAO();
		PlayerDAO playerDAO = new PlayerDAO();
		RoundDAO roundDAO = new RoundDAO();
		MatchBIZ matchBIZ = new MatchBIZ(matchDAO, clubDAO, cityDAO, leagueDAO,
				seasonDAO, playingdDao, playerDAO, roundDAO);

		String leagueName = leagueNameStr;
		Integer seasonStartYear = Integer.parseInt(seasonStartYearStr);
		Integer homeTeamId = Integer.parseInt(homeIdStr);
		Integer visitTeamId = Integer.parseInt(visitIdStr);
		Integer roundNumber = Integer.parseInt(roundNumberStr);
		
		
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat1.parse(dateStr);
		} catch (ParseException e) {
			jm.setSucceed(false);
			jm.setMessage("wrong date information");
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		
		Integer homeScore = Integer.parseInt(homeScoreStr);
		Integer visitScore = Integer.parseInt(visitScoreStr);
		List<Integer> playerIds = null;

		try {
			matchBIZ.addMatch(leagueName, seasonStartYear, homeTeamId,
					visitTeamId, roundNumber, date, stadium, homeScore,
					visitScore, playerIds);
		} catch (Exception e) {
			jm.setSucceed(false);
			jm.setMessage(e.getMessage());
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}finally{
			jm.setSucceed(true);
			jm.setMessage(JsonMessage.SUCCEED_MSG);
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
