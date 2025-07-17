package com.bp.darkcuisine.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class WeiEffect extends StatusEffect {

    public WeiEffect() {
        super(StatusEffectCategory.HARMFUL, 0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
