package me.chaounne.reachcirclemod;

import me.chaounne.reachcirclemod.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.joml.Matrix4f;

public class ReachCircleModClient implements ClientModInitializer {

    private static boolean showCircle = false;
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
