package com.ase.springboot.sentiment;


import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Comment;
import com.restfb.types.From;
import com.restfb.types.Page;
import com.restfb.types.Post;

import uk.ac.wlv.sentistrength.SentiStrength;


public class FBcomments {

	public static void main(String[] args) {
				

		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		sentimentAnalyzer.initialize();

		SentiStrength sentiStrength = new SentiStrength(); 

		//Create an array of command line parameters to send (not text or file to process)
		String ssthInitialisation[] = {"sentidata", "c:/SentStrength_Data/", "scale"};
		sentiStrength.initialise(ssthInitialisation); //Initialise
		
	String accessToken="EAACFioF1x7UBAKhgXXXu4zTcqZCMyuWK98HXZCnTT3ZAUOUEXSF1QKYF4vpQzXwQqjoFCfOJ4R30aGKlubZAme3fC9m8q4cCXvChDJC3Kxc8KgK0fjeZBdz848bGJPJLZCgaUi53IDNEZCNM1MIYcjY8Vay5d4bTYiZCzcEf71hVywZDZD";
	
	FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_3_1);
	Page me=facebookClient.fetchObject("me", Page.class, 
			Parameter.with("fields", "id,name,about,category"));
//	System.out.println(me.getId());
//	System.out.println("Page name: " + me.getName());
//	System.out.println(me.getAbout());
//	System.out.println("Category:"+me.getCategory());
//	System.out.println(" ");
//	

	Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
	// Connections support paging and are iterable

	
	// Iterate over the feed to access the particular pages
	for (List<Post> myFeedPage : myFeed) {
		
		System.out.println("number of posts in this feed: ");
		System.out.println(myFeedPage.size());
		List <String> postList= new ArrayList<>();
		// Iterate over the list of contained data 
		// to access the individual object
		for (Post post : myFeedPage) {
			String postId=post.getId();
			postList.add(postId);
			
			System.out.println(" ");
			//System.out.println(postId);
			System.out.println(post.getMessage());
			System.out.println(" ");
	
			System.out.println(sentiStrength.computeSentimentScores(post.getMessage())); 			
			System.out.println(sentimentAnalyzer.getSentimentResult(post.getMessage()));
			
		}
		
		
		for (String postId : postList) {
	
		Connection<Comment> commentConnection =
				facebookClient.fetchConnection(postId + "/comments", 
							Comment.class, Parameter.with("limit", 10));
		for (List<Comment> commentPage : commentConnection) {	
		
			for (Comment comment : commentPage) {
				//System.out.println("Id: " + comment.getId());
				Long likes=comment.getLikeCount();
				System.out.println(comment.getMessage()+ " number of likes: "+ likes);

				System.out.println(sentiStrength.computeSentimentScores(comment.getMessage()));
				System.out.println(sentimentAnalyzer.getSentimentResult(comment.getMessage()));
				
				
			
			
			
				From username=comment.getFrom();
				//System.out.println(" \n \n \n \n \n"+username.getName()+" \n \n \n");
				
				String commentId=comment.getId();
				Connection<Comment> replyConnection =
						facebookClient.fetchConnection(commentId + "/comments", 
							Comment.class, Parameter.with("limit", 10));
				
				for (List<Comment> replyPage : replyConnection) {	
					for(Comment reply:replyPage) {
						System.out.println(reply.getMessage());

						//System.out.println(sentiStrength.computeSentimentScores(reply.getMessage()));
						//System.out.println(sentimentAnalyzer.getSentimentResult(reply.getMessage()));
					}
				}
				System.out.println(" ");
		
			}
		  }
		}
	  }
	
	System.out.println("wow");
	}
}
