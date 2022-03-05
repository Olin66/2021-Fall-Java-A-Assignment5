import java.util.ArrayList;
import java.util.List;

public class DirectSearch extends Search {

    @Override
    public List<String> searchRoutes(String departPort, String arrivePort) {
        List<String> list = new ArrayList<>();
        for (Flight flight : map.values()) {
            if (flight.getDepartPort().equals(departPort) &&
                    flight.getArrivePort().equals(arrivePort))
                list.add(flight.toString());
        }
        return list;
    }

    @Override
    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
        for (Flight flight : map.values()) {
            if (flight.getDepartPort().equals(departPort) &&
                    flight.getArrivePort().equals(arrivePort))
                return flight.toString();
        }
        return null;
    }
}
