
package felpo.tools;


import java.io.*;
import java.net.*;
import java.util.*;



public class DatagramTeleport {
    
    public static final int PACKET_LENGTH=8*1024;
    
    public DatagramSocket soc;
    public SocketAddress address;
    
    
    public void send(byte[] buf, int offset, int length){
        try {
            DatagramPacket packet=new DatagramPacket(buf, offset, length, address);
            soc.send(packet);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public int receive(byte[] buf){
        try {
            DatagramPacket packet=new DatagramPacket(buf, buf.length);
            soc.receive(packet);
            return packet.getLength();
        } catch (Exception e) {e.printStackTrace();}
        return 0;
    }
    
    public void send(Serializable o){
        try {
            byte[] buf=Tool.objectToBytes(o);
            send(buf,0,buf.length);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public static void sendObject(String ip, int port, Serializable o){
        try {
            byte[] b=Tool.objectToBytes(o);
            InetSocketAddress address=new InetSocketAddress(ip, port);
            DatagramPacket packet=new DatagramPacket(b, b.length, address);
            DatagramSocket soc=new DatagramSocket();
            soc.send(packet);
            soc.close();
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public static Object receiveObject(int port){
        try {
            byte[] b=new byte[PACKET_LENGTH];
            DatagramPacket packet=new DatagramPacket(b, b.length);
            DatagramSocket soc=new DatagramSocket(port);
            soc.receive(packet);
            Object o=Tool.bytesToObject(b);
            soc.close();
            return o;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    
    
}
