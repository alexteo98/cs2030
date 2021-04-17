import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Alex Teo
 * @version: CS2030S AY20/21 Semester 2, Lab 16A
 */
class BusSg {

  /**
   * Given a bus stop and a name, find the bus services that serve between
   * the given stop and any bus stop with matching mame.
   * @param  stop The bus stop.  Assume to be not null.
   * @param  searchString The (partial) name of other bus stops, assume not null.
   * @return The (optional) bus routes between the stops.
   */
  public static CompletableFuture<BusRoutes> findBusServicesBetween(
      BusStop stop, String searchString) {
    return CompletableFuture.supplyAsync(() -> new BusRoutes(stop, searchString, 
    stop.getBusServices().thenApply(x -> x.stream())
      .thenApply(x -> x.collect(Collectors.toMap(
        service -> service, 
        service -> service.findStopsWith(searchString))))
      .exceptionally(t -> {
        System.out.println("Unable to complete query: " + t); 
        return Map.of();
      })));
  }
}
