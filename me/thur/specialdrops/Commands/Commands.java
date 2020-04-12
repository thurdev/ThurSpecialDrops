package me.thur.specialdrops.Commands;


import java.util.ArrayList;

import me.thur.specialdrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Commands implements CommandExecutor{

    private final Main instance = Main.getMain();
    public static boolean debug_mode = false;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        ArrayList<String> lore = new ArrayList<String>();
        if(command.getName().equalsIgnoreCase("tsd")){
            if(p.hasPermission("thurspecialdrops.edit")){
                if(args.length == 1){
                    if(p.hasPermission("thurspecialdrops.debug")){
                        if(args[0].equals("debug")){
                            if(debug_mode){
                                debug_mode = false;
                                p.sendMessage("§cModo debug desativado.");
                            }else{
                                debug_mode = true;
                                p.sendMessage("§aModo debug ativado.");
                            }
                        }else{
                            p.sendMessage("§cUtilize: /tsd debug.");
                        }
                    }else{
                        p.sendMessage("§cVocê não tem permissão para executar este comando.");
                    }
                }else{
                    Inventory inv_mobs = Bukkit.createInventory(null, 4*9, "§eThurSpecialDrops");
                    ArrayList<String> lore_mobs = new ArrayList<String>();
                    lore_mobs.add("§7Clique para editar.");
                    inv_mobs.setItem(0, createItem(Material.MONSTER_EGG, lore_mobs, "§2Creeper", (short) 50));
                    inv_mobs.setItem(1, createItem(Material.MONSTER_EGG, lore_mobs, "§7Skeleton", (short) 51));
                    inv_mobs.setItem(2, createItem(Material.MONSTER_EGG, lore_mobs, "§cSpider", (short) 52));
                    inv_mobs.setItem(3, createItem(Material.MONSTER_EGG, lore_mobs, "§2Zombie", (short) 54));
                    inv_mobs.setItem(4, createItem(Material.MONSTER_EGG, lore_mobs, "§aSlime", (short) 55));
                    inv_mobs.setItem(5, createItem(Material.MONSTER_EGG, lore_mobs, "§fGhast", (short) 56));
                    inv_mobs.setItem(6, createItem(Material.MONSTER_EGG, lore_mobs, "§cZombie Pigman", (short) 57));
                    inv_mobs.setItem(7, createItem(Material.MONSTER_EGG, lore_mobs, "§5Enderman", (short) 58));
                    inv_mobs.setItem(8, createItem(Material.MONSTER_EGG, lore_mobs, "§4Cave Spider", (short) 59));
                    inv_mobs.setItem(9, createItem(Material.MONSTER_EGG, lore_mobs, "§8Silverfish", (short) 60));
                    inv_mobs.setItem(10, createItem(Material.MONSTER_EGG, lore_mobs, "§eBlaze", (short) 61));
                    inv_mobs.setItem(11, createItem(Material.MONSTER_EGG, lore_mobs, "§6Magma Cube", (short) 62));
                    inv_mobs.setItem(12, createItem(Material.MONSTER_EGG, lore_mobs, "§7Bat", (short) 65));
                    inv_mobs.setItem(13, createItem(Material.MONSTER_EGG, lore_mobs, "§dWitch", (short) 66));
                    inv_mobs.setItem(14, createItem(Material.MONSTER_EGG, lore_mobs, "§5Endermite", (short) 67));
                    inv_mobs.setItem(15, createItem(Material.MONSTER_EGG, lore_mobs, "§bGuardian", (short) 68));
                    inv_mobs.setItem(16, createItem(Material.MONSTER_EGG, lore_mobs, "§cPig", (short) 90));
                    inv_mobs.setItem(17, createItem(Material.MONSTER_EGG, lore_mobs, "§fSheep", (short) 91));
                    inv_mobs.setItem(18, createItem(Material.MONSTER_EGG, lore_mobs, "§8Cow", (short) 92));
                    inv_mobs.setItem(19, createItem(Material.MONSTER_EGG, lore_mobs, "§7Chicken", (short) 93));
                    inv_mobs.setItem(20, createItem(Material.MONSTER_EGG, lore_mobs, "§3Squid", (short) 94));
                    inv_mobs.setItem(21, createItem(Material.MONSTER_EGG, lore_mobs, "§fWolf", (short) 95));
                    inv_mobs.setItem(22, createItem(Material.MONSTER_EGG, lore_mobs, "§cCow Mushroom", (short) 96));
                    inv_mobs.setItem(23, createItem(Material.MONSTER_EGG, lore_mobs, "§eOcelot", (short) 98));
                    inv_mobs.setItem(24, createItem(Material.MONSTER_EGG, lore_mobs, "§7Horse", (short) 100));
                    inv_mobs.setItem(25, createItem(Material.MONSTER_EGG, lore_mobs, "§9Rabbit", (short) 101));
                    inv_mobs.setItem(26, createItem(Material.MONSTER_EGG, lore_mobs, "§fVillager", (short) 120));

                    ItemStack golem = new ItemStack(Material.IRON_BLOCK, 1);
                    ItemStack whiter = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
                    ItemStack whiterskeleton = new ItemStack(Material.SOUL_SAND, 1);
                    ItemStack snow = new ItemStack(Material.SNOW_BLOCK, 1);
                    ItemMeta gmeta = golem.getItemMeta();
                    gmeta.setDisplayName("§fIron Golem");
                    gmeta.setLore(lore_mobs);
                    golem.setItemMeta(gmeta);
                    gmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    ItemMeta smeta = snow.getItemMeta();
                    smeta.setDisplayName("§fSnowman");
                    smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    smeta.setLore(lore_mobs);
                    snow.setItemMeta(smeta);
                    ItemMeta wmeta = whiter.getItemMeta();
                    wmeta.setDisplayName("§8Whiter");
                    wmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    wmeta.setLore(lore_mobs);
                    whiter.setItemMeta(wmeta);
                    ItemMeta wsmeta = whiterskeleton.getItemMeta();
                    wsmeta.setDisplayName("§8Whiter Skeleton");
                    wsmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    wsmeta.setLore(lore_mobs);
                    whiterskeleton.setItemMeta(wsmeta);

                    inv_mobs.setItem(29, whiter);
                    inv_mobs.setItem(30, golem);
                    inv_mobs.setItem(32, snow);
                    inv_mobs.setItem(33, whiterskeleton);

                    ItemStack espaco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                    ItemMeta emeta = espaco.getItemMeta();
                    emeta.setDisplayName("§eThurSpecialDrops");
                    emeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    espaco.setItemMeta(emeta);

                    inv_mobs.setItem(27, espaco);
                    inv_mobs.setItem(28, espaco);
                    inv_mobs.setItem(31, espaco);
                    inv_mobs.setItem(34, espaco);
                    inv_mobs.setItem(35, espaco);

                    p.openInventory(inv_mobs);
                }
            }else{
                p.sendMessage("§cVocê não tem permissão para executar este comando.");
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public ItemStack createItem(Material type, ArrayList lore, String nome, Short data){
        ItemStack item = new ItemStack(type, 1, (short) data);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(nome);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
