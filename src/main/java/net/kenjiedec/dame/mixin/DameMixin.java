package net.kenjiedec.dame.mixin;

import net.kenjiedec.dame.Dame;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)

public abstract class DameMixin extends LivingEntity {
	@Shadow public abstract boolean damage(DamageSource source, float amount);

	@Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setAbsorptionAmount(F)V"), cancellable = true)
	private void cancel(DamageSource source, float amount, CallbackInfo CallbackInfo) {
		if (!world.getGameRules().getBoolean(Dame.DAMAGE) && !source.isOutOfWorld()) {
			CallbackInfo.cancel();
		}
	}

	@ModifyVariable(method = "applyDamage", at = @At("HEAD"), ordinal = 0)
	private float scale(float y) {
		return y * world.getGameRules().getInt(Dame.SCALE);
	}

	@Inject(method = "isInvulnerableTo", at = @At("TAIL"), cancellable = true)
	private void lmao(DamageSource dS, CallbackInfoReturnable<Boolean> CallbackInfoReturnable) {
		if (dS.getAttacker() != null && !dS.isMagic() && dS.getAttacker() != this) {
			CallbackInfoReturnable.setReturnValue(!world.getGameRules().getBoolean(Dame.MOBS));
		} else if (dS.getAttacker() != null && !dS.isMagic() && dS.getAttacker() == this) {
			CallbackInfoReturnable.setReturnValue(!world.getGameRules().getBoolean(Dame.SELF));
		} else if (dS.isMagic()) CallbackInfoReturnable.setReturnValue(!world.getGameRules().getBoolean(Dame.POTION));
	}




	protected DameMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	private void init(CallbackInfo info) {
		Dame.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
