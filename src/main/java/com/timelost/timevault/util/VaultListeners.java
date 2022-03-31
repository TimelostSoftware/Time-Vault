package com.timelost.timevault.util;

import com.timelost.timevault.TimeVault;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;

public class VaultListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        PersistentDataContainer data = p.getPersistentDataContainer();

        if (!data.has(new NamespacedKey(TimeVault.getPlugin(), "vault"), PersistentDataType.STRING)){
            data.set(new NamespacedKey(TimeVault.getPlugin(), "vault"), PersistentDataType.STRING, "");
        }

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){

        Player p = (Player) e.getPlayer();
        p.playSound(p.getLocation(), Sound.BLOCK_BARREL_CLOSE, 50f, 0.01f);


        if (e.getView().getTitle().equalsIgnoreCase("Your Timeless Vault")){

            ArrayList<ItemStack> prunedItems = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(itemStack -> {
                        if (itemStack == null){
                            return false;
                        }
                        return true;
                    })
                    .forEach(itemStack -> prunedItems.add(itemStack));

//            TimeVault.info("size: " + prunedItems.size());

            TimeVaultUtil.storeItems(prunedItems, p);

        }

    }

}
