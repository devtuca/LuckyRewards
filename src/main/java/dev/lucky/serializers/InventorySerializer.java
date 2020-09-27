package dev.lucky.serializers;


import net.minecraft.server.v1_8_R1.NBTCompressedStreamTools;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.math.BigInteger;
import java.util.Base64;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */
public class InventorySerializer {

    public String encode(ItemStack stack) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(outputStream);
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            net.minecraft.server.v1_8_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(stack);

            nmsItem.save(nbtTagCompound);
            NBTCompressedStreamTools.a(nbtTagCompound, ((DataOutput) dataOutput));
            return new BigInteger(1, outputStream.toByteArray()).toString(32);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public ItemStack decode(String string) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new BigInteger(string, 32).toByteArray());
        NBTTagCompound nbtTagCompound;
        try {
            nbtTagCompound = NBTCompressedStreamTools.a(new DataInputStream(inputStream));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return CraftItemStack.asBukkitCopy(net.minecraft.server.v1_8_R1.ItemStack.createStack(nbtTagCompound));

    }
}