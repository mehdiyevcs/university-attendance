/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.project.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.project.dao.AttendanceHistoryDAO;
import org.project.dto.AttendanceHistoryDTO;

/**
 *
 * @author Incognito
 */
public class AttendanceHistDaoImpl implements AttendanceHistoryDAO{

    private java.sql.Connection userConn;
    private final String tableName="ATTENDANCE_HISTORY";
    
    protected String SQL_INSERT = "insert into "+this.tableName+" values(?,?,current_time())";
    
    
    @Override
    public void insert(AttendanceHistoryDTO dto) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            
            conn = (userConn!=null) ? this.userConn : ResourceManager.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, dto.getMacAddress());
            stmt.setString(2, dto.getRfId());
            
            stmt.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            ResourceManager.close(stmt);
            
            if(this.userConn!=null){
                ResourceManager.close(stmt);
            }
        }
        
        
    }

    @Override
    public Connection getUserConn() {
        return userConn;
    }
    
}
