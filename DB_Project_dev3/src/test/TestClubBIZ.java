package test;

import java.util.Calendar;
import java.util.Date;

import dao.CityDAO;
import dao.ClubDAO;
import entity.Club;
import biz.ClubBIZ;

public class TestClubBIZ {
	public static void main(String[] args) {
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		ClubBIZ clubBIZ = new ClubBIZ(clubDAO, cityDAO);
		
		Club club = null;
//		try {
//			club = clubBIZ.findClubById(11);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			club = clubBIZ.findClubByName("Manchester United");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Calendar c= Calendar.getInstance();
		c.set(1904, 4, 4);

		Date start = c.getTime();
		try {
			club = clubBIZ.addClub("Manchester", "Manchester_City", "blue moon", start , "Ethade", "Arbic", "Manchinni");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(club.getName());
	}
}
