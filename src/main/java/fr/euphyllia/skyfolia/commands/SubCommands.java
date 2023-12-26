package fr.euphyllia.skyfolia.commands;

import ca.spottedleaf.concurrentutil.util.Validate;
import fr.euphyllia.skyfolia.commands.subcommands.*;

public enum SubCommands {
    BIOME(new BiomeSubCommand()),
    CREATE(new CreateSubCommand()),
    DELETE(new DeleteSubCommand()),
    TELEPORT(new TeleportSubCommand());

    private final SubCommandInterface commandInterface;

    SubCommands(SubCommandInterface subCommandInterface) {
        this.commandInterface = subCommandInterface;
    }

    public static SubCommands subCommandByName(String name) {
        Validate.notNull(name, "Name can not be null");
        for (SubCommands sub : SubCommands.values()) {
            if (sub.name().equalsIgnoreCase(name)) {
                return sub;
            }
        }
        return null;
    }

    public SubCommandInterface getSubCommandInterface() {
        return this.commandInterface;
    }
}