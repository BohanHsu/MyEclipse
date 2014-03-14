package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class TestPlayerBIZ {
	public static void main(String[] args) {
		PlayerDAO playerDAO = new PlayerDAO();
		NationDAO nationDAO = new NationDAO();
		PositionDAO positionDAO = new PositionDAO();
		HaspositionDAO haspositionDAO = new HaspositionDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		
		PlayerBIZ playerBIZ = new PlayerBIZ(playerDAO, nationDAO, positionDAO, haspositionDAO, clubDAO, cityDAO, seasonDAO, enrollmentDAO);
		
		Player player = null;
		
//		try {
//			player = playerBIZ.findPlayerByName("Robin vanasd Persie");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			player = playerBIZ.findPlayerById(6);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		Calendar c= Calendar.getInstance();
//		c.set(1990, 9, 4);
//		Date birthday = c.getTime();
//
//		List<String> positionName = new ArrayList<String>();
//		positionName.add("dmf");
//		
//		try {
//			player = playerBIZ.addPlayer("Argentina", "maschelanuo", birthday, positionName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			player = playerBIZ.updatePlayer(4, "Real Madrid FC", 2014, 17, new Long(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(player.getId());
	}
}
