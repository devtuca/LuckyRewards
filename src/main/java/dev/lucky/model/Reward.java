package dev.lucky.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */


@Data
@AllArgsConstructor
public class Reward {

    private int id;
    private String name;
    private int delay;
    private List<ItemStack> items;

}