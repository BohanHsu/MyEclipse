package test;

import java.util.List;

import dao.AdminDAO;
import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.HaspositionDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.PositionDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Admin;
import biz.AdminBIZ;

public class TestAdminBIZ {
	AdminDAO adminDAO = new AdminDAO();
	CityDAO cityDAO = new CityDAO();
	ClubDAO clubDAO = new ClubDAO();
	EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
	HaspositionDAO haspositionDAO = new HaspositionDAO();
	LeagueDAO leagueDAO = new LeagueDAO();
	MatchDAO matchDAO = new MatchDAO();
	NationDAO nationDAO = new NationDAO();
	PlayerDAO playerDAO = new PlayerDAO();
	PlayingDAO playingDAO = new PlayingDAO();
	PositionDAO positionDAO = new PositionDAO();
	RoundDAO roundDAO = new RoundDAO();
	SeasonDAO seasonDAO = new SeasonDAO();
	
	private void testAdminLogin(){
		String str = null;

		AdminBIZ adminBIZ = new AdminBIZ(adminDAO);
		Admin admin1 = null;
		try {
			admin1 = adminBIZ.adminLogin("admin", "admin");
			Admin admin2 = adminBIZ.adminLogin("xbh", "xbh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		try {
			Admin admin3 = adminBIZ.adminLogin("admin", "xbh");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(admin1.getEmail());
		 
	}
	
	private void testAdminDAO(){
//		List rs = adminDAO.findByUsername("admin");
		List rs = adminDAO.findAll();
		for (Object object : rs) {
			System.out.println(object);
		}
	}
	
	private void tetAdmin2(){
		adminDAO.findAll();
	}
	
	public static void main(String[] args) {
		TestAdminBIZ t1 = new TestAdminBIZ();
//		System.out.println(t1.testAdminLogin());
		t1.testAdminLogin();
	}
}
