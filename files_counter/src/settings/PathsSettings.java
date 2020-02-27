package settings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathsSettings {

	private String path = "";
	private String inputFileName = "paths.txt";
	private String outputFileName = "results.txt";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		Pattern pattern = Pattern.compile("[\\/:*?\"<>]");
		Matcher matcher = pattern.matcher(outputFileName);

		if (inputFileName.contains(".txt") & !!matcher.find() & !inputFileName.contains(this.outputFileName)) {
			this.inputFileName = inputFileName;
		} else {
			System.out.println();
			System.out.println("Invalid input file name format.");
			System.out.println("File name for example: example.txt");
			System.out.println("Now the name of the input file: " + this.inputFileName);
		}
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		Pattern pattern = Pattern.compile("[\\/:*?\"<>]");
		Matcher matcher = pattern.matcher(outputFileName);

		if (outputFileName.contains(".txt") & !matcher.find() & !outputFileName.contains(this.inputFileName)) {
			this.outputFileName = outputFileName;
		} else {
			System.out.println();
			System.out.println("Invalid output file name format.");
			System.out.println("File name for example: example.txt");
			System.out.println("Now the name of the input file: " + this.outputFileName);
		}
	}

	@Override
	public String toString() {
		return "Input file: " + inputFileName + "; Output file: " + outputFileName + ";";
	}
}