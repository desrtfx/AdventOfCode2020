package Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day02 {
	
	static class DbEntry{
		int min;
		int max;
		char ltr;
		String pwd;
		
		final Pattern p=Pattern.compile("(\\d+)-(\\d+)\\s(.):.(\\S+)");
		
		public DbEntry(String entry) {
			Matcher m = p.matcher(entry);
			if(m.find()) {
				min = Integer.parseInt(m.group(1));
				max = Integer.parseInt(m.group(2));
				ltr = m.group(3).charAt(0);
				pwd = m.group(4);
			}
		}
		
		public boolean isValidPart1() {
			int cnt = (int)pwd.chars().filter(ch -> ch == ltr).count();
			return ((cnt >= min) && (cnt <= max));
		}
		
		public boolean isValidPart2() {
			return ((pwd.charAt(min-1) == ltr) ^ (pwd.charAt(max-1) == ltr));
		}
	}

	static List<DbEntry> data;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day02.txt"))
                        .stream()
	                    .map(s -> new DbEntry(s))
	                    .collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int part01(List<DbEntry> data) {
		return (int)data.stream().filter(e -> e.isValidPart1()).count();
	}

	public static int part02(List<DbEntry> data) {
		return  (int)data.stream().filter(e -> e.isValidPart2()).count();
	}

	public static void main(String[] args) {

		List<DbEntry> testData = new ArrayList<>();
		testData.add(new DbEntry("1-3 a: abcde"));
		testData.add(new DbEntry("1-3 b: cdefg"));
		testData.add(new DbEntry("2-9 c: ccccccccc"));
		int p = part01(testData);
		int expected = 2;
		System.out.printf("TestData - Part 1 result: %d - expected %d - %s%n%n", p,expected,(p==expected?"pass":"fail"));
		
		System.out.println("Part 1: " + part01(data) + "\n");

		p = part02(testData);
		expected = 1;
		System.out.printf("TestData - Part 2 result: %d - expected %d - %s%n%n", p,expected,(p==expected?"pass":"fail"));
		System.out.println("Part 2: " + part02(data) + "\n");
		
	}

}
