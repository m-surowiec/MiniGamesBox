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

package plugily.projects.minigamesbox.classic.commands.arguments.data;

import java.util.List;

/**
 * @author Tigerpanzer_02
 * <p>
 * Created at 01.11.2021
 */
public class LabeledCommandArgument extends CommandArgument {

  private final LabelData labelData;

  public LabeledCommandArgument(String argumentName, List<String> permissions, ExecutorType validExecutors, LabelData labelData) {
    super(argumentName, permissions, validExecutors);
    this.labelData = labelData;
  }

  public LabeledCommandArgument(String argumentName, String permissions, ExecutorType validExecutors, LabelData labelData) {
    super(argumentName, permissions, validExecutors);
    this.labelData = labelData;
  }

  /**
   * @return label data of command (description and usages of command)
   */
  public LabelData getLabelData() {
    return labelData;
  }
}
