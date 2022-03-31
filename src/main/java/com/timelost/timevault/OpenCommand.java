package com.timelost.timevault;

import com.timelost.timevault.util.TimeVaultUtil;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;


public class OpenCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("open")) {
                    ArrayList<ItemStack> vaultItems = TimeVaultUtil.getItems(p);
                    Inventory timevault = Bukkit.createInventory(p, 18, "Your Timeless Vault");
                    vaultItems.stream()
                            .forEach(itemStack -> timevault.addItem(itemStack));
                    p.openInventory(timevault);
                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "TimeVault" + ChatColor.GRAY + "] " + ChatColor.ITALIC + "Opening Vault...");

                    vfx(p);



                }
            }

        }
        return true;
    }

    private static void vfx(Player p){
        Location l = p.getLocation();
        p.playSound(l, Sound.BLOCK_LODESTONE_PLACE, 100f, 0.1f);
        p.playSound(l, Sound.BLOCK_BELL_RESONATE, 0.01f, 0.1f);
        double d = 1;
        double pcd = 100;
        double y = 0.1;
        while(pcd > 0) {
            for(int i = 0; i < 16; i++) {
                p.getWorld().spawnParticle(Particle.ASH, p.getLocation().clone()
                        .add(Vector.getRandom().subtract(Vector.getRandom()).setY(y+3).normalize().multiply(d)), 1, 0, 0, 0, 0);
            }
            pcd = pcd - 20;
        }
        p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 5.35f, 0.1f);
    }
}
