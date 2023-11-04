package team.creative.playerrevive.client;

import com.tac.guns.event.GunFireEvent;
import com.tac.guns.event.GunReloadEvent;
import dev.polv.policeitemsmod.common.HandcuffHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.creative.playerrevive.PlayerRevive;
import team.creative.playerrevive.api.IBleeding;
import team.creative.playerrevive.server.PlayerReviveServer;

/**
 * @author Pol Vallverdu (polv.dev)
 */
@Mod.EventBusSubscriber(modid = PlayerRevive.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EventHandler {

    @SubscribeEvent
    public static void onFireShoot(GunFireEvent.Pre event) {
        IBleeding revive = PlayerReviveServer.getBleeding(event.getPlayer());

        if (revive.isBleeding() || HandcuffHandler.isPlayerCuffed(event.getPlayer())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onReloadWeapon(GunReloadEvent.Pre event) {
        IBleeding revive = PlayerReviveServer.getBleeding(event.getPlayer());

        if (revive.isBleeding() || HandcuffHandler.isPlayerCuffed(event.getPlayer())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onGuiOpenEvent(GuiOpenEvent event) {
        if (Minecraft.getInstance().level == null || Minecraft.getInstance().player == null) {
            return;
        }

        IBleeding revive = PlayerReviveServer.getBleeding(Minecraft.getInstance().player);

        if (event.getGui() instanceof InventoryScreen) {
            if (revive.isBleeding()) {
                event.setCanceled(true);
            }
        }
    }

}
