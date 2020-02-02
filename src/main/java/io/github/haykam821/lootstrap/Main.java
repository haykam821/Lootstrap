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

public class Main {
	// The first join only
	public static final Identifier FIRST_JOIN_LOOT_TABLE = new Identifier("lootstrap", "first_join");

	// Every join but the first
	public static final Identifier REJOIN_LOOT_TABLE = new Identifier("lootstrap", "rejoin");

	// Every join
	public static final Identifier JOIN_LOOT_TABLE = new Identifier("lootstrap", "join");

	public static void giveLoot(ServerPlayerEntity playerEntity, Identifier lootTable) {
		ServerWorld world = (ServerWorld) playerEntity.world;
		LootTable table = world.getServer().getLootManager().getSupplier(lootTable);

		if (table != null) {
			LootContext.Builder builder = new LootContext.Builder(world);

			builder.setRandom(world.getRandom());
			builder.put(LootContextParameters.POSITION, playerEntity.getBlockPos());
			builder.put(LootContextParameters.THIS_ENTITY, playerEntity);

			LootContext context = builder.build(LootContextTypes.GIFT);
			List<ItemStack> stacks = table.getDrops(context);
			stacks.forEach(playerEntity::giveItemStack);
		}
	}
}