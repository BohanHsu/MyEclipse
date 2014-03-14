package test;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Match;
import entity.Player;
import biz.MatchBIZ;
import biz.PlayingBIZ;

public class TestPlayingBIZ {
	public static void main(String[] args) {
		PlayingDAO playingdDao = new PlayingDAO();
		PlayerDAO playerDAO = new PlayerDAO();
		MatchDAO matchDAO = new MatchDAO();
		ClubDAO clubDAO = new ClubDAO();
		CityDAO cityDAO = new CityDAO();
		LeagueDAO leagueDAO = new LeagueDAO();
		SeasonDAO seasonDAO = new SeasonDAO();
		RoundDAO roundDAO = new RoundDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		
		PlayingBIZ playingBIZ = new PlayingBIZ(playingdDao, playerDAO);
		MatchBIZ matchBIZ = new MatchBIZ(matchDAO, clubDAO, cityDAO, leagueDAO, seasonDAO, playingdDao, playerDAO, roundDAO);
		
		Match match = matchBIZ.findMatchByLeagueAndSeasonAndHomeAndVisitTeam("Premier League", 2013, 1, 2);
		
		try {
			playingBIZ.addPlaying(4, match, 0, 90, 2, 0, 0, new Long(6));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
