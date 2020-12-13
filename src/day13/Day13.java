package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day13 {
	
	static int earliest;
	static List<String> schedule;
	
	static {
		try {
			List <String> data = Files.readAllLines(Paths.get("Input/Day13.txt"));
			earliest = Integer.parseInt(data.get(0));
			schedule = Arrays.asList(data.get(1).split(","));
			

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static int part01() {
		int shortest = Integer.MAX_VALUE;
		int busNo = 0;
		for(String s : schedule) {
			if(!"x".equals(s)) {
				int t = Integer.parseInt(s);
				int f = earliest / t;
				int g = (f + 1) * t;
				int h = g - earliest;
				if (h < shortest) {
					shortest = h;
					busNo = t;
				}
			}
		}
		
		return shortest * busNo;
	}
	
	public static long part02() {
		long rP = 1L;
		long earliest = 0L;
		for(int i = 0; i < schedule.size(); i++) {
			String s = schedule.get(i);
			if ("x".equals(s)) {
				continue;
			}
			int id = Integer.parseInt(s);
			while ((earliest + i) % id != 0) {
				earliest += rP;
			}
			rP *= id;
		}
		return earliest;
	}
	
	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
		System.out.println("Part 02: " + part02());
	}

}
