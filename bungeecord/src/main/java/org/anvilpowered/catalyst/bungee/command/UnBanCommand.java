/*
 *     Copyright (C) 2020 STG_Allen
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.anvilpowered.catalyst.bungee.command;

import com.google.inject.Inject;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import org.anvilpowered.catalyst.common.command.CommonUnBanCommand;

public class UnBanCommand extends Command {

    @Inject
    private CommonUnBanCommand<
        TextComponent,
        CommandSender,
        CommandSender> unBanCommand;

    public UnBanCommand() {
        super("unban", "", "pardon");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        unBanCommand.execute(sender, sender, args);
    }
}
