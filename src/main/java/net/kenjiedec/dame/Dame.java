package net.kenjiedec.dame;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dame implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("modid");

	public static final GameRules.Key<GameRules.BooleanRule> DAMAGE = GameRuleRegistry.register("takeDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.IntRule> SCALE = GameRuleRegistry.register("damageReceiveMultiplier", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(100, 1));
	public static final GameRules.Key<GameRules.BooleanRule> MOBS = GameRuleRegistry.register("mobsDamage", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.BooleanRule> POTION = GameRuleRegistry.register("potionDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.BooleanRule> SELF = GameRuleRegistry.register("damageSelf", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Yo!");
	}
}
