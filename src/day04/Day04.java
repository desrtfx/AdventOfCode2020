package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 {

	static final String TWO_LINE_BREAKS = System.lineSeparator() + System.lineSeparator();

	static class Passport {

		final Pattern PATTERN = Pattern.compile("(\\S*:\\S*)[\\s]?");
		final List<String> REQUIRED = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

		Map<String, String> attrib = new HashMap<>();

		public Passport(String data) {
			Matcher m = PATTERN.matcher(data);
			while (m.find()) {
				String[] d = m.group().split(":");
				attrib.put(d[0].trim(), d[1].trim());
			}
		}

		boolean isValid1() {
			return attrib.keySet().containsAll(REQUIRED);
		}

		boolean numInRange(String num, int min, int max) {
			if (num == null) {
				return false;
			}
			int inum;
			try {
				inum = Integer.parseInt(num);
			} catch (Exception e) {
				return false;
			}

			return ((inum >= min) && (inum <= max));
		}

		boolean matchesRegex(String data, String rule) {
			if (data == null)
				return false;
			final Pattern pattern = Pattern.compile(rule);
			return pattern.matcher(data).matches();
		}

		boolean isValid2() {
			if (!numInRange(attrib.getOrDefault("byr", null), 1920, 2002))
				return false; // byr
			if (!numInRange(attrib.getOrDefault("iyr", null), 2010, 2020))
				return false; // iyr
			if (!numInRange(attrib.getOrDefault("eyr", null), 2020, 2030))
				return false; // eyr
			if (!matchesRegex(attrib.getOrDefault("hgt", null), "^((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))$"))
				return false; // hgt
			if (!matchesRegex(attrib.getOrDefault("hcl", null), "^(#[0-9a-f]{6})$"))
				return false; // hcl
			if (!matchesRegex(attrib.getOrDefault("ecl", null), "^(amb|blu|brn|gry|grn|hzl|oth)$"))
				return false; // ecl
			return matchesRegex(attrib.getOrDefault("pid", null), "^[0-9]{9}$"); // pid

		}

	}

	public static void main(String[] args) {


		try {

			List<Passport> valid1 =  Stream.of(Files.readString(Paths.get("Input/Day04.txt")).split(TWO_LINE_BREAKS))
										  .map(Passport::new)
										  .filter(p -> p.isValid1())
										  .collect(Collectors.toList());
			System.out.println("Part 1: " + valid1.size());

			long valid2 = valid1.stream()
						  		.filter(p -> p.isValid2())
						  		.count();
			System.out.println("Part 2: " + valid2);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
