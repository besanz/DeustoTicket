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

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public void addUser(User user) {
    PersistenceManager pm = DBConfig.getPersistenceManager();
        try {
            pm.makePersistent(user);
        } finally {
            pm.close();
        }
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

    public User findByEmail(String email) {
        User user = null;
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            Query<User> query = pm.newNamedQuery(User.class, "findByEmail");
            query.setUnique(true);
            user = (User) query.execute(email);

            if (user != null) {
                System.out.println("UserDAO: User found by email.");
            } else {
                System.out.println("UserDAO: User not found by email.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("UserDAO: Exception occurred while finding user by email.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return user;
    }

    public User registerUser(User user) {
        if (findByEmail(user.getEmail()) != null) {
            System.err.println("UserDAO: A user with this email already exists.");
            return null;
        }

        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.makePersistent(user);
            tx.commit();
            System.out.println("UserDAO: User registered successfully.");
        } catch (Exception e) {
            System.err.println("UserDAO: Exception occurred while registering user.");
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

