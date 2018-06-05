package interfaces;

import java.util.List;
import abstracts.Train;

public interface FileOperations {
	public List<Train> readObjectFromTextFile();
	public void writeObjectInTextFile(List<Train> trains);
}
