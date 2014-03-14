package entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Playing entity. @author MyEclipse Persistence Tools
 */

public class Playing implements java.io.Serializable {

	// Fields

	private Integer id;
	private Player player;
	private Match match;
	private Integer startTime;
	private Integer endTime;
	private Integer score;
	private Integer yellowCard;
	private Integer redCard;
	private Long rate;

	// Constructors

	/** default constructor */
	public Playing() {
	}

	/** full constructor */
	public Playing(Player player, Match match, Integer startTime,
			Integer endTime, Integer score, Integer yellowCard,
			Integer redCard, Long rate) {
		this.player = player;
		this.match = match;
		this.startTime = startTime;
		this.endTime = endTime;
		this.score = score;
		this.yellowCard = yellowCard;
		this.redCard = redCard;
		this.rate = rate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Match getMatch() {
		return this.match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Integer getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getYellowCard() {
		return this.yellowCard;
	}

	public void setYellowCard(Integer yellowCard) {
		this.yellowCard = yellowCard;
	}

	public Integer getRedCard() {
		return this.redCard;
	}

	public void setRedCard(Integer redCard) {
		this.redCard = redCard;
	}

	public Long getRate() {
		return this.rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

	
public Map<String, Object> toMap(){
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", this.id);
		map.put("playerId", this.player.getId());
		map.put("playerName", this.player.getName());
		map.put("playerImageUrl", this.player.getImageUrl());
		map.put("startTime", this.startTime);
		map.put("endTime", this.endTime);
		map.put("score", this.score);
		map.put("yellowCard", this.yellowCard);
		map.put("redCard", this.redCard);
		map.put("rate", this.rate);
		map.put("matchID",this.match.getId());
		
		return map;
	}
}