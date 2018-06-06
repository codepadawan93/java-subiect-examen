package classes;

import java.util.List;
import java.util.Vector;

import abstracts.Train;

public class FreightTrain extends Train {

	private float seatNo;
	private Vector<String> freightSn;
	public FreightTrain(String serialNo, float weight, String label, float seatNo) throws Exception {
		super(serialNo, weight, label);
		if(weight < 0)
		{
			throw new Exception("Invalid weight");
		}
		this.seatNo = seatNo;
		freightSn = new Vector<String>();
	}

	@Override
	public float getCapacity() {
		return seatNo;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof FreightTrain))
		{
			return false;
		}
		FreightTrain trainObj = (FreightTrain)obj;
		if(trainObj.equals(this) && trainObj.seatNo == seatNo)
		{
			return true;
		}
		return false;
	}
	
	@Override 
	protected Object clone()
	{
		FreightTrain train = null;
		try {
			train = new FreightTrain(getSerialNo(), getWeight(), getLabel(), this.seatNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(String s : freightSn)
		{
			train.freightSn.addElement(s);
		}
		return train;
	}

	@Override
	public List<String> getInfo() {
		return freightSn;
	}

	@Override
	public void addInfo(String s) {
		freightSn.add(s);
	}
	
	@Override
	public String toString()
	{
		String retval = super.toString();
		retval += "; Seat no " + seatNo + "; ";
		for(String s : freightSn)
		{
			retval += s + ", ";
		}
		return retval;
	}
	
	@Override
	public String toString(String format)
	{
		if("serialize".equals(format))
		{
			String stringified = String.format(
					"%s#%f#%s#%f#",
					getSerialNo(),
					getWeight(),
					getLabel(),
					getCapacity()
					);
			
			for(String s : freightSn)
			{
				stringified += s + ",";
			}
			return stringified;
		}else{
			return "";
		}
	}
}
