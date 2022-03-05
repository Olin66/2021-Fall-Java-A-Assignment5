import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Search {
    protected TreeMap<String, Flight> map;
    protected final int AIRPORT_FEE = 80;
    protected final int TRANSIT_TIME = 30;

    public void initializeMap(SustechAirline sustechAirline) {
        this.map = sustechAirline.getMap();
    }

    public abstract List<String> searchRoutes(String departPort, String arrivePort);

    public abstract String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan);

}
