package me.thur.specialdrops.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import JH_StackMobs.API;
import me.thur.specialdrops.Commands.Commands;
import me.thur.specialdrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;


public class Events implements Listener{
    private final Main instance = Main.getMain();
    public ArrayList<Player> setcmd_creeper = new ArrayList<Player>();
    public ArrayList<Player> setchance_creeper = new ArrayList<Player>();
    public ArrayList<Player> setcmd_skeleton = new ArrayList<Player>();
    public ArrayList<Player> setchance_skeleton = new ArrayList<Player>();
    public ArrayList<Player> setcmd_spider = new ArrayList<Player>();
    public ArrayList<Player> setchance_spider= new ArrayList<Player>();
    public ArrayList<Player> setcmd_zombie = new ArrayList<Player>();
    public ArrayList<Player> setchance_zombie = new ArrayList<Player>();
    public ArrayList<Player> setcmd_slime = new ArrayList<Player>();
    public ArrayList<Player> setchance_slime = new ArrayList<Player>();
    public ArrayList<Player> setcmd_ghast = new ArrayList<Player>();
    public ArrayList<Player> setchance_ghast = new ArrayList<Player>();
    public ArrayList<Player> setcmd_zombiepig = new ArrayList<Player>();
    public ArrayList<Player> setchance_zombiepig = new ArrayList<Player>();
    public ArrayList<Player> setcmd_enderman = new ArrayList<Player>();
    public ArrayList<Player> setchance_enderman = new ArrayList<Player>();
    public ArrayList<Player> setchance_cavespider = new ArrayList<Player>();
    public ArrayList<Player> setcmd_cavespider = new ArrayList<Player>();
    public ArrayList<Player> setchance_silverfish = new ArrayList<Player>();
    public ArrayList<Player> setcmd_silverfish = new ArrayList<Player>();
    public ArrayList<Player> setcmd_blaze = new ArrayList<Player>();
    public ArrayList<Player> setchance_blaze = new ArrayList<Player>();
    public ArrayList<Player> setcmd_magmacube = new ArrayList<Player>();
    public ArrayList<Player> setchance_magmacube = new ArrayList<Player>();
    public ArrayList<Player> setcmd_bat = new ArrayList<Player>();
    public ArrayList<Player> setchance_bat = new ArrayList<Player>();
    public ArrayList<Player> setcmd_witch = new ArrayList<Player>();
    public ArrayList<Player> setchance_witch = new ArrayList<Player>();
    public ArrayList<Player> setcmd_endermite = new ArrayList<Player>();
    public ArrayList<Player> setchance_endermite = new ArrayList<Player>();
    public ArrayList<Player> setcmd_guardian = new ArrayList<Player>();
    public ArrayList<Player> setchance_guardian = new ArrayList<Player>();
    public ArrayList<Player> setcmd_pig = new ArrayList<Player>();
    public ArrayList<Player> setchance_pig = new ArrayList<Player>();
    public ArrayList<Player> setcmd_sheep = new ArrayList<Player>();
    public ArrayList<Player> setchance_sheep = new ArrayList<Player>();
    public ArrayList<Player> setcmd_cow = new ArrayList<Player>();
    public ArrayList<Player> setchance_cow = new ArrayList<Player>();
    public ArrayList<Player> setcmd_chicken = new ArrayList<Player>();
    public ArrayList<Player> setchance_chicken = new ArrayList<Player>();
    public ArrayList<Player> setcmd_squid = new ArrayList<Player>();
    public ArrayList<Player> setchance_squid = new ArrayList<Player>();
    public ArrayList<Player> setcmd_wolf = new ArrayList<Player>();
    public ArrayList<Player> setchance_wolf = new ArrayList<Player>();
    public ArrayList<Player> setcmd_mushroomcow = new ArrayList<Player>();
    public ArrayList<Player> setchance_mushroomcow = new ArrayList<Player>();
    public ArrayList<Player> setcmd_ocelot = new ArrayList<Player>();
    public ArrayList<Player> setchance_ocelot = new ArrayList<Player>();
    public ArrayList<Player> setcmd_horse = new ArrayList<Player>();
    public ArrayList<Player> setchance_horse = new ArrayList<Player>();
    public ArrayList<Player> setcmd_rabbit = new ArrayList<Player>();
    public ArrayList<Player> setchance_rabbit = new ArrayList<Player>();
    public ArrayList<Player> setcmd_villager = new ArrayList<Player>();
    public ArrayList<Player> setchance_villager = new ArrayList<Player>();
    public ArrayList<Player> setcmd_whiter = new ArrayList<Player>();
    public ArrayList<Player> setchance_whiter = new ArrayList<Player>();
    public ArrayList<Player> setcmd_irongolem = new ArrayList<Player>();
    public ArrayList<Player> setchance_irongolem = new ArrayList<Player>();
    public ArrayList<Player> setcmd_snowman = new ArrayList<Player>();
    public ArrayList<Player> setchance_snowman = new ArrayList<Player>();
    public ArrayList<Player> setcmd_whiterskeleton = new ArrayList<Player>();
    public ArrayList<Player> setchance_whiterskeleton = new ArrayList<Player>();


