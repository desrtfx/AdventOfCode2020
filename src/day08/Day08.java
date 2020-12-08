package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Day08 {

	static List<Instruction> program;
	static Map<Integer, Instruction> candidates = new HashMap<>();

	static {
		try {
			program = Files.readAllLines(Paths.get("Input/Day08.txt")).stream().map(s -> {
				String[] t = s.split(" ");
				return new Instruction(t[0], Integer.parseInt(t[1]));
			}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static Optional<Integer> run(List<Instruction> prg, int part) {
		Cpu cpu = new Cpu();
		Set<Integer> visited = new HashSet<>();
		while (true) {
			if ((part == 2) && (cpu.getIp() >= prg.size())) {
				return Optional.of(cpu.getAccu());
			}
			if (!visited.add(cpu.getIp())) {
				if(part == 1) {
					break;
				} else {
					return Optional.empty();
				}
			}
			Instruction inst = prg.get(cpu.getIp());
			if ((part == 1) && (inst.arg() < 0) && ((inst.cmd().equals("jmp") || inst.cmd().equals("nop")))) {
				candidates.put(cpu.getIp(), inst);
			}
			cpu.execute(inst);
		}
		return Optional.of(cpu.getAccu());
	}

	public static int part01(List<Instruction> prg) {
		Optional<Integer> result = run(prg,1);
		if (result.isPresent()) {
			return result.get();
		} else {
			return -1;
		}
	}

	public static int part02(List<Instruction> prg) {
		for (Entry<Integer, Instruction> item : candidates.entrySet()) {
			// preparation
			List<Instruction> testPrg = new ArrayList<>();
			for (int i = 0; i < prg.size(); i++) {
				if (i != item.getKey()) {
					testPrg.add(new Instruction(prg.get(i).cmd(), prg.get(i).arg()));
				} else {
					String cmd = prg.get(i).cmd();
					int arg = prg.get(i).arg();
					if (cmd.equals("jmp")) {
						cmd = "nop";
					} else {
						cmd = "jmp";
					}
					testPrg.add(new Instruction(cmd, arg));
				}
			}

			// program execution
			Optional<Integer> result = run(testPrg,2);
			if (result.isPresent()) {
				return result.get();
			}
		}
		return -1;

	}

	public static void main(String[] args) {
		System.out.println("Part01: " + part01(program));
		System.out.println("Part02: " + part02(program));
	}

}
