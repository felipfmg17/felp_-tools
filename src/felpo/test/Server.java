
package felpo.test;

import felpo.tools.DatagramTeleport;
import felpo.tools.Gui;
import felpo.tools.Teleport;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args){
        receiveObjectDatagram();
    }
    
    public static void receiveFileTest(){
        try {
           File path=Gui.selectDirectory(null);
           Teleport tele=Teleport.getTeleport(4010);
           tele.receiveFiles(path);
           tele.close();
            
        } catch (Exception e) {e.printStackTrace(); }
    }
    
    public static void receiveObjectDatagram(){
        String s=(String)DatagramTeleport.receiveObject(3000);
        System.out.println(s);
    }
}
