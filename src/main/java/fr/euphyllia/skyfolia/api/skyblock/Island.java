package fr.euphyllia.skyfolia.api.skyblock;

import fr.euphyllia.skyfolia.api.skyblock.model.Position;
import fr.euphyllia.skyfolia.configuration.ConfigToml;
import org.bukkit.Location;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Island {

    private final Position position;
    private final UUID islandId;
    private String islandType;
    private final ConcurrentHashMap<String, Location> warps;
    private boolean privateIsland;
    private final CopyOnWriteArrayList<Players> members;
    private boolean disable;
    private final Timestamp createDate;
    private UUID ownerId;

    public Island(String islandType, UUID futurIslandId, UUID ownerId, int disable, int privateIsland, Position position) {
        this.islandType = islandType;
        this.islandId = futurIslandId;
        this.ownerId = ownerId;
        this.disable = disable == 1;
        this.members = new CopyOnWriteArrayList<>();
        this.privateIsland = privateIsland == 1;
        this.warps = new ConcurrentHashMap<>();
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.position = position;
    }

    /**
     * @param islandType    Type Island (config.toml)
     * @param islandId      Island ID
     * @param ownerId       Owner Island
     * @param disable       Island disable
     * @param privateIsland Island closed visitor ?
     * @param membersList   List member of island
     * @param warpsMap      List warps
     * @param position      Position X/Z region File
     * @param date          Create Date
     */
    public Island(String islandType, UUID islandId, UUID ownerId, int disable, int privateIsland, CopyOnWriteArrayList<Players> membersList, ConcurrentHashMap<String, Location> warpsMap, Position position, Timestamp date) {
        this.islandType = islandType;
        this.islandId = islandId;
        this.ownerId = ownerId;
        this.disable = disable == 1;
        this.members = membersList;
        this.privateIsland = privateIsland == 1;
        this.warps = warpsMap;
        this.createDate = date;
        this.position = position;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public UUID getIslandId() {
        return this.islandId;
    }

    public ConcurrentMap<String, Location> getWarps() {
        return this.warps;
    }

    public boolean addWarps(String name, Location loc) {
        try {
            this.warps.put(name, loc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisable() {
        return this.disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isPrivateIsland() {
        return this.privateIsland;
    }

    public void setPrivateIsland(boolean privateIsland) {
        this.privateIsland = privateIsland;
    }

    public CopyOnWriteArrayList<Players> getMembers() {
        return this.members;
    }

    public boolean addMembers(Players member) {
        if (ConfigToml.islandTypes.get(this.islandType).maxMembers() >= this.members.size()) {
            return this.members.add(member);
        } else {
            return false;
        }
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getIslandType() {
        return islandType;
    }

    public void setIslandType(String islandType) {
        this.islandType = islandType;
    }
}
