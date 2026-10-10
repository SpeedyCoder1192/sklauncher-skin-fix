package dev.sklauncherskinfix.mixin;

import com.mojang.authlib.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.client.resources.SkinManager$CacheKey")
public interface CacheKeyAccessor {
	@Accessor("packedTextures")
	Property sklauncherskinfix$packedTextures();
}
