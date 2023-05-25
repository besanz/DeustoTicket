package data.dao;

import java.util.List;

import data.entidades.User;

public interface IUserDAO {

    /**
     * Buscar un usuario por su correo electrónico y contraseña.
     * @param email: Correo electrónico del usuario.
     * @param password: Contraseña del usuario.
     * @return: Objeto de tipo User que representa al usuario encontrado.
     */
    User findByLoginAndPassword(String email, String password);

    /**
     * Registrar un nuevo usuario.
     * @param user: Objeto de tipo User que se desea registrar.
     * @return: Objeto de tipo User que representa al usuario registrado.
     */
    User registerUser(User user);

    /**
     * Buscar un usuario por su correo electrónico.
     * @param email: Correo electrónico del usuario.
     * @return: Objeto de tipo User que representa al usuario encontrado.
     */
    User findByEmail(String email);

    /**
     * Agregar un nuevo usuario.
     * @param user: Objeto de tipo User que se desea agregar.
     */
    void addUser(User user);

    /**
     * Recuperar una lista de todos los usuarios.
     * @return: Lista de objetos de tipo User que representan a todos los usuarios registrados.
     */
    List<User> findAllUsers();

    /**
     * Eliminar un usuario por su número de identificación (DNI).
     * @param dni: Número de identificación del usuario a eliminar.
     */
    void deleteUserByDni(String dni);

}
