package business.banking.dgnravepay.auth.security;

import business.banking.dgnravepay.auth.service.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        try {
            // The subject here is the Password Hash
            String passwordHash = jwtService.parse(token).getSubject();

            if (passwordHash != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(passwordHash);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ignored) {}

        chain.doFilter(request, response);
    }
}








//package business.banking.dgnravepay.auth.security;
//
//
//
//
//
//import business.banking.dgnravepay.auth.service.JwtService;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain
//    ) throws ServletException, IOException {
//
//        String header = request.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = header.substring(7);
//        try {
//            String phone = jwtService.parse(token).getSubject();
//            if (phone != null &&
//                    SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails =
//                        userDetailsService.loadUserByUsername(phone);
//
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities());
//
//                auth.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        } catch (Exception ignored) {}
//
//        chain.doFilter(request, response);
//    }
//}
