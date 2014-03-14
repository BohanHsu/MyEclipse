package entity;

/**
 * Enrollment entity. @author MyEclipse Persistence Tools
 */

public class Enrollment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Season season;
	private Player player;
	private Club club;
	private Integer number;
	private Long salary;

	// Constructors

	/** default constructor */
	public Enrollment() {
	}

	/** full constructor */
	public Enrollment(Season season, Player player, Club club, Integer number,
			Long salary) {
		this.season = season;
		this.player = player;
		this.club = club;
		this.number = number;
		this.salary = salary;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getSalary() {
		return this.salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

}