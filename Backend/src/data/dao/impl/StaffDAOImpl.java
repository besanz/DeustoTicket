package data.dao.impl;

import data.dao.StaffDAO;
import data.entidades.Staff;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {
    private static StaffDAOImpl instance;
    private List<Staff> staffs;

    // Constructor privado
    private StaffDAOImpl() {
        // Carga los staff desde la base de datos o desde un archivo
        staffs = new ArrayList<>();
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
        return staffs.stream()
                .filter(staff -> staff.getUsername().equals(username) && staff.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}