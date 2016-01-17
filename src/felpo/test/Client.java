
package felpo.test;


import felpo.tools.DatagramTeleport;
import felpo.tools.Gui;
import felpo.tools.Teleport;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    
    public static void main(String[] args){
        sendObjectDatagram();
    }
    
    
    public static void sendFileTest(){
        File[] files=Gui.selectFiles(null);
        Teleport tele=Teleport.getTeleport("localhost",4010);
        tele.sendFiles(files);
        tele.close();

//            File f=STool.selectFile(null);
//            Teleport tele=Teleport.getTeleport("localhost", 5000);
//            tele.sendFile(f);
    }
    
    public static void sendObjectDatagram(){
        DatagramTeleport.sendObject("localhost", 3000, "hola soy felipe en udp");
    }
}
