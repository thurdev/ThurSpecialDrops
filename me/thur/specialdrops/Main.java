package me.thur.specialdrops;

import me.thur.specialdrops.Event.Events;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.thur.specialdrops.Commands.Commands;

public class Main extends JavaPlugin implements Listener{

    ConsoleCommandSender ccs = Bukkit.getConsoleSender();

    @Override
    public void onLoad() {
        ccs.sendMessage("§e[ThurSpecialDrops] - Carregando...");
    }

    @Override
    public void onEnable() {
        RegisterEvents();
        RegisterCommands();
        saveDefaultConfig();
        ccs.sendMessage("§a[ThurSpecialDrops] - Carregado!");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        ccs.sendMessage("§C[ThurSpecialDrops] - Desligado...");
    }

    public void RegisterCommands(){
        getCommand("tsd").setExecutor(new Commands());
    }

    public void RegisterEvents(){
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    public static Main getMain(){
        return (Main) Bukkit.getPluginManager().getPlugin("ThurSpecialDrops");
    }


}
