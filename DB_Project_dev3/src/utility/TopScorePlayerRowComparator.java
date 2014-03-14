package utility;

import java.util.Comparator;

import entity.derived.TopScorePlayerRow;

public class TopScorePlayerRowComparator implements
		Comparator<TopScorePlayerRow> {

	public int compare(TopScorePlayerRow o1, TopScorePlayerRow o2) {
		return o1.getScores() - o2.getScores();
	}

}
