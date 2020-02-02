package io.github.haykam821.lootstrap.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.lootstrap.Main;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Inject(at = @At("TAIL"), method = "onPlayerConnect", cancellable = true)
	private void connect(ClientConnection connection, ServerPlayerEntity playerEntity, CallbackInfo callbackInfo) {
		Main.giveSpawnLoot(playerEntity);
	}
}