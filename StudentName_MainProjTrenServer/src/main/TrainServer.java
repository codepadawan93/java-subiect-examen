package main;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import abstracts.Train;
import classes.FreightTrain;
import classes.PassengerTrain;

public class TrainServer extends Thread {
	
	final int port = 16969;
	private static ArrayList<Train> trains = new ArrayList<Train>();
	
	public static void main(String[] args) {
		
		TrainServer ts = new TrainServer();
		ts.start();
	}
	
	@Override 
	public void run()
	{
		 try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server started on port: " + port);
			System.out.println("Trains received: ");
			while(true) { 
				Socket server = serverSocket.accept();
				InputStream is = server.getInputStream();
				DataInputStream inputStream = new DataInputStream(is);
				String message = inputStream.readUTF();
				
				// parse the input into a train
				String[] parts = message.split("#");
				Train t = null;
				if("P".equals(parts[0].indexOf(0)))
				{
					t = new PassengerTrain(parts[0],Float.parseFloat(parts[1]),parts[2],Float.parseFloat(parts[3]));
				}else{
					t = new FreightTrain(parts[0],Float.parseFloat(parts[1]),parts[2],Float.parseFloat(parts[3]));
				}
				if(parts.length > 4)
				{
					String[] additionalParts = parts[4].split(",");
					for(String part : additionalParts)
					{
						t.addInfo(part);
					}
				}
				trains.add(t);
				
				inputStream.close();
				is.close();
				System.out.println(trains.get(trains.size()-1));
			}
		}catch(EOFException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
