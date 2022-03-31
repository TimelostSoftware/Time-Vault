package com.timelost.timevault;

import com.timelost.timevault.util.VaultListeners;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class TimeVault extends JavaPlugin {
    private static TimeVault plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("vault").setExecutor(new OpenCommand());
        getServer().getPluginManager().registerEvents(new VaultListeners(), this);
        TimeVault.info("TimeVault Synchronised");


    }

    @Override
    public void onDisable() {
        TimeVault.info("Dropping the sand.....");
    }

    public static TimeVault getPlugin() {
        return plugin;
    }


    // Print Shit
    private static String tl() {return ChatColor.GRAY + "[" + ChatColor.GOLD + "TimeVault" + ChatColor.GRAY + "] " + ChatColor.WHITE;}
    public static void msg(String string) {
        try {
            System.out.println(string.replaceAll("(<([^>]+)>)", ""));
        } catch (Throwable ignored1) {

        }
    }

    public static void info(String string) {
        msg(tl() + "Info: " + string);
    }

    public static void error(String string) {
        msg(tl() + ChatColor.DARK_RED + "  ERROR:" + string);
    }

}
