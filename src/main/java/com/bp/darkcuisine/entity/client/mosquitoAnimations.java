package com.bp.darkcuisine.entity.client;// Save this class in your mod and generate all required imports

import net.minecraft.client.render.entity.animation.*;

/**
 * Made with Blockbench 4.12.5
 * Exported for Minecraft version 1.19 or later with Yarn mappings
 * @author Author
 */
public class mosquitoAnimations {
	public static final AnimationDefinition fly = AnimationDefinition.Builder.create(1.0F).looping()
		.addBoneAnimation("wingl", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -20.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 20.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("wingr", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 20.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -20.0F), Transformation.Interpolations.LINEAR),
				new Keyframe(1F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition attack = AnimationDefinition.Builder.create(1.0F)
		.addBoneAnimation("bone", new Transformation(Transformation.Targets.MOVE_ORIGIN,
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, -1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE, 
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(2.0F, AnimationHelper.createScalingVector(1.2F, 1.2F, 1.2F), Transformation.Interpolations.LINEAR)
		))
		.build();
}