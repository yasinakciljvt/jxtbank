package com.jxt.jxtbank.security;


import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.auth_users.repository.UserRepository;
import com.jxt.jxtbank.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("Email not found"));
        return AuthUser.builder().user(user).build();
    }
}
