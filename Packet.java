import java.lang.reflect.Constructor;

public class Packet {
    int arriveSlot;

    // Packet class that keeps the time slot that each packet arrived.
    public Packet(int slot) {
        this.arriveSlot = slot;
    }
}
