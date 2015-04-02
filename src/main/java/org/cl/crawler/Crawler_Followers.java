package org.cl.crawler;
import org.cl.service.RWUid;
import org.cl.service.SaveInfo;
import org.cl.service.Twitter4JAPI;

import com.google.gson.Gson;
import twitter4j.*;

public final class Crawler_Followers {
	static Gson gson = new Gson();
	//需补充超过一定数量则不爬的逻辑
	public static void crawler_Followers(int deepId,RWUid userid_src){
		RWUid userid = userid_src.Copy();
		SaveInfo.initForFollowersCrawler(deepId); 
		Twitter twitter = TwitterFactory.getSingleton();
		RWUid userid_tooMany = SaveInfo.getUserId("/"+deepId+"/UserInfo_tooManyShip.txt");
		while(userid.getNum()>0){
			String username = userid.getUid();//"NatGeo";
			if(userid_tooMany.isExist(username))continue;//粉丝数或好友数太多不爬
			//System.out.println(username);
			long cursor = -1;
			IDs ids = null;
			do{
				ids = Twitter4JAPI.getUserFollowers(twitter,username,cursor);
				String relationship = "";
				String expandId = "";
				if(ids==null){SaveInfo.saveFailure_Followers(username+"\t:ids==null\r\n");continue;}
				for (long id : ids.getIDs()) {
					//System.out.println(id);
					relationship += username+"\t"+id+"\r\n";
					expandId += id+"\r\n";
				}
				SaveInfo.saveFollowers(relationship);
				SaveInfo.saveExpandID(expandId);
			}while (ids!=null&&(cursor = ids.getNextCursor()) != 0);
		}
		SaveInfo.closeForFollowersCrawler();
	}
}