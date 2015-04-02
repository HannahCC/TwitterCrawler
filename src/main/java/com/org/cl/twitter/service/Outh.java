package com.org.cl.twitter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Hello world!
 *
 */
public class Outh 
{
    public static void main( String[] args ) throws TwitterException, IOException
    {
        // The factory instance is re-useable and thread safe.
          Twitter twitter = TwitterFactory.getSingleton();
          twitter.setOAuthConsumer("UaKGg4nLc3aD5bg05HsEYKEYH", "at498oK82dI0fXNe2CgEwSWvUsDDvznr8Esyxcokkj4TSAbWjb");
          RequestToken requestToken = twitter.getOAuthRequestToken();
          AccessToken accessToken = null;
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          while (null == accessToken) {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try{
               if(pin.length() > 0){
                 accessToken = twitter.getOAuthAccessToken(requestToken, pin);
               }else{
                 accessToken = twitter.getOAuthAccessToken();
               }
            } catch (TwitterException te) {
              if(401 == te.getStatusCode()){
                System.out.println("Unable to get the access token.");
              }else{
                te.printStackTrace();
              }
            }
          }
          //persist to the accessToken for future reference.
          storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
          twitter.setOAuthAccessToken(accessToken);
          /*User userInfo = twitter.showUser("terkojakobson");
          System.out.println("Showing user infomation.");
          System.out.println(userInfo.getId()+"|"+userInfo.getScreenName());*/
          IDs ids = twitter.getFriendsIDs("terkojakobson", -1);
          System.out.println("Showing user Friends.");
          long[] ids_list = ids.getIDs();
          for (long id : ids_list) {
              System.out.println(id);
          }
          System.exit(0);
      }
      private static void storeAccessToken(long useId, AccessToken accessToken){
    	  System.out.println(accessToken.getToken());
    	  System.out.println(accessToken.getTokenSecret());
        //store accessToken.getToken()
        //store accessToken.getTokenSecret()
      } 
}
