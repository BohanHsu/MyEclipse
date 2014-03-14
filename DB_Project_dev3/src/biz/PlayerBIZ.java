package biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CityDAO;
import dao.ClubDAO;
import dao.EnrollmentDAO;
import dao.HaspositionDAO;
import dao.NationDAO;
import dao.PlayerDAO;
import dao.PositionDAO;
import dao.SeasonDAO;
import entity.Club;
import entity.Nation;
import entity.Player;
import entity.Position;
import entity.Season;

public class PlayerBIZ {
	private PlayerDAO playerDAO = null;
	private NationDAO nationDAO = null;
	private PositionDAO positionDAO = null;
	private HaspositionDAO haspositionDAO = null;
	private ClubDAO clubDAO = null;
	private CityDAO cityDAO = null;
	private SeasonDAO seasonDAO = null;
	private EnrollmentDAO enrollmentDAO = null;

	public PlayerBIZ(PlayerDAO playerDAO, NationDAO nationDAO,
			PositionDAO positionDAO, HaspositionDAO haspositionDAO,
			ClubDAO clubDAO, CityDAO cityDAO, SeasonDAO seasonDAO,
			EnrollmentDAO enrollmentDAO) {
		super();
		this.playerDAO = playerDAO;
		this.nationDAO = nationDAO;
		this.positionDAO = positionDAO;
		this.haspositionDAO = haspositionDAO;
		this.clubDAO = clubDAO;
		this.cityDAO = cityDAO;
		this.seasonDAO = seasonDAO;
		this.enrollmentDAO = enrollmentDAO;
	}

	public Player findPlayerByName(String name) throws Exception {
		// Object resultObj = this.playerDAO.findByName(name).get(0);
		// if (resultObj == null) {
		// throw new Exception("player not found: " + name);
		// } else {
		// return (Player) resultObj;
		// }
		List<Object> resultList = this.playerDAO.findByName(name);
		Object resultObj = null;
		if (resultList.size() == 0) {
			throw new Exception("player not found: " + name);
		} else {
			resultObj = resultList.get(0);
			return (Player) resultObj;
		}

	}

	public Player findPlayerById(Integer id) throws Exception {
		Player resultPly = playerDAO.findById(id);
		if (resultPly == null) {
			throw new Exception("player not found: (id=" + id + ")");
		} else {
			return resultPly;
		}
	}

	@SuppressWarnings("finally")
	public Player addPlayer(String nationName, String name, Date birthday,
			List<String> positionName) throws Exception {

		Nation nation = new NationBIZ(nationDAO).findNationByName(nationName);
		PositionBIZ positionBIZ = new PositionBIZ(this.positionDAO);
		HaspositionBIZ haspositionBIZ = new HaspositionBIZ(haspositionDAO);
		List<Position> listOfPosition = new ArrayList<Position>();

		Session session = playerDAO.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		for (String pn : positionName) {
			Position p = positionBIZ.findPositionByName(pn);
			listOfPosition.add(p);
		}

		if (nation == null) {
			return null;
		}

//		try {
//			findPlayerByName(name);
//			throw new Exception("player with same name already exist: " + name);
//		} catch (Exception e) {
//			Player newPlayer = new Player(nation, name, birthday, null, null,
//					null, null);
//			playerDAO.save(newPlayer);
//			Player resultPlayer = findPlayerByName(name);
//			for (Position position : listOfPosition) {
//				haspositionBIZ.addHasposition(resultPlayer, position);
//			}
//		} finally {
//			return findPlayerByName(name);
//		}
		Player oldPlayer = null;
		try {
			oldPlayer = findPlayerByName(name);
		} catch (Exception e) {

		}
		
		if(oldPlayer != null){
			throw new Exception("player with same name already exist: " + name);
		}
		
		Player newPlayer = new Player(nation, name, birthday, null, null,
				null, null);
		playerDAO.save(newPlayer);
		Player resultPlayer = findPlayerByName(name);
		for (Position position : listOfPosition) {
			haspositionBIZ.addHasposition(resultPlayer, position);
		}
		tx.commit();
		session.flush();
		session.close();
		return resultPlayer;
	}

	public Player updatePlayer(Integer id, String clubName,
			Integer seasonStartYear, Integer number, Long salary)
			throws Exception {
		Club club = new ClubBIZ(this.clubDAO, this.cityDAO)
				.findClubByName(clubName);
		Player updatePlayer = findPlayerById(id);
		Season newSeason = new SeasonBIZ(seasonDAO)
				.findSeasonByStartYear(seasonStartYear);
		new EnrollmentBIZ(this.enrollmentDAO).addEnrollment(newSeason,
				updatePlayer, club, number, salary);
		return findPlayerById(id);
	}
	
	public List<Player> getAllPlayers(){
		return this.playerDAO.findAll();
	}

}
