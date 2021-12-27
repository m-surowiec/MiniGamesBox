/*
 * MiniGamesBox - Library box with massive content that could be seen as minigames core.
 * Copyright (C)  2021  Plugily Projects - maintained by Tigerpanzer_02 and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


package plugily.projects.minigamesbox.classic.handlers.hologram;

import org.bukkit.configuration.file.FileConfiguration;
import plugily.projects.minigamesbox.classic.PluginMain;
import plugily.projects.minigamesbox.classic.utils.configuration.ConfigUtils;
import plugily.projects.minigamesbox.classic.utils.serialization.LocationSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigerpanzer_02
 * <p>
 * Created at 09.10.2021
 */
public class LeaderboardRegistry {

  private final List<LeaderboardHologram> leaderboardHolograms = new ArrayList<>();
  private final PluginMain plugin;

  public LeaderboardRegistry(PluginMain plugin) {
    this.plugin = plugin;
    registerHolograms();
  }

  private void registerHolograms() {
    FileConfiguration config = ConfigUtils.getConfig(plugin, "internal/leaderboards_data");
    org.bukkit.configuration.ConfigurationSection section = config.getConfigurationSection("holograms");
    if(section == null) {
      return;
    }

    for(String key : section.getKeys(false)) {
      LeaderboardHologram hologram;

      try {
        hologram = new LeaderboardHologram(plugin, Integer.parseInt(key), plugin.getStatsStorage().getStatisticType(section.getString(key + ".statistics")),
            section.getInt(key + ".top-amount"), LocationSerializer.getLocation(section.getString(key + ".location", "")));
      } catch(IllegalArgumentException ex) {
        continue;
      }

      hologram.initUpdateTask();
      registerHologram(hologram);
    }
  }

  public void registerHologram(LeaderboardHologram hologram) {
    leaderboardHolograms.add(hologram);
  }

  public void disableHologram(int id) {
    for(LeaderboardHologram hologram : leaderboardHolograms) {
      if(hologram.getId() == id) {
        hologram.cancel();
        return;
      }
    }
  }

  public void disableHolograms() {
    leaderboardHolograms.forEach(LeaderboardHologram::cancel);
  }

}
