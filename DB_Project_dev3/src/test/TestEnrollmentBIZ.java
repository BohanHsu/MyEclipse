package test;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.HaspositionDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PositionDAO;
import dao.SeasonDAO;
import entity.Club;
import entity.Player;
import biz.EnrollmentBIZ;
import biz.PlayerBIZ;

public class TestEnrollmentBIZ {
	public static void main(String[] args) {
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		EnrollmentBIZ enrollmentBIZ = new EnrollmentBIZ(enrollmentDAO);

		Integer seasonStartYear;
//		 enrollmentBIZ.addEnrollment(season, player, club, number, salary);
		try {
			Player player = null;
			PlayerDAO playerDAO = new PlayerDAO();
			NationDAO nationDAO = new NationDAO();
			PositionDAO positionDAO = new PositionDAO();
			HaspositionDAO haspositionDAO = new HaspositionDAO();
			ClubDAO clubDAO = new ClubDAO();
			CityDAO cityDAO = new CityDAO();
			SeasonDAO seasonDAO = new SeasonDAO();
			PlayerBIZ playerBIZ = new PlayerBIZ(playerDAO, nationDAO, positionDAO, haspositionDAO, clubDAO, cityDAO, seasonDAO, enrollmentDAO);
			
			player = playerBIZ.findPlayerById(4);
			Club c = enrollmentBIZ.findClubByPlayerAndSeason(player, 2013);
			System.out.println(c.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
