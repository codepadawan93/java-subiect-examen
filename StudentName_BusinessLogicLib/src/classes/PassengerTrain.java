package classes;

import java.util.List;
import java.util.Vector;

import abstracts.Train;

public class PassengerTrain extends Train {
	
	private float capacityKg;
	private Vector<String> passengerId;
	public PassengerTrain(String serialNo, float weight, String label, float capacityKg) throws Exception {
		super(serialNo, weight, label);
		if(weight < 0)
		{
			throw new Exception("Invalid weight");
		}
		this.capacityKg = capacityKg;
		passengerId = new Vector<String>();
	}

	@Override
	public float getCapacity() {
		return capacityKg;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof PassengerTrain))
		{
			return false;
		}
		PassengerTrain trainObj = (PassengerTrain)obj;
		if(trainObj.equals(this) && trainObj.capacityKg == capacityKg)
		{
			return true;
		}
		return false;
	}
	
	@Override 
	protected Object clone()
	{
		PassengerTrain train = null;
		try {
			train = new PassengerTrain(getSerialNo(), getWeight(), getLabel(), this.capacityKg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(String s : passengerId)
		{
			train.passengerId.addElement(s);
		}
		return train;
	}

	@Override
	public List<String> getInfo() {
		return passengerId;
	}

	@Override
	public void addInfo(String s) {
		passengerId.add(s);
	}
	
	@Override
	public String toString()
	{
		String retval = super.toString();
		retval += "; Capacity Kg " + capacityKg + "; ";
		for(String s : passengerId)
		{
			retval += s + ", ";
		}
		return retval;
	}

}
