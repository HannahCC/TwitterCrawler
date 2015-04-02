package org.cl.service;

import org.cl.conf.Config;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class Twitter4JAPI {
	private static int request_num = 0;
	private static String errorInfo = "";
	public static User getUserInfo(Twitter twitter,String username) {
		countRequest_num();
		User user = null;
		try {
			Thread.sleep(5000);
			user = twitter.showUser(username);
		} catch (TwitterException e) {
			e.printStackTrace();
			errorInfo = e.getMessage();
			if(errorInfo.contains("Sorry, that page does not exist")){
				SaveInfo.saveUserNotExist(username+"\r\n");
			}else if(errorInfo.contains("rate limit")){
				SaveInfo.saveFailure_userinfo(username+"\t:"+errorInfo+"\r\n");
				sleep();
			}else{
				SaveInfo.saveFailure_userinfo(username+"\t:"+errorInfo+"\r\n");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static ResponseList<Status> getUserTimeline(Twitter twitter, String username, Paging paging) {
		// TODO Auto-generated method stub
		countRequest_num();
		ResponseList<Status> status_list = null;
		try {
			Thread.sleep(5000);
			status_list = twitter.getUserTimeline(username,paging);
		} catch (TwitterException e){
			e.printStackTrace();
			errorInfo = e.getMessage();
			if(errorInfo.contains("Sorry, that page does not exist")){
			}else if(errorInfo.contains("rate limit")){
				SaveInfo.saveFailure_twitter(username+"\t:"+errorInfo+"\r\n");
				sleep();
			}else{
				SaveInfo.saveFailure_twitter(username+"\t:"+errorInfo+"\r\n");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status_list;
	}

	public static IDs getUserFriens(Twitter twitter, String username,long cursor) {
		countRequest_num();
		IDs ids = null;
		try{
			Thread.sleep(5000);
			ids = twitter.getFriendsIDs(username, cursor);
		} catch (TwitterException e){
			e.printStackTrace();
			errorInfo = e.getMessage();
			if(errorInfo.contains("Sorry, that page does not exist")){
			}else if(errorInfo.contains("rate limit")){
				SaveInfo.saveFailure_Friends(username+"\t:"+errorInfo+"\r\n");
				sleep();
			}else{
				SaveInfo.saveFailure_Friends(username+"\t:"+errorInfo+"\r\n");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ids;
	}

	public static IDs getUserFollowers(Twitter twitter, String username,
			long cursor) {
		countRequest_num();
		IDs ids = null;
		try {
			Thread.sleep(1000);
			ids = twitter.getFollowersIDs(username, cursor);
		} catch (TwitterException e) {
			e.printStackTrace();
			errorInfo = e.getMessage();
			if(errorInfo.contains("Sorry, that page does not exist")){
			}else if(errorInfo.contains("rate limit")){
				SaveInfo.saveFailure_Followers(username+"\t:"+errorInfo+"\r\n");
				sleep();
			}else{
				SaveInfo.saveFailure_Followers(username+"\t:"+errorInfo+"\r\n");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ids;
	}
	//请求达到一定次数后休眠
	private static void countRequest_num(){
		if(request_num>Config.REQUEST_MAX){
			sleep();
		}else{
			request_num++;
		}
	}
	private static void sleep(){
		try {
			System.out.println("Sleeping……");
			Thread.sleep(Config.SLEEP_TIME);
			request_num = 0;
			System.out.println("Waking……");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
