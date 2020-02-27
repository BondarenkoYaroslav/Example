package interfaceIO;

import java.util.List;

public interface InterfaceFileIO {

	boolean checkingFileExistence(String path, String file);

	boolean createNewFile(String path, String name);

	List<String> readInputFileToList(String path, String inputFileName);

	int fileСounter(String path);

	boolean writeOutputFile(String path, String outputFileName, String countingAndWritingToFile, boolean append);
}
