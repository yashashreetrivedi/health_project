package io.catalyte.health_api.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
@Id
private String _id;
private String name;
private String title;
private String email;
private String password;
private String roles[];


public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String get_id() {
	return _id;
}

public void set_id(String _id) {
	this._id = _id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String[] getRoles() {
	return roles;
}
public void setRoles(String[] roles) {
	this.roles = roles;
}

}
