package com.kek.kekMovie.Services;

import com.kek.kekMovie.Entities.User;
import com.kek.kekMovie.Entities.VerificationToken;
import com.kek.kekMovie.Services.Exceptions.EmailExistsException;

public interface UserService {
    VerificationToken registerUserAccount(String username, String password, String email) throws EmailExistsException, Exception;
    User confirmUserAccount(String token);
    User getUser(long id);
    User getUser(String usernameOrEmail);
    Iterable<User> getUsers();
    void deleteUser(long id);
}