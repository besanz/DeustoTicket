package data.dao.impl;

import data.dao.UserDAO;
import data.entidades.User;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;
    private List<User> users;

    // Constructor privado
    private UserDAOImpl() {
        // Carga los usuarios desde la base de datos o desde un archivo
    }

    // Método estático para obtener la instancia de la clase
    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return users.stream()
            .filter(user -> user.getEmail().equals(login) && user.getPassword().equals(password))
            .findFirst()
            .orElse(null);
    }
}

