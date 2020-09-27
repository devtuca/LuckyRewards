package dev.lucky.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemComposer {

    private final ItemStack item;

    public ItemComposer(ItemStack item) {
        this.item = item;
    }

    public ItemComposer(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemComposer(Material material, int amount, int durability) {
        this.item = new ItemStack(material, amount, (short) durability);
    }

    public static ItemComposer of(Material material) {
        return new ItemComposer(material);
    }

    public static ItemComposer of(Material material, int amount, int durability) {
        return new ItemComposer(material, amount, durability);
    }

    public static ItemComposer of(ItemStack item) {
        return new ItemComposer(item);
    }

    public ItemComposer compose(Consumer<ItemStack> consumer) {
        consumer.accept(item);
        return this;
    }

    public ItemComposer composeMeta(Consumer<ItemMeta> consumer) {
        ItemMeta meta = item.getItemMeta();
        consumer.accept(meta);
        item.setItemMeta(meta);
        return this;
    }

    public ItemComposer setName(String name) {
        if (name == null || name.equalsIgnoreCase("nulo")) return this;
        composeMeta(meta -> meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name)));
        return this;
    }

    public ItemComposer setLore(List<String> lore) {
        if (lore == null || lore.isEmpty() || lore.get(0).equalsIgnoreCase("nulo")) return this;
        composeMeta(meta -> meta.setLore(lore.stream().map(string -> ChatColor.translateAlternateColorCodes('&', string)).collect(Collectors.toList())));
        return this;
    }

    public ItemComposer setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemComposer addLore(String... lore) {
        return addLore(Arrays.asList(lore));
    }

    public ItemComposer addLore(List<String> lore) {
        composeMeta(meta -> {
            List<String> newLore = meta.getLore();
            newLore.addAll((lore));
            meta.setLore(newLore);
        });
        return this;
    }

    public ItemComposer addGlow(boolean glow) {
        if (!glow) return this;
        compose(it -> it.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1));
        composeMeta(meta -> meta.addItemFlags(ItemFlag.HIDE_ENCHANTS));
        return this;
    }

    public ItemComposer setAmount(int amount) {
        compose(it -> it.setAmount(amount));
        return this;
    }

    public ItemComposer setDurability(int durability) {
        compose(it -> it.setDurability((short) durability));
        return this;
    }

    public ItemComposer addEnchantment(Enchantment enchantment, int level) {
        compose(it -> it.addUnsafeEnchantment(enchantment, level));
        return this;
    }

    public ItemComposer addEnchantments(HashMap<Enchantment, Integer> enchantments) {
        compose(it -> it.addUnsafeEnchantments(enchantments));
        return this;
    }

    public ItemComposer addItemFlag(ItemFlag... itemflag) {
        composeMeta(meta -> meta.addItemFlags(itemflag));
        return this;
    }

    public ItemComposer setSkullOwner(String owner) {
        composeMeta(meta -> {
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwner(owner);
        });
        return this;
    }

    public ItemComposer setClickListener(JavaPlugin plugin, Consumer<PlayerInteractEvent> consumer) {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInteract(PlayerInteractEvent e) {
                if (e.hasItem() && e.getItem().isSimilar(item)) {
                    consumer.accept(e);
                }
            }
        }, plugin);
        return this;
    }

    public ItemStack toItemStack() {
        return item;
    }

    public ItemStack build() {
        return item;
    }

}
