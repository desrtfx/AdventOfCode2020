package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day01 {

	static int[] data;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day01.txt")).stream().mapToInt(Integer::parseInt).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static int part01(int[] input, int target) {
		int result = 0;
		for (int i = 0; i < input.length - 1; i++) {
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] + input[j] == target) {
					return input[i] * input[j];
				}
			}
		}
		return result;
	}

	static long part02(int[] input, int target) {
		long result = 0L;
		for (int i = 0; i < input.length - 2; i++) {
			for (int j = i + 1; j < input.length - 1; j++) {
				for (int k = j + 1; k < input.length; k++) {
					if (input[i] + input[j] + input[k] == target) {
						return (long) input[i] * input[j] * input[k];
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] test = new int[] { 1721, 979, 366, 299, 675, 1456 };
		System.out.println("Test part 1: " + part01(test, 2020));
		System.out.println("Part 1: " + part01(data, 2020));

		System.out.println("Test part 2: " + part02(test, 2020));
		System.out.println("Part 2: " + part02(data, 2020));

	}

}
