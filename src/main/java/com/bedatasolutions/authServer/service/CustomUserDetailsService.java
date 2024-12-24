package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.dao.UserDao;
import com.bedatasolutions.authServer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<String> authorities = new ArrayList<>();
        AtomicReference<CustomUserDetails> customUserDetails = new AtomicReference<>();
        // AuthorityUtils.createAuthorityList(authorities)
        userRepository.findByFullName(userName).ifPresent(user -> {
            if (userName.equals(user.getFullName())) {
                customUserDetails.set(new CustomUserDetails(
                        new UserDao(user.getId(), user.getFullName(), user.getEmail(), user.getPhone(), user.getAge(), user.getAddress(), user.getPassword()
                                , user.getCreatedAt(), user.getUpdatedAt(), user.getIsAccountNonExpired(), user.getIsAccountNonLocked(), user.getIsCredentialsNonExpired()
                                , user.getEnabled(), user.getIsRoleResourceAccess(), user.getMfaSecret(), user.getMfaKeyId(), user.getMfaEnabled()
                                , user.getMfaRegistered(), user.getRoles(), user.getResources(), user.getAuthorities())
                ));
            }
        });
        if (customUserDetails.get() != null) {
            return customUserDetails.get();
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
    }

    public void saveUserInfoMfaRegistered(String secret, String username) {
        userRepository.findByFullName(username).ifPresent(user -> {
            user.setMfaSecret(secret);
            user.setMfaRegistered(true);
            userRepository.save(user);
        });
    }
}