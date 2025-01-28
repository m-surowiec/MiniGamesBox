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

package plugily.projects.minigamesbox.classic.handlers.party;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import plugily.projects.minigamesbox.classic.PluginMain;

/**
 * @author Tigerpanzer_02
 * <p>
 * Created at 21.09.2021
 */
public class PartySupportInitializer {

  //todo internal party manager
  public PartyHandler initialize(PluginMain plugin) {
    PartyHandler partyHandler;
    if(!plugin.getConfigPreferences().getOption("EXTERNAL_PARTIES")) {
      if(Bukkit.getServer().getPluginManager().getPlugin("Parties") != null) {
        return new PartiesPartyHandlerImpl();
      }
      if(Bukkit.getServer().getPluginManager().getPlugin("Spigot-Party-API-PAF") != null) {
        return new PAFBPartyHandlerImpl();
      } else if(Bukkit.getServer().getPluginManager().getPlugin("PartyAndFriends") != null) {
        return new PAFSPartyHandlerImpl();
      }
    }
    partyHandler = new PartyHandler() {
      @Override
      public GameParty getParty(Player player) {
        return null;
      }

      @Override
      public boolean partiesSupported() {
        return false;
      }

      @Override
      public PartyPluginType getPartyPluginType() {
        return PartyPluginType.NONE;
      }
    };
    return partyHandler;
  }

}
