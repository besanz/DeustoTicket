package data.dao;

import data.entidades.User;

public interface IUserDAO {
    User findByLoginAndPassword(String login, String password);
}
