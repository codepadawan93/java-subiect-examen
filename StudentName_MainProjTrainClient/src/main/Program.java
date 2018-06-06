package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import abstracts.Train;
import classes.FileInOutOperations;
import classes.FreightTrain;
import classes.PassengerTrain;

public class Program {
	
	final static int port = 16969;
	final static String ip = "localhost";
	
	public static void main(String[] args) throws Exception {
		
		Train t1 = new PassengerTrain("P1010", 100, "5888", 1000);
		Train t2 = new FreightTrain("F40", 300, "1112", 36);
		List<Train> trains = new ArrayList<Train>(Arrays.asList(
				t1,
				t2, 
				new PassengerTrain("P34", 69, "TTT", 1000)
		));
		
		FileInOutOperations ops = new FileInOutOperations("trains.txt");
		ops.writeObjectInTextFile(trains);
		List<Train> trains2 = ops.readObjectFromTextFile();
		for(Train train : trains2)
		{
			System.out.println(train);
		}
		
		// client for tcp server - I won't mess around with swing or 
		// other such nonsense, this will be a simple console app
		Scanner scanner = new Scanner(System.in);
		Thread t = new Thread(()->{
			System.out.print("How many trains to send: ");
			String message = scanner.nextLine();
			int i = Integer.parseInt(message);
			if(i > trains2.size())
			{
				i = trains2.size();
				System.out.print("Insufficient number of trains. Sending only " + i);
			}
			for(int j = 0; j < i; j++){
				try( Socket client = new Socket(ip, port) ){
					OutputStream os = client.getOutputStream();
					DataOutputStream outputStream = new DataOutputStream(os);
					
					String stringifiedTrain = trains2.get(j).toString("serialize");
					outputStream.writeUTF(stringifiedTrain);
					Thread.sleep(200);
					outputStream.close();
					os.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

}
