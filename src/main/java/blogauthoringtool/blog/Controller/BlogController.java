package blogauthoringtool.blog.Controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blogauthoringtool.blog.Model.blog;
import blogauthoringtool.blog.Service.PostService;
import blogauthoringtool.dto.Postdto;

@RestController
@RequestMapping("/api/posts/")
public class BlogController {

	@Autowired
	private PostService postService;

	@PostMapping("/createblog")
	public Object createPost(@RequestBody Postdto postDto) {
		blog bl=postService.createPost(postDto);
		return bl;
	}

	@GetMapping("/getAllBlogs/{userId}")
	private ArrayList<blog> getAllBlogs(@PathVariable String userId) {
		ArrayList<blog> bl=postService.getAllBlogs(userId);
				return bl;
		
	}

	@GetMapping("/searchBlog/{userId}/search")
	private ArrayList<blog> searchBlog(@PathVariable String userId, @RequestParam(name="searchText") String searchText) {
	
		return postService.searchBlog(userId,searchText);
	}

	@PutMapping("/editBlog/{userId}")
	private Object editBlog(@PathVariable String userId, @RequestBody blog bl) {
		
		return postService.editBlog(userId, bl);
	}

	@DeleteMapping("/deleteBlog/{userId}")
	private Object deleteBlog(@PathVariable String userId, @RequestBody blog bl) {
		return postService.deleteBlogs(userId, bl);
	}
	
	
	
}
