package org.cl.parser;

import twitter4j.User;


public class UserParser
{
	public static String getUser(User userInfo)
	{
		String userStr = "";
		userStr += userInfo.getId()+"\t";
		userStr += userInfo.getName()+"\t";
		userStr += userInfo.getScreenName()+"\t";
		userStr += userInfo.getLocation()+"\t";
		userStr += userInfo.getLang()+"\t";
		userStr += userInfo.getDescription()+"\t";
		userStr += userInfo.getFollowersCount()+"\t";
		userStr += userInfo.getFriendsCount()+"\t";
		userStr += userInfo.getStatusesCount()+"\t";
		userStr += userInfo.getCreatedAt()+"\r\n";
		return userStr;
	}
	/*
	 * {"id":2561049626,"name":"hannahChen_","screenName":"hannahChen_",
	 * "location":"","description":"","descriptionURLEntities":[],
	 * "isContributorsEnabled":false,"profileImageUrl":"http://pbs.twimg.com/profile_images/463705376991375360/Bh0rNEiJ_normal.png",
	 * "profileImageUrlHttps":"https://pbs.twimg.com/profile_images/463705376991375360/Bh0rNEiJ_normal.png",
	 * "isDefaultProfileImage":false,
	 * "isProtected":false,
	 * "followersCount":0,
	 * "status":{"createdAt":"Aug 8, 2014 3:31:20 PM","id":497646225995620352,"text":"@Found_you_Final hello!","source":"\u003ca href\u003d\"http://twitter.com\" rel\u003d\"nofollow\"\u003eTwitter Web Client\u003c/a\u003e","isTruncated":false,"inReplyToStatusId":495055350371217409,"inReplyToUserId":2697243710,"isFavorited":false,"isRetweeted":false,"favoriteCount":0,"inReplyToScreenName":"Found_you_Final","retweetCount":0,"isPossiblySensitive":false,"lang":"en","contributorsIDs":[],"userMentionEntities":[{"name":"hannah","screenName":"Found_you_Final","id":2697243710,"start":0,"end":16}],"urlEntities":[],"hashtagEntities":[],"mediaEntities":[],"extendedMediaEntities":[],"symbolEntities":[],"currentUserRetweetId":-1},
	 * "profileBackgroundColor":"C0DEED","profileTextColor":"333333","profileLinkColor":"0084B4","profileSidebarFillColor":"DDEEF6","profileSidebarBorderColor":"C0DEED","profileUseBackgroundImage":true,"isDefaultProfile":true,"showAllInlineMedia":false,
	 * "friendsCount":66,
	 * "createdAt":"Jun 11, 2014 6:10:41 PM",
	 * "favouritesCount":0,"utcOffset":-1,"profileBackgroundImageUrl":"http://abs.twimg.com/images/themes/theme1/bg.png",
	 * "profileBackgroundImageUrlHttps":"https://abs.twimg.com/images/themes/theme1/bg.png",
	 * "profileBannerImageUrl":"https://pbs.twimg.com/profile_banners/2561049626/1402481471",
	 * "profileBackgroundTiled":false,"lang":"zh-cn","statusesCount":3,"isGeoEnabled":false,"isVerified":false,"translator":false,
	 * "listedCount":0,"isFollowRequestSent":false}
	 */
}