    @EventHandler
    public void mobDeath(EntityDeathEvent event){
        Random rand = new Random();
        Player p = (Player) event.getEntity().getKiller();
        LivingEntity e = event.getEntity();

        for(String key: instance.getConfig().getKeys(false)){
            if(key != null){
                if(e.getType().toString().equals(key)){
                    if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                        event.getDrops().clear();
                    }
                    Double chance = (instance.getConfig().getDouble(key + ".CHANCE"))/100;
                    Double rate = rand.nextDouble();
                    int quantity = rand.nextInt(instance.getConfig().getInt(key + ".QUANT_MAX") - instance.getConfig().getInt(key + ".QUANT_MIN")) + instance.getConfig().getInt(key + ".QUANT_MIN");
                    if(Commands.debug_mode){
                        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + "§e§lChance: §6§l" + (chance*100) + " §f§l>= " + " §e§lRate: §6§l" + (rate*100) + "\"}"), (byte) 2);
                        packet = packet;
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                    }
                    if(chance > rate){
                        p.sendMessage(instance.getConfig().getString(key + ".MESSAGE").replace("&", "§").replace("{PLAYER}", p.getDisplayName()).replace("{CHANCE}", String.valueOf(chance*100)));
                        String cmds = instance.getConfig().getString(key + ".COMMAND");
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        if(p.isSneaking()){
                            for(int i = 0; i < API.getStackAmount(e); i++){
                                Bukkit.dispatchCommand(console, cmds.replace("{PLAYER}", p.getDisplayName()).replace("{QUANTIDADE}", String.valueOf(quantity)));
                            }
                        }else {
                            Bukkit.dispatchCommand(console, cmds.replace("{PLAYER}", p.getDisplayName()).replace("{QUANTIDADE}", String.valueOf(quantity)));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void clickItem(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        //MOBS
        if(e.getInventory().getName().equals("§eThurSpecialDrops")){
            e.setCancelled(true);
            //CREEPER
            if(e.getSlot() == 0){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_creeper = Bukkit.createInventory(null, 3*9, "§2Creeper");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("CREEPER")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_creeper.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_creeper.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CREEPER")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_creeper.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_creeper.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_creeper);

                //SKELETON
            }else if(e.getSlot() == 1){


                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_skeleton = Bukkit.createInventory(null, 3*9, "§7Skeleton");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SKELETON")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_skeleton.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_skeleton.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SKELETON")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_skeleton.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_skeleton.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_skeleton);

                //SPIDER
            }else if(e.getSlot() == 2){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_spider = Bukkit.createInventory(null, 3*9, "§cSpider");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SPIDER")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_spider.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_spider.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SPIDER")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_spider.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_spider.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_spider);

                //ZOMBIE
            }else if(e.getSlot() == 3){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_zombie = Bukkit.createInventory(null, 3*9, "§2Zombie");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("ZOMBIE")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_zombie.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_zombie.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ZOMBIE")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_zombie.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_zombie.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_zombie);

                //SLIME
            }else if(e.getSlot() == 4){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_slime = Bukkit.createInventory(null, 3*9, "§aSlime");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SLIME")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_slime.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_slime.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SLIME")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_slime.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_slime.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_slime);

