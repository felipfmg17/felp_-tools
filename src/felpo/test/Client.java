
package felpo.test;


import felpo.tools.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    
    public static void main(String[] args){
        
        try {
           Socket soc = new Socket(InetAddress.getLoopbackAddress(), 4000);
           File[] files = STool.selectFiles(new File("C:\\Users\\Usuario\\Desktop"));
           Tool.write(files, soc.getOutputStream());
           soc.close();
        } catch (Exception e) {e.printStackTrace(); }
        
        
    }
    
}
