package io.magicrc.rom.optimizer;

public class Guest {
    private final int payment;

    public Guest(int payment) {
        this.payment = payment;
    }

    public int amount() {
        return payment;
    }

    public boolean isPremium() {
        return amount() >= 100;
    }
}
