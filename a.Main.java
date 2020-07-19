package a;

import me.zombie_striker.pixelprinter.util.RGBBlockColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onRedstone(BlockRedstoneEvent e) {
        e.setNewCurrent(0);
    }

    @EventHandler
    public void onPhysics(BlockPhysicsEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        new BukkitRunnable() {
            @Override
            public void run() {

                try {
                    BufferedImage image = ImageIO.read(new File(getDataFolder() + "/screenshot.png"));

                    for (int x = 0; x < image.getWidth(); x++) {
                        for (int z = 0; z < image.getHeight(); z++) {
                            if (Bukkit.getWorld("world") == null) continue;
                            Bukkit.getWorld("world")
                                    .getBlockAt(x, 4, z)
                                    .setType(
                                            RGBBlockColor.getClosestBlockValue(new Color(image.getRGB(x, z)), true, false).getMaterial()
                                    );
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.runTaskTimer(this, 0, 1);
    }
}
