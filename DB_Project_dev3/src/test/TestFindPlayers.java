package test;

import java.util.List;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import entity.Club;
import entity.Player;
import biz.ClubBIZ;
import biz.EnrollmentBIZ;

public class TestFindPlayers {
	public static void main(String[] args) throws Exception {
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		ClubBIZ clubBIZ = new ClubBIZ(clubDAO, cityDAO);
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		EnrollmentBIZ enrollmentBIZ = new EnrollmentBIZ(enrollmentDAO);
		
		Club club = clubBIZ.findClubById(4);
		
		List<Player> pl = enrollmentBIZ.findPlayersByClub(club, 2011);
		
		for (Player player : pl) {
			System.out.println(player.getId());
		}
	}
}
