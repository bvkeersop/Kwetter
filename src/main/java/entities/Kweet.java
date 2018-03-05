package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Kweet implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String text;
	private Date creationDate;
	@OneToOne
	private Profile owner;
	@ElementCollection
	private List<String> hashtags;
	@OneToMany
	private List<Profile> mentions;
	@OneToMany
	private List<Profile> likedBy;

	public Kweet(){
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	public List<Profile> getMentions() {
		return mentions;
	}

	public void setMentions(List<Profile> mentions) {
		this.mentions = mentions;
	}

	public List<Profile> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<Profile> likedBy) {
		this.likedBy = likedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
