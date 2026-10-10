plugins {
	id("dev.kikugie.loom-back-compat")
	id("me.modmuss50.mod-publish-plugin")
}

val mc = stonecutter.current.version
val modId = property("mod.id") as String
val javaVersion = when {
	stonecutter.current.parsed >= "26.1" -> 25
	stonecutter.current.parsed >= "1.20.5" -> 21
	else -> 17
}

version = "${property("mod.version")}+$mc"
group = property("mod.group") as String
base.archivesName = modId

dependencies {
	minecraft("com.mojang:minecraft:$mc")
	loomx.applyMojangMappings()
	modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
}

java {
	toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
}

tasks.processResources {
	val props = mapOf(
		"version" to project.version,
		"minecraft" to project.property("mod.mc_fabric"),
		"java" to javaVersion
	)
	inputs.properties(props)
	filesMatching(listOf("fabric.mod.json", "$modId.mixins.json")) { expand(props) }
}

tasks.jar {
	from(rootProject.file("LICENSE")) {
		rename { "${it}_$modId" }
	}
}

tasks.register<Copy>("collectJar") {
	group = "build"
	from(loomx.modJar.map { it.archiveFile })
	into(rootProject.layout.buildDirectory.dir("libs"))
}

publishMods {
	val modrinthId = property("publish.modrinth_id") as String
	file = loomx.modJar.flatMap { it.archiveFile }
	displayName = "SKlauncher Skin Fix ${property("mod.version")} ($mc)"
	version = project.version.toString()
	changelog = providers.gradleProperty("changelog").orElse(rootProject.file("CHANGELOG.md").readText())
	type = if (findProperty("publish.type") == "beta") BETA else STABLE
	modLoaders.add("fabric")
	// missing a token or project id -> only prints what it would upload
	dryRun = modrinthId.isEmpty() ||
		(!hasProperty("modrinth.token") && System.getenv("MODRINTH_TOKEN") == null) ||
		System.getenv("GITHUB_TOKEN") == null

	modrinth {
		accessToken = providers.gradleProperty("modrinth.token").orElse(providers.environmentVariable("MODRINTH_TOKEN"))
		projectId = modrinthId.ifEmpty { "dry-run" }
		environment = CLIENT_ONLY
		minecraftVersions.addAll((property("publish.mc_targets") as String).split(" "))
	}

	// one release per version, same text as on Modrinth. ./release.sh fills GITHUB_TOKEN from `gh auth token`
	github {
		accessToken = providers.environmentVariable("GITHUB_TOKEN")
		repository = "SpeedyCoder1192/sklauncher-skin-fix"
		commitish = "main"
		tagName = project.version.toString()
	}
}
