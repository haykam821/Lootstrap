package io.github.haykam821.lootstrap.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.lootstrap.Main;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Inject(method = "onPlayerConnect", at = @At("TAIL"))
	private void connect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
		int timesLeftGame = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.LEAVE_GAME));
		Main.giveLoot(player, new Identifier(Main.MOD_ID, "join_index_" + timesLeftGame));

		if (timesLeftGame == 0) {
			Main.giveLoot(player, Main.FIRST_JOIN_LOOT_TABLE);
		} else {
			Main.giveLoot(player, Main.REJOIN_LOOT_TABLE);
		}

		Main.giveLoot(player, Main.JOIN_LOOT_TABLE);
	}
}