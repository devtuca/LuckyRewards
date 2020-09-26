package dev.lucky.serializers;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */
public class InventorySerializer {

    /**
     * Serializes an Inventory to a string.
     *
     * @param inv The Inventory
     * @return The unencoded serialized string
     */
    public String serialize(Inventory inv) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inv.getSize());

            for (int i = 0; i < inv.getSize(); i++) {
                dataOutput.writeObject(inv.getItem(i));
            }

            dataOutput.close();

            return serializeToBase64(inv);

        } catch (IOException e) {
            throw new IllegalStateException("Unable to save inventory.", e);
        }
    }

    /**
     * Serializes an Inventory to a string and encodes it to Base64.
     *
     * @param inv The Inventory
     * @return The encoded serialized string
     */
    public String serializeToBase64(Inventory inv) {
        return Base64Coder.encodeLines(serialize(inv).getBytes());
    }

    /**
     * Deserializes a String into an Inventory. The string must not be encoded!
     *
     * @param data The data string, must not be encoded
     * @return The Inventory
     * @throws IOException
     */
    public Inventory deserialize(String data) throws IOException {
        try {
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(new ByteArrayInputStream(data.getBytes()));

            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException ex) {
            throw new IOException("Unable to decode class type.", ex);
        }
    }

    /**
     * Deserializes a String into an Inventory. The string must be encoded to
     * Base64.
     *
     * @param data The encoded data string
     * @return The Inventory
     * @throws IOException
     */
    public Inventory deserializeFromBase64(String data) throws IOException {
        return deserialize(new String(Base64Coder.decodeLines(data)));
    }
}