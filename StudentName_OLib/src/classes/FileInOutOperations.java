package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import abstracts.Train;
import interfaces.FileOperations;
import classes.FreightTrain;
import classes.PassengerTrain;

public class FileInOutOperations implements FileOperations, Comparable<Object>{
	
	private File file;
	
	public FileInOutOperations(String filename)
	{
		file = new File(filename);
	}
	
	public FileInOutOperations() {
		
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof FileInOutOperations))
		{
			return false;
		}
		FileInOutOperations fioo = (FileInOutOperations)obj;
		return fioo.file.equals(file);
	}
	
	@Override
	protected Object clone()
	{
		return new FileInOutOperations(file.getPath());
	}

	@Override
	public int compareTo(Object o) {
		if(!(o instanceof FileInOutOperations))
		{
			return 0;
		}
		FileInOutOperations fioo = (FileInOutOperations)o;
		if(fioo.file.length() < file.length())
		{
			return 1;
		}else if(fioo.file.length() == file.length())
		{
			return 0;
		}else{
			return -1;
		}
	}

	@Override
	public List<Train> readObjectFromTextFile() {
		ArrayList<Train> trains = new ArrayList<Train>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = "";
			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split("#");
				Train t = null;
				if(file.getPath().contains("passanger"))
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
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trains;
	}

	@Override
	public void writeObjectInTextFile(List<Train> trains) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			
			for(Train t : trains)
			{
				writer.write(
						String.format(
								"%s#%f#%s#%f#",
								t.getSerialNo(),
								t.getWeight(),
								t.getLabel(),
								t.getCapacity()
								)
						);
				List<String> info = t.getInfo();
				for(String i : info)
				{
					writer.write(i + ",");
				}
				writer.write(System.getProperty("line.separator"));
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException x){
			x.printStackTrace();
		}
	}

}
