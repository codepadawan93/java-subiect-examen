package abstracts;

import java.util.List;

public abstract class Train {
	private String serialNo;
	private float weight;
	private final String label;
	
	public Train(String serialNo, float weight, String label)
	{
		this.serialNo = serialNo;
		this.weight = weight;
		this.label = label;
		
		
	}

	public String getSerialNo() {
		return serialNo;
	}


	public float getWeight() {
		return weight;
	}

	public String getLabel() {
		return label;
	}
	
	public abstract float getCapacity();
	public abstract List<String> getInfo();
	public abstract void addInfo(String s);
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Train))
		{
			return false;
		}
		Train trainObj = (Train)obj;
		if(
			this.serialNo == trainObj.serialNo &&
			this.weight == trainObj.weight &&
			this.label == trainObj.label
		)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Serial No: " + serialNo + "; weight: " + weight + "; label: " + label;
	}
	
	public String toString(String format){
		if("serialize".equals(format))
		{
			return String.format(
					"%s#%f#%s#%f#",
					getSerialNo(),
					getWeight(),
					getLabel(),
					getCapacity()
					);
		}else{
			return "";
		}
	};
}
