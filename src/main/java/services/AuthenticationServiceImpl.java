package services;


import dto.LoginDto;
import entities.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Stateless
public class AuthenticationServiceImpl implements AuthenticationService{

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public LoginDto authenticate(String email, String password) throws Exception {
		TypedQuery<Profile> query =
				entityManager.createNamedQuery("Profile.authenticate", Profile.class);
		query.setParameter("email", email).setParameter("password", password);

		Profile profile = query.getSingleResult();

		if (profile != null){
			return new LoginDto(profile.getId(),
								profile.getUsername(),
					"https://pbs.twimg.com/profile_images/874276197357596672/kUuht00m_400x400.jpg",
								generateToken(profile));
		}
		else {
			throw new Exception();
		}
	}

	private String generateToken(Profile profile) {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[128];
		random.nextBytes(bytes);
		String token = Base64.getEncoder().encodeToString(bytes);

		while (getProfileByToken(token) != null){
			random.nextBytes(bytes);
			token = Base64.getEncoder().encodeToString(bytes);
		}

		writeTokenToDb(profile.getId(), token);
		return token;
	}

	private void writeTokenToDb(long profileId, String token){
		Profile profile = entityManager.find(Profile.class, profileId);
		profile.setToken(token);
		entityManager.merge(profile);
	}

	public void deleteToken(Profile profile) {
		profile.setToken(null);
		entityManager.merge(profile);
	}

	public Profile getProfileByToken(String token) {
		TypedQuery<Profile> query = entityManager.createNamedQuery("Profile.getProfileByToken", Profile.class);
		List<Profile> profile = query.setParameter("token", token).getResultList();

		if (profile.size() != 1){
			return null;
		}
		else return profile.get(0);
	}
}
