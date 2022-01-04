import java.util.ArrayList;
import java.util.List;

public class Station {
    List<Packet> buffer = new ArrayList<>(); // empty packet buffer of station.

    // if buffer is not full, inserts packet in buffer, otherwise
    // packet is discarded and returns 0/1 if packet was inserted or not.
    // (0 -> no packet lost, 1 -> 1 packet lost.)
    public int arrivePacket(Packet packet) {
        int bufferfull = 0;
        if (buffer.size() < 5) {
            buffer.add(packet);
        } else {
            bufferfull = 1;
        }

        return bufferfull;
    }

    // station tries to send packet and returns total delay
    public int sendPacket(int sendslot) {
        // initialize delay to -1, to know if buffer was empty and nothing was sent.
        int delay = -1;
        if (buffer.size() > 0) {
            delay = sendslot - buffer.get(0).arriveSlot;
            buffer.remove(0);
        }
        return delay;
    }
}
