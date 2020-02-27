package counterFilesApp;

import java.util.List;

import interfaceIO.InterfaceFileIO;
import interfaceIO.impl.InterfaceFileIOimpl;
import settings.PathsSettings;

public class ExecutionFilesCounter implements Runnable {

	private static InterfaceFileIO interfaceIO = new InterfaceFileIOimpl();
	private static PathsSettings pathsSettings = new PathsSettings();
	private static List<String> listFromFile;

	public void startApps() {

		interfaceIO.createNewFile(pathsSettings.getPath(), pathsSettings.getInputFileName());
		interfaceIO.createNewFile(pathsSettings.getPath(), pathsSettings.getOutputFileName());
		
		listFromFile = interfaceIO.readInputFileToList(pathsSettings.getPath(), pathsSettings.getInputFileName());

		for (int i = 0; i < listFromFile.size(); i++) {
			Thread myThread = new Thread(new ExecutionFilesCounter(), "" + (i + 1));
			myThread.start();
		}

	}

	@Override
	public void run() {

		int threadNumber = Integer.parseInt(Thread.currentThread().getName());
		int indexOfList = threadNumber - 1;

		interfaceIO.writeOutputFile(pathsSettings.getPath(), pathsSettings.getOutputFileName(),
				listFromFile.get(indexOfList), true);
		System.out.println(threadNumber + ", " + interfaceIO.fileСounter(listFromFile.get(indexOfList)) + ", "
				+ listFromFile.get(indexOfList));

	}
}
