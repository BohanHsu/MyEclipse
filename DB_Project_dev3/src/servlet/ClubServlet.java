package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.json.JsonMessage;
import utility.json.MyJSONParser;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.SeasonDAO;
import entity.Admin;
import entity.Club;
import entity.Player;

import biz.ClubBIZ;
import biz.EnrollmentBIZ;
import biz.SeasonBIZ;

public class ClubServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ClubServlet() {
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

		Club club = null;
		Integer currentSeason = null;
		List<Player> playerList = null;

		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		SeasonDAO seasonDAO = new SeasonDAO();

		ClubBIZ clubBIZ = new ClubBIZ(clubDAO, cityDAO);
		EnrollmentBIZ enrollmentBIZ = new EnrollmentBIZ(enrollmentDAO);
		SeasonBIZ seasonBIZ = new SeasonBIZ(seasonDAO);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		// /test print
		System.out.println("url: " + request.getRequestURL());
		String clubName = (String) request.getParameter("name");
		System.out.println("club name:" + clubName);
		///
		
		if(clubName == null){
			List<Club> clubList = clubBIZ.getAllClubs();
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			for (Club club2 : clubList) {
				resultList.add(club2.toMap());
			}
			jm.addData("clubs", resultList);
			jm.setSucceed(true);
			jm.setMessage(JsonMessage.SUCCEED_MSG);
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}

		try {
			club = clubBIZ.findClubByName(clubName);
			currentSeason = seasonBIZ.findCurrentSeason();
			playerList = enrollmentBIZ.findPlayersByClub(club, currentSeason);

		} catch (Exception e) {
			msgStr = e.getMessage();
			jm.setSucceed(false);
			jm.setMessage(msgStr);

			String jStr = jm.toJSONString();
			System.out.println(jm.toJSONString());
			out.println(jStr);
			out.flush();
			out.close();
			return;

		} finally {
			if (club == null) {
			} else {

				jm.appendMapToData(club.toMap());
				jm.setSucceed(true);
				jm.setMessage(msgStr);

				ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

				for (Player player : playerList) {
					list.add(player.toMap());

				}

				jm.addData("players", list);
			}
			String jStr = jm.toJSONString();
			System.out.println(jm.toJSONString());
			out.println(jStr);
			out.flush();
			out.close();
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

		JsonMessage jm = new JsonMessage();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Admin admin = (Admin) request.getSession().getAttribute("currentAdmin");

		if (admin == null) {
			jm.setSucceed(false);
			jm.setMessage("please login!");
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		ClubBIZ clubBIZ = new ClubBIZ(clubDAO, cityDAO);
		
		Club club = null;
		
//		String jsonExpression = request.getParameter("postMsg");
//		
//		String name = MyJSONParser.getValueFromEntity("name", jsonExpression);
//		String nickname = MyJSONParser.getValueFromEntity("nickname", jsonExpression);
//		String cityName = MyJSONParser.getValueFromEntity("cityName", jsonExpression);
//		
//		String strStart = MyJSONParser.getValueFromEntity("start", jsonExpression);
//		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date start;
//		try {
//			start = dateFormat1.parse(strStart);
//		} catch (ParseException e) {
//			jm.setSucceed(false);
//			jm.setMessage("wrong date information");
//			out.println(jm.toJSONString());
//			out.flush();
//			out.close();
//			return;
//		}
//		
//		String home = MyJSONParser.getValueFromEntity("home", jsonExpression);
//		String owner = MyJSONParser.getValueFromEntity("owner", jsonExpression);
//		String coach = MyJSONParser.getValueFromEntity("coach", jsonExpression);

		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String cityName = request.getParameter("cityname");
		String strStart = request.getParameter("starttime");
		
		System.out.println(name);
		System.out.println(nickname);
		System.out.println(cityName);
		System.out.println(strStart);
		
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start;
		try {
		start = dateFormat1.parse(strStart);
	} catch (ParseException e) {
		jm.setSucceed(false);
		jm.setMessage("wrong date information");
		out.println(jm.toJSONString());
		out.flush();
		out.close();
		return;
	}
		
		String home = request.getParameter("home");
		String owner = request.getParameter("owner");
		String coach = request.getParameter("coach");

		
		
		if(name == null){
			jm.setSucceed(false);
			jm.setMessage("missing name");
			out.print(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		
		try {
			club = clubBIZ.addClub(cityName, name, nickname, start, home, owner, coach);
			jm.appendMapToData(club.toMap());
			jm.setSucceed(true);
			jm.setMessage(JsonMessage.SUCCEED_MSG);
			
		} catch (Exception e) {
			jm.setSucceed(false);
			jm.setMessage(e.getMessage());
		}
		out.print(jm.toJSONString());
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
