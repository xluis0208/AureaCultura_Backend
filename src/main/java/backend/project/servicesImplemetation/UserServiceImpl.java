package backend.project.servicesImplemetation;

import backend.project.entities.Authority;
import backend.project.entities.User;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.AuthorityRepository;
import backend.project.repositories.UserRepository;
import backend.project.security.SecurityUser;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public User insertUser(User user) {
        user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User insertUser(String username, String password, Boolean active, Long authorityId) {
        User user = new User();
        user.setUsername(username);
        user.setActive(active);

        Authority authority = authorityRepository.findById(authorityId)
                .orElseThrow(() -> new ResourceNotFoundException("Authority not found with ID: " + authorityId));
        user.setPassword( new BCryptPasswordEncoder().encode(password));
        user.setAuthority(authority);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> findByActiveStatus(Boolean active) {
        return userRepository.findByActive(active);
    }

    @Override
    public User findByUsername(String username) {
        User userFound = userRepository.findByUsername(username);
        if (userFound==null){
            throw new ResourceNotFoundException("User with username: "+ username+ " can not be found");
        }
        return userFound;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUser(findByUsername(username));
    }

}
