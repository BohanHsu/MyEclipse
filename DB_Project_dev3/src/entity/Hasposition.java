package entity;

/**
 * Hasposition entity. @author MyEclipse Persistence Tools
 */

public class Hasposition implements java.io.Serializable {

	// Fields

	private Integer id;
	private Position position;
	private Player player;

	// Constructors

	/** default constructor */
	public Hasposition() {
	}

	/** full constructor */
	public Hasposition(Position position, Player player) {
		this.position = position;
		this.player = player;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}