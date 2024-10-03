package ru.itmentor.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.configs.exception.LoginException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AppService extends UserDetailsService {

    List<User> getAllUsers();

    User deleteUser(long parseUnsignedInt);

    User readUser(long id);

    List<Role> findAllRoles();

    void authenticateOrLogout(Model model, HttpSession session, LoginException authenticationException, String authenticationName);

    boolean saveUser(User user, BindingResult bindingResult, Model model);

    //for REST
    void saveUser(User user);
}
