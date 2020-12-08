package day08;

public class Cpu {
	private int ip;
	private int accu;
	
	public Cpu() {
		ip = 0;
		accu = 0;
	}
	
	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public int getAccu() {
		return accu;
	}

	public void setAccu(int accu) {
		this.accu = accu;
	}

	public void execute(Instruction inst) {
		switch(inst.cmd()) {
		case "nop":
			ip++;
			break;
		case "acc":
			accu += inst.arg();
			ip++;
			break;
		case "jmp":
			ip += inst.arg();
			break;
		default:
			break;
		}
	}

}
