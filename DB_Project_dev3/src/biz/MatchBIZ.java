package biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.BaseHibernateDAO;
import dao.CityDAO;
import dao.ClubDAO;
import dao.LeagueDAO;
import dao.MatchDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import dao.RoundDAO;
import dao.SeasonDAO;

import entity.Club;
import entity.League;
import entity.Match;
import entity.Round;
import entity.Season;

public class MatchBIZ {
	private MatchDAO matchDAO = null;
	private ClubDAO clubDAO = null;
	private CityDAO cityDAO = null;
	private LeagueDAO leagueDAO = null;
	private SeasonDAO seasonDAO = null;
	private PlayingDAO playingdDao = null;
	private PlayerDAO playerDAO = null;
	private RoundDAO roundDAO = null;


	public MatchBIZ(MatchDAO matchDAO, ClubDAO clubDAO, CityDAO cityDAO,
			LeagueDAO leagueDAO, SeasonDAO seasonDAO, PlayingDAO playingdDao,
			PlayerDAO playerDAO, RoundDAO roundDAO) {
		super();
		this.matchDAO = matchDAO;
		this.clubDAO = clubDAO;
		this.cityDAO = cityDAO;
		this.leagueDAO = leagueDAO;
		this.seasonDAO = seasonDAO;
		this.playingdDao = playingdDao;
		this.playerDAO = playerDAO;
		this.roundDAO = roundDAO;
	}

	public Match findMatchById(Integer id) throws Exception{
		Match match = matchDAO.findById(id);
		if(match == null){
			throw new Exception("match not found : (id="+id+")");
		}
		return match;
	}
	
	public List<Match> findMatchByLeagueAndSeason(String leagueName,
			Integer seasonStartYear) {

		List<Match> resultList = new ArrayList<Match>();
		String hql = "from entity.Match as m where m.league.name =? and m.season.startyear =?";
		Session session = matchDAO.getSession();
		Query query = session.createQuery(hql);
		query.setString(0, leagueName);
		query.setInteger(1, seasonStartYear);
		List<Object> resultObjs = query.list();

		for (Object object : resultObjs) {
			resultList.add((Match) object);
		}
		return resultList;
	}

	public Match findMatchByLeagueAndSeasonAndHomeAndVisitTeam(
			String leagueName, Integer seasonStartYear, Integer homeId,
			Integer visitId) {
		List<Match> resultList = findMatchByLeagueAndSeason(leagueName,
				seasonStartYear);
		for (Match match : resultList) {
			if (match.getClubByHomeTeamId().getId().equals(homeId)) {
				if (match.getClubByVisitTeamId().getId().equals(visitId)) {
					return match;
				}
			}
		}
		return null;
	}

	public List<Match> findMatchByHomeTeam(Integer team_Id) throws Exception {
		List<Match> resultList = new ArrayList<Match>();
		ClubBIZ clubBIZ = new ClubBIZ(this.clubDAO, this.cityDAO);
		Club club = clubBIZ.findClubById(team_Id);
		List resultObjs = matchDAO.findByProperty("clubByHomeTeamId", club);
		for (Object object : resultObjs) {
			resultList.add((Match) object);
		}
		return resultList;
	}

	public List<Match> findMatchByVisitTeam(Integer team_Id) throws Exception {
		List<Match> resultList = new ArrayList<Match>();
		ClubBIZ clubBIZ = new ClubBIZ(this.clubDAO, this.cityDAO);
		Club club = clubBIZ.findClubById(team_Id);
		List resultObjs = matchDAO.findByProperty("clubByVisitTeamId", club);
		for (Object object : resultObjs) {
			resultList.add((Match) object);
		}
		return resultList;
	}

	public void addMatch(String leagueName, Integer seasonStartYear,
			Integer homeTeamId, Integer visitTeamId, Integer roundNumber, Date date, String stadium,
			Integer homeScore, Integer visitScore, List<Integer> playerIds)
			throws Exception {
		Session session = this.matchDAO.getSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		League league = new LeagueBIZ(this.leagueDAO)
				.findLeagueByName(leagueName);
		Season season = new SeasonBIZ(this.seasonDAO)
				.findSeasonByStartYear(seasonStartYear);
		Club homeTeam = new ClubBIZ(clubDAO, cityDAO).findClubById(homeTeamId);
		Club visitTeam = new ClubBIZ(clubDAO, cityDAO)
				.findClubById(visitTeamId);
		
		Round round = new RoundBIZ(roundDAO ).findRoundByRound(roundNumber);
		Match newMatch = new Match(league, homeTeam, round, visitTeam, season, date, stadium, homeScore, visitScore, null);
		
		
		this.matchDAO.save(newMatch);
		tx.commit();
		session.flush();
		session.close();
	}
	
	public List<Match> findMatchsByLeagueAndSeasonAndRound(String leagueName,
			Integer seasonStartYear, Integer roundNumber) throws Exception{
		
		Round round = new RoundBIZ(roundDAO).findRoundByRound(roundNumber);
		
		Integer requestRoundId = round.getId();
		
		ArrayList<Match> resultList = new ArrayList<Match>();
		
		List<Match> list = findMatchByLeagueAndSeason(leagueName, seasonStartYear);

		for (Match match : list) {
			if (requestRoundId.equals(match.getRound().getId())){
				resultList.add(match);
			}
		}
		return resultList;
	}
	
	public List<Match> getAllMatches(){
		return this.matchDAO.findAll();
	}

}
