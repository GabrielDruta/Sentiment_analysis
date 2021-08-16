package com.ase.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ase.springboot.model.CommentNode;
import com.ase.springboot.service.CommentNodeService;
import com.restfb.types.Post;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentNodeService commentNodeService;
	
	@GetMapping("/all")
	public ResponseEntity< List<Post> >  getPosts(@RequestParam("accessToken") String accessToken){
		return ResponseEntity.ok().body(commentNodeService.getPosts(accessToken));
	}
 
	@GetMapping("/processed")	
	public ResponseEntity<CommentNode>  getAnalyzedComments(@RequestParam("accessToken") String accessToken){
		return ResponseEntity.ok().body(commentNodeService.getAnalyzedComments(accessToken));
	}
	
	
	
	
}
