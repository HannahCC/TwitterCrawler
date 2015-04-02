package com.org.cl.twitter;

import org.cl.crawler.Crawler_Followers;
import org.cl.crawler.Crawler_Friends;
import org.cl.crawler.Crawler_Twitters;
import org.cl.crawler.Crawler_userInfo;
import org.cl.service.RWUid;
import org.cl.service.SaveInfo;

public class Main {
	public static void main(String args[]){
		//爬两层
		int deepId = Integer.parseInt(args[0]);
		int funcitonId = Integer.parseInt(args[1]);
		for(int i=1;i<=deepId;i++){
			RWUid userid = SaveInfo.getUserId("/UserId"+i+".txt");
			switch (funcitonId){
			case 1:Crawler_userInfo.crawler_userInfo(i,userid);break;
			case 2:Crawler_Friends.crawler_Friends(i,userid);break;
			case 3:Crawler_Followers.crawler_Followers(i,userid);break;
			case 4:Crawler_Twitters.crawler_Twitters(i,userid);break;
			default:System.out.println("argsuments error!");
			}
		}
	}
}
