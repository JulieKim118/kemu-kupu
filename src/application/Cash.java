package application;

public class Cash {
	int amount;

	public int getCash() {
		return amount;
	}

	public void setCash(int amount) {
		this.amount = amount;
	}

	public boolean hasEnoughFunds(int amount) {
		return (this.amount >= amount);
	}

	public void deposit(int amount) {
		this.amount += amount;
	}

	public boolean withdraw(int amount) {
		this.amount -= amount;

		if (this.amount >= 0) {
			return true;
		} else {
			this.amount = 0;
			return false;
		}
	}

	public void reset() {
		this.amount = 0;
	}
}
