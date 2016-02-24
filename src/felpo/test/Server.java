
package felpo.test;

import felpo.tools.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args){

        try {
           ServerSocket server = new ServerSocket(4000);
           Socket soc= server.accept();
           File path = new File("C:\\Users\\Usuario\\Desktop\\test");
           Tool.readFiles(path, soc.getInputStream());
           soc.close();
        } catch (Exception e) {e.printStackTrace();}
        
        
    }
    
    
}
