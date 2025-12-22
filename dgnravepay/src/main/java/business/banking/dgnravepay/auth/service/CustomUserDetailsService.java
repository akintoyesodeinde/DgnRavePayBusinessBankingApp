package business.banking.dgnravepay.auth.service;

import business.banking.dgnravepay.auth.entity.UserEntity;
import business.banking.dgnravepay.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String passwordHash) throws UsernameNotFoundException {
        // We use the passwordHash as the unique identifier for the Spring Security Principal
        UserEntity user = userRepo.findByPasswordHash(passwordHash)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

        return new User(
                user.getPasswordHash(), // Username is set to the hash for internal tracking
                user.getPasswordHash(),
                Collections.emptyList() // Roles/Permissions removed as requested
        );
    }
}

















//package business.banking.dgnravepay.auth.service;
//
//
//
//import business.banking.dgnravepay.auth.entity.UserEntity;
//import business.banking.dgnravepay.auth.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String phoneNumber)
//            throws UsernameNotFoundException {
//
//        UserEntity user = userRepo.findByPhoneNumber(phoneNumber)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new User(
//                user.getPhoneNumber(),
//                user.getPasswordHash(),
//                Collections.emptyList() // NO ROLES
//        );
//    }
//}
