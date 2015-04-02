package org.cl.crawler;
import java.util.ArrayList;
import java.util.Iterator;
import org.cl.parser.TweetParser;
import org.cl.service.RWUid;
import org.cl.service.SaveInfo;
import org.cl.service.Twitter4JAPI;

import com.google.gson.Gson;
import twitter4j.*;

public final class Crawler_Twitters {
	static Gson gson = new Gson();
	//需补充超过一定数量则不爬的逻辑
	public static void crawler_Twitters(int deepId,RWUid userid_src){
		RWUid userid = userid_src.Copy();
		SaveInfo.initForTwitterCrawler(deepId); 
		Twitter twitter = TwitterFactory.getSingleton();
		while(userid.getNum()>0){
			String username = userid.getUid();//"NatGeo";
			//System.out.println(username);
			int count = 20;
			int page = 1;
			Paging paging = new Paging(page,count);
			ArrayList<String> tweet_list = new ArrayList<String>();
			ResponseList<Status> status_list = null;
			do {
				status_list = Twitter4JAPI.getUserTimeline(twitter,username,paging);
				if(status_list==null){SaveInfo.saveFailure_twitter(username+"\t:status_list==null\r\n");continue;}
				Iterator<Status> it = status_list.iterator();
				while(it.hasNext()){
					Status status = it.next();
					String statuStr = TweetParser.getTweet(status);
					//System.out.println(statuStr);
					tweet_list.add(statuStr);
				}
				SaveInfo.savetwitter(username, tweet_list);
				paging.setPage(page++);
			}while (page<=10&&status_list!=null&&status_list.size()==count);
		}
		SaveInfo.closeForTwitterCrawler();
	}
}