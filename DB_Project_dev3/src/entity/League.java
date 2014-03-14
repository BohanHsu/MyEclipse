package entity;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * League entity. @author MyEclipse Persistence Tools
 */

public class League implements java.io.Serializable {

	// Fields

	private Integer id;
	private Nation nation;
	private String name;
	private String imageUrl;
	private Set matchs = new HashSet(0);
	private Set playsins = new HashSet(0);

	// Constructors

	/** default constructor */
	public League() {
	}

	/** full constructor */
	public League(Nation nation, String name, String imageUrl, Set matchs,
			Set playsins) {
		this.nation = nation;
		this.name = name;
		this.imageUrl = imageUrl;
		this.matchs = matchs;
		this.playsins = playsins;
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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set getMatchs() {
		return this.matchs;
	}

	public void setMatchs(Set matchs) {
		this.matchs = matchs;
	}

	public Set getPlaysins() {
		return this.playsins;
	}

	public void setPlaysins(Set playsins) {
		this.playsins = playsins;
	}

	public Map<String, Object> toMap(){
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", this.id);
		map.put("name", this.name);
		map.put("nation", this.nation.getName());
		map.put("image_url", this.imageUrl);
		
		return map;
	}
}