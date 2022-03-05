public class Flight {
    private String flightNo;
    private String departPort;
    private String arrivePort;
    private SustechTime departTime;
    private SustechTime arriveTime;
    private int price;

    public Flight(String flightInfo) {
        String[] strings = flightInfo.split("\\s+");
        this.flightNo = strings[0];
        this.departPort = strings[1];
        this.arrivePort = strings[2];
        this.departTime = new SustechTime(strings[3]);
        this.arriveTime = new SustechTime(strings[4]);
        this.price = Integer.parseInt(strings[5]);
    }

    public SustechTime getDepartTime() {
        return departTime;
    }

    public SustechTime getArriveTime() {
        return arriveTime;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getDepartPort() {
        return departPort;
    }

    public String getArrivePort() {
        return arrivePort;
    }

    public int getTimeDifference() {
        return departTime.timeDifference(arriveTime);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return flightNo + " [" + departPort + " -> " + arrivePort + "] " +
                departTime + " -> " + arriveTime + " (" + price + ")";
    }
}
