package com.ase.springboot.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ase.springboot.model.CommentNode;
import com.ase.springboot.sentiment.SentimentAnalyzer;
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

@Service
@Transactional
public class CommentNodeServiceImpl implements CommentNodeService {

//	public CommentNodeServiceImpl(SentimentAnalyzer sentimentAnalyzer, SentiStrength sentiStrength) {
//		this.sentimentAnalyzer = sentimentAnalyzer;
//		this.sentiStrength = sentiStrength;
//	}
	public CommentNodeServiceImpl() {
		this.sentiStrength = new SentiStrength();
		this.sentimentAnalyzer = new SentimentAnalyzer();
	}

	private final SentimentAnalyzer sentimentAnalyzer;

	private final SentiStrength sentiStrength;

	@PostConstruct
	private void initialise() {
		sentimentAnalyzer.initialize();
		String ssthInitialisation[] = { "sentidata", "c:/SentStrength_Data/", "scale" };
		sentiStrength.initialise(ssthInitialisation); //

	}

	@Override
	public CommentNode getAnalyzedComments(String accessToken) {
		

		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_3_1);
		Page me = facebookClient.fetchObject("me", Page.class, Parameter.with("fields", "id,name,about,category"));

		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
		// Connections support paging and are iterable

		CommentNode rootNode = new CommentNode();

		for (List<Post> myFeedPage : myFeed) {
			for (Post post : myFeedPage) {

				CommentNode postNode = new CommentNode();

				postNode.setCommentText(post.getMessage());
				postNode.setId(post.getId());
				postNode.setPost(true);

				Connection<Comment> commentConnection = facebookClient.fetchConnection(postNode.getId() + "/comments",
						Comment.class, Parameter.with("limit", 10));

				for (List<Comment> commentPage : commentConnection) {
					for (Comment comment : commentPage) {

						CommentNode commentNode = new CommentNode();

						String message = comment.getMessage();
						commentNode.setCommentText(message);
						commentNode.setScore(sentiStrength.computeSentimentScores(message));
						commentNode.setResult(sentimentAnalyzer.getSentimentResult(message));
						commentNode.setId(comment.getId());

						postNode.getCommentList().add(commentNode);

					}
				}

				rootNode.getCommentList().add(postNode);
			}

		}

		return rootNode;

	}

	@Override
	public List<Post> getPosts(String accessToken) {

		List<Post> postsList = new ArrayList<>();

		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_3_1);
		Page me = facebookClient.fetchObject("me", Page.class, Parameter.with("fields", "id,name,about,category"));
		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

		// List<List<Post>> myFeedPages=new ArrayList<>();

		for (List<Post> myFeedPage : myFeed) {
			for (Post post : myFeedPage)
				postsList.add(post);
		}

		return postsList;

	}

}
