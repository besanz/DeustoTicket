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

    // Private constructor
    private StaffDAO() {
    }

    // Static method to get the instance of the class
    public static StaffDAO getInstance() {
        if (instance == null) {
            instance = new StaffDAO();
        }
        return instance;
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
}
