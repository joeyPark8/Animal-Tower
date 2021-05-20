package com.joey.animalTower;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.EntityTypeArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    @Override
    public void onLoad() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new EntityTypeArgument("type"));
        arguments.add(new IntegerArgument("floor"));

        new CommandAPICommand("animal")
                .withArguments(arguments)
                .withAliases("am")
                .withPermission(CommandPermission.OP)
                .executesPlayer((player, args) -> {
                    List<Entity> entities = new ArrayList<>();
                    Entity lastEntity = null;

                    for (int i = 0; i < (int) args[1]; i += 1) {
                        Entity entity = player.getWorld().spawnEntity(player.getLocation(), (EntityType) args[0]);
                        entities.add(entity);
                    }

                    for (Entity j : entities) {
                        if (lastEntity != null) {
                            lastEntity.addPassenger(j);
                        }

                        lastEntity = j;
                    }

                    player.sendMessage(ChatColor.GREEN + "successfully summon pig tower with " + args[1] + " pigs");
                }).register();
    }

    @Override
    public void onEnable() {
        System.out.println("AnimalTower is activated");
    }

    @Override
    public void onDisable() {
        System.out.println("AnimalTower is deactivated");
    }
}
