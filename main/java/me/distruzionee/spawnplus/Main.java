package me.distruzionee.spawnplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getCommand("setSpawn").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.hasPermission("spawn.set")){
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                double lookY = player.getLocation().getYaw();
                double lookP = player.getLocation().getPitch();

                getConfig().set("x", x);

                getConfig().set("y", y);

                getConfig().set("z", z);

                getConfig().set("ly", lookY);

                getConfig().set("lp", lookP);

                saveConfig();

                player.sendMessage(ChatColor.GREEN + "Ok....");

            }

        }

        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        float x = (float) getConfig().getDouble("x");
        float y = (float) getConfig().getDouble("y");
        float z = (float) getConfig().getDouble("z");
        float yaw = (float) getConfig().getDouble("ly");
        float pitch = (float) getConfig().getDouble("lp");

        Location spawn = new Location(player.getWorld(), x, y, z, yaw, pitch);

        player.teleport(spawn);
    }

}
