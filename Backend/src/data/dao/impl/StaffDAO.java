// StaffDAO.java
package data.dao.impl;

import data.DBConfig;
import data.dao.IStaffDAO;
import data.entidades.Staff;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.List;

public class StaffDAO implements IStaffDAO {
    private static StaffDAO instance;

    private StaffDAO() {
    }

    public static StaffDAO getInstance() {
        if (instance == null) {
            instance = new StaffDAO();
        }
        return instance;
    }

    public void addStaff(Staff staff) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        try {
            pm.makePersistent(staff);
        } finally {
            pm.close();
        }
    }

    @Override
    public Staff findByUsernameAndPassword(String username, String password) {
        Staff staff = null;
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            Query<Staff> query = pm.newNamedQuery(Staff.class, "findByUsernameAndPassword");
            query.setFilter("username == u && password == p");
            query.declareParameters("String u, String p");
            List<Staff> results = (List<Staff>) query.execute(username, password);

            if (!results.isEmpty()) {
                staff = results.get(0);
                System.out.println("StaffDAO: Staff found in the database.");
            } else {
                System.out.println("StaffDAO: Staff not found in the database.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("StaffDAO: Exception occurred while finding staff by username and password.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return staff;
    }

    public Staff findByUsername(String username) {
        Staff staff = null;
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            Query<Staff> query = pm.newNamedQuery(Staff.class, "findByUsername");
            query.setUnique(true);
            staff = (Staff) query.execute(username);

            if (staff != null) {
                System.out.println("StaffDAO: Staff found by username.");
            } else {
                System.out.println("StaffDAO: Staff not found by username.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("StaffDAO: Exception occurred while finding staff by username.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return staff;
    }

    public Staff registerStaff(Staff staff) {
        if (findByUsername(staff.getUsername()) != null) {
            System.err.println("StaffDAO: A staff with this email already exists.");
            return null;
        }

        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.makePersistent(staff);
            tx.commit();
            System.out.println("StaffDAO: Staff registered successfully.");
        } catch (Exception e) {
            System.err.println("StaffDAO: Exception occurred while registering staff.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return staff;
    }
}
