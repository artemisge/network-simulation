import java.util.List;

public class Station {
    List<Packet> buffer; // empty packet buffer

    // if buffer is not full, inserts packet in buffer, otherwise
    // packet is discarded and returns if packet was inserted or not.
    public int arrivePacket(Packet packet) {
        int bufferfull = 0;

        if (buffer.size() < 5) {
            buffer.add(packet);
        } else {
            bufferfull = 1;
        }

        return bufferfull;
    }

    public void sendPacket() {
        if (buffer.size() > 0)
            buffer.remove(0);
    }
}
