package com.kek.kekMovie.Services.Implemaentations;

import com.kek.kekMovie.Entities.Role;
import com.kek.kekMovie.Entities.User;
import com.kek.kekMovie.Entities.VerificationToken;
import com.kek.kekMovie.Repositories.RoleRepository;
import com.kek.kekMovie.Repositories.UserRepository;
import com.kek.kekMovie.Repositories.VerificationTokenRepository;
import com.kek.kekMovie.Services.UserService;
import com.kek.kekMovie.Services.Exceptions.EmailExistsException;
import com.sun.media.sound.ModelMappedInstrument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    VerificationTokenRepository tokenRepository;

//    @Autowired
//    private JavaMailSender mailSender;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)* (.[A-Za-z]{2,})$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public UserServiceImpl() {
    }

    @Transactional
    public VerificationToken registerUserAccount(String username, String password, String email) throws EmailExistsException, Exception {
//        matcher = pattern.matcher(email);
//        if (!matcher.matches())
//            throw new Exception("Bad email format!");
        if (emailExist(email))
            throw new EmailExistsException("There is an account with that email address: " + email);

        Role roleUser = roleRepository.findByRoleIgnoreCase("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user =  userRepository.save(new User(username, password, email, roles));
        System.out.print("User: ");
        System.out.println(user);
        return generateToken(user);
    }


    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

    private VerificationToken generateToken(User user){
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setToken(user.getUsername() + String.valueOf(user.getId()));
        token.setExpiryDate();
        return tokenRepository.save(token);
    }


    public User confirmUserAccount(String token){
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (!verificationToken.equals(null)){
            User user = verificationToken.getUser();
            user.enable();
            tokenRepository.delete(verificationToken);
            return userRepository.save(user);
        }
        return null;
    }

    public User getUser(long id){
        return userRepository.findOne(id);
    }


    public User getUser(String usernameOrEmail){
        if (userRepository.findByUsername(usernameOrEmail) != null)
            return userRepository.findByUsername(usernameOrEmail);
        else
            return userRepository.findByEmail(usernameOrEmail);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }


    public void deleteUser(long id){
        userRepository.delete(id);
    }

}


