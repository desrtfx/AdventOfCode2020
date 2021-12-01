package day24;

public class Day24 {

	static class HexNode {
		public HexNode east = null;
		public HexNode southeast = null;
		public HexNode southwest = null;
		public HexNode west = null;
		public HexNode northwest = null;
		public HexNode northeast = null;
		char color = 'w';
		
		public void flip( ) {
			color = (color == 'w') ? 'b' : 'w';
		}
	}
	
	public enum Dirs {E, SE, SW, W, NW, NE };
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
