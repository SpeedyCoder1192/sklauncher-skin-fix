package dev.serverskinsfirst.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import com.mojang.authlib.properties.Property;
//? if >=1.21.9 {
import net.minecraft.server.Services;
//?} else
/*import com.mojang.authlib.minecraft.MinecraftSessionService;*/
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// SKlauncher's agent swaps the skin read in this lambda for the skmedix one, so return the
// server's skin before it gets there. Matched by signature, the lambda's name changes between versions

@Mixin(targets = "net.minecraft.client.resources.SkinManager$1")
abstract class SkinManagerLoaderMixin {
	//? if >=1.21.9 {
	@Inject(method = "*(Lnet/minecraft/client/resources/SkinManager$CacheKey;Lnet/minecraft/server/Services;)Lcom/mojang/authlib/minecraft/MinecraftProfileTextures;", at = @At("HEAD"), cancellable = true)
	private static void serverskinsfirst$useServerSkin(@Coerce Object key, Services services, CallbackInfoReturnable<MinecraftProfileTextures> cir) {
		Property textures = ((CacheKeyAccessor) key).serverskinsfirst$packedTextures();
		if (textures != null) {
			cir.setReturnValue(services.sessionService().unpackTextures(textures));
		}
	}
	//?} else {
	/*@Inject(method = "*(Lnet/minecraft/client/resources/SkinManager$CacheKey;Lcom/mojang/authlib/minecraft/MinecraftSessionService;)Lcom/mojang/authlib/minecraft/MinecraftProfileTextures;", at = @At("HEAD"), cancellable = true)
	private static void serverskinsfirst$useServerSkin(@Coerce Object key, MinecraftSessionService sessionService, CallbackInfoReturnable<MinecraftProfileTextures> cir) {
		Property textures = ((CacheKeyAccessor) key).serverskinsfirst$packedTextures();
		if (textures != null) {
			cir.setReturnValue(sessionService.unpackTextures(textures));
		}
	}
	*///?}
}
