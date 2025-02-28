package net.lawliet.nea_hunger.compatibility.appleskin;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.slf4j.Logger;
import squeek.appleskin.api.event.TooltipOverlayEvent;

@EventBusSubscriber(modid = "appleskin", bus = EventBusSubscriber.Bus.MOD)
public class AppleSkinEventHandler {

    private static final Logger LOGGER = LogUtils.getLogger();


    @SubscribeEvent
    public void onPreTooltipEvent(TooltipOverlayEvent.Pre event) {
        LOGGER.info("Inside the event bus of apple skin");
    }
}
