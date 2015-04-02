package org.cl.crawler;

import org.cl.conf.Config;
import org.cl.parser.UserParser;
import org.cl.service.RWUid;
import org.cl.service.SaveInfo;
import org.cl.service.Twitter4JAPI;

import com.google.gson.Gson;

import twitter4j.*;

public final class Crawler_userInfo {
	static Gson gson = new Gson();
	public static void crawler_userInfo(int deepId,RWUid userid_src){
		RWUid userid = userid_src.Copy();
		SaveInfo.initForUserInfoCrawler(deepId); 
		Twitter twitter = TwitterFactory.getSingleton();
		while(userid.getNum()>0){
			String username = userid.getUid();//"NatGeo";
			//System.out.println("start to get userInfo of "+username);
			User userInfo = Twitter4JAPI.getUserInfo(twitter,username);
			if(userInfo==null){SaveInfo.saveFailure_userinfo(username+"\t:userInfo==null\r\n");continue;}
			String userStr = UserParser.getUser(userInfo);
			if(userInfo.getFollowersCount()>Config.FOLLOW_MAX||userInfo.getFriendsCount()>Config.FRIENDS_MAX){
				SaveInfo.saveUsertooMany(userStr);
			}else{
				SaveInfo.saveUser(userStr);
			}
		}
		SaveInfo.closeForUserInfoCrawler();
	}
}

