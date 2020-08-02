/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.project.device;

import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.project.dto.AttendanceHistoryDTO;
import org.project.jdbc.AttendanceHistDaoImpl;

/**
 *
 * @author Incognito
 */
public class DeviceData {
    
    
    //Do not know where to save the information 
    //So just return the List<String> (in case if we will need another file) 
    public static String getData(String port) {
        
        //Serial Communication to COM3 port
        SerialPort sp = SerialPort.getCommPort(port);
        
        //Data length 8 bits, parity -NONE (This is default configurations actually)
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        
        //Check for port availability
        if(!sp.openPort()){
            sp.closePort();
            return "Device is not connected or it is already in Use!!!";
        }
        
        try{
            //This should be modified to length of smart card 
            //which will be given
            byte[] b = new byte [14];
            for(int i=0;sp.openPort();){

                if(i==b.length-1){
                    String str = new String(b);
                    i=0;
                    List<String> entity = new ArrayList<>();
                    
                    //1.The RFID
                    entity.add(str);
                 /*   
                    //2.The date and time
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();  
                    entity.add(formatter.format(date));
                 */
                    AttendanceHistoryDTO dto = new AttendanceHistoryDTO();
                    dto.setRfId(str);
                    
                    AttendanceHistDaoImpl impl = new AttendanceHistDaoImpl();
                    impl.insert(dto);
                    sp.closePort();
                    return "The RFID is added!";
                }else{
                    
                    b[i] = (byte) sp.getInputStream().read();
                    //If bytes equals to 13 and 10 
                    //Then they are "\t" and "\n" accordingly
                    //So we do not need them
                    if(b[i]!=13 && b[i]!=10){
                        i++;
                    }
                }
            }
        }catch(IOException ex){
            sp.closePort();
            return "Device has been ejected!!!";
        }catch(SQLException sqle){
            sp.closePort();
            return "Error with connection to the database!!!";
        }
        sp.closePort();
        return "Device Turned Off.";
    }
    
}