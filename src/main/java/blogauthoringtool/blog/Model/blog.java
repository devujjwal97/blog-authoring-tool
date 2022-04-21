package blogauthoringtool.blog.Model;

import javax.persistence.*;

	@Entity
	@Table(name="blog_details")
	public class blog {
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long id;

	    @Column
	    private String title;
	    @Column
	    private  Long userId;
	    @Lob
	    @Column
	    private String content;
	    
	    public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		 public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	
	}


