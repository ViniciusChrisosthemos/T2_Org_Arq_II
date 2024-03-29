package logic;

import java.util.ArrayList;
import java.util.List;

public class Set {

	private List<Line> lines;
	private int ways;

	public Set(int ways) {
		this.ways = ways;
		lines = new ArrayList<>(ways);

		for (int i = 0; i < ways; i++) {
			lines.add(new Line());
		}
	}

	public int setLine(int tag) {
		int index = 0;
		for (Line line : lines) {
			if (!line.isValid()) {
				line.setLine(tag);
				line.setValidateBit(true);
				line.setAccesses(1);
				break;
			}
			index++;
		}

		return index;
	}

	public void replaceLine(int index, int tag) {
		Line line = lines.get(index);
		line.setLine(tag);
		line.setAccesses(1);
		lines.set(index, line);
	}

	public boolean findAddress(int tag, int accessId) {
		for (Line line : lines) {
			if (line.isValid()) {
				if (tag == line.getTag()) {
					line.addAccesses();
					line.setLastAccess(accessId);
					return true;
				}
			}
		}

		return false;
	}

	public List<Line> getLines() {
		return lines;
	}

	public int getWays() {
		return ways;
	}

	public int getIndex(int tag) {
		int index = 0;
		for (Line line : lines) {
			if (line.getTag() == tag)
				return index;
			else
				index++;
		}

		return index;
	}

	public boolean isFull() {
		return lines.stream().allMatch(line -> line.isValid());
	}

	@Override
	public String toString() {
		String string = "";
		for (Line line : lines) {
			string += "\n   " + line.toString();
		}

		return string + "\n";
	}
}
