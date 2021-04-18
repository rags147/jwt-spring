package io.javaprojects.jwtspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javaprojects.jwtspring.model.AppAuthReq;
import io.javaprojects.jwtspring.model.AppAuthRes;
import io.javaprojects.jwtspring.service.AppUserDetailService;
import io.javaprojects.jwtspring.util.JWTUtil;

@RestController
class AuthenticationResource {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AppUserDetailService userDetailService;
    @Autowired
    JWTUtil jwtUtil;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateAndReturnToken(@RequestBody AppAuthReq request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            System.out.println(ex.toString());
        }
        final UserDetails userDetails=userDetailService.loadUserByUsername(request.getUserName());
        final String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AppAuthRes(jwt));
    }
}