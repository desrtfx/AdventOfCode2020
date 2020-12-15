package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
	
	static List<String> data;
	
	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day14.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	static long part01() {
		long sum = 0;
		Map<Long, Long> regs = new HashMap<>();
		long orMask = 0L;
		long andMask = 0L;
		for(String s : data) {
			switch(s.substring(0,3)) {
			case "mas": // mask
				String tmp = s.substring(7);
				orMask = 0L;
				andMask = 0L;
				for(char c : tmp.toCharArray()) {
					orMask = (orMask << 1) + ((c == '1') ? 1 : 0);
					andMask = (andMask << 1) + ((c == '0' ? 0 : 1));
				}
				break;
			case "mem": // write memory
				String[] t = s.split(" = ");
				long reg = Long.parseLong(t[0].substring(4,t[0].length()-1));
				long num = Long.parseLong(t[1]); 
				num = (num & andMask) | orMask;
				regs.put(reg, num);
				break;
			default:
				break;
			}
		}
		for(Long entry : regs.values()) {
			sum += entry;
		}
		return sum;
	}
	
	public static long part02() {
		long sum = 0;
		Map<Long, Long> regs = new HashMap<>();
		String mask = "";
		List<Long> comb = new ArrayList<>();
		for(String s : data) {
			switch(s.substring(0,3)) {
			case "mas": // mask
				mask = s.substring(7);
				break;
			case "mem": // write memory
				String[] t = s.split(" = ");
				long reg = Long.parseLong(t[0].substring(4,t[0].length()-1));
				StringBuilder sb = new StringBuilder();
				char[] m = mask.toCharArray();
				for(int i= 0 ; i < 36; i++) {
					long tmp = 1L << (35-i);
					boolean set = (reg & tmp) == tmp;
					switch(m[i]) {
					case '1':
						sb.append('1');
						break;
					case 'X':
						sb.append('X');
						break;
					case '0':
						sb.append(set ? '1' : '0');
						break;
					default:
						break;
					}
				}
				comb = makeAddresses(sb.toString());
				long num = Long.parseLong(t[1]);
				for (long c : comb) {
					regs.put(c, num);
				}
				break;
			default:
				break;
			}
		}
		for(Long entry : regs.values()) {
			sum += entry;
		}
		return sum;		
	}
	
	
	public static List<Long> makeAddresses(String s) {
		List<Long> comb = new ArrayList<>();
		List<Integer> floating = new ArrayList<>();
		// Step 1 - identify all the floating bits & store their position
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'X') {
				floating.add(i); // add the position
				c = '0'; // reset char
			}
			b.append(c);
		}
		List<String> strings = new ArrayList<>();
		strings.add(b.toString());
		b.setCharAt(floating.get(0), '1');
		strings.add(b.toString());
		for (int i = 1; i < floating.size(); i++) {
			int pos = floating.get(i);
			List<String> tmp = new ArrayList<>();
			for(String t : strings) {
				char[] ch = t.toCharArray();
				ch[pos] = '1';
			    tmp.add(new String(ch));
			}
			strings.addAll(tmp);
		}
		for(String t : strings) {
			comb.add(Long.parseLong(t,2));
		}
		return comb;
	}

	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
		System.out.println("Part 02: "+ part02());
		
	}

}
