package org.cl.conf;

public class Config
{
	private static String PRO_NAME = "Twitter_Crawler";
	private static String RES_NAME = "Twitter_res";
	public static String ROOT_PATH = "";
	public static final int REQUEST_MAX=10000;
	public static final long SLEEP_TIME=1000000;//15分钟一个间隔
	public static final int FRIENDS_MAX=1000;
	public static final int FOLLOW_MAX=1000;
	public static long START=0;
	public static long END=0;
	static {
		ROOT_PATH = System.getProperty("user.dir");
		ROOT_PATH = Config.ROOT_PATH.replace(PRO_NAME, "");
		ROOT_PATH += "/"+RES_NAME+"/";
	}
}
