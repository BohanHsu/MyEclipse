package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Player entity. @author MyEclipse Persistence Tools
 */

public class Player implements java.io.Serializable {

	// Fields

	private Integer id;
	private Nation nation;
	private String name;
	private Date birthday;
	private String imageUrl;
	private Set enrollments = new HashSet(0);
	private Set playings = new HashSet(0);
	private Set haspositions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Player() {
	}

	/** full constructor */
	public Player(Nation nation, String name, Date birthday, String imageUrl,
			Set enrollments, Set playings, Set haspositions) {
		this.nation = nation;
		this.name = name;
		this.birthday = birthday;
		this.imageUrl = imageUrl;
		this.enrollments = enrollments;
		this.playings = playings;
		this.haspositions = haspositions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Nation getNation() {
		return this.nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(Set enrollments) {
		this.enrollments = enrollments;
	}

	public Set getPlayings() {
		return this.playings;
	}

	public void setPlayings(Set playings) {
		this.playings = playings;
	}

	public Set getHaspositions() {
		return this.haspositions;
	}

	public void setHaspositions(Set haspositions) {
		this.haspositions = haspositions;
	}

	public Map<String, Object> toMap() {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", this.id);
		map.put("name", this.name);
		map.put("nation", this.nation.getName());
		map.put("birthday", this.birthday.toString());
		map.put("imageUrl", this.imageUrl);


		List<String> positionArray = new ArrayList<String>();

		Set<Hasposition> positionSet = this.haspositions;
		
		
		
		for (Hasposition hasposition : positionSet) {
			String positionName = hasposition.getPosition().getName();
			positionArray.add(positionName);
		}

		map.put("positions", positionArray);

		return map;
	}

}