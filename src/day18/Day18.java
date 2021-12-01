package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day18 {
	
	static List<String> data;
	
	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day18.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit((-1));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
