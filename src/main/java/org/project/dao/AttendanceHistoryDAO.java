/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.project.dto.AttendanceHistoryDTO;

/**
 *
 * @author Incognito
 */
public interface AttendanceHistoryDAO {
    
    public void insert(AttendanceHistoryDTO dto) throws SQLException;
    
    public Connection getUserConn();
}
