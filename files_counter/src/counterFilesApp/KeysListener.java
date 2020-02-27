package counterFilesApp;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import settings.PathsSettings;

public class KeysListener implements NativeKeyListener, Runnable {

	private static boolean defaultSettings = true;
	private static PathsSettings pathsSettings = new PathsSettings();

	private void infoSettings() {

		if (!defaultSettings) {
			System.out.println();
		}
		System.out.println("Your settings: ");
		System.out.println(pathsSettings.toString());
		if (defaultSettings) {
			System.out.println("Press F5 if you want to change the settings.");
		}
		System.out.println("Press TAB to continue.");
		System.out.println("Press ESC to exit.");
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {

		if (arg0.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {

			System.out.println("The program has completed work.");
			System.exit(1);

		} else if (arg0.getKeyCode() == NativeKeyEvent.VC_F5) {

			if (defaultSettings) {
				Scanner scan = new Scanner(System.in);

				System.out.println();
				System.out.println("Enter input file name:");
				pathsSettings.setInputFileName(scan.nextLine());

				System.out.println();
				System.out.println("Enter output file name: ");
				pathsSettings.setOutputFileName(scan.nextLine());

				scan.close();

				defaultSettings = false;
				infoSettings();
			} else {
				System.out.println();
				System.out.println("Restart the program if you want to change the settings.");
			}
		} else if (arg0.getKeyCode() == NativeKeyEvent.VC_TAB)

		{

			new ExecutionFilesCounter().startApps();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
	}

	@Override
	public void run() {

		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new KeysListener());

		infoSettings();

	}
}