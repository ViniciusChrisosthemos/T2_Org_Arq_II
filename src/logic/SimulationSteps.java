package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import logic.Set;
import view.Console;

public class SimulationSteps {
	private List<Step> steps;
	private List<Set> associativeSets;
	private ListIterator<Step> currentStep;

	public SimulationSteps(int stepsAmount, List<Set> associativeSets) {
		steps = new ArrayList<>(stepsAmount);
		this.associativeSets = associativeSets;
	}

	public void finishSimulation() {
		while (currentStep.hasNext()) {
			doStep();
		}
	}

	public void startSimulation() {
		currentStep = steps.listIterator();
	}

	public void addStep(Step step) {
		steps.add(step);
	}

	public boolean hasNexT() {
		return currentStep.hasNext();
	}

	public void doStep() {
		Step step = currentStep.next();

		if (step instanceof MissStep) {
			MissStep missStep = (MissStep) step;
			if (missStep.isValidData()) {
				Set set = associativeSets.get(missStep.getAddr().getSet());
				set.replaceLine(missStep.getIndex(), missStep.getAddr().getTag());

				associativeSets.set(missStep.getAddr().getSet(), set);
			} else {
				Set set = associativeSets.get(missStep.getAddr().getSet());
				set.setLine(missStep.getAddr().getTag());

				associativeSets.set(missStep.getAddr().getSet(), set);
			}
		}

		Console.debug(step);
	}

	public String getAssociativeSetsStatus() {
		String status = "[\n";
		for (Set set : associativeSets) {
			status += set + "\n";
		}
		return status + "]";
	}
}
