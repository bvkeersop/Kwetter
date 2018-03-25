package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name="Kweet.getFeedKweets",
				query="SELECT k FROM Kweet k WHERE k.id IN :ids ORDER BY k.creationDate DESC"),
		@NamedQuery(name="Kweet.getMyKweetsByDateDesc",
				query="SELECT k FROM Kweet k WHERE k.id = :id ORDER BY k.creationDate DESC")
})
public class Kweet implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kweet_id")
	private Long id;
	private String text;
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@ManyToOne(optional = false)
	private Profile owner;
	@ElementCollection
	private List<String> hashtags;

	@ManyToMany
	@JoinTable(
			name = "kweet_mentions",
			joinColumns = @JoinColumn(
					name = "kweet_id",
					referencedColumnName = "kweet_id",
					nullable = false),
			inverseJoinColumns = @JoinColumn(
					name = "profile_id",
					referencedColumnName = "profile_id",
					nullable = false))
	@JsonIgnore
	private List<Profile> mentions;

	@ManyToMany
	@JsonIgnore
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