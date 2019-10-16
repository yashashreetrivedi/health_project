package io.catalyte.health_api.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.catalyte.health_api.domain.User;


public class UserPrincipal implements UserDetails{
	private String id;
	private String username;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
	
    public static UserPrincipal create(Optional<User> user) {
    	final List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();    	
    	for (int i=0; i < user.get().getRoles().length; i++) {
    		authorities.add(new SimpleGrantedAuthority(user.get().getRoles()[i]));
    	}
        return new UserPrincipal(
                user.get().get_id(),
                user.get().getEmail(),
                user.get().getPassword(),
                authorities
        );
    }
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
