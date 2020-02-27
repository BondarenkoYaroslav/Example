package interfaceIO.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interfaceIO.InterfaceFileIO;

public class InterfaceFileIOimpl implements InterfaceFileIO {

	@Override
	public boolean checkingFileExistence(String path, String file) {

		File checkedFile = new File(path + file);

		if (checkedFile.exists()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean createNewFile(String path, String name) {

		File createdFile = new File(path + name);

		try {
			createdFile.createNewFile();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<String> readInputFileToList(String path, String inputFileName) {

		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();

		try (FileReader reader = new FileReader(path + inputFileName)) {

			int c;
			while ((c = reader.read()) != -1) {

				if (c != 59) {
					if (c != 13 & c != 10) {
						sb.append((char) c);
					}
				} else {
					list.add(sb.toString());
					sb.setLength(0);
				}
			}
			return list;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}

	@Override
	public int fileСounter(String path) {

		try {
			return new File(path).list().length;
		} catch (NullPointerException e) {
			return -1;
		}
	}

	@Override
	public boolean writeOutputFile(String path, String outputFileName, String countingAndWritingToFile,
			boolean append) {

		try (FileWriter writer = new FileWriter(path + outputFileName, append)) {

			try {
				writer.write(countingAndWritingToFile + ", " + new File(countingAndWritingToFile).list().length + ";");
			} catch (NullPointerException e) {
				writer.write(countingAndWritingToFile + ", " + (-1) + ";");
			}
			writer.append('\n');
			writer.flush();
			
			return true;
		} catch (IOException | NullPointerException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}