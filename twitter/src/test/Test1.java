package test;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Test1 {
	public static void main(String[] args) throws TwitterException {
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus("123");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
}