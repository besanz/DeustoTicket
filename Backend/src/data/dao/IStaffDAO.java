package data.dao;

import data.entidades.Staff;

public interface IStaffDAO {
    /**
     * Buscar un miembro del personal por su nombre de usuario y contraseña.
     * @param username: Nombre de usuario del miembro del personal.
     * @param password: Contraseña del miembro del personal.
     * @return: Objeto de tipo Staff que representa al miembro del personal encontrado.
     */
    Staff findByUsernameAndPassword(String username, String password);

    /**
     * Buscar un miembro del personal por su nombre de usuario.
     * @param username: Nombre de usuario del miembro del personal.
     * @return: Objeto de tipo Staff que representa al miembro del personal encontrado.
     */
    Staff findByUsername(String username);

    /**
     * Agregar un nuevo miembro del personal.
     * @param staff: Objeto de tipo Staff que se desea agregar.
     */
    void addStaff(Staff staff);

}

