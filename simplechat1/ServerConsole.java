import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.ChatClient;
import common.ChatIF;

public class ServerConsole implements ChatIF  {
    //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
    //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient user;


  public ServerConsole(String host, int port) 
  {
    try 
    {
      user = new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
  }

public void accept() {
    try
    {
      BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true) 
      {
        message = fromConsole.readLine();
        user.handleMessageFromClientUI("Server MSG>" + message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

    @Override
    public void display(String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
    public static void main(String[] args) {
    String host = "";
    int port = 0;  //The port number
    try{//this finds he port that is is being used and if it can't find it, it uses thh default port number(5555) Changed for E49 
      port = Integer.parseInt(args[0]); //Get port from command line Changed for E49
    }
    catch(Throwable t){
      port = DEFAULT_PORT;
    }
    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    ClientConsole chat= new ClientConsole(host, port);
    chat.accept();  //Wait for console data
    
    
  }
}
