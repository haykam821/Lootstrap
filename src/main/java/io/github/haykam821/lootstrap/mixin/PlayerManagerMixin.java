package io.github.haykam821.lootstrap.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.lootstrap.Main;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Inject(at = @At("TAIL"), method = "onPlayerConnect", cancellable = true)
	private void connect(ClientConnection connection, ServerPlayerEntity playerEntity, CallbackInfo callbackInfo) {
		int timesLeftGame = playerEntity.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.LEAVE_GAME));

		if (timesLeftGame == 0) {
			Main.giveLoot(playerEntity, Main.FIRST_JOIN_LOOT_TABLE);
		} else {
			Main.giveLoot(playerEntity, Main.REJOIN_LOOT_TABLE);
		}

		Main.giveLoot(playerEntity, Main.JOIN_LOOT_TABLE);
	}
}