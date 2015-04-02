package com.org.cl.twitter;
import com.google.gson.Gson;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class test {

	public static void main(String args[]) throws TwitterException{
		Gson gson = new Gson();
		String username = "hannahChen_";
		Twitter twitter = TwitterFactory.getSingleton();
		//userInfo
		try{
			User userInfo = twitter.showUser(username);
			String jsonStr = gson.toJson(userInfo);
			System.out.println("Showing user infomation.");
			System.out.println(jsonStr);
		}catch (TwitterException e){
			e.printStackTrace();
		}
		//friends
		try{
			IDs ids = twitter.getFriendsIDs(username, -1,100);
			String expandId = "";
			for (long id : ids.getIDs()) {
				expandId += id+"\r\n";
			}
			System.out.println("Showing user friends.");
			System.out.println(expandId);
		}catch (TwitterException e){
			e.printStackTrace();
		}
		//follwers
		try{
			IDs ids = twitter.getFollowersIDs(username, -1,100);
			String expandId = "";
			for (long id : ids.getIDs()) {
				expandId += id+"\r\n";
			}
			System.out.println("Showing user follwers.");
			System.out.println(expandId);
		}catch (TwitterException e){
			e.printStackTrace();
		}
		//status
		try{
			ResponseList<Status> status_list = twitter.getUserTimeline(username);
			System.out.println("Showing user Status.");
			for(Status status :status_list){
				String jsonStr = gson.toJson(status);
				System.out.println(status.getCreatedAt()+":"+status.getText()+":"+status.getInReplyToScreenName());
				System.out.println(jsonStr);
			}
		}catch (TwitterException e){
			e.printStackTrace();
		}
	}
}
