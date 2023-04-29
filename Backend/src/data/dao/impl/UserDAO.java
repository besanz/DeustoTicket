package data.dao.impl;

import data.DBConfig;
import data.dao.IUserDAO;
import data.entidades.User;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static UserDAO instance;

    // Private constructor
    private UserDAO() {
    }

    // Static method to get the instance of the class
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User findByLoginAndPassword(String email, String password) {
        User user = null;
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            Query<User> query = pm.newNamedQuery(User.class, "findByLoginAndPassword");
            List<User> results = (List<User>) query.execute(email, password);

            if (!results.isEmpty()) {
                user = results.get(0);
                System.out.println("UserDAO: User found in the database.");
            } else {
                System.out.println("UserDAO: User not found in the database.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("UserDAO: Exception occurred while finding user by email and password.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return user;
    }
}
