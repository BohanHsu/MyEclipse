package test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.BaseHibernateDAO;
import dao.CityDAO;
import dao.ClubDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Match;
import biz.MatchBIZ;
import biz.PlayingBIZ;

public class TestMatchBIZ {
	public static void main(String[] args) {
		MatchDAO matchDAO = new MatchDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		LeagueDAO leagueDAO = new LeagueDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		PlayingDAO playingdDao = new PlayingDAO();
		PlayerDAO playerDAO = new PlayerDAO();
		RoundDAO roundDAO = new RoundDAO();
		MatchBIZ matchBIZ = new MatchBIZ(matchDAO, clubDAO, cityDAO, leagueDAO, seasonDAO, playingdDao, playerDAO, roundDAO);

		Match result = null;
		List<Match> resultList = null;
		
//		List<Match> resultList = matchBIZ.findMatchByLeagueAndSeason("La Liga",
//				2013);

		
//		Match result = matchBIZ.findMatchByLeagueAndSeasonAndHomeAndVisitTeam("La Liga",
//		 2013, 3, 4);
		 
		
//		try {
//			resultList = matchBIZ.findMatchByHomeTeam(1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		Calendar c= Calendar.getInstance();
//		c.set(2013, 9, 4);
//		Date date = c.getTime();
//		
//		try {
//			matchBIZ.addMatch("Premier League", 2013, 1, 4, 12, date, "Eithad", 4, 1, null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			resultList = matchBIZ.findMatchsByLeagueAndSeasonAndRound("La Liga", 2012, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		for (Match match : resultList) {
			System.out.println(match.getId());
		}
		
	}
}
