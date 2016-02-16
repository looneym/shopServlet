package shopservlet;

import java.io.*;
import java.net.*;
import java.nio.CharBuffer;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;



public class Shop
{
 public static void main1 (String args[]) 
  {
   String requestMessageLine;
   String command;
   Products products = new Products();
	Cart theCart = new Cart();
	Gson gson = new Gson();
try{
   // check if a port number is given as the first command line argument
   // if not argument is given, use port number 6789
   int myPort = 8001;
   if (args.length > 0)
     {
      try {
	   myPort = Integer.parseInt(args[0]);
	  } 
      catch (ArrayIndexOutOfBoundsException e) 
          {
	   System.out.println("Need port number as argument");
	   System.exit(-1);
	  } 
      catch (NumberFormatException e) 
          {
	   System.out.println("Please give port number as integer.");
	   System.exit(-1);
	  }
     }

   // set up connection socket
   ServerSocket listenSocket = new ServerSocket (myPort);
   boolean options = false;
   BufferedReader inFromClient=null; 
   BufferedOutputStream outToClient=null;
   Socket connectionSocket=null;
   while (true){
   // listen (i.e. wait) for connection request
   System.out.println ("Web server waiting for request on port " + myPort);
   if (!options){
	   connectionSocket = listenSocket.accept();

   // set up the read and write end of the communication socket
   inFromClient = new BufferedReader (new InputStreamReader(connectionSocket.getInputStream()));
   outToClient = new BufferedOutputStream (
                 connectionSocket.getOutputStream());
   }
   options=false;
   // retrieve first line of request and set up for parsing
   requestMessageLine = inFromClient.readLine();
   System.out.println ("Request: " + requestMessageLine);
   StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

   // check for GET request
   String method = tokenizedLine.nextToken();
   System.out.println("method "+method);
   if (method.equals("OPTIONS")){
	   options = true;
	   System.out.println("options");
	   outToClient.write ("HTTP/1.0 200 Document Follows\r\n".getBytes());
       
       
	   outToClient.write( "Access-Control-Allow-Origin: *\r\n".getBytes() ); 
	   outToClient.write( "Access-Control-Allow-Methods: POST, GET, OPTIONS\r\n".getBytes() );
	   outToClient.write("Access-Control-Allow-Headers: X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept\r\n".getBytes());
	   
       outToClient.write ("\r\n".getBytes());
       outToClient.flush();
	   
	   }
		   
   else
   //if (method.equals("GET")||method.equals("POST"))
     {
      command = tokenizedLine.nextToken();
     String Data="";
      // remove leading slash from line if exists
      if (command.startsWith("/") == true)
          command = command.substring(1);
      System.out.println("command "+command);
      requestMessageLine = inFromClient.readLine();
      while (requestMessageLine.length() >= 5)
         {
          System.out.println ("Request: " + requestMessageLine);
          requestMessageLine = inFromClient.readLine();
         }
      char[] cb = new char[300];
      if (inFromClient.ready())
      inFromClient.read(cb);
      System.out.println ("Request: " + requestMessageLine);
      if (command.equals("getProducts")){
    	 String xx=gson.toJson(products.getProducts());
    	 System.out.println("xx "+xx);
    	 outToClient.write ("HTTP/1.0 200 Document Follows\r\n".getBytes());
    	 outToClient.write("content-type: application/json\r\n".getBytes());
    	 outToClient.write( "Access-Control-Allow-Origin: *\r\n".getBytes() );
         outToClient.write (("Content-Length: " + xx.length() + "\r\n").getBytes());
         outToClient.write ("\r\n".getBytes());
    	 outToClient.write (xx.getBytes());
      }
      else
      if (command.equals("getCart")){
     	 String xx=gson.toJson(theCart.getCart());
     	 System.out.println("xx "+xx);
     	 outToClient.write ("HTTP/1.0 200 Document Follows\r\n".getBytes());
     	 outToClient.write("content-type: application/json\r\n".getBytes());
     	 outToClient.write( "Access-Control-Allow-Origin: *\r\n".getBytes() );
          outToClient.write (("Content-Length: " + xx.length() + "\r\n").getBytes());
          outToClient.write ("\r\n".getBytes());
     	 outToClient.write (xx.getBytes());
       }
      else
    	  if (command.compareTo("addToCart")==0 ){

    		  Data = new String(cb);
    		  System.out.println("data="+Data);
    		  JsonReader reader = new JsonReader(new StringReader(Data));
    		  reader.setLenient(true);
    		  addToCartMessage m = gson.fromJson(reader, addToCartMessage.class); 
    		  System.out.println("got addToCart"+ " mess "+m);
    		  Product p = products.getProduct(m.getProdid());
    		  if (p!=null){
    			  theCart.add(m.getQty(),p);
    		  }
    		  String xx =" ";
    		  outToClient.write ("HTTP/1.0 200 Document Follows\r\n".getBytes());
    		  outToClient.write("content-type: application/json\r\n".getBytes());
    		  outToClient.write( "Access-Control-Allow-Origin: *\r\n".getBytes() );
    		  outToClient.write (("Content-Length: " + xx.length() + "\r\n").getBytes());
    		  outToClient.write ("\r\n".getBytes());
    		  outToClient.write (xx.getBytes());     

    	  }
      // Send reply 

      outToClient.flush();
      // read and print out the rest of the request

      if (!options)
    	  connectionSocket.close();
     }
   //else
    // {
     // System.out.println ("Bad Request Message");
    // }
   }
}catch(Exception e){
	e.printStackTrace();
}
  }
}

      
          

