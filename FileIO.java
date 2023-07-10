package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
	
	Scanner newScanner;
	
	public Scanner readFile(File file) {
		
		newScanner = null;
		
		try {
			
			newScanner = new Scanner(file);
			
		}
		
		catch(FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return newScanner;
	}
	
	public void writeFile(String text) {
		
		try {
			
			FileWriter taskFile = new FileWriter("mazes.out");
			
			taskFile.write(text);
			
			taskFile.close();
			
		}
		catch(IOException e) {
			
			e.printStackTrace();
			
		}
	}

}
