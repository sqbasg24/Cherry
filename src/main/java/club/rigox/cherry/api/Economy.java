package club.rigox.cherry.api;

import club.rigox.cherry.Cherry;
import org.bukkit.entity.Player;

public class Economy {
    private Cherry cherry;

    public Economy (Cherry plugin) {
        this.cherry = plugin;
    }

    public void sumCredits(Player player, Double credits) {
        double dbCredits = cherry.getMongo().getMongoCredits(player.getUniqueId());
        cherry.getCredits().put(player, dbCredits + credits);
    }

    public void subtractCredits(Player player, Double credits) {
        double dbCredits = cherry.getMongo().getMongoCredits(player.getUniqueId());
        cherry.getCredits().put(player, dbCredits - credits);
    }

    public void setCredits(Player player, Double credits) {
        cherry.getCredits().put(player, credits);
    }

    public void resetCredits(Player player) {
        cherry.getCredits().put(player, 0.0);
    }
}