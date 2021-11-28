import java.util.ArrayList;
import java.util.List;

public class Station {
    List<Packet> buffer = new ArrayList<>(); // empty packet buffer

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

    // station sends packet and returns total latency
    public int sendPacket(int sendslot) {
        int latency = -1;
        if (buffer.size() > 0) {
            latency = sendslot - buffer.get(0).arriveSlot;
            buffer.remove(0);
        }
        return latency;
    }
}
