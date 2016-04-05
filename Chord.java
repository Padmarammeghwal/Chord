import java.io.*;
import java.net.*;
import java.util.*;
public class Chord
{
	static int i,id,port;

	public static void main(String[] args) 
	{

		//**********************************
		String ip = "";
		try 
    	{
        	Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        	while (interfaces.hasMoreElements()) {
            	NetworkInterface iface = interfaces.nextElement();
            
            	if (iface.isLoopback() || !iface.isUp())
                continue;

            	Enumeration<InetAddress> addresses = iface.getInetAddresses();
            	while(addresses.hasMoreElements()) {
             	   InetAddress addr = addresses.nextElement();
              	  ip = ip+" "+addr.getHostAddress();
                
            	}
        	}
    	} 
   	 	catch (SocketException e) 
    	{
       	 	throw new RuntimeException(e);
    	} 
    	//////////*************************
		Scanner sc = new Scanner(System.in);
		//int[]portArr = {9876,9875,9874,9874,9873,9872,9871,9870};
		String hostname = "localhost";
		Socket client;
		System.out.println("Enter your ID");
		id = sc.nextInt();
		System.out.println("Enter port no.");
		port = sc.nextInt();
		System.out.println("Enter id , port number to which it would connect");
		int id1 = sc.nextInt();
		int port1 = sc.nextInt();

		Node node = new Node(id,port,id1,port1);

		port1 (id1 > 0 && port1 > 0) 
		{
			//join(id,id1);
			//send(id1,port1);
		}
		try
		{
			while(true)
			{
				//out.println(line);
				InetAddress ipAddr = InetAddress.getLocalHost();
				InetAddress IP = InetAddress.getLocalHost();
				System.out.println("Client: What do you want to do");
				System.out.println("1 : Print it's IP Address and it's own ID");
				System.out.println("2 : Print The IP address and ID of the immediate clockwise neighbor");
				System.out.println("3 : Print The file key IDs it contains");
				System.out.println("4 : Print Its own finger table");
				System.out.println("5 : Exit");
				int num = sc.nextInt();
				switch (num) 
				{
					case 1:
						System.out.println("*********************************************************");
						System.out.println("IP Address :"+IP+"  ID :"+id);
						System.out.println("*********************************************************");
						System.out.println();
						break;
					case 2:
						
				}
			}
		}
		catch(Exception e){}
		
		//System.out.println("Hello");
		//clientSocket = new Socket(ip,port);
		//clientSocket.close();
	}
	static void join(int id,int id1)
	{

	}
	static void send(int id1,int port1)
	{
		try
		{
			String hostname = "localhost";
			Socket clientSocket = new Socket(hostname,port1);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out.println(id+"#"+port);
			String string = in.readLine();
			clientSocket.close();
		}
		catch(Exception e)
		{}
	}
}