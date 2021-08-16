package com.ase.springboot.service;

import java.util.List;

import com.ase.springboot.model.CommentNode;
import com.restfb.types.Post;

public interface CommentNodeService {

	CommentNode getAnalyzedComments(String accessToken);
	
	List <Post> getPosts(String accessToken);
	
}
