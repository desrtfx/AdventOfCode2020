package day12;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
	
	static final Map<Character,Point> DIRS = new HashMap<>();
	static final Map<Character,Character> LEFT = new HashMap<>();
	static final Map<Character, Character> RIGHT = new HashMap<>();
	static List<String> data = new ArrayList<>();
	static Point pos;
	static Point way;
	static char heading;
	
	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day12.txt"));

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		DIRS.put('N', new Point( 0, -1));
		DIRS.put('E', new Point( 1,  0));
		DIRS.put('S', new Point( 0,  1));
		DIRS.put('W', new Point(-1 , 0));
		LEFT.put('E', 'N');
		LEFT.put('N', 'W');
		LEFT.put('W', 'S');
		LEFT.put('S', 'E');
		RIGHT.put('E', 'S');
		RIGHT.put('S', 'W');
		RIGHT.put('W', 'N');
		RIGHT.put('N', 'E');
	}
	
	public static void travel(String cmd) {
		char dir = cmd.charAt(0);
		int offs = Integer.parseInt(cmd.substring(1));
		int dx;
		int dy;
		switch(dir) {
		case 'F':
			dx=DIRS.get(heading).x * offs;
			dy=DIRS.get(heading).y * offs;
			pos.translate(dx, dy);
			break;
		case 'R':
		case 'L':
			int steps = offs/90;
			for(int i = 0; i < steps; i++) {
				heading = (dir=='R') ? RIGHT.get(heading) : LEFT.get(heading);
			}
			break;
		case 'N':
		case 'E':
		case 'S':
		case 'W':
			dx=DIRS.get(dir).x * offs;
			dy=DIRS.get(dir).y * offs;
			pos.translate(dx, dy);
			break;
		default:
			break;
		}
	}

	public static void travel2(String cmd) {
		char dir = cmd.charAt(0);
		int offs = Integer.parseInt(cmd.substring(1));
		int dx;
		int dy;
		switch(dir) {
		case 'F':
			for(int i = 0; i < offs; i++) {
				pos.translate(way.x, way.y);
			}
			break;
		case 'R':
		case 'L':
			int steps = offs/90;
			int sgn = (dir == 'R') ? -1 : 1;
			for(int i = 0; i < steps; i++) {
					int tmp = way.x;
					way.x = way.y * sgn;
					way.y = tmp * (sgn * -1);
			}
			break;
		case 'N':
		case 'E':
		case 'S':
		case 'W':
			dx=DIRS.get(dir).x * offs;
			dy=DIRS.get(dir).y * offs;
			way.translate(dx, dy);
			break;
		default:
			break;
		}
	}
	
	
	public static int part01() {
		pos = new Point(0,0);
		heading = 'E';
		for(String s : data) {
			travel(s);
		}
		return (Math.abs(pos.x) + Math.abs(pos.y));
	}
	
	public static int part02() {
		pos = new Point(0,0);
		way = new Point(10,-1);
		heading = 'E';
		for( String s : data) {
			travel2(s);
		}
		return (Math.abs(pos.x) + Math.abs(pos.y));
		
	}
	

	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
		System.out.println("Part 02: " + part02());

	}

}
