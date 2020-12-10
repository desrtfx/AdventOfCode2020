package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {
	
	static int[] data;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day10.txt")).stream().mapToInt(Integer::parseInt).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int part01(int[] data) {
		int[] tmp = Arrays.copyOf(data, data.length);
		Arrays.sort(tmp);
		int[] jolts = new int[3];
		int last = 0;
		for(int i=0; i < tmp.length; i++) {
			int diff = tmp[i] - last;
			jolts[diff-1]++;
			last = tmp[i];
		}
		jolts[2]++;
		return jolts[0]*jolts[2];
	}
	
	public static long part02(int[] data) {
		List<Integer> tmp = new ArrayList<>();
		tmp.add(0);
		for(int d : data) {
			tmp.add(d);
		}
		Collections.sort(tmp, Collections.reverseOrder());
		long m = tmp.get(0) + 3;
		Map<Long,Long> dist  = new HashMap<>();
		dist.put(m,1L);
		for(long v : tmp) {
			long d1 = dist.getOrDefault(v+1,0L);
			long d2 = dist.getOrDefault(v+2,0L);
			long d3 = dist.getOrDefault(v+3,0L);
			dist.put(v,(d1+d2+d3));
		}
		return dist.get(0L);
		
	}

	public static void main(String[] args) {
		System.out.println("Part 01: " + part01(data));
		System.out.println("Part 01: " + part02(data));

	}

}
