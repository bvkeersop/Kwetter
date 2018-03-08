package services;

import entities.Profile;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ProfileService {
	void createProfile(Profile profile);
	void updateUsername(Profile profile);
	void followProfile(long profileId, Profile profileToFollow);
	Profile getProfile(long profileId);
	List<Profile> getProfiles(String name);
	List<Profile> getFollowers(long profileId);
	List<Profile> getFollowing(long profileId);
}
