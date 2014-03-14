package entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Match entity. @author MyEclipse Persistence Tools
 */

public class Match implements java.io.Serializable {

	// Fields

	private Integer id;
	private League league;
	private Club clubByHomeTeamId;
	private Round round;
	private Club clubByVisitTeamId;
	private Season season;
	private Date date;
	private String stadium;
	private Integer homeScore;
	private Integer visitScore;
	private Set playings = new HashSet(0);

	// Constructors

	/** default constructor */
	public Match() {
	}

	/** full constructor */
	public Match(League league, Club clubByHomeTeamId, Round round,
			Club clubByVisitTeamId, Season season, Date date, String stadium,
			Integer homeScore, Integer visitScore, Set playings) {
		this.league = league;
		this.clubByHomeTeamId = clubByHomeTeamId;
		this.round = round;
		this.clubByVisitTeamId = clubByVisitTeamId;
		this.season = season;
		this.date = date;
		this.stadium = stadium;
		this.homeScore = homeScore;
		this.visitScore = visitScore;
		this.playings = playings;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Club getClubByHomeTeamId() {
		return this.clubByHomeTeamId;
	}

	public void setClubByHomeTeamId(Club clubByHomeTeamId) {
		this.clubByHomeTeamId = clubByHomeTeamId;
	}

	public Round getRound() {
		return this.round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Club getClubByVisitTeamId() {
		return this.clubByVisitTeamId;
	}

	public void setClubByVisitTeamId(Club clubByVisitTeamId) {
		this.clubByVisitTeamId = clubByVisitTeamId;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStadium() {
		return this.stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public Integer getHomeScore() {
		return this.homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getVisitScore() {
		return this.visitScore;
	}

	public void setVisitScore(Integer visitScore) {
		this.visitScore = visitScore;
	}

	public Set getPlayings() {
		return this.playings;
	}

	public void setPlayings(Set playings) {
		this.playings = playings;
	}
	
	public Map<String, Object> toMap() {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", this.id);
		map.put("league", this.league.getName());
		map.put("homeTeam", this.clubByHomeTeamId.getName());
		map.put("visitTeam", this.clubByVisitTeamId.getName());
		map.put("round", this.round.getNumber());
		map.put("season", this.season.getStartyear());
		map.put("date", this.date.toString());
		map.put("stadium", this.stadium);
		map.put("homeScore", this.homeScore);
		map.put("visitScore", this.visitScore);
		map.put("homeLogo", this.clubByHomeTeamId.getImageUrl());
		map.put("visitLogo", this.clubByVisitTeamId.getImageUrl());

		return map;
	}

}