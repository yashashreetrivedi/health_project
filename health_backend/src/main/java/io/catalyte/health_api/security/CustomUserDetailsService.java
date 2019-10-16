package io.catalyte.health_api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.catalyte.health_api.domain.User;
import io.catalyte.health_api.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Optional<User> user = userRepo.findByEmail(email);
        return UserPrincipal.create(user);
    }

	public UserDetails findById(String userId) {
		Optional<User> user = userRepo.findById(userId);
        return UserPrincipal.create(user);
	}
}

