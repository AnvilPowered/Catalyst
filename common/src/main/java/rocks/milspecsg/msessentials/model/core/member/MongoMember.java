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

package rocks.milspecsg.msessentials.model.core.member;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import rocks.milspecsg.msrepository.model.data.dbo.MongoDbo;

import java.util.Date;
import java.util.UUID;

@Entity("members")
public class MongoMember extends MongoDbo implements Member<ObjectId> {

    private UUID userUUID;
    private String nickname;
    private boolean isBanned;
    private String ipAddress;
    private Date joinedUtc;
    private Date lastSeenUtc;
    private String userName;
    private String banReason;
    private boolean muteStatus;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setMuteStatus(boolean muteStatus) {
        this.muteStatus = muteStatus;
    }

    @Override
    public boolean getMuteStatus() {
        return muteStatus;
    }

    @Override
    public void setUserName(String username) {
        this.userName = username;
    }

    @Override
    public UUID getUserUUID() {
        return userUUID;
    }

    @Override
    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    @Override
    public String getNickName() {
        return nickname;
    }

    @Override
    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean getBanStatus() {
        return isBanned;
    }

    @Override
    public void setBanStatus(boolean banned) {
        this.isBanned = banned;
    }

    @Override
    public String getIPAddress() {
        return ipAddress;
    }

    @Override
    public void setIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public Date getJoinedUtc() {
        return joinedUtc;
    }

    @Override
    public void setJoinedUtc(Date joinDate) {
        this.joinedUtc = joinDate;
    }

    @Override
    public Date getLastSeenUtc() {
        return lastSeenUtc;
    }

    @Override
    public void setLastSeenUtc(Date lastSeen) {
        this.lastSeenUtc = lastSeen;
    }

    public String getBanReason() {
        return banReason;
    }

    @Override
    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }
}
