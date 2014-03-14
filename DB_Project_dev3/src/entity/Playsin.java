package entity;

/**
 * Playsin entity. @author MyEclipse Persistence Tools
 */

public class Playsin implements java.io.Serializable {

	// Fields

	private Integer id;
	private League league;
	private Season season;
	private Club club;

	// Constructors

	/** default constructor */
	public Playsin() {
	}

	/** full constructor */
	public Playsin(League league, Season season, Club club) {
		this.league = league;
		this.season = season;
		this.club = club;
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

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

}