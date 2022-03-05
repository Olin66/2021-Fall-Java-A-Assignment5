import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class SustechAirline implements Airline {
    private TreeMap<String, Flight> map = new TreeMap<>();

    public TreeMap<String, Flight> getMap() {
        return map;
    }

    @Override
    public void addFlight(String flightInfo) {
        Flight flight = new Flight(flightInfo);
        if (!map.containsKey(flight.getFlightNo()))
            map.put(flight.getFlightNo(), flight);
    }

    @Override
    public String getFlightInfo(String flightNo) {
        return map.containsKey(flightNo) ? map.get(flightNo).toString() : null;
    }

    @Override
    public List<String> getFlightRoute() {
        TreeMap<String, List<String>> ports = new TreeMap<>();
        for (Flight flight : map.values()) {
            if (!ports.containsKey(flight.getDepartPort()))
                ports.put(flight.getDepartPort(), new ArrayList<>());
            ports.get(flight.getDepartPort()).add(flight.getArrivePort());
        }
        for (List<String> list : ports.values()) Collections.sort(list);
        List<String> list = new ArrayList<>();
        for (String key : ports.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(key).append("->");
            List<String> strings = ports.get(key);
            for (int i = 0; i < strings.size(); i++) {
                sb.append(strings.get(i));
                if (i != strings.size() - 1) sb.append(" ");
            }
            list.add(sb.toString());
        }
        return list;
    }

    @Override
    public boolean removeFlight(String flightNo) {
        if (!map.containsKey(flightNo)) return false;
        map.remove(flightNo);
        return true;
    }

    @Override
    public List<Flight> getAllFlightsAboutDepartPort(String departPort) {
        if (departPort == null) return null;
        TreeMap<String, Flight> flights = new TreeMap<>();
        for (Flight flight : map.values()) {
            if (flight.getDepartPort().equals(departPort))
                flights.put(flight.getFlightNo(), flight);
        }
        return flights.values().stream().toList();
    }

    @Override
    public boolean isContainsFlight(String departPort, String arrivePort) {
        if (departPort == null || arrivePort == null) return false;
        for (Flight flight : map.values())
            if (departPort.equals(flight.getDepartPort()) && arrivePort.equals(flight.getArrivePort()))
                return true;
        return false;
    }

    @Override
    public boolean isRoundTrip(String port1, String port2) {
        if (port1 == null || port2 == null) return false;
        return isContainsFlight(port1, port2) && isContainsFlight(port2, port1);
    }

    @Override
    public List<String> searchAllRoutes(String departPort, String arrivePort, Search search) {
        search.initializeMap(this);
        return search.searchRoutes(departPort, arrivePort);
    }
    @Override
    public String searchBestRoute(String departPort, String arrivePort, Search search, SearchPlan searchPlan) {
        search.initializeMap(this);
        return search.searchBestRoute(departPort, arrivePort, searchPlan);
    }
}
