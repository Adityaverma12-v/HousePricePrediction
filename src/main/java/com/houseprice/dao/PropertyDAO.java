package com.houseprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.houseprice.exception.DatabaseException;
import com.houseprice.model.CommercialProperty;
import com.houseprice.model.IndustrialProperty;
import com.houseprice.model.Property;
import com.houseprice.model.ResidentialProperty;
import com.houseprice.util.DatabaseConnection;

/**
 * Property Data Access Object
 * Implements IRepository interface for Property entities
 * Handles all database operations using JDBC
 */
public class PropertyDAO implements IRepository<Property> {
    private DatabaseConnection dbConnection;

    public PropertyDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    @Override
    public boolean save(Property property) throws DatabaseException {
        String query = "INSERT INTO properties (address, area, bedrooms, bathrooms, year_built, property_type, status) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, property.getAddress());
            pstmt.setDouble(2, property.getArea());
            pstmt.setInt(3, property.getBedrooms());
            pstmt.setInt(4, property.getBathrooms());
            pstmt.setInt(5, property.getYearBuilt());
            pstmt.setString(6, property.getPropertyType());
            pstmt.setString(7, property.getStatus());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error saving property: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Property property) throws DatabaseException {
        String query = "UPDATE properties SET address=?, area=?, bedrooms=?, bathrooms=?, year_built=?, status=? WHERE property_id=?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, property.getAddress());
            pstmt.setDouble(2, property.getArea());
            pstmt.setInt(3, property.getBedrooms());
            pstmt.setInt(4, property.getBathrooms());
            pstmt.setInt(5, property.getYearBuilt());
            pstmt.setString(6, property.getStatus());
            pstmt.setInt(7, property.getPropertyId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error updating property: " + e.getMessage(), e);
        }
    }

    @Override
    public Property findById(int id) throws DatabaseException {
        String query = "SELECT * FROM properties WHERE property_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToProperty(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding property: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Property> findAll() throws DatabaseException {
        List<Property> properties = new ArrayList<>();
        String query = "SELECT * FROM properties";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                properties.add(mapResultSetToProperty(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving properties: " + e.getMessage(), e);
        }
        return properties;
    }

    @Override
    public boolean delete(int id) throws DatabaseException {
        String query = "DELETE FROM properties WHERE property_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting property: " + e.getMessage(), e);
        }
    }

    @Override
    public int count() throws DatabaseException {
        String query = "SELECT COUNT(*) as total FROM properties";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error counting properties: " + e.getMessage(), e);
        }
        return 0;
    }

    private Property mapResultSetToProperty(ResultSet rs) throws SQLException {
        String propertyType = rs.getString("property_type");
        Property property;

        switch (propertyType) {
            case "RESIDENTIAL":
                property = new ResidentialProperty();
                break;
            case "COMMERCIAL":
                property = new CommercialProperty();
                break;
            case "INDUSTRIAL":
                property = new IndustrialProperty();
                break;
            default:
                property = new ResidentialProperty();
        }

        property.setPropertyId(rs.getInt("property_id"));
        property.setAddress(rs.getString("address"));
        property.setArea(rs.getDouble("area"));
        property.setBedrooms(rs.getInt("bedrooms"));
        property.setBathrooms(rs.getInt("bathrooms"));
        property.setYearBuilt(rs.getInt("year_built"));
        property.setStatus(rs.getString("status"));

        return property;
    }
}
