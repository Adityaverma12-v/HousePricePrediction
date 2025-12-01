package com.houseprice.dao;

import java.util.List;

/**
 * Generic Repository Interface (Generics)
 * Provides CRUD operations for any entity type
 */
public interface IRepository<T> {
    /**
     * Save an entity
     */
    boolean save(T entity) throws Exception;

    /**
     * Update an entity
     */
    boolean update(T entity) throws Exception;

    /**
     * Find entity by ID
     */
    T findById(int id) throws Exception;

    /**
     * Get all entities
     */
    List<T> findAll() throws Exception;

    /**
     * Delete entity by ID
     */
    boolean delete(int id) throws Exception;

    /**
     * Count total entities
     */
    int count() throws Exception;
}
