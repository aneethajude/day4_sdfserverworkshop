package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public final class App {
    private App() {
    }

    
    public static void main(String[] args) throws IOException {
        
        String dirPath ="data2";

        File newDirectory = new File(dirPath);

        if(newDirectory.exists()){
            System.out.println("Directory alredy exists");
        }else{
            newDirectory.mkdir();
        }

       //Cookie cookie = new Cookie();
        //cookie.readCookieFile();
       // cookie.showCookies();

      ServerSocket ss = new ServerSocket(7000);
      while(true){
      Socket s = ss.accept();
      String msgReceived ="";

      InputStream is = s.getInputStream();
      BufferedInputStream bis = new BufferedInputStream(is);
      DataInputStream dis = new DataInputStream(bis);
        
      OutputStream os = s.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);
      
      do{
        msgReceived = dis.readUTF();
        System.out.println(msgReceived);
         //   msgReceived = dis.readUTF();
        if(msgReceived.equalsIgnoreCase("get-Cookie")){
          Cookie cookie = new Cookie();
          cookie.readCookieFile();

            String cookieValue = cookie.returnCookie();
            System.out.println(cookieValue);
                dos.writeUTF(cookieValue);
                dos.flush();
            }
        }while((!msgReceived.equals("close")));
      }}
        //s.close();
        //ss.close();
      }
      
      
    
