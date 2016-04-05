import java.util.*;
import java.io.*;
import java.net.*;

public class client 
{
	static int[]arr = new int[3];
	static int[]suc = new int[3];
	
	public static void main(String[] args) throws IOException
	{
		String hostname = "localhost";
		int portNumber = 3987;
		String ip="";

    	try 
    	{
        	Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        	while (interfaces.hasMoreElements()) {
            	NetworkInterface iface = interfaces.nextElement();
            // filters out 127.0.0.1 and inactive interfaces
            	if (iface.isLoopback() || !iface.isUp())
                continue;

            	Enumeration<InetAddress> addresses = iface.getInetAddresses();
            	while(addresses.hasMoreElements()) {
             	   InetAddress addr = addresses.nextElement();
              	  ip = ip+" "+addr.getHostAddress();
                //System.out.println(iface.getDisplayName() + " " + ip);
            	}
            //System.out.println(iface.getDisplayName() + " " + ip);
        	}
    	} 
   	 	catch (SocketException e) 
    	{
       	 	throw new RuntimeException(e);
    	}            

		int portnumber;

		Scanner sc = new Scanner(System.in);
		try
		{
			//Socket server = new Socket("172.16.129.125",portNumber);
			Socket server = new Socket(hostname,portNumber);

			portnumber = server.getLocalPort();
			
			PrintWriter out = new PrintWriter(server.getOutputStream(),true);
			System.out.println("Enter Your ID (Pre-Assigned)");
			int idt = sc.nextInt();

			out.println(ip+"#"+idt);
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

			BufferedReader StdIn =  new BufferedReader(new InputStreamReader(System.in));

			String line;
			//while((line = StdIn.readLine())!=null)
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
						//out.println(num+"#"+portnumber);
						System.out.println("*********************************************************");
						//System.out.println("IP Address :"+ip+"  ID :"+in.readLine());
						System.out.println("IP Address :"+ip+"  ID :"+idt);
						System.out.println("*********************************************************");
						System.out.println();
						break;
					case 2:
						out.println(num+"#"+idt);
						String string = in.readLine();
						String[]array = string.split("#");
						System.out.println(array[0]+"   ***"+array[1]);
						System.out.println("*********************************************************");
						if (array[0].equals("no")) 
						{
							System.out.println("There is only ONE Node present in the ring");
						}
						else
						{
							System.out.println("ID :"+array[0]+"  IP Address :"+array[1]);
						}
						
						System.out.println("*********************************************************");
						System.out.println();
						break;
					case 3:
                      	out.println(num+"#"+idt);
                      	String string1 = in.readLine();
                      	System.out.println("*********************************************************");
                      	System.out.println("Files keys it contains :");
                      	System.out.println(string1);
                      	System.out.println("*********************************************************");
                      	break;
                    case 4:          	
                    	arr[0] = (idt+1)%8;
                    	arr[1] = (idt+2)%8;
                    	arr[2] = (idt+4)%8;
                    	out.println(num+"#"+idt+"#"+arr[0]+"#"+arr[1]+"#"+arr[2]);
                    	String str = in.readLine();
                    	String[]suc = str.split("#");
                    	
                    	System.out.println("********************************************************");
                    	System.out.println(" Start "+"    "+"Interval        "+"   Successor");
                    	for (int j=0;j<3 ;j++ ) 
                    	{
                    		if (j!=2) 
                    		{
                    			System.out.println(" "+arr[j]+"     "+"     ["+arr[j]+","+arr[j+1]+")"+"               "+suc[j]);
                    		}
                    		else
                    		{
                    			int tmp = arr[0]-1;
                    			System.out.println(" "+arr[j]+"     "+"     ["+arr[j]+","+tmp+")"+"               "+suc[j]);
                    		}	
                    	}
                    	break;

                    case 5:
                    	break;

                    default:
                    	break;
					
					}
					if (num==5) 
					{
						out.println(num+"#"+idt);
						System.out.println("************************");
						System.out.println("Closing the Socket");
						System.out.println("************************");
						break;	
					}
			}

		}	
		catch(UnknownHostException e)
		{
			System.err.println("Don't know about host"+ hostname);
			System.exit(1);
		}
		catch(IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to "+hostname);
		}		
	}
	static int find_successor(int id)
	{
		StuffThread s = new StuffThread();
		boolean flag = false;
		int tmp=0;
		for (int i=0;i<8 ;i++ ) 
		{
			System.out.println(s.hm.get(i));	
		}
		for (int i=id;i<8 ;i++ ) 
		{
			if (s.hm.get(id)!=null) 
			{
				flag = true;
				tmp = id;	
				break;
			}		

		}
		if (flag == false) 
		{
			for (int i=0;i<id ;i++ ) 
			{
				if (s.hm.get(id)!=null) 
				{
					flag = true;
					tmp= id;
					break;	
				}	
			}	
		}
		return tmp;
	}

}
