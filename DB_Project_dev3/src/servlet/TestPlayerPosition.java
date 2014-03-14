package servlet;

import java.util.Map;

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
import biz.PlayerBIZ;

public class TestPlayerPosition {
	public static void main(String[] args) throws Exception {
		PlayerDAO playerDAO = new PlayerDAO();
		NationDAO nationDAO = new NationDAO();
		PositionDAO positionDAO = new PositionDAO();
		HaspositionDAO haspositionDAO = new HaspositionDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		
		PlayerBIZ playerBIZ = new PlayerBIZ(playerDAO, nationDAO, positionDAO, haspositionDAO, clubDAO, cityDAO, seasonDAO, enrollmentDAO);
		
		Player player = playerBIZ.findPlayerById(4);
		
		Map<String, Object> map = player.toMap();
		
		JsonMessage jm = new JsonMessage();
		
		jm.appendMapToData(map);
		
		String jsonExpression = jm.toJSONString();
		
		System.out.println(jsonExpression);
	}
}
