package data.dao;

import data.entidades.Staff;

public interface IStaffDAO {
    Staff findByUsernameAndPassword(String username, String password);
}
