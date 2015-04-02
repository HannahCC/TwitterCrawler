package com.org.cl.twitter.service;
import java.io.IOException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;

/**
 * Hello world!
 *
 */
public class Outh2 
{
    public static void main( String[] args ) throws TwitterException, IOException
    {
        // The factory instance is re-useable and thread safe.
          Twitter twitter = TwitterFactory.getSingleton();
          twitter.setOAuthConsumer("UaKGg4nLc3aD5bg05HsEYKEYH","at498oK82dI0fXNe2CgEwSWvUsDDvznr8Esyxcokkj4TSAbWjb");
          OAuth2Token accessToken = twitter.getOAuth2Token();
          System.out.println(accessToken);
          //persist to the accessToken for future reference.
          storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
          System.exit(0);
      }
      private static void storeAccessToken(long useId, OAuth2Token accessToken){
    	  System.out.println(accessToken);
        //store accessToken.getToken()
        //store accessToken.getTokenSecret()
      } 
}
