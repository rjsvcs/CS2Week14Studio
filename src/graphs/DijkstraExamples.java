package graphs;

/**
 * Demonstrates Dijkstra's Shortest Path using the examples from lecture.
 */
public class DijkstraExamples {
    /**
     * Creates and runs several of the examples form lecture.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Graph<String> small = new Graph<>();

        small.addValues("A", "B", "C", "D", "E", "F", "G");

        small.connectUndirected("A", "B", 6);
        small.connectUndirected("A", "C", 1);
        small.connectUndirected("A", "D", 2);
        small.connectUndirected("B", "C", 2);
        small.connectUndirected("B", "F", 5);
        small.connectUndirected("B", "G", 4);
        small.connectUndirected("C", "G", 5);
        small.connectUndirected("D", "F", 7);
        small.connectUndirected("E", "F", 4);
        small.connectUndirected("E", "G", 4);

        System.out.println(small.dijkstrasShortestPath("A", "E"));

        Graph<String> big = new Graph<>();
        big.addValues("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K");
        big.connectUndirected("A", "B", 3);
        big.connectUndirected("A", "C", 5);
        big.connectUndirected("B", "D", 8);
        big.connectUndirected("B", "E", 1);
        big.connectUndirected("C", "D", 12);
        big.connectUndirected("C", "E", 11);
        big.connectUndirected("C", "H", 7);
        big.connectUndirected("D", "F", 7);
        big.connectUndirected("E", "F", 2);
        big.connectUndirected("F", "G", 10);
        big.connectUndirected("I", "J", 9);
        big.connectUndirected("I", "K", 2);
        big.connectUndirected("J", "K", 4);

        System.out.println(big.dijkstrasShortestPath("A", "G"));
        System.out.println(big.dijkstrasShortestPath("A", "K"));

        Graph<String> airports = new Graph<>();
        airports.addValues("ROC", "DCA", "ORD", "ATL", "MCO", "PDX", "SFO",
                "LAX");
        airports.connectUndirected("ROC", "DCA", 10);
        airports.connectUndirected("ROC", "ORD", 15);
        airports.connectUndirected("ROC", "ATL", 22);
        airports.connectUndirected("ROC", "MCO", 24);
        airports.connectUndirected("DCA", "SFO", 25);
        airports.connectUndirected("DCA", "LAX", 34);
        airports.connectUndirected("ORD", "PDX", 26);
        airports.connectUndirected("ORD", "SFO", 37);
        airports.connectUndirected("ORD", "LAX", 35);
        airports.connectUndirected("ATL", "LAX", 31);
        airports.connectUndirected("MCO", "LAX", 31);
        airports.connectUndirected("PDX", "LAX", 21);
        airports.connectUndirected("SFO", "LAX", 10);

        System.out.println(airports.dijkstrasShortestPath("ROC", "LAX"));
    }
}