                //GHAST
            }else if(e.getSlot() == 5){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_ghast = Bukkit.createInventory(null, 3*9, "§fGhast");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("GHAST")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_ghast.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_ghast.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GHAST")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_ghast.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_ghast.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_ghast);

                //PIG_ZOMBIE
            }else if(e.getSlot() == 6){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_pigzombie = Bukkit.createInventory(null, 3*9, "§cZombie Pigman");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("PIG_ZOMBIE")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_pigzombie.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_pigzombie.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG_ZOMBIE")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_pigzombie.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_pigzombie.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_pigzombie);

                //ENDERMAN
            }else if(e.getSlot() == 7){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_enderman = Bukkit.createInventory(null, 3*9, "§5Enderman");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("ENDERMAN")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_enderman.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_enderman.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMAN")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_enderman.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_enderman.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_enderman);

                //CAVE_SPIDER
            }else if(e.getSlot() == 8){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_cavespider = Bukkit.createInventory(null, 3*9, "§4Cave Spider");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("CAVE_SPIDER")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_cavespider.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_cavespider.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CAVE_SPIDER")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_cavespider.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_cavespider.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_cavespider);

                //SILVERFISH
            }else if(e.getSlot() == 9){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_silverfish = Bukkit.createInventory(null, 3*9, "§8Silverfish");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SILVERFISH")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_silverfish.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_silverfish.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SILVERFISH")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_silverfish.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_silverfish.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_silverfish);
                //BLAZE
            }else if(e.getSlot() == 10){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§eBlaze");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("BLAZE")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BLAZE")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //MAGMA_CUBE
            }else if(e.getSlot() == 11){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§6Magma Cube");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("MAGMA_CUBE")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MAGMA_CUBE")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //BAT
            }else if(e.getSlot() == 12){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§7Bat");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("BAT")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BAT")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //WITCH
            }else if(e.getSlot() == 13){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§dWitch");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("WITCH")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WITCH")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //ENDERMITE
            }else if(e.getSlot() == 14){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§5Endermite");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("ENDERMITE")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMITE")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //GUARDIAN
            }else if(e.getSlot() == 15){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§bGuardian");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("GUARDIAN")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("Guardian")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //PIG
            }else if(e.getSlot() == 16){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§cPig");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("PIG")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //SHEEP
            }else if(e.getSlot() == 17){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§fSheep");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SHEEP")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SHEEP")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //COW
            }else if(e.getSlot() == 18){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§8Cow");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("COW")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("COW")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //CHICKEN
            }else if(e.getSlot() == 19){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§7Chicken");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("CHICKEN")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CHICKEN")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //SQUID
            }else if(e.getSlot() == 20){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§3Squid");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SQUID")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SQUID")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //WOLF
            }else if(e.getSlot() == 21){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§fWolf");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("WOLF")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WOLF")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //MUSHROOM_COW
            }else if(e.getSlot() == 22){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§cCow Mushroom");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("MUSHROOM_COW")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MUSHROOM_COW")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //OCELOT
            }else if(e.getSlot() == 23){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§eOcelot");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("OCELOT")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("OCELOT")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //HORSE
            }else if(e.getSlot() == 24){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§7Horse");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("HORSE")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("HORSE")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //RABBIT
            }else if(e.getSlot() == 25){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§9Rabbit");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("RABBIT")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("RABBIT")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //VILLAGER
            }else if(e.getSlot() == 26){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§fVillager");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("VILLAGER")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("VILLAGER")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //WHITER
            }else if(e.getSlot() == 29){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§8Whiter");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("WHITER")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //IRON_GOLEM
            }else if(e.getSlot() == 30){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§fIron Golem");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("IRON_GOLEM")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("IRON_GOLEM")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //SNOWMAN
            }else if(e.getSlot() == 32){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§fSnowman");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("SNOWMAN")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SNOWMAN")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
                //WHITER_SKULL
            }else if(e.getSlot() == 33){
                p.closeInventory();
                ArrayList<String> nt_lore = new ArrayList<String>();
                Inventory inv_blaze = Bukkit.createInventory(null, 3*9, "§8Whiter Skeleton");
                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta nt_meta = nametag.getItemMeta();
                nt_lore.add("§7Clique para editar a chance de dropar cada item.");
                for(String key: instance.getConfig().getKeys(false)) {
                    if (key != null) {
                        if (key.equals("WHITER_SKULL")) {
                            nt_lore.add("§7Chance atual: §f" + instance.getConfig().getString(key + ".CHANCE") + "%");
                        }
                    }
                }
                nt_meta.setDisplayName("§eEditar chance de drop");
                nt_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                nt_meta.setLore(nt_lore);
                nametag.setItemMeta(nt_meta);
                inv_blaze.setItem(11, nametag);

                ArrayList<String> cmd_lore = new ArrayList<String>();
                ItemStack cmd = new ItemStack(Material.COMMAND, 1);
                ItemMeta cmd_meta = cmd.getItemMeta();
                cmd_meta.setDisplayName("§cEditar Drop");
                cmd_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                cmd_lore.add("§7Clique para editar o drop deste mob");
                cmd_meta.setLore(cmd_lore);
                cmd.setItemMeta(cmd_meta);
                inv_blaze.setItem(13, cmd);

                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER_SKULL")){
                            if(!instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§aAtivar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §aativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }else{
                                ArrayList<String> tirar_dropdefault = new ArrayList<String>();
                                ItemStack dropdefault = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                                ItemMeta dropdefault_meta = dropdefault.getItemMeta();
                                dropdefault_meta.setDisplayName("§cDesativar Drop Default");
                                dropdefault_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                tirar_dropdefault.add("§7Clique para §cdesativar§7 o drop default do mob.");
                                dropdefault_meta.setLore(tirar_dropdefault);
                                dropdefault.setItemMeta(dropdefault_meta);
                                inv_blaze.setItem(15, dropdefault);
                            }
                        }
                    }
                }
                p.openInventory(inv_blaze);
            }
        }

        //CREEPER
        if(e.getInventory().getName().equals("§2Creeper")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CREEPER")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §2Creeper §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §2Creeper §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CREEPER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_creeper.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CREEPER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_creeper.add(p);
                        }
                    }
                }
            }
            //SKELETON
        }else if(e.getInventory().getName().equals("§7Skeleton")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SKELETON")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §7Skeleton §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §7Skeleton §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SKELETON")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_skeleton.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SKELETON")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_skeleton.add(p);
                        }
                    }
                }
            }
            //SPIDER
        }else if(e.getInventory().getName().equals("§cSpider")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SPIDER")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da Spider desativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §cSpider §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SPIDER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_spider.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SPIDER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_spider.add(p);
                        }
                    }
                }
            }
            //ZOMBIE
        }else if(e.getInventory().getName().equals("§2Zombie")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ZOMBIE")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §2Zombie §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §2Zombie §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ZOMBIE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_zombie.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ZOMBIE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_zombie.add(p);
                        }
                    }
                }
            }
            //SLIME
        }else if(e.getInventory().getName().equals("§aSlime")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SLIME")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §aSlime §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do Slime §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }

            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SLIME")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_slime.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SLIME")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_slime.add(p);
                        }
                    }
                }
            }
            //GHAST
        }else if(e.getInventory().getName().equals("§fGhast")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GHAST")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §fGhast §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §fGhast §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GHAST")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_ghast.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GHAST")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_ghast.add(p);
                        }
                    }
                }
            }
            //PIG_ZOMBIE
        }else if(e.getInventory().getName().equals("§cZombie Pigman")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG_ZOMBIE")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do Zombie Pigman desativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §cZombie Pigman §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG_ZOMBIE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_zombiepig.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG_ZOMBIE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_zombiepig.add(p);
                        }
                    }
                }
            }
            //ENDERMAN
        }else if(e.getInventory().getName().equals("§5Enderman")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMAN")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §5Enderman §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §5Enderman §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMAN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_enderman.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMAN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_enderman.add(p);
                        }
                    }
                }
            }
            //CAVE SPIDER
        }else if(e.getInventory().getName().equals("§4Cave Spider")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CAVE_SPIDER")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §4Cave Spider §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §4Cave Spider §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CAVE_SPIDER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_cavespider.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CAVE_SPIDER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_cavespider.add(p);
                        }
                    }
                }
            }
            //SILVERFISH
        }else if(e.getInventory().getName().equals("§8Silverfish")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SILVERFISH")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §8Silverfish §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §8Silverfish §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SILVERFISH")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_silverfish.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SILVERFISH")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_silverfish.add(p);
                        }
                    }
                }
            }
            //BLAZE
        }else if(e.getInventory().getName().equals("§eBlaze")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BLAZE")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §eBlaze §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §eBlaze §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BLAZE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_blaze.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BLAZE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_blaze.add(p);
                        }
                    }
                }
            }
            //MAGMA_CUBE
        }else if(e.getInventory().getName().equals("§6Magma Cube")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MAGMA_CUBE")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §6Magma Cube §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §6Magma Cube §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MAGMA_CUBE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_magmacube.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MAGMA_CUBE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_magmacube.add(p);
                        }
                    }
                }
            }
            //BAT
        }else if(e.getInventory().getName().equals("§7Bat")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BAT")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §7Bat §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §7Bat §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BAT")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_bat.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("BAT")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_bat.add(p);
                        }
                    }
                }
            }
            //WITCH
        }else if(e.getInventory().getName().equals("§dWitch")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WITCH")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §dWitch §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §dWitch §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WITCH")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_witch.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WITCH")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_witch.add(p);
                        }
                    }
                }
            }
            //ENDERMITE
        }else if(e.getInventory().getName().equals("§5Endermite")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMITE")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §5Endermite §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §5Endermite §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMITE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_endermite.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("ENDERMITE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_endermite.add(p);
                        }
                    }
                }
            }
            //GUARDIAN
        }else if(e.getInventory().getName().equals("§bGuardian")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GUARDIAN")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §bGuardian §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §bGuardian §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GUARDIAN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_guardian.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("GUARDIAN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_guardian.add(p);
                        }
                    }
                }
            }
            //PIG
        }else if(e.getInventory().getName().equals("§cPig")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §cPig §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §cPig §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_pig.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("PIG")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_pig.add(p);
                        }
                    }
                }
            }
            //SHEEP
        }else if(e.getInventory().getName().equals("§fSheep")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SHEEP")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §fSheep §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §fSheep §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SHEEP")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_sheep.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SHEEP")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_sheep.add(p);
                        }
                    }
                }
            }
            //COW
        }else if(e.getInventory().getName().equals("§8Cow")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("COW")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §8Cow §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §8Cow §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("COW")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_cow.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("COW")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_cow.add(p);
                        }
                    }
                }
            }
            //CHICKEN
        }else if(e.getInventory().getName().equals("§7Chicken")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CHICKEN")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §7Chicken §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §7Chicken §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CHICKEN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_chicken.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("CHICKEN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_chicken.add(p);
                        }
                    }
                }
            }
            //SQUID
        }else if(e.getInventory().getName().equals("§3Squid")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SQUID")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §3Squid §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §3Squid §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SQUID")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_squid.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SQUID")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_squid.add(p);
                        }
                    }
                }
            }
            //WOLF
        }else if(e.getInventory().getName().equals("§fWolf")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WOLF")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §fWolf §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §fWolf §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WOLF")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_wolf.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WOLF")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_wolf.add(p);
                        }
                    }
                }
            }
            //MUSHROOM_COW
        }else if(e.getInventory().getName().equals("§cCow Mushroom")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MUSHROOM_COW")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default da §cCow Mushroom §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default da §cCow Mushroom §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MUSHROOM_COW")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_mushroomcow.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("MUSHROOM_COW")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_mushroomcow.add(p);
                        }
                    }
                }
            }
            //OCELOT
        }else if(e.getInventory().getName().equals("§eOcelot")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("OCELOT")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §eOcelot §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §eOcelot §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("OCELOT")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_ocelot.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("OCELOT")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_ocelot.add(p);
                        }
                    }
                }
            }
            //HORSE
        }else if(e.getInventory().getName().equals("§7Horse")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("HORSE")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §7Horse §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §7Horse §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("HORSE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_horse.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("HORSE")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_horse.add(p);
                        }
                    }
                }
            }
            //RABBIT
        }else if(e.getInventory().getName().equals("§9Rabbit")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("RABBIT")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §9Rabbit §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §9Rabbit §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("RABBIT")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_rabbit.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("RABBIT")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_rabbit.add(p);
                        }
                    }
                }
            }
            //VILLAGER
        }else if(e.getInventory().getName().equals("§fVillager")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("VILLAGER")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §fVillager §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §fVillager §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("VILLAGER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_villager.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("VILLAGER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_villager.add(p);
                        }
                    }
                }
            }
            //WHITER
        }else if(e.getInventory().getName().equals("§8Whiter")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §8Whiter §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §8Whiter §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_whiter.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_whiter.add(p);
                        }
                    }
                }
            }
            //IRON_GOLEM
        }else if(e.getInventory().getName().equals("§fIron Golem")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("IRON_GOLEM")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §fIron Golem §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §fIron Golem §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("IRON_GOLEM")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_irongolem.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("IRON_GOLEM")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_irongolem.add(p);
                        }
                    }
                }
            }
            //SNOWMAN
        }else if(e.getInventory().getName().equals("§fSnowman")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SNOWMAN")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §fSnowman §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §fSnowman §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SNOWMAN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_snowman.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("SNOWMAN")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_snowman.add(p);
                        }
                    }
                }
            }
            //WHITER_SKULL
        }else if(e.getInventory().getName().equals("§8Whiter Skeleton")){
            e.setCancelled(true);
            if(e.getSlot() == 15){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER_SKULL")){
                            if(instance.getConfig().getBoolean(key + ".DROP_DEFAULT_ITEM")){
                                p.sendMessage("§cDrops default do §8Whiter Skeleton §cdesativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", false);
                                instance.saveConfig();
                                p.closeInventory();
                            }else{
                                p.sendMessage("§aDrops default do §8Whiter Skeleton §aativado!");
                                instance.getConfig().set(key + ".DROP_DEFAULT_ITEM", true);
                                instance.saveConfig();
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else if(e.getSlot() == 13){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER_SKULL")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite um comando para adicionar ao drop do mob.");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setcmd_whiterskeleton.add(p);
                        }
                    }
                }
            }else if(e.getSlot() == 11){
                for(String key: instance.getConfig().getKeys(false)){
                    if(key != null){
                        if(key.equals("WHITER_SKULL")){
                            p.closeInventory();
                            for(int i  = 0; i < 50; i++){
                                p.sendMessage(" ");
                            }
                            p.sendMessage("§a • Digite a nova % de drop (SEM A %).");
                            p.sendMessage("§7 • Responda no chat para finalizar.");
                            p.sendMessage(" ");
                            setchance_whiterskeleton.add(p);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent e){
        Player p = (Player) e.getPlayer();
        if(setcmd_creeper.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CREEPER")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_creeper.remove(p);
                        p.sendMessage("§aDrop do §2Creeper§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_creeper.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CREEPER")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_creeper.remove(p);
                        p.sendMessage("§aChance de drop do §2Creeper§a editado com sucesso!");
                    }
                }
            }
        }

        if(setcmd_skeleton.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SKELETON")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_skeleton.remove(p);
                        p.sendMessage("§aDrop do §7Skeleton§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_skeleton.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SKELETON")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_skeleton.remove(p);
                        p.sendMessage("§aChance de drop do §7Skeleton§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_spider.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SPIDER")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_spider.remove(p);
                        p.sendMessage("§aDrop da §cSpider§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_spider.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SPIDER")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_spider.remove(p);
                        p.sendMessage("§aChance de drop do §cSpider§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_zombie.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("ZOMBIE")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_zombie.remove(p);
                        p.sendMessage("§aDrop do §2Zombie§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_zombie.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("ZOMBIE")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_zombie.remove(p);
                        p.sendMessage("§aChance de drop do §2Zombie§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_slime.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SLIME")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_slime.remove(p);
                        p.sendMessage("§aDrop do §aSlime§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_slime.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SLIME")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_slime.remove(p);
                        p.sendMessage("§aChance de drop do §aSlime§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_ghast.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("GHAST")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_ghast.remove(p);
                        p.sendMessage("§aDrop do §fGhast§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_ghast.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("GHAST")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_ghast.remove(p);
                        p.sendMessage("§aChance de drop do §fGhast§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_zombiepig.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("PIG_ZOMBIE")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_zombiepig.remove(p);
                        p.sendMessage("§aDrop do §cZombie Pigman§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_zombiepig.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("PIG_ZOMBIE")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_zombiepig.remove(p);
                        p.sendMessage("§aChance de drop do §cZombie Pigman§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_enderman.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("ENDERMAN")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_enderman.remove(p);
                        p.sendMessage("§aDrop do §5Enderman§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_enderman.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("ENDERMAN")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_enderman.remove(p);
                        p.sendMessage("§aChance de drop do §5Enderman§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_cavespider.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CAVE_SPIDER")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_cavespider.remove(p);
                        p.sendMessage("§aDrop da §4Cave Spider§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_cavespider.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CAVE_SPIDER")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_cavespider.remove(p);
                        p.sendMessage("§aChance de drop da §4Cave Spider§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_silverfish.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SILVERFISH")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_silverfish.remove(p);
                        p.sendMessage("§aDrop do §8Silver§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_silverfish.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SILVERFISH")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_silverfish.remove(p);
                        p.sendMessage("§aChance de drop do §8Silver§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_blaze.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("BLAZE")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_blaze.remove(p);
                        p.sendMessage("§aDrop do §eBlaze§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_blaze.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("BLAZE")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_blaze.remove(p);
                        p.sendMessage("§aChance de drop do §eBlaze§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_magmacube.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("MAGMA_CUBE")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_magmacube.remove(p);
                        p.sendMessage("§aDrop do §6Magma Cube§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_magmacube.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("MAGMA_CUBE")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_magmacube.remove(p);
                        p.sendMessage("§aChance de drop do §6Magma Cube§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_bat.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("BAT")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_bat.remove(p);
                        p.sendMessage("§aDrop do §7Bat§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_bat.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("BAT")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_bat.remove(p);
                        p.sendMessage("§aChance de drop do §7Bat§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_witch.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WITCH")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_witch.remove(p);
                        p.sendMessage("§aDrop da §dWitch§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_witch.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WITCH")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_witch.remove(p);
                        p.sendMessage("§aChance de drop da §dWitch§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_endermite.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("ENDERMITE")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_endermite.remove(p);
                        p.sendMessage("§aDrop do §5Endermite§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_endermite.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("ENDERMITE")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_endermite.remove(p);
                        p.sendMessage("§aChance de drop do §5Endermite§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_guardian.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("GUARDIAN")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_guardian.remove(p);
                        p.sendMessage("§aDrop do §bGuardian§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_guardian.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("GUARDIAN")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_guardian.remove(p);
                        p.sendMessage("§aChance de drop do §bGuardian§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_pig.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("PIG")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_pig.remove(p);
                        p.sendMessage("§aDrop do §cPig§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_pig.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("PIG")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_pig.remove(p);
                        p.sendMessage("§aChance de drop do §cPig§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_sheep.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SHEEP")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_sheep.remove(p);
                        p.sendMessage("§aDrop da §fSheep§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_sheep.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SHEEP")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_sheep.remove(p);
                        p.sendMessage("§aChance de drop da §fSheep§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_cow.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("COW")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_cow.remove(p);
                        p.sendMessage("§aDrop da §8Cow§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_cow.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("COW")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_cow.remove(p);
                        p.sendMessage("§aChance de drop da §8Cow§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_chicken.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CHICKEN")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_chicken.remove(p);
                        p.sendMessage("§aDrop da §7Chicken§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_chicken.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CHICKEN")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_chicken.remove(p);
                        p.sendMessage("§aChance de drop da §7Chicken§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_squid.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SQUID")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_squid.remove(p);
                        p.sendMessage("§aDrop da §3Squid§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_squid.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SQUID")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_squid.remove(p);
                        p.sendMessage("§aChance de drop da §3Squid§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_wolf.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WOLF")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_wolf.remove(p);
                        p.sendMessage("§aDrop do §fWolf§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_wolf.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WOLF")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_wolf.remove(p);
                        p.sendMessage("§aChance de drop do §fWolf§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_mushroomcow.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("MUSHROOM_COW")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_mushroomcow.remove(p);
                        p.sendMessage("§aDrop da §cCow Mushroom§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_mushroomcow.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("MUSHROOM_COW")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_mushroomcow.remove(p);
                        p.sendMessage("§aChance de drop da §cCow Mushroom§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_ocelot.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("OCELOT")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_ocelot.remove(p);
                        p.sendMessage("§aDrop do §eOcelot§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_ocelot.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("OCELOT")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_ocelot.remove(p);
                        p.sendMessage("§aChance de drop do §eOcelot§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_horse.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("CREEPER")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_horse.remove(p);
                        p.sendMessage("§aDrop do §7Horse§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_horse.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("HORSE")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_horse.remove(p);
                        p.sendMessage("§aChance de drop do §7Horse§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_rabbit.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("RABBIT")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_rabbit.remove(p);
                        p.sendMessage("§aDrop da §9Rabbit§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_rabbit.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("RABBIT")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_rabbit.remove(p);
                        p.sendMessage("§aChance de drop da §9Rabbit§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_villager.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("VILLAGER")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_villager.remove(p);
                        p.sendMessage("§aDrop do §fVillager§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_villager.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("VILLAGER")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_villager.remove(p);
                        p.sendMessage("§aChance de drop do §fVillager§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_whiter.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WHITER")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_whiter.remove(p);
                        p.sendMessage("§aDrop do §8Whiter§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_whiter.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WHITER")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_whiter.remove(p);
                        p.sendMessage("§aChance de drop do §8Whiter§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_irongolem.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("IRON_GOLEM")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_irongolem.remove(p);
                        p.sendMessage("§aDrop do §fIron Golem§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_irongolem.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("IRON_GOLEM")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_irongolem.remove(p);
                        p.sendMessage("§aChance de drop do §fIron Golem§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_snowman.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SNOWMAN")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_snowman.remove(p);
                        p.sendMessage("§aDrop do §fSnowman§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_snowman.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("SNOWMAN")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_snowman.remove(p);
                        p.sendMessage("§aChance de drop do §fSnowman§a editado com sucesso!");
                    }
                }
            }
        }
        if(setcmd_whiterskeleton.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WHITER_SKULL")){
                        instance.getConfig().set(key + ".COMMAND", msg);
                        instance.saveConfig();
                        setcmd_whiterskeleton.remove(p);
                        p.sendMessage("§aDrop do §8Whiter Skeleton§a editado com sucesso!");
                    }
                }
            }
        }else if(setchance_whiterskeleton.contains(p)){
            e.setCancelled(true);
            String msg = e.getMessage();
            for(String key: instance.getConfig().getKeys(false)){
                if(key != null){
                    if(key.equals("WHITER_SKULL")){
                        instance.getConfig().set(key + ".CHANCE", Integer.parseInt(msg));
                        instance.saveConfig();
                        setchance_whiterskeleton.remove(p);
                        p.sendMessage("§aChance de drop do §8Whiter Skeleton§a editado com sucesso!");
                    }
                }
            }
        }

    }

}
