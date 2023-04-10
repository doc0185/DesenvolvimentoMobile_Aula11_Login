package br.edu.ifsp.dmo.app11_login.DAO;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.app11_login.Exception.UserDuplicatedException;
import br.edu.ifsp.dmo.app11_login.Model.User;

public class UserDAOImpl implements IUserDAO{
    private List<User> dataset;
    private static UserDAOImpl instance = null;

    private UserDAOImpl(){
        dataset = new ArrayList<>();
    }

    public static UserDAOImpl getInstance(){
        if (instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean create(User user) throws UserDuplicatedException {
        if(user != null){
            User inDataset = dataset.stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).findAny().orElse(null);
            // daria para usar o mesmo de baixo;
            if(inDataset != null){
                throw new UserDuplicatedException(user.getUsername());
            }
            dataset.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return dataset.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return dataset;
    }
}
