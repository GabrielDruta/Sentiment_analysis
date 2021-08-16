package com.ase.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.ase.springboot.repository.UserRepository;
import com.ase.springboot.sentiment.SentimentAnalyzer;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.Post;

import uk.ac.wlv.sentistrength.SentiStrength;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBackendApplication.class, args);

	}

//	@Autowired
//	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
//
//		String accessToken = "EAACFioF1x7UBAKhgXXXu4zTcqZCMyuWK98HXZCnTT3ZAUOUEXSF1QKYF4vpQzXwQqjoFCfOJ4R30aGKlubZAme3fC9m8q4cCXvChDJC3Kxc8KgK0fjeZBdz848bGJPJLZCgaUi53IDNEZCNM1MIYcjY8Vay5d4bTYiZCzcEf71hVywZDZD";
//
//		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_3_1);
//		Page me = facebookClient.fetchObject("me", Page.class, Parameter.with("fields", "id,name,about,category"));
//
//		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
//		// Connections support paging and are iterable
//
//		// initiate analyzers
//		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
//		sentimentAnalyzer.initialize();
//
//		SentiStrength sentiStrength = new SentiStrength();
//		// Create an array of command line parameters to send (not text or file to
//		// process)
//		String ssthInitialisation[] = { "sentidata", "c:/SentStrength_Data/", "scale" };
//		sentiStrength.initialise(ssthInitialisation); // Initialise
//
//		// Iterate over the feed to access the particular pages
//		for (List<Post> myFeedPage : myFeed) {
//
//			System.out.println("number of posts in this feed: ");
//			System.out.println(myFeedPage.size());
//			List<String> postList = new ArrayList<>();
//			// Iterate over the list of contained data
//			// to access the individual object
//			for (Post post : myFeedPage) {
//				String postId = post.getId();
//				postList.add(postId);
//
////			System.out.println(" ");
////			System.out.println(post.getMessage());
////			System.out.println(" ");
//
//				System.out.println(sentiStrength.computeSentimentScores(post.getMessage()));
//				String sentiS = sentiStrength.computeSentimentScores(post.getMessage());
//				//
//				// System.out.println(sentimentAnalyzer.getSentimentResult(post.getMessage()));
//				String scor = sentimentAnalyzer.getSentimentResult(post.getMessage());
//
//				/// nu au ce cauta aici
//				this.userRepository.save(new User(post.getMessage(), scor, sentiS));
//			}
//
//		}
//		this.userRepository.save(new User("Gabriel", "Druta", "drutagabriel@gmail.com"));
//
////

	}
	// sign in,
	// user, id, username, password, first last name, nickname,
	// id, userid, accees token

	// userService, user actions, CRUD, Service, transactional,

	// new package, service

}