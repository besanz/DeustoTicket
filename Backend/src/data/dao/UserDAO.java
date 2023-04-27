package data.dao;

import data.entidades.User;

public interface UserDAO {
    User findByLoginAndPassword(String login, String password);
}
