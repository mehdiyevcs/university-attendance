/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.project.dto;

import java.util.Date;
import org.project.mac.MacAdress;


/**
 *
 * @author Incognito
 */
public class AttendanceHistoryDTO {
    
    private final String macAddress=MacAdress.getNetworkMacAddress();
    private String rfId;

    public AttendanceHistoryDTO() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    @Override
    public String toString() {
        return "AttendanceHistory{" + "macAddress=" + macAddress + ", rfId=" + rfId + '}';
    }
    
}
