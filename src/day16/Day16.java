package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {

	record Rule(String desc, Set<Integer> range) {};
	
	
	public static Pattern DESC_PATTERN = Pattern.compile("(.*): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

	public static List<String> data;
	public static List<Rule> rules = new ArrayList<>();
	public static int[] myTicket;
	public static List<int[]> nearby = new ArrayList<>();
	public static List<int[]> valids = new ArrayList<>();
	public static Set<Integer> allRules = new HashSet<>();
	public static List<Rule> departures = new ArrayList<>();

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day16.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		int state = 0;
		for(String s : data) {
			if (s.isEmpty()) {
				state++;
				continue;
			}
			switch(state) {
			case 0:
				Matcher m = DESC_PATTERN.matcher(s);
				if(m.matches()) {
					String desc = m.group(1);
					int fromA = Integer.parseInt(m.group(2));
					int toA = Integer.parseInt(m.group(3));
					int fromB = Integer.parseInt(m.group(4));
					int toB = Integer.parseInt(m.group(5));
					Set<Integer> range = new HashSet<>();
					for(int i = fromA; i <= toA; i++) {
						range.add(i);
					}
					for (int i = fromB; i <= toB; i++) {
						range.add(i);
					}
					rules.add(new Rule(desc, range));
					if(desc.startsWith("departure")) {
						departures.add(new Rule(desc, range));
					}
					allRules.addAll(range);
				}
				break;
			case 1:
				if(s.equals("your ticket:")) {
					continue;
				} else {
					myTicket = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt)
						    .toArray();
				}
				break;
			case 2:
				if (s.equals("nearby tickets:")) {
					continue;
				}
				nearby.add(Arrays.stream(s.split(",")).mapToInt(Integer::parseInt)
					    .toArray());
				break;
			default:
				break;
			}
			
		}
		
		
		
		
	}

	public static long part01() {
		long sum = 0L;
		for(int[] t : nearby) {
			boolean valid = true;
			for(int item : t) {
				if(!allRules.contains(item)) {
					sum += (long)item;
					valid = false;
				}
			}
			if (valid) {
				valids.add(t);
			}
		}
		return sum;
	}
	
	public static long part02() {
		long res = 1;
		List<Set<Integer>> tickets = new ArrayList<>();
		for(int i = 0; i <= myTicket.length; i++) {
			tickets.add(new HashSet<>());
		}
		for(int[] ticket : valids) {
			for(int i = 0; i < ticket.length; i++) {
				tickets.get(i).add(ticket[i]);
			}	
		}
		List<Integer> val = new ArrayList<>();
		for(Set<Integer> t : tickets) {
			for(int i = 0; i < departures.size(); i++) {
				if(departures.get(i).range.equals(t)) {
					val.add(myTicket[i]);
				}
			}
		}
		
		for(int i : val) {
			res *= i;
		}
		
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
		System.out.println("Part 02: " + part02());
	}

}
