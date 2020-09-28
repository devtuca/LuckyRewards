package dev.lucky.model;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */


@Data
public class Reward {

    private int id;
    private String name;
    private int delay;
    private List<ItemStack> items;

    public Reward(String name, int id, int delay) {
        this.id = id;
        this.name = name;
        this.delay = delay;
    }

}