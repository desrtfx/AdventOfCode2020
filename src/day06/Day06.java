package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day06 {

	static final String TWO_LINE_BREAKS = System.lineSeparator() + System.lineSeparator();

	public static void main(String[] args) {

		try {
			List<List<String>> groupDetails = Stream.of(Files.readString(Paths.get("Input/Day06.txt"))
					                                .split(TWO_LINE_BREAKS))
											        .map(s -> Arrays.asList(s.split(System.lineSeparator())))
													.collect(Collectors.toList());
			int countTotal = 0;
			int countDistinct = 0;
			for (List<String> group : groupDetails) { // t = one group
				List<Set<Character>> groupSets = new ArrayList<>();

				for (String s : group) { // s = one line
					Set<Character> distinct = new HashSet<>();
					for (Character ch : s.toCharArray()) {
						distinct.add(ch);
					}
					groupSets.add(distinct);
				}
				Set<Character> union = new HashSet<>(groupSets.get(0));
				Set<Character> distinct = new HashSet<>(groupSets.get(0));
				
				for (int i = 1; i < groupSets.size(); i++) {
					Set<Character> g = groupSets.get(i);
					union.addAll(g);  // addAll creates a union of two sets
					distinct.retainAll(g); // retainAll creates an intersection of two sets
				}
				countTotal += union.size();
				countDistinct += distinct.size();
			}

			System.out.println("Part 1: " + countTotal);
			System.out.println("Part 2: " + countDistinct);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
