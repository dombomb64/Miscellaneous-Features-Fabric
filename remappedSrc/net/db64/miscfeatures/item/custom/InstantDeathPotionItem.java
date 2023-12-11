package net.db64.miscfeatures.item.custom;

import net.db64.miscfeatures.effect.ModEffects;
import net.db64.miscfeatures.util.IEntityDataSaver;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InstantDeathPotionItem extends Item {
	public static final String IS_EFFECT_GOOD_KEY = "isInstantDeathEffectGood";

	public InstantDeathPotionItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		var nbt = stack.getNbt();
		var disclaimer = ".creative";
		if (context.isCreative()) {
			if (nbt != null && nbt.getCompound(IEntityDataSaver.dataId) != null && nbt.getCompound(IEntityDataSaver.dataId).getBoolean(IS_EFFECT_GOOD_KEY)) {
				tooltip.add(Text.translatable("effect.miscfeatures.instant_death_good" + disclaimer).formatted(StatusEffectCategory.BENEFICIAL.getFormatting()));
			} else {
				tooltip.add(Text.translatable("effect.miscfeatures.instant_death_bad" + disclaimer).formatted(StatusEffectCategory.HARMFUL.getFormatting()));
			}
		}
		else {
			if (nbt != null && nbt.getCompound(IEntityDataSaver.dataId) != null && nbt.getCompound(IEntityDataSaver.dataId).getBoolean(IS_EFFECT_GOOD_KEY)) {
				tooltip.add(Text.translatable("effect.miscfeatures.instant_death_good").formatted(ModEffects.INSTANT_DEATH_GOOD.getCategory().getFormatting()));
			} else {
				tooltip.add(Text.translatable("effect.miscfeatures.instant_death_bad").formatted(ModEffects.INSTANT_DEATH_BAD.getCategory().getFormatting()));
			}
		}
		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
		if (playerEntity instanceof ServerPlayerEntity) {
			Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
		}
		if (!world.isClient) {
			var nbt = stack.getNbt();
			if (nbt != null && nbt.getCompound(IEntityDataSaver.dataId) != null && nbt.getCompound(IEntityDataSaver.dataId).getBoolean(IS_EFFECT_GOOD_KEY))
				user.addStatusEffect(new StatusEffectInstance(ModEffects.INSTANT_DEATH_GOOD, -1, 0, false, false));
			else
				user.addStatusEffect(new StatusEffectInstance(ModEffects.INSTANT_DEATH_BAD, -1, 0, false, false));
		}
		if (playerEntity != null) {
			playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
			if (!playerEntity.getAbilities().creativeMode) {
				stack.decrement(1);
			}
		}
		if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
			if (stack.isEmpty()) {
				return new ItemStack(Items.GLASS_BOTTLE);
			}
			if (playerEntity != null) {
				playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
			}
		}
		user.emitGameEvent(GameEvent.DRINK);
		return stack;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 32;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		return ItemUsage.consumeHeldItem(world, user, hand);
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		var nbt = stack.getNbt();
		if (nbt != null && nbt.getCompound(IEntityDataSaver.dataId) != null && nbt.getCompound(IEntityDataSaver.dataId).getBoolean(IS_EFFECT_GOOD_KEY))
			return "item.minecraft.potion.effect.instant_death_good";
		return "item.minecraft.potion.effect.instant_death_bad";
	}

	@Override
	public String getTranslationKey() {
		return "item.minecraft.potion.effect.instant_death_bad";
	}

	/**
	Shut up I know they're the same color shut up shut up shut up
	 **/
	public static int getColor(ItemStack stack) {
		var nbt = stack.getNbt();
		if (nbt != null && nbt.getCompound(IEntityDataSaver.dataId) != null && nbt.getCompound(IEntityDataSaver.dataId).getBoolean(IS_EFFECT_GOOD_KEY))
			return ModEffects.INSTANT_DEATH_GOOD.getColor();
		return ModEffects.INSTANT_DEATH_BAD.getColor();
	}
}
