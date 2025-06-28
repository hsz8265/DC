package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.entity.client.tstEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class mosquitoModel extends EntityModel<tstEntityRenderState> {
	private final ModelPart all;
	private final ModelPart bone;
	private final ModelPart wingr;
	private final ModelPart wingl;
	public mosquitoModel(ModelPart root) {
        super(root);
        this.all = root.getChild("all");
		this.bone = this.all.getChild("bone");
		this.wingr = this.all.getChild("wingr");
		this.wingl = this.all.getChild("wingl");
	}
	public static TexturedModelData getTexturedModelData()  {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create().uv(0, 12).cuboid(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 10.0F, new Dilation(0.0F))
				.uv(14, 12).cuboid(-1.5F, -4.0F, 3.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 4).cuboid(-2.3F, -3.0F, 4.5F, 1.5F, 1.5F, 1.5F, new Dilation(0.0F))
				.uv(0, 0).cuboid(0.7F, -3.0F, 4.5F, 1.5F, 1.5F, 1.5F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData cube_r1 = all.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7F, 2.0F, -4.0F, 0.2F, 6.0F, 0.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.3491F));

		ModelPartData cube_r2 = all.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7F, 0.0F, -1.0F, 0.2F, 6.0F, 0.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData cube_r3 = all.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7F, 1.0F, 2.0F, 0.2F, 6.0F, 0.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.3491F));

		ModelPartData cube_r4 = all.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(0.3F, 2.0F, -4.0F, 0.2F, 6.0F, 0.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, -0.3491F));

		ModelPartData cube_r5 = all.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(0.3F, 0.0F, -1.0F, 0.2F, 6.0F, 0.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData cube_r6 = all.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(0.3F, 1.0F, 2.0F, 0.2F, 6.0F, 0.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.3491F));

		ModelPartData bone = all.addChild("bone", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r7 = bone.addChild("cube_r7", ModelPartBuilder.create().uv(0, 8).cuboid(-0.3F, -1.0F, 5.0F, 0.8F, 4.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData wingr = all.addChild("wingr", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r8 = wingr.addChild("cube_r8", ModelPartBuilder.create().uv(0, 6).cuboid(-13.0F, -1.1F, -3.5F, 12.0F, 0.1F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		ModelPartData wingl = all.addChild("wingl", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r9 = wingl.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -1.1F, -3.5F, 12.0F, 0.1F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
		return TexturedModelData.of(modelData, 64, 64);
	}
}