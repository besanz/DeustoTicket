// UserDAOImpl.java
package data.dao.impl;

import data.DBConfig;
import data.dao.UserDAO;
import data.entidades.User;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;

    // Constructor privado
    private UserDAOImpl() {
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