package day15;

import java.util.HashMap;

public class Day15 {
	
	static int[] data = {8,0,17,4,1,12};
	static final int PART_01 = 2020;
	static final int PART_02 = 30_000_000;
	
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); 
		int turn = 1;
		for (int i : data) {
			map.put(i, turn);
			turn++;
		}
		int next = 0;
		while (turn < PART_02) {
			int last = map.getOrDefault(next, -1);
			map.put(next,  turn);
			next = (last == -1) ? 0 : turn - last;
			if(turn == PART_01 - 1) {
				System.out.println("Part 01: " + next);
			}
			turn++;
		}
		System.out.println("Part 02: " + next);
	}

}
