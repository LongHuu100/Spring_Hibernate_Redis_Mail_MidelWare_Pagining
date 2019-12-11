package vn.printgo.controller;

import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RObject;
import vn.printgo.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
 
    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(
            @RequestParam(value="username") String username,
            @RequestParam(value="password") String password) {
 
        String passEncoder = passwordEncoder.encode(password);
        logger.info("username:" + username + "|passEncoder:" + passEncoder);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                username,
                password
            )
        );
        String jwt = jwtProvider.generateJwtToken(authentication);
        RObject<?> responseObject = new RObject<Object>(
            ErrorData.SUCCESS,
            ErrorData.getMessage(ErrorData.SUCCESS),
            jwt
        );
        return ResponseEntity.ok(responseObject);
    }
    
    @RequestMapping(value = "/check-token", method = RequestMethod.POST)
    public ResponseEntity<?> refeshtoken(@RequestBody RToken rToken) {
        RObject<?> _rc = new RObject<Object>(
            ErrorData.INVALID_TOKEN,
            ErrorData.getMessage(ErrorData.INVALID_TOKEN),
            ""
        );
        logger.info("token: {}", rToken.token);
        try {
            Claims claims = jwtProvider.getClaims(rToken.token);
            String username = claims.getSubject();
            if(username != null) {
                _rc.setErrorCode(ErrorData.SUCCESS);
                _rc.setMessage(ErrorData.getMessage(ErrorData.SUCCESS));
            }
        } catch (Throwable tb) {
            logger.error("error refesh token Message: {}",tb.getMessage());
        }
        
        return ResponseEntity.ok(_rc);
    }
    
    public static class RToken {
        public String token;
    }
}