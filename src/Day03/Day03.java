package Day03;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day03 {
	
	static char[][] data;
	static int width;
	
	static {
		try {
			List<String> tmp = Files.readAllLines(Paths.get("Input/Day03.txt"));
			data = new char[tmp.size()][];
			for(int i = 0; i < tmp.size(); i++) {
				data[i] = tmp.get(i).toCharArray();
			}
			width = data[0].length; 
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	static int part01(char[][] data, Point move) {
		int cnt = 0;
		Point cur = new Point(0,0);
		while (cur.y < data.length) {
			if (data[cur.y][cur.x] == '#') {
				cnt++;
			}
			cur.translate(move.x,move.y);
			cur.x = cur.x % width;
		}
		return cnt;
	}
	
	static long part02(char[][] data) {
		List<Point> tracks = new ArrayList<>();
		tracks.add(new Point(1,1));
		tracks.add(new Point(3,1));
		tracks.add(new Point(5,1));
		tracks.add(new Point(7,1));
		tracks.add(new Point(1,2));
		
		long res = 1L;
		for(Point p : tracks) {
			res = res * part01(data, p);
		}
		return res;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Part  1: " + part01(data, new Point(3,1)));
		System.out.println("Part  2: " + part02(data));
	}
}
