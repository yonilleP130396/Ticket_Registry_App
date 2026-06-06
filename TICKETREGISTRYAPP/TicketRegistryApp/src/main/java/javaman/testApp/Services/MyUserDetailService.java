package javaman.testApp.Services;

import javaman.testApp.Model.myUser;
import javaman.testApp.Repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<myUser> user = myUserRepository.findByUsername(username);

        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRole(userObj))
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRole(myUser user){

        if (user.getRole() == null){
            return new String[]{"USER","ADMIN"};
        }
        return user.getRole().split(",");
    }
}
