package com.gcu.data;

import java.util.List;

/**
 * Data access interface for generic type T.
 *
 * @param <T> the type of objects that this interface handles
 */
public interface DataAccessInterface<T> {

    /**
     * Finds all objects.
     *
     * @return a list of all objects
     */
    public List<T> findAll();

    /**
     * Finds an object by its ID.
     *
     * @param id the ID of the object
     * @return the object with the specified ID
     */
    public T findById(int id);

    /**
     * Creates a new object.
     *
     * @param t the object to create
     * @return true if creation is successful, false otherwise
     */
    public boolean create(T t);

    /**
     * Updates an object.
     *
     * @param t the object to update
     * @return true if update is successful, false otherwise
     */
    public boolean update(T t);

    /**
     * Deletes an object.
     *
     * @param t the object to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean delete(T t);
}