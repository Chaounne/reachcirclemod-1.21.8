package me.chaounne.reachcirclemod;

import me.chaounne.reachcirclemod.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class ReachCircleModClient implements ClientModInitializer {

    private static boolean showCircle = false;
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
