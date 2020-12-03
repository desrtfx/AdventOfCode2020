package day03;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day03 {

	static char[][] data;
	static int width;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day03.txt"))
					    .stream()
					    .map(e -> e.toCharArray())
					    .toArray(char[][]::new);
			width = data[0].length;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	static int part01(char[][] data, Point move) {
		int cnt = 0;
		Point cur = new Point(0, 0);
		while (cur.y < data.length) {
			if (data[cur.y][cur.x] == '#') {
				cnt++;
			}
			cur.translate(move.x, move.y);
			cur.x = cur.x % width;
		}
		return cnt;
	}

	static long part02(char[][] data) {
		long res = Stream.of(new Point(1, 1), new Point(3, 1), new Point(5, 1), new Point(7, 1), new Point(1, 2))
			           	 .mapToLong(p -> part01(data, p))
			           	 .reduce((a, b) -> a * b)
			           	 .getAsLong();
		return res;
	}

	public static void main(String[] args) {
		System.out.println("Part  1: " + part01(data, new Point(3, 1)));
		System.out.println("Part  2: " + part02(data));
	}
}
