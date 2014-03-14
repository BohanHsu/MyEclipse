package test;

import java.util.List;

import dao.CityDAO;
import dao.ClubDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.PlaysinDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.League;
import entity.derived.StandingRow;
import biz.LeagueBIZ;

public class TestLeagueBIZ {
	public static void main(String[] args) {
		LeagueDAO leagueDAO = new LeagueDAO();
		MatchDAO matchDAO = new MatchDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		PlayingDAO playingdDao = new PlayingDAO();
		PlayerDAO playerDAO = new PlayerDAO();
		PlaysinDAO playsinDAO = new PlaysinDAO();
		RoundDAO roundDAO = new RoundDAO();
		LeagueBIZ leagueBIZ = new LeagueBIZ(leagueDAO, matchDAO, clubDAO, cityDAO, seasonDAO, playingdDao, playerDAO, playsinDAO, roundDAO);
		League league = null;
//		try {
//			league = leagueBIZ.findLeagueByName("La Liga");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(league.getId());
//		
//		List<StandingRow> resultList = leagueBIZ.queryStatisicByLeagueNameAndSeasonStartYear("Premier League", 2013);
//		
//		for (StandingRow standingRow : resultList) {
//			System.out.println(standingRow);
//		}
		
		try {
			leagueBIZ.findTopScorePlayerStatisticByLeagueAndSeasonStartYear("Premier League", 2012);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
