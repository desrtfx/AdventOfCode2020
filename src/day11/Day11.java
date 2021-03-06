package day11;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day11 {

	public static final int[] DX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	public static final int[] DY = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static final char FLOOR = '.';
	public static final char EMPTY = 'L';
	public static final char OCC = '#';

	static char[][] data;
	static char[][] curr;
	static char[][] next;
	public static int height;
	public static int width;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day11.txt")).stream().map(e -> e.toCharArray())
					.toArray(char[][]::new);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean tick(boolean part1) {
		boolean changed = false;
		int maxOcc = (part1) ? 4 : 5;
		next = new char[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				next[row][col] = curr[row][col];
				if (curr[row][col] == FLOOR) {
					continue;
				}
				int neighbors = 0;
				for (int dir = 0; dir < DX.length; dir++) {
					int x = col;
					int y = row;
					while (true) {
						x += DX[dir];
						y += DY[dir];
						if (((x < 0) || (x > width - 1) || (y < 0) || (y >= height))) {
							break;
						}
						if (curr[y][x] == OCC) {
							neighbors++;
							break;
						}
						if (curr[y][x] == EMPTY) {
							break;
						}
						if (part1 || (neighbors >= maxOcc)) {
							break;
						}
					}
					if (neighbors >= maxOcc) {
						break;
					}
				}
				if ((curr[row][col] == EMPTY) && (neighbors == 0)) {
					next[row][col] = OCC;
					changed = true;
				}
				if ((curr[row][col] == OCC) && (neighbors >= maxOcc)) {
					next[row][col] = EMPTY;
					changed = true;
				}
			}
		}
		return changed;
	}

	public static void prepare(char[][] data) {
		height = data.length;
		width = data[0].length;
		curr = Arrays.stream(data).map(char[]::clone).toArray(char[][]::new);
	}

	public static void simulate(boolean part1) {
		boolean changed = true;
		while (changed) {
			changed = tick(part1);
			curr = next;
		}
	}

	public static int countTotals() {
		return (int) Arrays.stream(curr).map(CharBuffer::wrap).flatMapToInt(CharBuffer::chars).filter(i -> i == OCC)
				.count();
	}

	public static int part01(char[][] data) {
		prepare(data);
		simulate(true);
		return countTotals();
	}

	public static int part02(char[][] data) {
		prepare(data);
		simulate(false);
		return countTotals();
	}

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01(data));
		System.out.println("Part 2: " + part02(data));
	}

}
