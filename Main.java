public class Main {

  // return new packet with probability p and save the time slot
  // that it was created
  public static Packet newPacket(int slot) {
    double probability = 0.8;

    if (probability >= Math.random()) {
      Packet packet = new Packet(slot);
      return packet;
    }
    return null;
  }

  public static void main(String[] args) {
    int totaltimeslots = 5000;
    Station stations[] = new Station[8];
    int arrivalFail = 0; // counts how many packets were discarded because buffer was full

    for (int i = 0; i < totaltimeslots; i++) {
      // for every time slot a packet tries to arrive to every station
      for (Station s : stations) {
        Packet newpacket = newPacket(i);
        if (newpacket != null) {
          arrivalFail += s.arrivePacket(newpacket);
        }
      }
    
      // for every time slot stations try to send packets away
    }
  }

}