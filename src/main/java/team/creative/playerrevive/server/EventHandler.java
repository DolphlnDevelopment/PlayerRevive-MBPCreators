package team.creative.playerrevive.server;

import dev.polv.policeitemsmod.common.HandcuffHandler;
import dev.polv.policeitemsmod.common.item.custom.HandcuffKeysItem;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.creative.playerrevive.PlayerRevive;
import team.creative.playerrevive.api.IBleeding;
import team.creative.playerrevive.api.event.PlayerBleedOutEvent;
import team.creative.playerrevive.api.event.PlayerRevivedEvent;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

/**
 * @author Pol Vallverdu (polv.dev)
 */
@Mod.EventBusSubscriber(modid = PlayerRevive.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerBleed(PlayerBleedOutEvent event) {
        ScaleData motionScaleData = ScaleTypes.MOTION.getScaleData(event.getPlayer());

        motionScaleData.resetScale();
    }

    @SubscribeEvent
    public static void onPlayerRevive(PlayerRevivedEvent event) {
        ScaleData motionScaleData = ScaleTypes.MOTION.getScaleData(event.getPlayer());

        motionScaleData.resetScale();
    }

    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        IBleeding revive = PlayerReviveServer.getBleeding(event.getPlayer());
        if (revive.isBleeding()) {
            event.setCanceled(true);
            event.setCancellationResult(ActionResultType.FAIL);
        }
    }

}
