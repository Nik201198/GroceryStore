package com.ecommerce.Service;

import com.ecommerce.entity.User;
import com.ecommerce.entity.UserDetail;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(userName)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + userName));
    System.out.println("Loaded User: "+ user.getUsername());
    return new UserDetail(user);
  }
}
