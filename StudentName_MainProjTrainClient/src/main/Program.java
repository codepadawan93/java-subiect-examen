package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import abstracts.Train;
import classes.FileInOutOperations;
import classes.FreightTrain;
import classes.PassengerTrain;

public class Program {

	public static void main(String[] args) throws Exception {
		Train t1 = new PassengerTrain("T1010", 100, "5888", 1000);
		Train t2 = new FreightTrain("S40", 300, "1112", 36);
		List<Train> trains = new ArrayList<Train>(Arrays.asList(
				t1,
				t2, 
				new PassengerTrain("G34", 69, "TTT", 1000)
		));
		
		FileInOutOperations ops = new FileInOutOperations("trains.txt");
		ops.writeObjectInTextFile(trains);
		List<Train> trains2 = ops.readObjectFromTextFile();
		for(Train train : trains2)
		{
			System.out.println(train);
		}
	}

}
