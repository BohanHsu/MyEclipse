package entity;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set haspositions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** full constructor */
	public Position(String name, Set haspositions) {
		this.name = name;
		this.haspositions = haspositions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

		return map;
	}
}