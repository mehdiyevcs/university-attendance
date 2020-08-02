/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.project.mac;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author Incognito
 */
public class MacAdress {
    
    //CMD command for getting physaical devices mac addresses
    /*
    wmic path Win32_NetworkAdapter where "PNPDeviceID like '%PCI%' 
    AND AdapterTypeID='0'" get name, MacAddress
    */
    
    public static String getNetworkMacAddress(){
        
        //For network adapter
        InetAddress ip;
        StringBuilder sb = null;
        
        try{
            
            //Getting the ip address
            //And on its base looking for macAddress
            ip=InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip); 
            byte[] mac = network.getHardwareAddress();
            
            //Building macAddress from bytes
            sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
                
        }catch(UnknownHostException ex){
            ex.printStackTrace();
        }catch(SocketException ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
}
