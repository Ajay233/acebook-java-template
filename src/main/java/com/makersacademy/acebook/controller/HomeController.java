package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
public class HomeController {

	private final PostRepository postRepository;
//	private Post post;

	@Autowired
	public HomeController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/greeting")
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=true, defaultValue="Everybody") String name, Model model) {
		model.addAttribute("name", name);

		return "greeting";
	}

	@RequestMapping(value = "/posts")
	@PostMapping("/posts")
	public String posts(Model model){
		long id = 1;
		Post post = new Post("TestContent");
		model.addAttribute("post", post.getContent());
		model.addAttribute("allPosts", postRepository.findById((long)1));
		model.addAttribute("toString", postRepository.toString());
		model.addAttribute("findAll", postRepository.findAll());
		Optional<Post> post2 = postRepository.findById(id);
		model.addAttribute("findById", post2);
		model.addAttribute("findByIdString", post2.toString());
		model.addAttribute("find", post2.get().getContent());
		model.addAttribute("test", postRepository.getClass());
		return "posts";
	}



}
