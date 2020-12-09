package day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Day09 {

	static long[] data;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day09.txt")).stream().mapToLong(Long::parseLong).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Optional<Long> sumOfTwo(long[] data, long target) {

		Set<Long> nums = new HashSet<>();
		for (int i = 0; i < data.length; i++) {
			nums.add(data[i]);
		}
		boolean found = false;
		for (int i = 0; i < data.length; i++) {
			long result = target - data[i];
			if (nums.contains(result)) {
				found = true;
				break;
			}
		}
		if (!found) {
			return Optional.of(target);
		}
		return Optional.empty();
	}

	public static long part01(long[] data) {
		for (int i = 0; i < data.length - 26; i++) {
			long target = data[i + 25];
			long[] test = Arrays.copyOfRange(data, i, i + 25);
			Optional<Long> res = sumOfTwo(test, target);
			if (res.isPresent()) {
				return res.get();
			}
		}
		return -1L;
	}

	public static long part02(long[] data, long target) {
		long res = 0;
		for (int i = 0; i < data.length - 1; i++) {
			res = data[i];
			long min = res;
			long max = res;
			for (int j = i + 1; j < data.length; j++) {
				res += data[j];
				if (data[j] < min) {
					min = data[j];
				}
				if (data[j] > max) {
					max = data[j];
				}
				if (res == target) {
					return max + min;
				} else if (res > target)
					break;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		long part01Result = part01(data);
		System.out.println("Part 01: " + part01Result);
		System.out.println("Part 02: " + part02(data, part01Result));

	}

}
