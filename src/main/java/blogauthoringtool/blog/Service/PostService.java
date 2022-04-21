package blogauthoringtool.blog.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogauthoringtool.blog.Exception.CustomException;
import blogauthoringtool.blog.Model.blog;
import blogauthoringtool.dto.Postdto;
import blogauthoringtool.blog.Repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;

	public blog createPost(Postdto postDto) {
		blog bl = new blog();
		bl.setUserId(postDto.getUserId());
		bl.setContent(postDto.getContent());
		bl.setTitle(postDto.getTitle());
		postRepo.save(bl);
		return bl;
	}

	public ArrayList<blog> searchBlog(String userId, String searchtext) {
		List<blog> blist = postRepo.findAll();
		ArrayList<blog> al = new ArrayList<>();
		for (blog b : blist) {
			if (b.getUserId() == Long.parseLong(userId)
					&& b.getTitle().toLowerCase().contains(searchtext.toLowerCase())) {
				al.add(b);
			}
		}
		return al;

	}

	public Object editBlog(String userid,blog bl) {
		
		Optional<blog> b=postRepo.findById(bl.getId());
		if(b.isPresent() && b.get().getUserId()==Long.parseLong(userid))
		{
			blog newb=b.get();
			newb.setTitle(bl.getTitle());
			newb.setContent(bl.getContent());
			postRepo.save(newb);
			return newb;
		}
		else
		{
			CustomException cs=new CustomException();
			cs.setStatuscode("401");
			cs.setMsg("Unauthorized acess or Unable to Edit Post");
			return cs;
		}
		
		
	}

	public Object deleteBlogs(String userId,blog bl)
	{
		Optional<blog> b=postRepo.findById(bl.getId());
		if(b.isPresent() && b.get().getUserId()==Long.parseLong(userId))
		{
			postRepo.deleteById(bl.getId());
			return bl;
		}
		else
		{
			CustomException cs=new CustomException();
			cs.setStatuscode("401");
			cs.setMsg("Unauthorized acess or Unable to delete Post");
			return cs;
		}
		
	}
	public ArrayList<blog> getAllBlogs(String userId) {
		List<blog> blist = postRepo.findAll();
		ArrayList<blog> al = new ArrayList<>();
		for (blog b : blist) {
			if (b.getUserId() == Long.parseLong(userId)) {
				al.add(b);
			}
		}
		return al;
	}

}
