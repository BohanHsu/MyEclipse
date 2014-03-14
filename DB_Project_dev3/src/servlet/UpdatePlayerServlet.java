package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.HaspositionDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PositionDAO;
import dao.SeasonDAO;
import entity.Admin;
import entity.Club;
import entity.Enrollment;
import entity.Hasposition;
import entity.Player;
import entity.Season;

import biz.ClubBIZ;
import biz.EnrollmentBIZ;
import biz.HaspositionBIZ;
import biz.PlayerBIZ;
import biz.SeasonBIZ;

import utility.json.JsonMessage;
import utility.json.MyJSONParser;

public class UpdatePlayerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdatePlayerServlet() {
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

		JsonMessage jm = new JsonMessage();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		Admin admin = (Admin) request.getSession().getAttribute("currentAdmin");

		if (admin == null) {
			jm.setSucceed(false);
			jm.setMessage("please login!");
			out.println(jm.toJSONString());
			return;
		}
		
//		String jsonExp = request.getParameter("postMsg");
//		String strPlayerId = MyJSONParser.getValueFromEntity("id", jsonExp);
//		String strClubName = MyJSONParser.getValueFromEntity("clubName", jsonExp);
//		String strSeasonStartYear = MyJSONParser.getValueFromEntity("seasonStartYear", jsonExp);
//		String strNumber = MyJSONParser.getValueFromEntity("number", jsonExp);
//		String strSalary = MyJSONParser.getValueFromEntity("salary", jsonExp);
	
//		String jsonExp = request.getParameter("name");
		String strPlayerId = request.getParameter("id");
		String strClubName = request.getParameter("clubname");
		String strSeasonStartYear = request.getParameter("seasonstartyear");
		String strNumber = request.getParameter("number");
		String strSalary = request.getParameter("salary");
		
		if(strPlayerId == null || strClubName == null || strSeasonStartYear == null){
			jm.setSucceed(false);
			jm.setMessage("missing arguments");
			
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		
		PlayerDAO playerDAO = new PlayerDAO();
		NationDAO nationDAO = new NationDAO();
		PositionDAO positionDAO = new PositionDAO();
		HaspositionDAO haspositionDAO = new HaspositionDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		
		PlayerBIZ playerBIZ = new PlayerBIZ(playerDAO, nationDAO, positionDAO, haspositionDAO, clubDAO, cityDAO, seasonDAO, enrollmentDAO);
		SeasonBIZ seasonBIZ = new SeasonBIZ(seasonDAO);
		ClubBIZ clubBIZ = new ClubBIZ(clubDAO, cityDAO);
		EnrollmentBIZ enrollmentBIZ = new EnrollmentBIZ(enrollmentDAO);
		
		Player player = null;
		Season season = null;
		Club club = null;
		Enrollment enrollment = null;
		
		Integer number = Integer.parseInt(strNumber);
		Long salary = Long.parseLong(strSalary);
		
		try {
			player = playerBIZ.findPlayerById(Integer.parseInt(strPlayerId));
			season = seasonBIZ.findSeasonByStartYear(Integer.parseInt(strSeasonStartYear));
			club = clubBIZ.findClubByName(strClubName);
			
			enrollmentBIZ.addEnrollment(season, player, club, number, salary);
			
			player = playerBIZ.findPlayerById(Integer.parseInt(strPlayerId));
			jm.appendMapToData(player.toMap());
			jm.setMessage(JsonMessage.SUCCEED_MSG);
			jm.setSucceed(true);
			
		} catch (NumberFormatException e) {
			jm.setSucceed(false);
			jm.setMessage("number can't be parsed"+e.getMessage());
		} catch (Exception e) {
			jm.setSucceed(false);
			jm.setMessage(e.getMessage());
		}finally{
			out.println(jm.toJSONString());
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
