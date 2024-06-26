package tk.slicecollections.maxteer.lobby.cmd.ml;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.slicecollections.maxteer.lobby.cmd.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand extends SubCommand {

  private static final List<String> BUILDERS = new ArrayList<>();

  public BuildCommand() {
    super("build", "build", "Ativar/Desativar modo construtor.", true);
  }

  @Override
  public void perform(Player player, String[] args) {
    if (BUILDERS.contains(player.getName())) {
      BUILDERS.remove(player.getName());
      player.setGameMode(GameMode.ADVENTURE);
      player.sendMessage("§cModo construtor desativado.");
    } else {
      BUILDERS.add(player.getName());
      player.setGameMode(GameMode.CREATIVE);
      player.sendMessage("§aModo construtor ativado.");
    }
  }

  public static void remove(Player player) {
    BUILDERS.remove(player.getName());
  }

  public static boolean isBuilder(Player player) {
    return BUILDERS.contains(player.getName());
  }
}
