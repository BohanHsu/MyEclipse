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

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.HaspositionDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PositionDAO;
import dao.SeasonDAO;
import entity.Player;
import entity.Position;

import biz.HaspositionBIZ;
import biz.PlayerBIZ;

public class PlayerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PlayerServlet() {
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

		String strId = request.getParameter("id");

		String strName = request.getParameter("name");


		PlayerDAO playerDAO = new PlayerDAO();
		NationDAO nationDAO = new NationDAO();
		PositionDAO positionDAO = new PositionDAO();
		HaspositionDAO haspositionDAO = new HaspositionDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

		Player player = null;

		PlayerBIZ playerBIZ = new PlayerBIZ(playerDAO, nationDAO, positionDAO,
				haspositionDAO, clubDAO, cityDAO, seasonDAO, enrollmentDAO);
		
		
		if (strId == null && strName == null) {
//			jm.setSucceed(false);
//			jm.setMessage("missing arguments");
//			out.print(jm.toJSONString());
//			return;
			
			List<Player> playerList = playerBIZ.getAllPlayers();
			List<Map<String , Object>> resultList = new ArrayList<Map<String,Object>>();
			
			for (Player player2 : playerList) {
				resultList.add(player2.toMap());
			}
			
			jm.addData("players", resultList);
			jm.setSucceed(true);
			jm.setMessage(JsonMessage.SUCCEED_MSG);
			out.println(jm.toJSONString());
			out.flush();
			out.close();
			return;
		}
		

		HaspositionBIZ haspositionBIZ = new HaspositionBIZ(haspositionDAO);
		
		
		
		try {
			if (strId != null) {
				Integer id = Integer.parseInt(strId);
				player = playerBIZ.findPlayerById(id);
			} else {
				player = playerBIZ.findPlayerByName(strName);
			}
			
			List<Position> positions = haspositionBIZ.findPositionByPlayer(player);
			List<Map<String, Object>> positionArray = new ArrayList<Map<String,Object>>();
			
			for (Position position : positions) {
				positionArray.add(position.toMap());
			}
			
//			jm.addData("player", player.toMap());
			jm.appendMapToData(player.toMap());
			jm.addData("positions", positionArray);
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

		new AddPlayerServlet().doPost(request, response);
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
