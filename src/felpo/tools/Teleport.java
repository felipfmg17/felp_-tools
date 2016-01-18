package felpo.tools;

import java.io.*;
import java.net.*;
import java.util.*;

public class Teleport {
    
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public Socket soc;
    
    
    public  void sendFile(File f){
        Tool.sendFile(f, out);
    }
    
    public void receiveFile(File path){
        Tool.receiveFile(path, in);
    }
    
    public void sendFiles(File[] files){
        try {
            out.writeInt(files.length);
            for(File f:files)
                sendFile(f);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public void receiveFiles(File path){
        try {
            int n=in.readInt();
            for(int i=0;i<n;i++)
                receiveFile(path);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public void close(){
        try {
            out.close();
            in.close();
            soc.close();
        } catch (Exception e) {e.printStackTrace();}
    }


    
    /*Use on client*/
    public static Teleport getTeleport(Socket soc){
        try {
            Teleport tele=new Teleport();
            tele.soc=soc;
            tele.out=new ObjectOutputStream(tele.soc.getOutputStream());
            tele.in=new ObjectInputStream(tele.soc.getInputStream());
            return tele;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }

    /*Use on Client*/
    public static Teleport getTeleport(String ip, int port){
        try {      
            return getTeleport(new Socket(ip,port));    
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    
    /*Use on Server*/
    public static Teleport getTeleport(int port){
        try {
            ServerSocket server=new ServerSocket(port);
            Teleport tele=getTeleport(server);
            server.close();
            return tele;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    
    /*Use on Server*/
    public static Teleport getTeleport(ServerSocket server){
        try {
            Teleport tele=new Teleport();
            tele.soc=server.accept();
            tele.in=new ObjectInputStream(tele.soc.getInputStream());
            tele.out=new ObjectOutputStream(tele.soc.getOutputStream());
            return tele;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    
    
    
    public static void sendObject(String ip, int port, Serializable o)  {
        try {
            Socket soc=new Socket(ip,port);
            ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
            out.writeObject(o);
            out.flush();
            out.close();
            soc.close();
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public static Object receiveObject(int port)  {
        try {
            ServerSocket server=new ServerSocket(port);
            Socket soc=server.accept();
            server.close();
            ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
            Object o=in.readObject();
            in.close();
            soc.close();
            return o;
        } catch (Exception e) {e.printStackTrace(); }
        return null;
    }
    
   
}
