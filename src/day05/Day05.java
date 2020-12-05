package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {
	
	public static int seatToNum(String s) {
		int n = 0;
		for(char c : s.toCharArray()) {
			n = n * 2;
			n = n + (((c=='B')||(c=='R')) ? 1 : 0);
		}
		return n;
	}

	public static void main(String[] args) {
		try {
			List<Integer> nums = Files.readAllLines(Paths.get("Input/Day05.txt"))
					                  .stream()
					                  .map(s -> seatToNum(s))
					                  .collect(Collectors.toList());
			
			
			int max = nums.stream().max(Integer::compare).get();
			
			System.out.println("Part 01: " + max);
			
			nums.sort(Integer::compare);
			
			for(int i = 1; i < nums.size()-1; i++) {
				if(nums.get(i + 1) - nums.get(i) == 2) {  
					System.out.println("Part 2: " + (nums.get(i)+1));
					break;
				}		
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		

	}

}
