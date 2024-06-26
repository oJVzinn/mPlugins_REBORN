package tk.slicecollections.maxteer.lobby;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import tk.slicecollections.maxteer.plugin.config.MConfig;
import tk.slicecollections.maxteer.plugin.config.MWriter;
import tk.slicecollections.maxteer.plugin.config.MWriter.YamlEntry;
import tk.slicecollections.maxteer.plugin.logger.MLogger;
import tk.slicecollections.maxteer.utils.StringUtils;

@SuppressWarnings("rawtypes")
public class Language {

  public static long scoreboards$scroller$every_tick = 1;
  public static List<String> scoreboards$scroller$titles = Arrays
      .asList("§a§lREDE SLICE", "§f§l§6§lR§a§lEDE SLICE", "§f§lR§6§lE§a§lDE SLICE",
          "§f§lRE§6§lD§a§lE SLICE", "§f§lRED§6§lE §a§lSLICE", "§f§lREDE §6§lS§a§lLICE",
          "§f§lREDE S§6§lL§a§lICE", "§f§lREDE SL§6§lI§a§lCE", "§f§lREDE SLI§6§lC§a§lE",
          "§f§lREDE SLIC§6§lE", "§f§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§f§lREDE SLICE", "§f§lREDE SLICE",
          "§f§lREDE SLICE", "§f§lREDE SLICE", "§f§lREDE SLICE", "§f§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§f§lREDE SLICE", "§f§lREDE SLICE", "§f§lREDE SLICE",
          "§f§lREDE SLICE", "§f§lREDE SLICE", "§f§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE",
          "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE", "§a§lREDE SLICE");
  public static List<String> scoreboards$lobby = Arrays
      .asList("", "Grupo: §a%mCore_role%", "Cash: §b%mCore_cash%", "", "Online: §a%mCore_online%",
          "", "§eredeslice.com");

  public static String chat$delay = "§cAguarde mais {time}s para falar novamente.";
  public static String chat$color$default = "§7";
  public static String chat$color$custom = "§f";
  public static String chat$format$lobby = "{player}{color}: {message}";

  public static String lobby$broadcast = "{player} §6entrou no lobby!";

  public static boolean lobby$tab$enabled = true;
  public static String lobby$tab$header = " \n§b§lREDE SLICE\n  §fredeslice.com\n ";
  public static String lobby$tab$footer =
      " \n \n§aForúm: §fredeslice.com/forum\n§aTwitter: §f@RedeSlice\n§aDiscord: §fredeslice.com/discord\n \n                                          §bAdquira VIP acessando: §floja.redeslice.com                                          \n ";

  public static String lobby$npc$deliveries$deliveries = "§c{deliveries} Entrega{s}";
  public static List<String> lobby$npc$deliveries$hologram = Arrays
      .asList("{deliveries}", "§bEntregador", "§e§lCLIQUE DIREITO");

  public static String lobby$npc$deliveries$skin$value =
      "eyJ0aW1lc3RhbXAiOjE1ODM0NTc4OTkzMTksInByb2ZpbGVJZCI6IjIxMWNhN2E4ZWFkYzQ5ZTVhYjBhZjMzMTBlODY0M2NjIiwicHJvZmlsZU5hbWUiOiJNYXh0ZWVyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MWU0NTc3OTgzZjEzZGI2YTRiMWMwNzQ1MGUyNzQ2MTVkMDMyOGUyNmI0MGQ3ZDMyMjA3MjYwOWJmZGQ0YTA4IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=";
  public static String lobby$npc$deliveries$skin$signature =
      "SXnMF3f9x90fa+FdP2rLk/V6/zvMNuZ0sC4RQpPHF9JxdVWYRZm/+DhxkfjCHWKXV/4FSTN8LPPsxXd0XlYSElpi5OaT9/LGhITSK6BbeBfaYhLZnoD0cf9jG9nl9av38KipnkNXI+cO3wttB27J7KHznAmfrJd5bxdO/M0aGQYtwpckchYUBG6pDzaxN7tr4bFxDdxGit8Tx+aow/YtYSQn4VilBIy2y/c2a4PzWEpWyZQ94ypF5ZojvhaSPVl88Fbh+StdgfJUWNN3hNWt31P68KT4Jhx+SkT2LTuDj0jcYsiuxHP6AzZXtOtPPARqM0/xd53CUHCK+TEF5mkbJsG/PZYz/JRR1B1STk4D2cgbhunF87V4NLmCBtF5WDQYid11eO0OnROSUbFduCLj0uJ6QhNRRdhSh54oES7vTi0ja3DftTjdFhPovDAXQxCn+ROhTeSxjW5ZvP6MpmJERCSSihv/11VGIrVRfj2lo9MaxRogQE3tnyMNKWm71IRZQf806hwSgHp+5m2mhfnjYeGRZr44j21zqnSKudDHErPyEavLF83ojuMhNqTTO43ri3MVbMGix4TbIOgB2WDwqlcYLezENBIIkRsYO/Y1r5BWCA7DJ5IlpxIr9TCu39ppVmOGReDWA/Znyox5GP6JIM53kQoTOFBM3QWIQcmXll4=";

  public static final MLogger LOGGER = ((MLogger) Main.getInstance().getLogger())
      .getModule("LANGUAGE");
  private static final MConfig CONFIG = Main.getInstance().getConfig("language");

  public static void setupLanguage() {
    boolean save = false;
    MWriter writer = Main.getInstance().getWriter(CONFIG.getFile(),
        "mLobby - Criado por Maxteer\nVersão da configuração: " + Main.getInstance()
            .getDescription().getVersion());
    for (Field field : Language.class.getDeclaredFields()) {
      if (field.getName().contains("$") && !Modifier.isFinal(field.getModifiers())) {
        String nativeName = field.getName().replace("$", ".").replace("_", "-");

        try {
          Object value = null;

          if (CONFIG.contains(nativeName)) {
            value = CONFIG.get(nativeName);
            if (value instanceof String) {
              value = StringUtils.formatColors((String) value).replace("\\n", "\n");
            } else if (value instanceof List) {
              List l = (List) value;
              List<Object> list = new ArrayList<>(l.size());
              for (Object v : l) {
                if (v instanceof String) {
                  list.add(StringUtils.formatColors((String) v).replace("\\n", "\n"));
                } else {
                  list.add(v);
                }
              }

              l = null;
              value = list;
            }

            field.set(null, value);
            writer.set(nativeName, new YamlEntry(new Object[]{"", CONFIG.get(nativeName)}));
          } else {
            value = field.get(null);
            if (value instanceof String) {
              value = StringUtils.deformatColors((String) value).replace("\n", "\\n");
            } else if (value instanceof List) {
              List l = (List) value;
              List<Object> list = new ArrayList<>(l.size());
              for (Object v : l) {
                if (v instanceof String) {
                  list.add(StringUtils.deformatColors((String) v).replace("\n", "\\n"));
                } else {
                  list.add(v);
                }
              }

              l = null;
              value = list;
            }

            save = true;
            writer.set(nativeName, new YamlEntry(new Object[]{"", value}));
          }
        } catch (ReflectiveOperationException e) {
          LOGGER.log(Level.WARNING, "Unexpected error on settings file: ", e);
        }
      }
    }

    if (save) {
      writer.write();
      CONFIG.reload();
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
          () -> LOGGER.info("A config §6language.yml §afoi modificada ou criada."));
    }
  }
}
