// IUserDAO.java
package data.dao.impl;

import data.DBConfig;
import data.dao.IUserDAO;
import data.entidades.User;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class UserDAO implements IUserDAO {
    private static UserDAO instance;

    // Constructor privado
    private UserDAO() {
    }

    // Método estático para obtener la instancia de la clase
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        User user = null;
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query<User> query = pm.newNamedQuery(User.class, "findByLoginAndPassword");
            query.setFilter("email == e && password == p");
            query.declareParameters("String e, String p");
            user = (User) query.execute(login, password);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return user;
    }
}