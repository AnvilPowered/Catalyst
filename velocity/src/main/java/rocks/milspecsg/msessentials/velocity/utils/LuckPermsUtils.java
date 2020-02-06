/*
 *     MSEssentials - MilSpecSG
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

package rocks.milspecsg.msessentials.velocity.utils;

import com.velocitypowered.api.proxy.Player;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import rocks.milspecsg.msessentials.velocity.plugin.MSEssentials;

import java.util.Optional;
import java.util.UUID;

public class LuckPermsUtils {

    public static String getPrefix(Player player) {
        if (getMetaData(player).isPresent()) {
            if (getMetaData(player).get().getPrefix() == null) {
                return "";
            }
            return getMetaData(player).get().getPrefix();
        }
        return "";
    }

    public static String getSuffix(Player player) {
        if (getMetaData(player).isPresent()) {
            if (getMetaData(player).get().getSuffix() == null) {
                return "";
            }
            return getMetaData(player).get().getSuffix();
        }
        return "";
    }

    private static Optional<CachedMetaData> getMetaData(Player player) {
        UUID playerUUID = player.getUniqueId();
        User tempUser = MSEssentials.api.getUserManager().getUser(playerUUID);

        return Optional.ofNullable(tempUser).map(User::getCachedData)
            .map(data -> data.getMetaData(getQueryOptions(Optional.of(tempUser))));
    }

    public static String getNameColor(Player player) {
        if (getMetaData(player).isPresent()) {
            if (getMetaData(player).get().getMetaValue("name-color") != null) {
                return getMetaData(player).get().getMetaValue("name-color");
            } else {
                return "";
            }
        }
        return "";
    }

    public static String getChatColor(Player player) {
        if (getMetaData(player).isPresent()) {
            if (getMetaData(player).get().getMetaValue("chat-color") != null) {
                return getMetaData(player).get().getMetaValue("chat-color");
            } else {
                return "";
            }
        }
        return "";
    }

    private static QueryOptions getQueryOptions(Optional<User> user) {
        final ContextManager contextManager = MSEssentials.api.getContextManager();
        return user.flatMap(contextManager::getQueryOptions)
            .orElseGet(contextManager::getStaticQueryOptions);
    }
}
