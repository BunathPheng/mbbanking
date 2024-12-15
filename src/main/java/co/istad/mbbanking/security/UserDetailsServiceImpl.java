package co.istad.mbbanking.security;

import co.istad.mbbanking.domain.User;
import co.istad.mbbanking.feactures.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private  final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        log.info("Trying to load user by phone number: {}", phoneNumber);
        User  user =  userRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException("Use is has  not  found")
        );
        log.info("User : {}",user);
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user);
        return userDetails;
    }
}
