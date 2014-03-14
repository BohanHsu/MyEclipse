package biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.EnrollmentDAO;
import dao.PlayerDAO;
import dao.PlayingDAO;
import entity.Club;
import entity.Enrollment;
import entity.Match;
import entity.Player;
import entity.Playing;
import entity.Season;

public class PlayingBIZ {
	private PlayingDAO playingdDao = null;
	private PlayerDAO playerDAO = null;

	public PlayingBIZ(PlayingDAO playingdDao, PlayerDAO playerDAO) {
		super();
		this.playingdDao = playingdDao;
		this.playerDAO = playerDAO;
	}

	public void addPlaying(Integer playerId, Match match, Integer startTime,
			Integer endTime, Integer score, Integer yellowCard,
			Integer redCard, Long rate) throws Exception {

		Integer matchSeasonStartYear = match.getSeason().getStartyear();

		Player playingPlayer = (Player) playerDAO.findById(playerId);
		Set<Enrollment> enrollments = playingPlayer.getEnrollments();
		Enrollment playingSeasonEnrollment = null;

		for (Enrollment enrollment : enrollments) {
			if (enrollment.getSeason().getStartyear()
					.equals(matchSeasonStartYear)) {
				playingSeasonEnrollment = enrollment;
			}
		}

		if (playingSeasonEnrollment == null) {
			throw new Exception("this season(" + matchSeasonStartYear
					+ ") this player(" + playingPlayer.getName()
					+ ") is not enroll in any club");
		}

		Integer playingSeasonClubId = playingSeasonEnrollment.getClub().getId();

		Integer homeTeamId = match.getClubByHomeTeamId().getId();
		Integer visitTeamId = match.getClubByVisitTeamId().getId();

		if (homeTeamId.equals(playingSeasonClubId)
				|| visitTeamId.equals(playingSeasonClubId)) {

			Session session = playingdDao.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			Playing newPlaying = new Playing(playingPlayer, match, startTime,
					endTime, score, yellowCard, redCard, rate);
			playingdDao.save(newPlaying);
			tx.commit();
			session.flush();
			session.close();
		} else {
			throw new Exception("this season(" + matchSeasonStartYear
					+ ") this player(" + playingPlayer.getName()
					+ ") is not enroll in the home team("
					+ match.getClubByHomeTeamId().getName()
					+ ") or visit team("
					+ match.getClubByVisitTeamId().getName() + ")");
		}

	}

	public List<Playing> findPlayingByMatchId(Integer matchId) {
		List<Playing> playingList = new ArrayList<Playing>();
		String hql = "select p from Playing p where p.match.id = ?";
		Session session = playingdDao.getSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, matchId);
		@SuppressWarnings("unchecked")
		List<Object> resultlist = query.list();

		for (Object object : resultlist) {
			playingList.add((Playing) object);
		}
		return playingList;
	}

	public List<Playing> findPlayerForHome(Match match) {
		Club homeClub = match.getClubByHomeTeamId();
		Season matchSeason = match.getSeason();
		List<Playing> players = new ArrayList<Playing>();
		List<Playing> playings = findPlayingByMatchId(match.getId());

		for (Playing playing : playings) {
			Set<Enrollment> enrollments = playing.getPlayer().getEnrollments();
			for (Enrollment enrollment : enrollments) {
				if (enrollment.getSeason().getId().equals(matchSeason.getId())
						&& enrollment.getClub().getId()
								.equals(homeClub.getId())) {
					players.add(playing);
				}
			}
		}
		return players;
	}

	public List<Playing> findPlayerForVisit(Match match) {
		Club visitClub = match.getClubByHomeTeamId();
		Season matchSeason = match.getSeason();
		List<Playing> players = new ArrayList<Playing>();
		List<Playing> playings = findPlayingByMatchId(match.getId());

		System.out.println(playings);
		
		for (Playing playing : playings) {
			Set<Enrollment> enrollments = playing.getPlayer().getEnrollments();
			for (Enrollment enrollment : enrollments) {

				if (enrollment.getSeason().getId().equals(matchSeason.getId())
						&& enrollment.getClub().getId()
								.equals(visitClub.getId())) {
					players.add(playing);
				}
			}
		}

		return players;
	}
}