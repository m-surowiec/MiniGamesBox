/*
 *  MiniGamesBox - Library box with massive content that could be seen as minigames core.
 *  Copyright (C) 2023 Plugily Projects - maintained by Tigerpanzer_02 and contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package plugily.projects.minigamesbox.classic.utils.hologram;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.minigamesbox.classic.PluginMain;
import plugily.projects.minigamesbox.classic.utils.version.events.api.PlugilyEntityPickupItemEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigerpanzer_02
 * <p>
 * Created at 31.05.2021
 */
public class HologramManager implements Listener {

  private final PluginMain plugin;

  public HologramManager(PluginMain plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  private final List<ArmorStand> armorStands = new ArrayList<>();
  private final List<ArmorStandHologram> holograms = new ArrayList<>();

  public List<ArmorStand> getArmorStands() {
    return armorStands;
  }

  public List<ArmorStandHologram> getHolograms() {
    return holograms;
  }

  @EventHandler
  public void onItemPickup(PlugilyEntityPickupItemEvent event) {
    if(!(event.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) event.getEntity();
    if(plugin.getUserManager().getUser(player).getArena() == null) {
      return;
    }
    for(ArmorStandHologram hologram : holograms) {
      if(!hologram.hasPickupHandler()) {
        continue;
      }
      Item entityItem = hologram.getEntityItem();
      Item item = event.getItem();
      if(item.equals(entityItem)) {
        if(plugin.getUserManager().getUser(player).isSpectator()) {
          return;
        }
        event.setCancelled(true);
        hologram.getPickupHandler().onPickup(player);
        return;
      }
    }
  }

}
