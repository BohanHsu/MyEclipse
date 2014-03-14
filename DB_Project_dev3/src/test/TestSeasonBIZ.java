package test;

import dao.SeasonDAO;
import entity.Season;
import biz.SeasonBIZ;

public class TestSeasonBIZ {
	public static void main(String[] args) {
		SeasonDAO seasonDAO = new SeasonDAO();
		SeasonBIZ seasonBIZ = new SeasonBIZ(seasonDAO );
		Integer s = seasonBIZ.findCurrentSeason();
		System.out.println(s);
	}
}
