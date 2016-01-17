package felpo.tools;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tool {
    
    public static byte[] hexEncoder(byte[] m){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m.length;i++)
            sb.append(String.format("%02X",m[i]));
        return sb.toString().getBytes();
    }
    
    public static byte[] hexDecoder(byte[] h){
        String s=new String(h);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        for(int i=0;i<s.length();i+=2){
            int n=Integer.valueOf(s.substring(i,i+2), 16);
            bos.write((byte)n);
        }
        byte[] b=bos.toByteArray();
        return b;
    }

    public static byte[] objectToBytes(Serializable o) throws IOException {
        ByteArrayOutputStream bos= new  ByteArrayOutputStream();
        ObjectOutputStream oos= new ObjectOutputStream(bos);
        oos.writeObject(o);
        oos.flush();
        byte[] b=bos.toByteArray();
        oos.close();
        bos.close();
        return b;
    }
    
    public static Object bytesToObject(byte[] b) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis=new ByteArrayInputStream(b);
        ObjectInputStream ois=new ObjectInputStream(bis);
        Object o=ois.readObject();
        ois.close();
        bis.close();
        return o;
    }
    
    public static void bytesToFile(byte[] b, File f) throws IOException{
        FileOutputStream fos=new FileOutputStream(f);
        fos.write(b);
        fos.flush();
        fos.close();
    }
    
    public static byte[] fileToBytes(File f) throws IOException {
        FileInputStream fis=new FileInputStream(f);
        byte[] buf=new byte[(int)f.length()];
        fis.read(buf);
        fis.close();
        return buf;
    }
    
    public static void objectToFile(Serializable o, File f)  throws IOException, ClassNotFoundException {
        byte[] bytes=objectToBytes(o);
        bytesToFile(bytes, f);  
    }
    
    public static Object fileToObject(File f)  throws IOException, ClassNotFoundException {
        byte[] bytes=fileToBytes(f);
        return bytesToObject(bytes);
    }
    
    public static void objectToHexFile(Serializable o,File f) throws IOException, ClassNotFoundException {
        byte[] bytes=objectToBytes(o);
        byte[] hex=hexEncoder(bytes);
        bytesToFile(hex, f);
    }
    
    public static Object hexFileToObject(File f) throws IOException, ClassNotFoundException{
        byte[] hex=fileToBytes(f);
        byte[] bytes=hexDecoder(hex);
        return bytesToObject(bytes);
    }
    
    public static void bytesToHexFile(byte[] s, File f) throws IOException {
        byte[] hex=hexEncoder(s);
        bytesToFile(hex, f);       
    }
    
    public static byte[] hexFileToBytes(File f) throws IOException {
        byte[] hex=fileToBytes(f);
        return hexDecoder(hex);
    }
    
    public static byte[] intTobytes(int n){
        byte[] b = {0,0,0,0};
        for(int i=0;i<4;i++)
            b[3-i] = (byte)( ((255 << (8*i) ) & n) >>> (8*i) );           
        return b;
    }
    
    public static int bytesToInt(byte[] b){
        int n=0;
        for(int i=0;i<4;i++)
            n = (n << 8 ) |  (b[i]&(int)255) ;      
        return n;
    }
      
    public static void streamCopy(InputStream in, OutputStream out, long n) throws IOException{
        byte[] buf=new byte[64*1024];
        int b; 
        while(true){
            b=in.read(buf);
            if(b>0){
                out.write(buf,0,b);
                out.flush();
            }
            n-=b;
            if(n<=0) 
                break;
        }
        out.flush();
    }

    public static void fileCopy(File f1, File f2) throws IOException {
        FileInputStream in=new FileInputStream(f1);
        FileOutputStream out=new FileOutputStream(f2);
        streamCopy(in, out, f1.length());
        in.close();
        out.close();
    }
    
    public static void sendFile(File f, ObjectOutputStream out){
        try {
            out.writeObject(f.getName());
            out.flush();
            out.writeLong(f.length());
            out.flush();
            FileInputStream in=new FileInputStream(f);
            streamCopy(in, out, f.length() );
            out.flush();
            in.close();
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public static void receiveFile(File path, ObjectInputStream in){
        try {
            String name=(String)in.readObject();
            long length=in.readLong();
            File f=new File(path,name);
            FileOutputStream out=new FileOutputStream(f);
            streamCopy(in, out, length);
            out.flush();
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
