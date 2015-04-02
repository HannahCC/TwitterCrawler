package com.org.cl.twitter;

import java.io.File;

import org.cl.conf.Config;
import org.cl.crawler.Crawler_Followers;
import org.cl.crawler.Crawler_Friends;
import org.cl.crawler.Crawler_Twitters;
import org.cl.crawler.Crawler_userInfo;
import org.cl.service.RWUid;
import org.cl.service.SaveInfo;

public class ReCrawler {

	public static void main(String args[]){
		int deepId = Integer.parseInt(args[0]);
		int funcitonId = Integer.parseInt(args[1]);
		int startId = Integer.parseInt(args[2]);
		//判断从第几层开始爬取
		for(;startId<=deepId;startId++){
			RWUid userid = SaveInfo.getUserId("/UserId"+startId+".txt");
			if(funcitonId == 1){
				RWUid userid_copy = userid.Copy();
				deleteId_userInfogotten(startId,userid_copy);//去掉已经获得用户信息的id
				System.out.println(startId+":Crawler_userInfo……");
				Crawler_userInfo.crawler_userInfo(startId,userid_copy);
			}else if(funcitonId == 2){
				RWUid userid_copy = userid.Copy();
				deleteId_friendsgotten(startId,userid_copy);//去掉已经获得用户好友的id
				System.out.println(startId+":Crawler_Friends……");
				Crawler_Friends.crawler_Friends(startId,userid_copy);
			}else if(funcitonId == 3){
				RWUid userid_copy = userid.Copy();
				deleteId_followersgotten(startId,userid_copy);//去掉已经获得用户粉丝的id
				System.out.println(startId+":Crawler_Followers……");
				Crawler_Followers.crawler_Followers(startId, userid_copy);
			}else if(funcitonId == 4){
				RWUid userid_copy = userid.Copy();
				deleteId_twittergotten(startId,userid_copy);//去掉已经获得用户推特的id
				System.out.println(startId+":Crawler_Twitters……");
				Crawler_Twitters.crawler_Twitters(startId,userid_copy);
			}else{
				System.out.println("aguments error!");
			}
		}
	}

	public static void deleteId_userInfogotten(int startId,RWUid userid_copy){
		RWUid userid_got = SaveInfo.getUserId(startId+"/UserInfo.txt","\t",0);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Info的id;
		}

		userid_got = SaveInfo.getUserId(startId+"/UserInfo.txt","\t",2);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Info的screen name;
		}

		userid_got = SaveInfo.getUserId(startId+"/UserInfo_tooManyShip.txt","\t",0);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Info的Id;
		}

		userid_got = SaveInfo.getUserId(startId+"/UserInfo_tooManyShip.txt","\t",2);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Info的screen name;
		}
		//去掉用户不存在的
		userid_got = SaveInfo.getUserId(startId+"/Config/UserNotExist.txt");
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
	}
	public static void deleteId_friendsgotten(int startId,RWUid userid_copy){
		//去掉已经获取到的
		RWUid userid_got = SaveInfo.getUserId(startId+"/Friends.txt","\t",0);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
		//去掉获取不到的
		userid_got = SaveInfo.getUserId(startId+"/Config/FailToGet_Friends.txt","\t",0);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
		//去掉用户不存在的
		userid_got = SaveInfo.getUserId(startId+"/Config/UserNotExist.txt");
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
		//不需要去掉too many的，因为在crawler程序中已经对此作了筛选
	}
	public static void deleteId_followersgotten(int startId,RWUid userid_copy){
		RWUid userid_got = SaveInfo.getUserId(startId+"/Followers.txt","\t",0);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
		//去掉获取不到的
		userid_got = SaveInfo.getUserId(startId+"/Config/FailToGet_Followers.txt","\t",0);
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
		//去掉用户不存在的
		userid_got = SaveInfo.getUserId(startId+"/Config/UserNotExist.txt");
		while(userid_got.getNum()>0){
			userid_copy.delete(userid_got.getUid());//删除列表中已经获取到Followers的Id;
		}
	}
	public static void deleteId_twittergotten(int startId,RWUid userid_copy){
		File twitters = new File(Config.ROOT_PATH+startId+"/Twitters");
		File[] fs = twitters.listFiles();
		for(File f : fs){
			userid_copy.delete(f.getName().split(".txt")[0]);
		}
	}
}
