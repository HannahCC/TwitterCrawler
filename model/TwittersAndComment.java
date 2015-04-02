package org.cl.model;

import java.util.ArrayList;

import twitter4j.Twitter;

public class TwittersAndComment
{
	/** ÍÆÌØ*/
	private Twitter twitter=null;
	/** ÆÀÂÛ*/
	private ArrayList<Comment> comments=null;
	
	public TwittersAndComment()
	{
		comments=new ArrayList<Comment>();
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	

}
