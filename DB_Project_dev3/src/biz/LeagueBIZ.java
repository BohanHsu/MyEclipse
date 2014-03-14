package biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;

import utility.StandingRowComparator;
import utility.TopScorePlayerRowComparator;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.PlaysinDAO;
import dao.RoundDAO;
import dao.SeasonDAO;
import entity.Club;
import entity.Enrollment;
import entity.League;
import entity.Match;
import entity.Nation;
import entity.Player;
import entity.derived.StandingRow;
import entity.derived.TopScorePlayerRow;

public class LeagueBIZ {
	private LeagueDAO leagueDAO= null;
	private MatchDAO matchDAO=null;
	private ClubDAO clubDAO=null;
	private CityDAO cityDAO=null;
	private SeasonDAO seasonDAO=null;
	private PlayingDAO playingdDao=null;
	private PlayerDAO playerDAO=null;
	private PlaysinDAO playsinDAO=null;
	private RoundDAO roundDAO=null;
	private NationDAO nationDAO = null;

	LeagueBIZ(LeagueDAO leagueDAO) {
		super();
		this.leagueDAO = leagueDAO;
	}
	
	

	public LeagueBIZ(LeagueDAO leagueDAO, NationDAO nationDAO) {
		super();
		this.leagueDAO = leagueDAO;
		this.nationDAO = nationDAO;
	}



	public LeagueBIZ(LeagueDAO leagueDAO, MatchDAO matchDAO, ClubDAO clubDAO,
			CityDAO cityDAO, SeasonDAO seasonDAO, PlayingDAO playingdDao,
			PlayerDAO playerDAO, PlaysinDAO playsinDAO, RoundDAO roundDAO) {
		super();
		this.leagueDAO = leagueDAO;
		this.matchDAO = matchDAO;
		this.clubDAO = clubDAO;
		this.cityDAO = cityDAO;
		this.seasonDAO = seasonDAO;
		this.playingdDao = playingdDao;
		this.playerDAO = playerDAO;
		this.playsinDAO = playsinDAO;
		this.roundDAO = roundDAO;
	}

	public LeagueBIZ(LeagueDAO leagueDAO, MatchDAO matchDAO, ClubDAO clubDAO,
			CityDAO cityDAO, SeasonDAO seasonDAO, PlayingDAO playingdDao,
			PlayerDAO playerDAO, PlaysinDAO playsinDAO, RoundDAO roundDAO,
			NationDAO nationDAO) {
		super();
		this.leagueDAO = leagueDAO;
		this.matchDAO = matchDAO;
		this.clubDAO = clubDAO;
		this.cityDAO = cityDAO;
		this.seasonDAO = seasonDAO;
		this.playingdDao = playingdDao;
		this.playerDAO = playerDAO;
		this.playsinDAO = playsinDAO;
		this.roundDAO = roundDAO;
		this.nationDAO = nationDAO;
	}

	public League findLeagueByName(String leagueName) throws Exception{

		Object resultObj = null;
		List<Object> resultList = leagueDAO.findByName(leagueName);
		if(resultList.size() == 0){
			throw new Exception("league not found: (name="+leagueName+")");
		}else{
			resultObj = resultList.get(0);
			return (League) resultObj;
		}
	}
	
