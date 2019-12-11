
package vn.printgo.service;

import vn.printgo.dao.UserDao;
import vn.printgo.model.User;
import vn.printgo.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    UserDao userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = userRepository.findBySSO(username);
        if(user == null ) {
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + user);
        } 	
        return UserPrinciple.build(user);
    }
}
