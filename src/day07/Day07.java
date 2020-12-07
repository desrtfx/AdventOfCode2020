package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day07 {
	
	record Bag(String color, Map<String, Integer> bags) {};

	static final String REGEX_LINE = " bags contain ";
	//static final Pattern PATTERN_LINE = Pattern.compile(REGEX_LINE);
	static final Pattern PATTERN_CONTENT = Pattern.compile("((\\d+?) (.+?) bag)+");
	static final String NO_OTHER = "no other bags.";
	static final String MY_BAG = "shiny gold";
	
	static Map<String,Bag> data;
	static Set<String> found;
	
	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day07.txt"))
                        .stream()
                        .map(s -> {
                        	Bag b = parseBag(s);
                        	return Map.entry(b.color, b);
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); 
		}  catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	
	
		 
		
	private static Bag parseBag(String line) {
        String[] d = line.split(REGEX_LINE);
        String color = d[0];
        String contents = d[1];

		Map<String,Integer> cont = new HashMap<>();
		if (!NO_OTHER.equals(contents)) {
			String[] items = contents.split(",");
			for(String item : items) {
				Matcher m = PATTERN_CONTENT.matcher(item.trim());
				while (m.find()) {
					int num = Integer.parseInt(m.group(2).trim());
					String col = m.group(3).trim();
					cont.put(col, num);
				}
			}
		}
		return new Bag(color, cont);
	}
	
	
	public static Set<String> recurseOut(String color) {
		Set<String> s = new HashSet<>();
		for(Entry<String,Bag> d : data.entrySet()) {
			Map<String, Integer> item = d.getValue().bags();
			if (item.containsKey(color)) {
				s.add(d.getKey());
			    s.addAll(recurseOut(d.getKey()));
			}
		}
		return s;
	}
	
	public static int recurseIn(String color) {
		int cnt = 1;
		Bag b = data.get(color);
		for (Entry<String,Integer> e : b.bags.entrySet()) {
			cnt += (e.getValue() * recurseIn(e.getKey()));
		}
		return cnt;
	}
	
	
	public static int part01(String bag) {
		return recurseOut(bag).size();
	}
	
	public static int part02(String bag) {
		return recurseIn(bag) -1;  // -1 because of first bag
	}
	
	public static void main(String[] args) {
		System.out.println("Part 01: " + part01(MY_BAG));
		System.out.println("Part 02: " + (part02(MY_BAG)));
	}

}