	public List<StandingRow> queryStatisicByLeagueNameAndSeasonStartYear(String leagueName, Integer seasonStartYear){
		List<StandingRow> resultList = new ArrayList<StandingRow>();
		List<Match> matchList = null;
		MatchBIZ matchBIZ = new MatchBIZ(matchDAO, clubDAO, cityDAO, leagueDAO, seasonDAO, playingdDao, playerDAO, roundDAO );
		matchList = matchBIZ.findMatchByLeagueAndSeason(leagueName, seasonStartYear);
		
		PlaysinBIZ playsinBIZ = new PlaysinBIZ(playsinDAO);
		
		List<Club> clubList = playsinBIZ.findClubsByLeagueAndSeason(leagueName, seasonStartYear);
		
		for (Club club : clubList) {
			StandingRow sr = new StandingRow(club, 0, 0, 0, 0, 0, 0, 0, 0);
			for (Match match : matchList) {
				if(match.getClubByHomeTeamId().getId().equals(club.getId())){
					if (match.getHomeScore() > match.getVisitScore()){
						sr.setGamePlayed(sr.getGamePlayed() + 1);
						sr.setWins(sr.getWins() + 1);
						sr.setGoalsFor(sr.getGoalsFor() + match.getHomeScore());
						sr.setGoalsAgainst(sr.getGoalsAgainst() + match.getVisitScore());
					}
					if (match.getHomeScore() == match.getVisitScore()){
						sr.setGamePlayed(sr.getGamePlayed() + 1);
						sr.setDraws(sr.getDraws() + 1);
						sr.setGoalsFor(sr.getGoalsFor() + match.getHomeScore());
						sr.setGoalsAgainst(sr.getGoalsAgainst() + match.getVisitScore());
					}
					if (match.getHomeScore() < match.getVisitScore()){
						sr.setGamePlayed(sr.getGamePlayed() + 1);
						sr.setLosses(sr.getLosses() + 1);
						sr.setGoalsFor(sr.getGoalsFor() + match.getHomeScore());
						sr.setGoalsAgainst(sr.getGoalsAgainst() + match.getVisitScore());
					}
				}
				if(match.getClubByVisitTeamId().getId().equals(club.getId())){
					if (match.getHomeScore() < match.getVisitScore()){
						sr.setGamePlayed(sr.getGamePlayed() + 1);
						sr.setWins(sr.getWins() + 1);
						sr.setGoalsFor(sr.getGoalsFor() + match.getHomeScore());
						sr.setGoalsAgainst(sr.getGoalsAgainst() + match.getVisitScore());
					}
					if (match.getHomeScore() == match.getVisitScore()){
						sr.setGamePlayed(sr.getGamePlayed() + 1);
						sr.setDraws(sr.getDraws() + 1);
						sr.setGoalsFor(sr.getGoalsFor() + match.getHomeScore());
						sr.setGoalsAgainst(sr.getGoalsAgainst() + match.getVisitScore());
					}
					if (match.getHomeScore() > match.getVisitScore()){
						sr.setGamePlayed(sr.getGamePlayed() + 1);
						sr.setLosses(sr.getLosses() + 1);
						sr.setGoalsFor(sr.getGoalsFor() + match.getHomeScore());
						sr.setGoalsAgainst(sr.getGoalsAgainst() + match.getVisitScore());
					}
				}
			}
			
			sr.setGamePlayed(sr.getWins()+sr.getDraws()+sr.getLosses());
			sr.setPoints(sr.getWins()* 3 +sr.getDraws());
			sr.setGoalsDifference(sr.getGoalsFor() - sr.getGoalsAgainst());
			resultList.add(sr);
		}
		
		//sort the arrayList
		Collections.sort(resultList, new StandingRowComparator());
		return resultList;
	}
	
	public List<TopScorePlayerRow> findTopScorePlayerStatisticByLeagueAndSeasonStartYear(String leagueName, Integer seasonStartYear) throws Exception{
		
		List<TopScorePlayerRow> resultList = new ArrayList<TopScorePlayerRow>();
		
		
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		EnrollmentBIZ enrollmentBIZ = new EnrollmentBIZ(enrollmentDAO );
		
		Session session = this.leagueDAO.getSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "select p.player,sum(p.score) from Match m join m.playings p where m.season.startyear=? and m.league.name=? group by p.player.id having sum(p.score) > 0";
		
		Query query = session.createQuery(hql);
		query.setInteger(0, seasonStartYear);
		query.setString(1, leagueName);
		
		
		List<Object> playerList = query.list();
		
		
		for (Object object : playerList) {
			Player p = (Player) (((Object[]) object)[0]);
			Integer i = Integer.parseInt((((Object[]) object)[1]).toString());
			
			///
			System.out.println(p.getId());
			System.out.println(p.getName());
			System.out.println(i);
			
			
			Club c = enrollmentBIZ.findClubByPlayerAndSeason(p, seasonStartYear);
			
			resultList.add(new TopScorePlayerRow(p,i, c));
		}
		//sort the list
		
		Collections.sort(resultList, new TopScorePlayerRowComparator());
		
		return resultList;
	}
	
	public League addLeague(String leagueName, String nationName) throws Exception{
		Nation nation = new NationBIZ(nationDAO).findNationByName(nationName);
		League newLeague = new League(nation, leagueName, null, null, null);
		
		Session session = this.leagueDAO.getSession();
		
		Transaction tx = session.beginTransaction();
		
		leagueDAO.save(newLeague);
		
		tx.commit();
		session.close();
		return (League) findLeagueByName(leagueName);
	}
	
	public List<League> findAllLeagues(){
		List<League> resultList = this.leagueDAO.findAll();
		return resultList;
	}
}
