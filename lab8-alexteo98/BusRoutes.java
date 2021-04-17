import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Encapsulates the result of a query: for a bus stop and a search string,
 * it stores a map of bus services that servce stops with matching name.
 * e.g., stop 12345, name "MRT", with map contains:
 *    96: 11223 "Clementi MRT"
 *    95:  22334 "Beuno Vista MRT"
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030S AY20/21 Semester 2, Lab 8
 */
class BusRoutes {
  final BusStop stop;
  final String name;
  final CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services;

  /**
   * Constructor for creating a bus route.
   * @param stop The first bus stop.
   * @param name The second bus stop.
   * @param services The set of bus services between the two stops.
   */
  BusRoutes(BusStop stop, String name, CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services) {
    this.stop = stop;
    this.name = name;
    this.services = services;
  }

  /**
   * Return a string describing the bus route.
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public CompletableFuture<String> description() {
    String result = "Search for: " + this.stop + " <-> " + name + ":\n" + "From " +  this.stop + "\n";

    CompletableFuture<String> CFResult = CompletableFuture.<String>supplyAsync(() -> result);
    /*    CompletableFuture<String> identity = CompletableFuture.<String>supplyAsync(() -> "");

          for (BusService service : services.keySet()) {
          CompletableFuture<String> stops = services.get(service)
          .thenApply(x -> describeService(service, x));

          identity.thenCombine(identity, (x, y) -> x + y);

          }*/

   /* CompletableFuture<String> decompose(Map<BusService, CompletableFuture<Set<BusStop>>> services) { 
      CompletableFuture<String> identity = CompletableFuture.<String>supplyAsync(() -> "");

      for (BusService service : services.keySet()) {
        CompletableFuture<String> stops = services.get(service)
          .thenApply(x -> describeService(service, x));

        identity.thenCombine(identity, (x, y) -> x + y);
        return identity;
      }
    }*/
return
    this.services.<String>thenCompose(x -> { 
      CompletableFuture<String> identity = CompletableFuture.<String>supplyAsync(() -> "");

      for (BusService service : x.keySet()) {
        CompletableFuture<String> stops = x.get(service)
          .thenApply(y -> describeService(service, y));
      }
        identity.thenCombine(identity, (a, b) -> a + b);
        return identity;
      
    })
                 .thenCombine(CFResult, (x, y) -> x + y);

    /*
       CompletableFuture.supplyAsync(result);

       CompletableFuture<String> = stops.thenApply(x -> describeService(service, x));

       thenCombine("", (x, y) -> x + describeService() )
       */
    //return CFResult.thenCombine(CFResult, (x, y) -> x + y);
  }

  /**
   * Return a string representation a bus service and its matching stops.
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public String describeService(BusService service, Set<BusStop> stops) {
    if (stops.isEmpty()) {
      return "";
    } 
    return stops.stream()
      .filter(stop -> stop != this.stop) 
      .reduce("- Can take " + service + " to:\n",
          (str, stop) -> str += "  - " + stop + "\n",
          (str1, str2) -> str1 + str2);
  }
}
