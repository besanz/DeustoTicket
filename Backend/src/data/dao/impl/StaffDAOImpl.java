// StaffDAOImpl.java
package data.dao.impl;

import data.DBConfig;
import data.dao.StaffDAO;
import data.entidades.Staff;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class StaffDAOImpl implements StaffDAO {
    private static StaffDAOImpl instance;

    // Constructor privado
    private StaffDAOImpl() {
    }

    // Método estático para obtener la instancia de la clase
    public static StaffDAOImpl getInstance() {
        if (instance == null) {
            instance = new StaffDAOImpl();
        }
        return instance;
    }

    @Override
    public Staff findByUsernameAndPassword(String username, String password) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Staff staff = null;
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query<Staff> query = pm.newNamedQuery(Staff.class, "findByUsernameAndPassword");
            query.setFilter("username == u && password == p");
            query.declareParameters("String u, String p");
            staff = (Staff) query.execute(username, password);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return staff;
    }
}
