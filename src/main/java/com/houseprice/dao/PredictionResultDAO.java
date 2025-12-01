package com.houseprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.houseprice.exception.DatabaseException;
import com.houseprice.model.PredictionResult;
import com.houseprice.util.DatabaseConnection;

/**
 * Prediction Result Data Access Object
 * Implements IRepository interface for PredictionResult entities
 */
public class PredictionResultDAO implements IRepository<PredictionResult> {
    private DatabaseConnection dbConnection;

    public PredictionResultDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    @Override
    public boolean save(PredictionResult result) throws DatabaseException {
        String query = "INSERT INTO prediction_results (property_id, predicted_price, actual_price, accuracy, algorithm, status, prediction_date) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, result.getPropertyId());
            pstmt.setDouble(2, result.getPredictedPrice());
            pstmt.setDouble(3, result.getActualPrice());
            pstmt.setDouble(4, result.getAccuracy());
            pstmt.setString(5, result.getAlgorithm());
            pstmt.setString(6, result.getStatus());
            pstmt.setTimestamp(7, Timestamp.valueOf(result.getPredictionDate()));
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error saving prediction result: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(PredictionResult result) throws DatabaseException {
        String query = "UPDATE prediction_results SET actual_price=?, accuracy=?, status=? WHERE prediction_id=?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDouble(1, result.getActualPrice());
            pstmt.setDouble(2, result.getAccuracy());
            pstmt.setString(3, result.getStatus());
            pstmt.setInt(4, result.getPredictionId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error updating prediction result: " + e.getMessage(), e);
        }
    }

    @Override
    public PredictionResult findById(int id) throws DatabaseException {
        String query = "SELECT * FROM prediction_results WHERE prediction_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToPredictionResult(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding prediction result: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<PredictionResult> findAll() throws DatabaseException {
        List<PredictionResult> results = new ArrayList<>();
        String query = "SELECT * FROM prediction_results";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                results.add(mapResultSetToPredictionResult(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving prediction results: " + e.getMessage(), e);
        }
        return results;
    }

    @Override
    public boolean delete(int id) throws DatabaseException {
        String query = "DELETE FROM prediction_results WHERE prediction_id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting prediction result: " + e.getMessage(), e);
        }
    }

    @Override
    public int count() throws DatabaseException {
        String query = "SELECT COUNT(*) as total FROM prediction_results";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error counting prediction results: " + e.getMessage(), e);
        }
        return 0;
    }

    private PredictionResult mapResultSetToPredictionResult(ResultSet rs) throws SQLException {
        PredictionResult result = new PredictionResult();
        result.setPredictionId(rs.getInt("prediction_id"));
        result.setPropertyId(rs.getInt("property_id"));
        result.setPredictedPrice(rs.getDouble("predicted_price"));
        result.setActualPrice(rs.getDouble("actual_price"));
        result.setAccuracy(rs.getDouble("accuracy"));
        result.setAlgorithm(rs.getString("algorithm"));
        result.setStatus(rs.getString("status"));
        result.setPredictionDate(rs.getTimestamp("prediction_date").toLocalDateTime());
        return result;
    }
}
