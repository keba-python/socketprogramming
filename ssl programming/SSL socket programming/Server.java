import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
public class Server
  {	public static void main(String args[])
      {    int port = 35786; // the port number which this server accept client connection           
           System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");
           System.setProperty("javax.net.ssl.keyStorePassword", "kibrom55");
           //System.setProperty("javax.net.debug","all");
           
                  
      try {	SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
          	SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
          	System.out.println("Echo server started and Ready to accept client connection");
          	SSLSocket sslSocket = (SSLSocket)sslServerSocket.accept();
          	DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());
          	DataOutputStream outputStream = new DataOutputStream( sslSocket.getOutputStream());
          	outputStream.writeUTF("Hello client, say something!");
          	
          	while(true)
          	{	String recivedMessage = inputStream.readUTF();
          		System.out.println(" client said : " + recivedMessage);
          		 if ( recivedMessage.equals("reply") )
          		  	{  
          		  		String messageTosend = System.console().readLine();
          		  		outputStream.writeUTF(messageTosend);
          		  		System.err.println(inputStream.readUTF());
          		  		}
          		 if(recivedMessage.equals("close"))
          				{  outputStream.writeUTF("Bye");
          		  			outputStream.close();
          		  			inputStream.close();
          		  			sslSocket.close();
          		  			sslServerSocket.close();
          		  			break ;    }
          		
          		  	else {outputStream.writeUTF("YOU Said : " + recivedMessage);}          		  
          	}
         }
    catch(Exception ex){ System.err.println("Error Happend : " + ex.toString());}
        	
     }
  }
