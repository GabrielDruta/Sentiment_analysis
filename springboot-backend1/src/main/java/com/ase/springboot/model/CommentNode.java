package com.ase.springboot.model;

import java.util.ArrayList;
import java.util.List;

public class CommentNode {

	private List<CommentNode> commentList= new ArrayList<>();
	
	private String commentText;
	private String result;
	private String score;
	private String id;
	private boolean isPost;
	
	
	
	public List<CommentNode> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentNode> commentList) {
		this.commentList = commentList;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isPost() {
		return isPost;
	}
	public void setPost(boolean isPost) {
		this.isPost = isPost;
	}
	
	
	
}
