package model;

import enums.Permissions;

import javax.enterprise.inject.Model;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

public class AuthenticatedUser {
	private long id;
	private List<Permissions> permissions;

	public List<Permissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permissions> permissions) {
		this.permissions = permissions;
	}

	public AuthenticatedUser() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
