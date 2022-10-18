import java.io.DataInputStream; 
import java.io.DataOutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;



public class Client
  {	public static void main(String args[])
        {  int serverPort = 35786; // this is customized port number which this server will accept client connections
           String serverName = "localhost";
           System.setProperty("javax.net.ssl.trustStore","myKeyStore.jks"); 
           System.setProperty("javax.net.ssl.trustStorePassword", "kibrom55");
            //System.setProperty("javax.net.debug","all");  //if we want to see the detail how it debug the connection
           
         try {  SSLSocketFactory sslSocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
          		 SSLSocket sslSocket = (SSLSocket)sslSocketfactory.createSocket(serverName, serverPort );
         		 DataOutputStream outputStream = new DataOutputStream(sslSocket.getOutputStream());
          		 DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());
          		 System.out.println(inputStream.readUTF());
          	
          	    while(true)
          			{	System.out.println("write a Message : ");
          				String messageToSend = System.console().readLine();
          				outputStream.writeUTF(messageToSend);
          				System.err.println(inputStream.readUTF());
          				if(messageToSend.equals("close"))
          		 			{   break ; } 
	                } 
      		 }
        catch(Exception ex)
        		{ System.err.println("Error Happend : " + ex.toString()); } 
      } 
  }
