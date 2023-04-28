package data.dao;

import data.entidades.Staff;

public interface StaffDAO {
    Staff findByUsernameAndPassword(String username, String password);
}
