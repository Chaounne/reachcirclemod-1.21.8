package me.chaounne.reachcirclemod;

import net.fabricmc.api.ClientModInitializer;
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
    @Override
    public void onInitializeClient() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            PlayerEntity player = client.player;

            if (player == null) return;

            renderCircle(
                    context.matrixStack(),
                    context.consumers(),
                    context.camera(),
                    player
            );
        });

    }

    private void renderCircle(
            MatrixStack matrices,
            VertexConsumerProvider consumers,
            Camera camera,
            PlayerEntity player
    ) {
        double x = player.getX() - camera.getPos().x;
        double y = player.getY() - camera.getPos().y;
        double z = player.getZ() - camera.getPos().z;

        matrices.push();
        matrices.translate(x, y + 0.1, z);

        float radius = 3.0f;
        float thickness = 0.2f;
        int segments = 64;

        float inner = radius - thickness / 2.0f;
        float outer = radius + thickness / 2.0f;

        VertexConsumer buffer = consumers.getBuffer(RenderLayer.getDebugFilledBox());
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        for (int i = 0; i < segments; i++) {
            double a1 = 2 * Math.PI * i / segments;
            double a2 = 2 * Math.PI * (i + 1) / segments;

            float ix1 = (float) (Math.cos(a1) * inner);
            float iz1 = (float) (Math.sin(a1) * inner);
            float ox1 = (float) (Math.cos(a1) * outer);
            float oz1 = (float) (Math.sin(a1) * outer);

            float ix2 = (float) (Math.cos(a2) * inner);
            float iz2 = (float) (Math.sin(a2) * inner);
            float ox2 = (float) (Math.cos(a2) * outer);
            float oz2 = (float) (Math.sin(a2) * outer);

            // Quad (2 triangles)
            buffer.vertex(matrix, ix1, 0, iz1).color(0, 255, 0, 150).normal(0, 1, 0);
            buffer.vertex(matrix, ox1, 0, oz1).color(0, 255, 0, 150).normal(0, 1, 0);
            buffer.vertex(matrix, ox2, 0, oz2).color(0, 255, 0, 150).normal(0, 1, 0);

            buffer.vertex(matrix, ix1, 0, iz1).color(0, 255, 0, 150).normal(0, 1, 0);
            buffer.vertex(matrix, ox2, 0, oz2).color(0, 255, 0, 150).normal(0, 1, 0);
            buffer.vertex(matrix, ix2, 0, iz2).color(0, 255, 0, 150).normal(0, 1, 0);
        }

        matrices.pop();
    }

}
