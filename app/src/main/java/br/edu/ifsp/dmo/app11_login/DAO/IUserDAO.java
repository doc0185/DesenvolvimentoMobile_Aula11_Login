package br.edu.ifsp.dmo.app11_login.DAO;

import java.util.List;

import br.edu.ifsp.dmo.app11_login.Exception.UserDuplicatedException;
import br.edu.ifsp.dmo.app11_login.Model.User;

public interface IUserDAO {
    boolean create(User user) throws UserDuplicatedException;
    User findByUsername(String username);
    List<User> findAll();
}
