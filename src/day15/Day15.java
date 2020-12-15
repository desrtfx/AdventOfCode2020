package day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {
	
	static int[] data = {8,0,17,4,1,12};

	static int play(int numTurns) {
		Map<Integer, Integer> nums = new HashMap<>();
		int turn = 0;
		for(int i=0; i < data.length-1; i++) {
			nums.put(data[i], i);
			turn++;
		}
		int lastSpoken = nums.containsKey(data[data.length - 1]) ? data.length : 0;
		int nextNumber;
		for(int i= turn; i < numTurns-1;i++) {
			nextNumber = nums.containsKey(lastSpoken) ? i - nums.get(lastSpoken) : 0;
			nums.put(lastSpoken, i);
			lastSpoken = nextNumber;
			//turn++;
		}
		return lastSpoken;
	}

	
	
	public static void main(String[] args) {
		System.out.println("Part 01: " + play(20201));
		
		int[] inputs = {8,0,17,4,1,12};
		Map<Integer, List<Integer>> lastSpoken = new HashMap<>();
		int turn = 1; //we start at 1 apparently
		int lastNumber = 0;
		while (turn <= inputs.length) {
		    lastNumber = inputs[turn - 1];
		    lastSpoken.putIfAbsent(lastNumber, new ArrayList<Integer>());
		    lastSpoken.get(lastNumber).add(turn);
		    ++turn;
		}

		int part1 = 0;
		while (turn <= 30000000) {
		    if (lastSpoken.get(lastNumber).size() == 1) {
		        lastNumber = 0;
		    } else {
		        List<Integer> l = lastSpoken.get(lastNumber);
		        lastNumber = l.get(l.size() - 1) - l.get(l.size() - 2);
		    }

		    lastSpoken.putIfAbsent(lastNumber, new ArrayList<Integer>());
		    lastSpoken.get(lastNumber).add(turn);

		    if (turn == 2020) part1 = lastNumber;
		    ++turn;
		}
		System.out.println("Part1 : " + part1);
		System.out.println("Part2 : " + lastNumber);
	}

}
