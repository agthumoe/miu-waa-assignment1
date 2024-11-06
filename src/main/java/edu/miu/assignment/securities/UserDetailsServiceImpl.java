package edu.miu.assignment.securities;

import edu.miu.assignment.exceptions.HttpStatusException;
import edu.miu.assignment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameIgnoreCase(username).orElseThrow(()-> new HttpStatusException("Username: " + username + " not found", HttpStatus.NOT_FOUND));
    }
}
