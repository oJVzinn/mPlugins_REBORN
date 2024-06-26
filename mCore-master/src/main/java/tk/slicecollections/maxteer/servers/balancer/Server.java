package tk.slicecollections.maxteer.servers.balancer;

import lombok.Getter;
import tk.slicecollections.maxteer.servers.ServerItem;
import tk.slicecollections.maxteer.servers.ServerPing;
import tk.slicecollections.maxteer.servers.balancer.elements.LoadBalancerObject;
import tk.slicecollections.maxteer.servers.balancer.elements.NumberConnection;

import java.net.InetSocketAddress;

/**
 * @author Maxter
 */
public class Server implements LoadBalancerObject, NumberConnection {

  private final ServerPing serverPing;
  @Getter
  private String name;
  private final int max;

  public Server(String ip, String name, int max) {
    this.serverPing = new ServerPing(new InetSocketAddress(ip.split(":")[0], Integer.parseInt(ip.split(":")[1])));
    this.name = name;
    this.max = max;
  }

  public void fetch() {
    this.serverPing.fetch();
    ServerItem.SERVER_COUNT.put(this.name, this.serverPing.getOnline());
  }

  @Override
  public int getActualNumber() {
    return ServerItem.getServerCount(this.name);
  }

  @Override
  public int getMaxNumber() {
    return this.max;
  }

  @Override
  public boolean canBeSelected() {
    return this.serverPing.getMotd() != null && this.getActualNumber() < this.max;
  }
}
