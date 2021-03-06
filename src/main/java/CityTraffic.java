import java.util.*;

public class CityTraffic {

//    Challenge
//    Have the function CityTraffic(strArr) read strArr which will be a representation of an undirected graph
//    in a form similar to an adjacency list. Each element in the input will contain an integer which will
//    represent the population for that city, and then that will be followed by a comma separated list of
//    its neighboring cities and their populations (these will be separated by a colon).
//    For example: strArr may be
//["1:[5]", "4:[5]", "3:[5]", "5:[1,4,3,2]", "2:[5,15,7]", "7:[2,8]", "8:[7,38]", "15:[2]", "38:[8]"].
//    This graph then looks like the following picture:
//
//    Each node represents the population of that city and each edge represents a road to that city.
//    Your goal is to determine the maximum traffic that would occur via a single road if everyone decided to go to that city.
//    For example: if every single person in all the cities decided to go to city 7, then via the upper road the
//    number of people coming in would be (8 + 38) = 46. If all the cities beneath city 7 decided to go to it via
//    the lower road, the number of people coming in would be (2 + 15 + 1 + 3 + 4 + 5) = 30.
//    So the maximum traffic coming into the city 7 would be 46 because the maximum value of (30, 46) = 46.
//    Your program should determine the maximum traffic for every single city and return the answers in a
//    comma separated string in the format: city:max_traffic,city:max_traffic,...
//    The cities should be outputted in sorted order by the city number.
//    For the above example, the output would therefore be:
//            1:82,2:53,3:80,4:79,5:70,7:46,8:38,15:68,38:45.
//    The cities will all be unique positive integers and there will not be any cycles in the graph.
//    There will always be at least 2 cities in the graph.
//    Hard challenges are worth 15 points and you are not timed for them.
    private final HashMap<Integer,City> cities;
    public CityTraffic(){
        this.cities= new HashMap<>();
    }
    HashMap<Integer, Integer> findMaximumTraffic(String[] strArr) {
        var response=new HashMap<Integer,Integer>();
        //"1:[5]", "4:[5]", "3:[5]", "5:[1,4,3,2]", "2:[5,15,7]", "7:[2,8]", "8:[7,38]", "15:[2]", "38:[8]"
        for (String text : strArr) {
            var node = text.split(":");
            var neighbors = findNeighbors(node[1]);
            var city = new City(Integer.parseInt(node[0]));
            city.setNeighbors(neighbors);
            cities.put(city.population,city);
        }
        for (var city : cities.entrySet()) {
            var maximumTraffic=getMaximumTraffic(city.getValue());
            response.put(city.getKey(),maximumTraffic);
        }
        return response;
    }
    private List<Integer> findNeighbors(String neighborsStr){
        var neighbors=new ArrayList<Integer>();
        var index=neighborsStr.replace("[","").replace("]","");
        for(var neighbor:index.split(",")) {
            neighbors.add(Integer.parseInt(neighbor));
        }
        return neighbors;
    }

    private int getMaximumTraffic(City parent) {
        int maximum = 0;
        for(Integer p: parent.neighbors) {
            var neighbor=cities.get(p);
            int traffic=getTrafficTo(neighbor,parent);
            if (traffic > maximum) maximum = traffic;
        }
        return maximum;
    }

    private int getTrafficTo(City c,City parentCity) {

        if(parentCity.trafficCache.containsKey(c.population)) {
            return parentCity.trafficCache.get(c.population);
        }
        int traffic=0;
        for(Integer neighborPop:c.neighbors) {
            if(neighborPop!=parentCity.population) {
                var neighbor=cities.get(neighborPop);
                traffic+=getTrafficTo(neighbor,c);
            }
        }
        traffic += c.population;

        parentCity.trafficCache.put(c.population,traffic);
        return traffic;
    }

    static class City {
        private final int population;
        private List<Integer> neighbors;
        private final Map<Integer, Integer> trafficCache;
        public City(int population) {
            this.population = population;
            neighbors = new ArrayList<>();
            trafficCache = new HashMap<>();
        }
        public void setNeighbors(List<Integer> neighbors) {
            this.neighbors = neighbors;
        }
    }
}
