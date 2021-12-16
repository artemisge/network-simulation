import java.util.ArrayList;
import java.util.List;

public class Main {

  /* function that returns new packet with probability p and saves the time slot
  that it was created. If no packet is to be created, it will return null. */
  public static Packet newPacket(int slot, double probability) {
    if (probability >= Math.random()) {
      Packet packet = new Packet(slot);
      return packet;
    }
    return null;
  }

  /* function that decides if a station will send a new packet during a time slot, 
  depending on the probability and if buffer is already full.*/
  public static boolean willstationsend(Station s) {
    double probability = 0.5;
    return probability >= Math.random() && s.buffer.size() > 0;
  }

  public static void main(String[] args) {
    int totaltimeslots = 10000; // time slots that the simulation will run.
    List<Double> avgdelay = new ArrayList<>(); // average delay for each probability 0.1-1.
    List<Double> throughput = new ArrayList<>(); // throughput for each probability.
    List<Double> pckloss = new ArrayList<>(); // packet loss for each probability.

    // Creates 8 stations.
    Station[] stations = new Station[8];
    for (int i = 0; i < 8; i++) {
      stations[i] = new Station();
    }

    double probability;
    // runs simulation for every probability 0.1-1.
    for (probability = 0.1; probability <= 1; probability += 0.1) {

      int totalpacketlost = 0; // counts how many packets were discarded because buffer was full
      int totalpacketcreated = 0;
      int totalpacketsent = 0;
      int totaldelay = 0;

      // for every time slot
      for (int i = 0; i < totaltimeslots; i++) {
        // for every time slot a packet tries to arrive to every station
        for (Station s : stations) {
          Packet newpacket = newPacket(i, probability); // packet may arrive, otherwise null
          // System.out.println("new packet: " + newpacket);
          if (newpacket != null) {
            totalpacketcreated++;
            // try to add the new packet to station's buffer.
            // If buffer is full, increase the total packets that are lost.
            totalpacketlost += s.arrivePacket(newpacket);
          }
        }

        // for every time slot stations try to send packets away.
        // They're coupled by two since they share wavelength.
        for (int j = 0; j < stations.length; j += 2) {
          boolean s1 = willstationsend(stations[j]);
          boolean s2 = willstationsend(stations[j+1]);
          if (s1 && s2) {
            // collision - nothing happens.
            // System.out.println("COLLISION");
          } else if (s1) {
            // Station 1 will send
            int delay = stations[j].sendPacket(i);
            if (delay >= 0) {
              // System.out.println("station " + j + " will send. "+delay);
              totaldelay += delay;
              totalpacketsent++;
            }
          } else if (s2) {
            // Station 2 will send
            int delay = stations[j + 1].sendPacket(i);
            // check if delay is not -1, because that means buffer was empty and nothing was sent.
            if (delay >= 0) {
              // System.out.println("station " + j+1 + " will send. Latency:" + delay);

              totaldelay += delay;
              totalpacketsent++;
            }
          } else {
            // unlucky, or both buffers empty
            // System.out.println("TIPOTA");
          }
        }

      }

      // round probability due to floating errors.
      System.out.println("p = " + Math.round(probability*10)/10.0 + " total packets created/sent/lost: " +totalpacketcreated +"/" +totalpacketsent+"/"+totalpacketlost);
      // finished calculations and now print stats:
      avgdelay.add((double) totaldelay / totalpacketsent);
      throughput.add((double) totalpacketsent / totaltimeslots);
      pckloss.add((double) totalpacketlost / totalpacketcreated);
    }

    System.out.println("Average Delay: " + avgdelay);
    System.out.println("Throughput: " + throughput);
    System.out.println("Packet loss rate: " + pckloss);
  }

}