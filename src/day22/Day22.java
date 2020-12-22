package day22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day22 {

	static final int[] P1_START = { 41, 48, 12, 6, 1, 25, 47, 43, 4, 35, 10, 13, 23, 39, 22, 28, 44, 42, 32, 31, 24, 50,
			34, 29, 14 };
	static final int[] P2_START = { 36, 49, 11, 16, 20, 17, 26, 30, 18, 5, 2, 38, 7, 27, 21, 9, 19, 15, 8, 45, 37, 40,
			33, 46, 3 };
	static final int P1_WIN = 1;
	static final int P2_WIN = 2;

	public static long calcScore(List<Integer> player1, List<Integer> player2) {
		List<Integer> winner = (player1.size() == 0 ? player2 : player1);
		return IntStream.range(0, winner.size()).map(i -> ((winner.get(i) * (winner.size() - i)))).sum();
	}

	public static int subGame(List<Integer> player1, List<Integer> player2) {
		Set<List<Integer>> p1Decks = new HashSet<>();
		Set<List<Integer>> p2Decks = new HashSet<>();
		int winner;
		int c1;
		int c2;
		while (player1.size() != 0 && player2.size() != 0) {
			if (p1Decks.contains(player1) || p2Decks.contains(player2)) { // Infinite game rule
				winner = P1_WIN;
				c1 = player1.remove(0);
				c2 = player2.remove(0);
			} else {
				p1Decks.add(player1);
				p2Decks.add(player2);
				c1 = player1.remove(0);
				c2 = player2.remove(0);
				if (c1 <= player1.size() && c2 <= player2.size()) {
					List<Integer> p1 = new ArrayList<>();
					for (int i = 0; i < c1; i++) {
						p1.add(player1.get(i));
					}
					List<Integer> p2 = new ArrayList<>();
					for (int i = 0; i < c2; i++) {
						p2.add(player2.get(i));
					}
					winner = subGame(p1, p2);
				} else {
					winner = (c1 > c2) ? P1_WIN : P2_WIN;
				}
			}
			if (winner == P1_WIN) {
				player1.addAll(List.of(c1, c2));
			} else {
				player2.addAll(List.of(c2, c1));
			}
		}
		return (player1.size() == 0) ? P2_WIN : P1_WIN;
	}

	public static long part01() {
		List<Integer> player1 = Arrays.stream(P1_START).boxed().collect(Collectors.toList());
		List<Integer> player2 = Arrays.stream(P2_START).boxed().collect(Collectors.toList());
		while (player1.size() > 0 && player2.size() > 0) {
			int c1 = player1.remove(0);
			int c2 = player2.remove(0);
			if (c1 > c2) {
				player1.addAll(List.of(c1, c2));
			} else {
				player2.addAll(List.of(c2, c1));
			}
		}
		return calcScore(player1, player2);
	}

	public static long part02() {
		List<Integer> player1 = Arrays.stream(P1_START).boxed().collect(Collectors.toList());
		List<Integer> player2 = Arrays.stream(P2_START).boxed().collect(Collectors.toList());
		while ((player1.size() != 0) && (player2.size() != 0)) {
			subGame(player1, player2);
		}
		return calcScore(player1, player2);
	}

	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
		System.out.println("Part 02: " + part02());
	}

}
