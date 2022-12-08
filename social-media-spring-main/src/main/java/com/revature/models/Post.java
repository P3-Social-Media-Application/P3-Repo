package com.revature.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@SequenceGenerator(name="mysequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mysequence")
    private int id;
	private String text;
	private String imageUrl;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> comments;
	@ManyToOne
	private User author;
	private boolean comment;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Post(String text, String imageUrl, List<Post> comments, User author, boolean comment) {
		super();
		this.text = text;
		this.imageUrl = imageUrl;
		this.comments = comments;
		this.author = author;
		this.comment = comment;
	}

	public Post(int id, String text, String imageUrl, List<Post> comments, User author, boolean comment) {
		super();
		this.id = id;
		this.text = text;
		this.imageUrl = imageUrl;
		this.comments = comments;
		this.author = author;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Post> getComments() {
		return comments;
	}

	public void setComments(List<Post> comments) {
		this.comments = comments;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, comment, comments, id, imageUrl, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(author, other.author) && comment == other.comment
				&& Objects.equals(comments, other.comments) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", text=" + text + ", imageUrl=" + imageUrl + ", comments=" + comments + ", author="
				+ author + ", comment=" + comment + "]";
	}
}
