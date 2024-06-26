package tk.slicecollections.maxteer.lobby.lobby;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import tk.slicecollections.maxteer.libraries.holograms.HologramLibrary;
import tk.slicecollections.maxteer.libraries.holograms.api.Hologram;
import tk.slicecollections.maxteer.libraries.npclib.NPCLibrary;
import tk.slicecollections.maxteer.libraries.npclib.api.npc.NPC;
import tk.slicecollections.maxteer.lobby.Main;
import tk.slicecollections.maxteer.lobby.lobby.trait.NPCHandTrait;
import tk.slicecollections.maxteer.lobby.lobby.trait.NPCSkinTrait;
import tk.slicecollections.maxteer.plugin.config.MConfig;
import tk.slicecollections.maxteer.utils.BukkitUtils;
import tk.slicecollections.maxteer.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class PlayNPC {

  private String id;
  private ServerEntry entry;
  private Location location;

  private NPC npc;
  private Hologram hologram;

  public PlayNPC(Location location, String id, ServerEntry entry) {
    this.location = location;
    this.id = id;
    this.entry = entry;
    if (!this.location.getChunk().isLoaded()) {
      this.location.getChunk().load(true);
    }

    this.spawn();
  }

  public void spawn() {
    if (this.npc != null) {
      this.npc.destroy();
      this.npc = null;
    }

    if (this.hologram != null) {
      HologramLibrary.removeHologram(this.hologram);
      this.hologram = null;
    }

    this.hologram = HologramLibrary.createHologram(this.location.clone().add(0, 0.5, 0));
    for (int index = this.entry.listHologramLines().size(); index > 0; index--) {
      this.hologram
          .withLine(this.entry.listHologramLines().get(index - 1).replace("{players}", StringUtils.formatNumber(this.entry.getServerItem().getBalancer().getTotalNumber())));
    }

    this.npc = NPCLibrary.createNPC(EntityType.PLAYER, "§8[NPC] ");
    this.npc.data().set("play-npc", this.entry.getKey());
    this.npc.data().set(NPC.HIDE_BY_TEAMS_KEY, true);
    this.npc.addTrait(new NPCHandTrait(this.npc, this.entry.getHand()));
    this.npc.addTrait(new NPCSkinTrait(this.npc, this.entry.getSkinValue(), this.entry.getSkinSignature()));
    this.npc.spawn(this.location);
  }

  public void update() {
    int size = this.entry.listHologramLines().size();
    for (int index = size; index > 0; index--) {
      this.hologram.updateLine(size - (index - 1),
          this.entry.listHologramLines().get(index - 1).replace("{players}", StringUtils.formatNumber(this.entry.getServerItem().getBalancer().getTotalNumber())));
    }
  }

  public void destroy() {
    this.id = null;
    this.entry = null;
    this.location = null;

    this.npc.destroy();
    this.npc = null;
    HologramLibrary.removeHologram(this.hologram);
    this.hologram = null;
  }

  private static final MConfig CONFIG = Main.getInstance().getConfig("npcs");
  private static final List<PlayNPC> NPCS = new ArrayList<>();

  public static void setupNPCs() {
    ServerEntry.setupEntries();
    if (!CONFIG.contains("play")) {
      CONFIG.set("play", new ArrayList<>());
    }

    for (String serialized : CONFIG.getStringList("play")) {
      if (serialized.split("; ").length > 6) {
        String id = serialized.split("; ")[6];
        ServerEntry entry = ServerEntry.getByKey(serialized.split("; ")[7]);
        if (entry == null) {
          continue;
        }

        NPCS.add(new PlayNPC(BukkitUtils.deserializeLocation(serialized), id, entry));
      }
    }

    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> NPCS.forEach(PlayNPC::update), 20, 20);
  }

  public static void add(String id, Location location, ServerEntry mode) {
    NPCS.add(new PlayNPC(location, id, mode));
    List<String> list = CONFIG.getStringList("play");
    list.add(BukkitUtils.serializeLocation(location) + "; " + id + "; " + mode.getKey());
    CONFIG.set("play", list);
  }

  public static void remove(PlayNPC npc) {
    NPCS.remove(npc);
    List<String> list = CONFIG.getStringList("play");
    list.remove(BukkitUtils.serializeLocation(npc.getLocation()) + "; " + npc.getId() + "; " + npc.getEntry().getKey());
    CONFIG.set("play", list);

    npc.destroy();
  }

  public static PlayNPC getById(String id) {
    return NPCS.stream().filter(npc -> npc.getId().equals(id)).findFirst().orElse(null);
  }

  public static Collection<PlayNPC> listNPCs() {
    return NPCS;
  }
}
