package entity.derived;

import java.util.LinkedHashMap;
import java.util.Map;

import entity.Club;

public class StandingRow {
	private Club club = null;
	private Integer gamePlayed = null;
	private Integer wins = null;
	private Integer draws = null;
	private Integer losses = null;
	private Integer goalsFor = null;
	private Integer goalsAgainst = null;
	private Integer goalsDifference = null;
	private Integer points = null;
	public StandingRow(Club club, Integer gamePlayed, Integer wins,
			Integer draws, Integer losses, Integer goalsFor,
			Integer goalsAgainst, Integer goalsDifference, Integer points) {
		super();
		this.club = club;
		this.gamePlayed = gamePlayed;
		this.wins = wins;
		this.draws = draws;
		this.losses = losses;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.goalsDifference = goalsDifference;
		this.points = points;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public Integer getGamePlayed() {
		return gamePlayed;
	}
	public void setGamePlayed(Integer gamePlayed) {
		this.gamePlayed = gamePlayed;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public Integer getDraws() {
		return draws;
	}
	public void setDraws(Integer draws) {
		this.draws = draws;
	}
	public Integer getLosses() {
		return losses;
	}
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	public Integer getGoalsFor() {
		return goalsFor;
	}
	public void setGoalsFor(Integer goalsFor) {
		this.goalsFor = goalsFor;
	}
	public Integer getGoalsAgainst() {
		return goalsAgainst;
	}
	public void setGoalsAgainst(Integer goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	public Integer getGoalsDifference() {
		return goalsDifference;
	}
	public void setGoalsDifference(Integer goalsDifference) {
		this.goalsDifference = goalsDifference;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "StandingRow [club=" + club + ", gamePlayed=" + gamePlayed
				+ ", wins=" + wins + ", draws=" + draws + ", losses=" + losses
				+ ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst
				+ ", goalsDifference=" + goalsDifference + ", points=" + points
				+ "]";
	}
	
	public Map<String, Object> toMap() {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("clubName", this.club.getName());
		map.put("gamePlayed", this.gamePlayed);
		map.put("wins", this.wins);
		map.put("draws", this.draws);
		map.put("losses", this.losses);
		map.put("goalsFor", this.goalsFor);
		map.put("goalsAgainst", this.goalsAgainst);
		map.put("goalsDifference", this.goalsDifference);
		map.put("points", this.points);

		return map;
	}
	
}
