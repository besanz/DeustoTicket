package data.dao;
import java.util.List;


import data.entidades.User;

public interface IUserDAO {
    User findByLoginAndPassword(String login, String password);
    User registerUser(User user);
    User findByEmail(String email);
    void addUser(User user);
    List<User> findAllUsers();
    void deleteUserByDni(String dni);
}
