package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import entity.Player;
import entity.Position;
import entity.Season;

import biz.ClubBIZ;
import biz.EnrollmentBIZ;
import biz.HaspositionBIZ;
import biz.PlayerBIZ;
import biz.PositionBIZ;
import biz.SeasonBIZ;

import utility.json.JsonMessage;
import utility.json.MyJSONParser;

public class AddPlayerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddPlayerServlet() {
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

		Admin admin = (Admin) request.getSession().getAttribute("currentAdmin");

		if (admin == null) {
			jm.setSucceed(false);
			jm.setMessage("please login!");
			out.println(jm.toJSONString());
			
			out.flush();
			out.close();
			return;
		}

		String name = request.getParameter("name");
		String nationName = request.getParameter("nationname");
		String strBirthday = request.getParameter("birthday");
		String positions = request.getParameter("positions");
		
		String clubName = request.getParameter("club");
		String number = request.getParameter("number");
		String salary = request.getParameter("salary");

		if (name == null) {
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

		PlayerBIZ playerBIZ = new PlayerBIZ(playerDAO, nationDAO, positionDAO,
				haspositionDAO, clubDAO, cityDAO, seasonDAO, enrollmentDAO);
		HaspositionBIZ haspositionBIZ = new HaspositionBIZ(haspositionDAO);
		PositionBIZ positionBIZ = new PositionBIZ(positionDAO);

		List<String> positionNames = MyJSONParser
				.getAllEntityInArray(positions);

		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday;
		try {
			birthday = dateFormat1.parse(strBirthday);
		} catch (ParseException e) {
			jm.setSucceed(false);
			jm.setMessage("wrong date information");
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}

		Player player = null;

		try {
			player = playerBIZ.addPlayer(nationName, name, birthday,
					positionNames);

			for (String string : positionNames) {
				Position position = positionBIZ.findPositionByName(string);
				haspositionBIZ.addHasposition(player, position);
			}

			
			if(clubName != null && number != null && salary != null){
				Club club = new ClubBIZ(new ClubDAO(), new CityDAO()).findClubByName(clubName);
				SeasonBIZ seasonBIZ = new SeasonBIZ(new SeasonDAO());
				Integer seasonNumber = seasonBIZ.findCurrentSeason();
				Season season = seasonBIZ.findSeasonByStartYear(seasonNumber);
				new EnrollmentBIZ(new EnrollmentDAO()).addEnrollment(season, player, club, Integer.parseInt(number), Long.parseLong(salary));
			}
			
			player = playerBIZ.findPlayerById(player.getId());

			jm.appendMapToData(player.toMap());
			jm.setSucceed(true);
			jm.setMessage(JsonMessage.SUCCEED_MSG);
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
