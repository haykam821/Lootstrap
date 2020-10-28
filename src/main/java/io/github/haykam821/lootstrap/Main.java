package io.github.haykam821.lootstrap;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class Main {
	public static final String MOD_ID = "lootstrap";

	// The first join only
	public static final Identifier FIRST_JOIN_LOOT_TABLE = new Identifier(MOD_ID, "first_join");

	// Every join but the first
	public static final Identifier REJOIN_LOOT_TABLE = new Identifier(MOD_ID, "rejoin");

	// Every join
	public static final Identifier JOIN_LOOT_TABLE = new Identifier(MOD_ID, "join");

	public static void giveLoot(ServerPlayerEntity player, Identifier lootTable) {
		ServerWorld world = player.getServerWorld();
		LootTable table = world.getServer().getLootManager().getTable(lootTable);

		if (table != null) {
			LootContext.Builder builder = new LootContext.Builder(world);

			builder.random(world.getRandom());
			builder.parameter(LootContextParameters.ORIGIN, player.getPos());
			builder.parameter(LootContextParameters.THIS_ENTITY, player);

			LootContext context = builder.build(LootContextTypes.GIFT);
			List<ItemStack> stacks = table.generateLoot(context);
			stacks.forEach(player::giveItemStack);
		}
	}
}