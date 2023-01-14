/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew;
import javax.swing.*;  
import java.net.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Client{
   public static void main(String args[]) throws Exception{
   
      Socket soc;
      BufferedImage img = null;
      soc=new Socket("localhost",4000);
      System.out.println("Client is running. ");
      
      try {
      //   System.out.println("Reading image from Url ");
         Scanner in= new Scanner(System.in);
          System.out.println("write Url: ");
        
            String website = in.nextLine();
           // System.out.println("write name of image :");
           // String fileName = in.nextLine() + ".jpg";
            System.out.println( "Start Downloading Image From :URL");
            URL url = new URL(website);
            InputStream inputStream = url.openStream();
           img = ImageIO.read(inputStream);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         
         ImageIO.write(img, "jpg", baos);
         baos.flush();
         
         byte[] bytes = baos.toByteArray();
         baos.close();
         
         System.out.println("Sending image to server. ");
         
         OutputStream out = soc.getOutputStream(); 
         DataOutputStream dos = new DataOutputStream(out);
         
         dos.writeInt(bytes.length);
         dos.write(bytes, 0, bytes.length);
         
         System.out.println("Image sent to server. ");

         dos.close();
         out.close();
         
      } catch (Exception e) {
         System.out.println("Exception: " + e.getMessage());
         soc.close();
      }
      soc.close();
   }
}
