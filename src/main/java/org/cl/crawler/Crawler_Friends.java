package org.cl.crawler;
import org.cl.service.RWUid;
import org.cl.service.SaveInfo;
import org.cl.service.Twitter4JAPI;

import com.google.gson.Gson;
import twitter4j.*;

public final class Crawler_Friends {
	static Gson gson = new Gson();
	public static void crawler_Friends(int deepId,RWUid userid_src){
		RWUid userid = userid_src.Copy();
		SaveInfo.initForFriendsCrawler(deepId); 
		Twitter twitter = TwitterFactory.getSingleton();
		RWUid userid_tooMany = SaveInfo.getUserId("/"+deepId+"/UserInfo_tooManyShip.txt");
		while(userid.getNum()>0){
			String username = userid.getUid();//"NatGeo";
			if(userid_tooMany.isExist(username))continue;//粉丝数或好友数太多不爬
			//System.out.println(username);
			long cursor = -1;
			IDs ids = null;
			String relationship = "";
			String expandId = "";
			do {
				ids = Twitter4JAPI.getUserFriens(twitter,username,cursor);
				if(ids==null){SaveInfo.saveFailure_Friends(username+"\t:ids==null\r\n");continue;}
				for (long id : ids.getIDs()) {
					//System.out.println(id);
					relationship += username+"\t"+id+"\r\n";
					expandId += id+"\r\n";
				}
			}while (ids!=null&&(cursor = ids.getNextCursor()) != 0);
			SaveInfo.saveFriends(relationship);//每次存储一个用户的好友，以保证数据完整性
			SaveInfo.saveExpandID(expandId);
		}
		SaveInfo.closeForFriendsCrawler();
	}
}