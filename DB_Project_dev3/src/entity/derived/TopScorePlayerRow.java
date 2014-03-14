package entity.derived;

import java.util.LinkedHashMap;
import java.util.Map;

import entity.Club;
import entity.Player;

public class TopScorePlayerRow {
	private Player player = null;
	private Integer scores = null;
	private Club club = null;


	
	
	public TopScorePlayerRow(Player player, Integer scores, Club club) {
		super();
		this.player = player;
		this.scores = scores;
		this.club = club;
	}

	public TopScorePlayerRow() {
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getScores() {
		return scores;
	}

	public void setScores(Integer scores) {
		this.scores = scores;
	}
	
	

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Map<String, Object> toMap() {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", this.player.getId());
		map.put("name", this.player.getName());
		map.put("club", this.club.getName());
		map.put("scores", this.scores);

		return map;
	}
}
