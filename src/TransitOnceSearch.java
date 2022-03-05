import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransitOnceSearch extends Search {

    @Override
    public List<String> searchRoutes(String departPort, String arrivePort) {
        List<String> list = new ArrayList<>();
        for (Flight flight : map.values()) {
            if (flight.getArrivePort().equals(arrivePort)) {
                for (Flight flight1 : map.values()) {
                    if (flight1.getDepartPort().equals(departPort) &&
                            flight1.getArrivePort().equals(flight.getDepartPort()) &&
                            flight1.getArriveTime().isTimeValid(flight.getDepartTime()))
                        list.add(flight1 + "\t" + flight);
                }
            }
        }
        return list;
    }

    @Override
    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
        String s = null;
        int minPrice = Integer.MAX_VALUE;
        int minTime = 1440;
        for (Flight flight : map.values()) {
            if (flight.getArrivePort().equals(arrivePort)) {
                for (Flight flight1 : map.values()) {
                    if (flight1.getDepartPort().equals(departPort) &&
                            flight1.getArrivePort().equals(flight.getDepartPort()) &&
                            flight1.getArriveTime().isTimeValid(flight.getDepartTime())) {
                        if (searchPlan == SearchPlan.LESS_PRICE &&
                                flight.getPrice() + flight1.getPrice() <= minPrice) {
                            if (flight.getPrice() + flight1.getPrice() == minPrice &&
                                    Objects.requireNonNull(s).compareTo(flight1 + "\t" + flight) > 0) {
                                minPrice = flight.getPrice() + flight1.getPrice();
                                s = flight1 + "\t" + flight;
                            } else if (flight.getPrice() + flight1.getPrice() < minPrice) {
                                minPrice = flight.getPrice() + flight1.getPrice();
                                s = flight1 + "\t" + flight;
                            }
                        }
                        if (searchPlan == SearchPlan.LESS_TIME &&
                                flight1.getDepartTime().timeDifference(flight.getArriveTime()) <= minTime) {
                            if (flight1.getDepartTime().timeDifference(flight.getArriveTime()) == minTime &&
                                    Objects.requireNonNull(s).compareTo(flight1 + "\t" + flight) > 0) {
                                minTime = flight1.getDepartTime().timeDifference(flight.getArriveTime());
                                s = flight1 + "\t" + flight;
                            } else if (flight1.getDepartTime().timeDifference(flight.getArriveTime()) < minTime) {
                                minTime = flight1.getDepartTime().timeDifference(flight.getArriveTime());
                                s = flight1 + "\t" + flight;
                            }
                        }
                    }
                }
            }
        }
        return s;
    }
}
