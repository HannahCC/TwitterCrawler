package org.cl.model;
import java.util.Date;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

public class Tweet {
	
	public Tweet(long id, Date createdAt, GeoLocation geoLocation, String text,
			String source, Place place, long retweetCount, 
			HashtagEntity[] hashtagEntities, 
			URLEntity[] urlEntities, 
			MediaEntity[] mediaEntities,
			User user, 
			Status retweetedStatus,
			long inreplyToStatusId,
			long inreplyToUserId,
			UserMentionEntity[] usermentionEntities) {
		
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.geoLocation = geoLocation;
		this.text = text;
		this.source = source;
		this.place = place;
		this.retweetCount = retweetCount;
		this.hashtagEntities = hashtagEntities;
		this.urlEntities = urlEntities;
		this.mediaEntities = mediaEntities;
		this.user = user;
		this.retweetedStatus = retweetedStatus;
	}
	long id;
	Date createdAt;
	GeoLocation geoLocation;
	String text;
	String source;
	Place place;
	long retweetCount;
	HashtagEntity[] hashtagEntities; 
	URLEntity[] urlEntities;
	MediaEntity[] mediaEntities;
	User user;
	Status retweetedStatus;
	long inreplyToStatusId;
	long inreplyToUserId;
	UserMentionEntity[] usermentionEntities;

}
