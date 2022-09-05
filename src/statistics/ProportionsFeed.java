package statistics;

public class ProportionsFeed {
	int mastered;
	int faulted;
	int failed;

	public int getMastered() {
		return mastered;
	}

	public int getFaulted() {
		return faulted;
	}

	public int getFailed() {
		return failed;
	}

	public void incrementMastered() {
		this.mastered++;
	}

	public void incrementFaulted() {
		this.faulted++;
	}

	public void incrementFailed() {
		this.failed++;
	}
}
