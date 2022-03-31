package com.timelost.timevault;

import com.timelost.timevault.util.TimeVaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
                }
            }

        }
        return true;
    }

}
