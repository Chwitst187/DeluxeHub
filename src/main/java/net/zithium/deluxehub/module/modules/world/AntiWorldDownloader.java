package net.zithium.deluxehub.module.modules.world;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.reflection.XReflection;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.zithium.deluxehub.DeluxeHubPlugin;
import net.zithium.deluxehub.Permissions;
import net.zithium.deluxehub.config.ConfigType;
import net.zithium.deluxehub.config.Messages;
import net.zithium.deluxehub.module.Module;
import net.zithium.deluxehub.module.ModuleType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class AntiWorldDownloader extends Module implements PluginMessageListener {

    private final boolean legacy;
    private static final String WDL_INIT_CHANNEL = "wdl:init";
    private static final String WDL_CONTROL_CHANNEL = "wdl:control";

    public AntiWorldDownloader(DeluxeHubPlugin plugin) {
        super(plugin, ModuleType.ANTI_WDL);
        this.legacy = !XReflection.supports(13);
    }

    @Override
    public void onEnable() {
        if (legacy) {
            getPlugin().getServer().getMessenger().registerIncomingPluginChannel(getPlugin(), WDL_INIT_CHANNEL, this);
            getPlugin().getServer().getMessenger().registerOutgoingPluginChannel(getPlugin(), WDL_CONTROL_CHANNEL);
        } else {
            getPlugin().getServer().getMessenger().registerIncomingPluginChannel(getPlugin(), WDL_INIT_CHANNEL, this);
            getPlugin().getServer().getMessenger().registerOutgoingPluginChannel(getPlugin(), WDL_CONTROL_CHANNEL);
        }
    }

    @Override
    public void onDisable() {
        if (legacy) {
            getPlugin().getServer().getMessenger().unregisterIncomingPluginChannel(getPlugin(), WDL_INIT_CHANNEL);
            getPlugin().getServer().getMessenger().unregisterOutgoingPluginChannel(getPlugin(), WDL_CONTROL_CHANNEL);
        } else {
            getPlugin().getServer().getMessenger().unregisterIncomingPluginChannel(getPlugin(), WDL_INIT_CHANNEL);
            getPlugin().getServer().getMessenger().unregisterOutgoingPluginChannel(getPlugin(), WDL_CONTROL_CHANNEL);
        }
    }

    public void onPluginMessageReceived(@NotNull String channel, Player player, byte[] data) {
        if (player.hasPermission(Permissions.ANTI_WDL_BYPASS.getPermission())) {
            return;
        }

        if (legacy && channel.equals(WDL_INIT_CHANNEL) || !legacy && channel.equals(WDL_INIT_CHANNEL)) {

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeInt(0);
            out.writeBoolean(false);
            player.sendPluginMessage(getPlugin(), WDL_CONTROL_CHANNEL, out.toByteArray());

            if (!getPlugin().getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getBoolean("anti_wdl.admin_notify")) {
                return;
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission(Permissions.ANTI_WDL_NOTIFY.getPermission())) {
                    Messages.WORLD_DOWNLOAD_NOTIFY.send(p, "%player%", player.getName());
                }
            }
        }
    }
}
