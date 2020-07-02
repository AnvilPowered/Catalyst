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

package org.anvilpowered.catalyst.common.service;

import com.google.inject.Singleton;
import org.anvilpowered.catalyst.api.service.StaffChatService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Singleton
public class CommonStaffChatService implements StaffChatService {

    public static Set<UUID> staffChatSet = new HashSet<>();

    @Override
    public void add(UUID userUUID) {
        staffChatSet.add(userUUID);
    }

    @Override
    public void remove(UUID userUUID) {
        staffChatSet.remove(userUUID);
    }

    @Override
    public boolean contains(UUID userUUID) {
        return staffChatSet.contains(userUUID);
    }
}
