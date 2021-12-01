import java.util.ArrayList;
import java.util.List;

public class Main {

  // return new packet with probability p and save the time slot
  // that it was created
  public static Packet newPacket(int slot, double probability) {
    if (probability >= Math.random()) {
      Packet packet = new Packet(slot);
      return packet;
    }
    return null;
  }

  public static boolean willstationsend() {
    double probability = 0.5;
    return probability >= Math.random();
  }

  public static void main(String[] args) {
    int totaltimeslots = 5000;
    List<Double> avgdelay = new ArrayList<>();
    List<Double> throughput = new ArrayList<>();
    List<Double> pckloss = new ArrayList<>();

    Station[] stations = new Station[8];
    for (int i = 0; i < 8; i++) {
      stations[i] = new Station();
    }

    double probability;
    for (probability = 0.1; probability <= 1; probability += 0.1) {

      int totalpacketlost = 0; // counts how many packets were discarded because buffer was full
      int totalpacketcreated = 0;
      int totalpacketsent = 0;
      int totaldelay = 0;

      for (int i = 0; i < totaltimeslots; i++) {
        // for every time slot a packet tries to arrive to every station
        for (Station s : stations) {
          Packet newpacket = newPacket(i, probability); // packet may arrive, otherwise null
          // System.out.println("new packet: " + newpacket);
          if (newpacket != null) {
            totalpacketcreated++;
            totalpacketlost += s.arrivePacket(newpacket);
          }
        }

        // for every time slot stations try to send packets away
        // they're coupled by two since they share wavelength
        for (int j = 0; j < stations.length; j += 2) {
          boolean s1 = willstationsend();
          boolean s2 = willstationsend();
          if (s1 && s2) {
            // collision
            // System.out.println("COLLISION");
          } else if (s1) {
            int delay = stations[j].sendPacket(i);
            if (delay >= 0) {
              // System.out.println("station " + j + " will send. "+delay);
              totaldelay += delay;
              totalpacketsent++;
            }
          } else if (s2) {
            int delay = stations[j + 1].sendPacket(i);
            if (delay >= 0) {
              // System.out.println("station " + j+1 + " will send. Latency:" + delay);

              totaldelay += delay;
              totalpacketsent++;
            }
          }
        }

      }
      // System.out.println("total packets created/sent/lost: " +totalpacketcreated
      // +"/" +totalpacketsent+"/"+totalpacketlost);
      // finished calculations and now print stats:
      avgdelay.add((double) totaldelay / totalpacketsent);
      throughput.add((double) totalpacketsent / totaltimeslots);
      pckloss.add((double) totalpacketlost / totalpacketcreated);
      // System.out.println("With package arrival probability of " + probability +
      // ":");

    }
    System.out.println("Average Delay: " + avgdelay);
    System.out.println("Throughput: " + throughput);
    System.out.println("Packet loss rate: " + pckloss);
  }

}