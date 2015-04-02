package org.cl.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.cl.conf.Config;
import org.cl.service.RWUid;

/** 保存信息到文件*/
public class SaveInfo 
{
	private static String path = "";
	//拓展用户ID
	private static FileOutputStream user_id=null;
	//用户信息
	private static FileOutputStream user_info=null;
	//关系过多的用户信息
	private static FileOutputStream user_info_toomany=null;
	//粉丝	原用户ID	扩展用户ID		关系
	private static FileOutputStream followers=null;
	//好友	原用户ID	扩展用户ID		关系
	private static FileOutputStream friends=null;
	//保存推特信息的文件夹名
	private static final String filename="Twitters";
	//用户信息获取失败的用户ID
	private static FileOutputStream failure_userinfo=null;
	//获取互粉关系失败的用户ID
	private static FileOutputStream user_notExist=null;
	//用户粉丝获取失败的用户ID
	private static FileOutputStream failure_followers=null;
	//用户好友获取失败的用户ID
	private static FileOutputStream failure_friends=null;
	//用户推特获取失败的用户ID
	private static FileOutputStream failure_twitter=null;

	public static void initForUserInfoCrawler(int deepId){
		path = Config.ROOT_PATH+deepId+"/";
		File file= new File(path+"Config");//创建目录
		if(!file.exists()){file.mkdirs();}
		System.out.println(path);

		File temp2=new File(path+"UserInfo.txt");
		File temp3=new File(path+"UserInfo_tooManyShip.txt");
		File temp4=new File(path+"Config/UserNotExist.txt");
		File temp5=new File(path+"Config/FailToGet_userInfo.txt");

		try 
		{
			user_info=new FileOutputStream(temp2,true);
			user_info_toomany=new FileOutputStream(temp3,true);
			user_notExist=new FileOutputStream(temp4,true);
			failure_userinfo=new FileOutputStream(temp5,true);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public static void initForFollowersCrawler(int deepId){
		path = Config.ROOT_PATH+deepId+"/";
		File file= new File(path+"Config");//创建目录
		if(!file.exists()){file.mkdirs();}
		System.out.println(path);

		File temp1=new File(path+"Followers.txt");
		File temp3=new File(path+"Config/FailToGet_Followers.txt");
		File temp5=new File(Config.ROOT_PATH+"UserId"+(deepId+1)+".txt");		
		try 
		{
			followers=new FileOutputStream(temp1,true);
			failure_followers=new FileOutputStream(temp3,true);
			user_id=new FileOutputStream(temp5,true);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public static void initForFriendsCrawler(int deepId){
		path = Config.ROOT_PATH+deepId+"/";
		File file= new File(path+"Config");//创建目录
		if(!file.exists()){file.mkdirs();}
		System.out.println(path);

		File temp2=new File(path+"Friends.txt");
		File temp4=new File(path+"Config/FailToGet_Friends.txt");
		File temp5=new File(Config.ROOT_PATH+"UserId"+(deepId+1)+".txt");

		try 
		{
			friends=new FileOutputStream(temp2,true);
			failure_friends=new FileOutputStream(temp4,true);
			user_id=new FileOutputStream(temp5,true);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public static void initForTwitterCrawler(int deepId){
		path = Config.ROOT_PATH+deepId+"/";
		File file1= new File(path+"Config");//创建目录
		if(!file1.exists()){file1.mkdirs();}
		File file2= new File(path+filename);//创建存储Twitter的文件夹
		if(!file2.exists()){file2.mkdirs();}
		System.out.println(path);

		File temp=new File(path+"Config/FailToGet_twitter.txt");

		try 
		{
			failure_twitter=new FileOutputStream(temp,true);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/** 关闭所有与用户信息存储文件
	 * @throws IOException */
	public static void closeForUserInfoCrawler()
	{
		try {
			user_info.flush();
			user_info.close();
			user_info_toomany.flush();
			user_info_toomany.close();
			failure_userinfo.flush();
			failure_userinfo.close();
			user_notExist.flush();
			user_notExist.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/** 关闭所有与粉丝相关的文件
	 * @throws IOException */
	public static void closeForFollowersCrawler()
	{
		try {
			followers.flush();
			followers.close();
			failure_followers.flush();
			failure_followers.close();
			user_id.flush();
			user_id.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/** 关闭所有与关系相关的文件
	 * @throws IOException */
	public static void closeForFriendsCrawler()
	{
		try {
			friends.flush();
			friends.close();
			failure_friends.flush();
			failure_friends.close();
			user_id.flush();
			user_id.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/** 关闭所有与推特相关的文件
	 * @throws IOException */
	public static void closeForTwitterCrawler()
	{
		try {
			failure_twitter.flush();
			failure_twitter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 保存扩展用户的ID
	 * @throws IOException */
	public synchronized static void saveExpandID(String info)
	{
		//扩展用户的ID	ID
		try {
			user_id.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/** 保存粉丝
	 * @throws IOException */
	public synchronized static void saveFollowers(String info)
	{
		try {
			followers.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 保存好友
	 * @throws IOException */
	public synchronized static void saveFriends(String info)
	{
		try {
			friends.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 保存用户信息
	 * @throws IOException */
	public synchronized static void saveUser(String info)
	{
		try {
			user_info.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 关系过多的用户信息
	 * @throws IOException */
	public synchronized static void saveUsertooMany(String info)
	{
		try {
			user_info_toomany.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 获取用户信息失败的用户ID
	 * @throws IOException */
	public synchronized static void saveFailure_userinfo(String info)
	{
		try {
			failure_userinfo.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 获取粉丝失败的用户ID
	 * @throws IOException */
	public synchronized static void saveFailure_Followers(String info)
	{
		try {
			failure_followers.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 获取好友失败的用户ID
	 * @throws IOException */
	public synchronized static void saveFailure_Friends(String info)
	{
		try {
			failure_friends.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 获取微博失败的用户ID
	 * @throws IOException */
	public synchronized static void saveFailure_twitter(String info)
	{
		try {
			failure_twitter.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 不存在的用户ID
	 * @throws IOException */
	public synchronized static void saveUserNotExist(String info)
	{
		try {
			user_notExist.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 保存推特
	 * @throws IOException */
	public static void savetwitter(String uid,ArrayList<String> twitters)
	{
		File file=new File(path+filename+"/"+uid+".txt");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file,true);
			for(String tw : twitters)
			{
				fout.write(tw.getBytes());
			}
			fout.flush();
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static RWUid getUserId(String file_name){
		File f = new File(Config.ROOT_PATH+"/"+file_name);
		RWUid userId = new RWUid();
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(f));
			String uid=null;
			while((uid=br.readLine())!=null)
			{
				if(!(uid.equals("")))userId.setUid(uid);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	//获取文件每行中第subscript个元祖，每个元祖之间使用regex隔开
	public static RWUid getUserId(String file_name,String regex,int subscript){
		File f = new File(Config.ROOT_PATH+"/"+file_name);
		RWUid userId = new RWUid();
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(f));
			String uid=null;
			while((uid=br.readLine())!=null)
			{
				String[] tmp = uid.split(regex);
				if(tmp.length>subscript){
					uid = tmp[subscript];
					userId.setUid(uid);
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
}

