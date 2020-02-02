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
	public static final Identifier SPAWN_LOOT_TABLE = new Identifier("lootstrap", "spawn");

	public static void giveSpawnLoot(ServerPlayerEntity playerEntity) {
		ServerWorld world = (ServerWorld) playerEntity.world;
		LootTable table = world.getServer().getLootManager().getSupplier(SPAWN_LOOT_TABLE);

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