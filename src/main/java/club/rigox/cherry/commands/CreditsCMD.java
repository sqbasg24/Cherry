package club.rigox.cherry.commands;

import club.rigox.cherry.Cherry;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static club.rigox.cherry.utils.Config.getLangString;
import static club.rigox.cherry.utils.Logger.sendMessage;

@CommandAlias("credits|balance|money")
public class CreditsCMD {
    private final Cherry cherry;

    public CreditsCMD (Cherry plugin) {
        this.cherry = plugin;
    }

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        if (args.length == 1) {
            UUID uuid = cherry.getServer().getPlayerUniqueId(args[0]);

            // Sends a message to CommandSender (String on Economy.class)
            cherry.getEconomy().returnCredits(sender, uuid);
            return;
        }

        Player player = (Player) sender;
        double mapCredits = cherry.getCredits().get(player.getUniqueId());
        sendMessage(player, String.format(getLangString("CREDITS.SELF"), mapCredits));
    }

}
