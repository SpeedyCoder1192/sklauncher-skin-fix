plugins {
	id("dev.kikugie.stonecutter")
}

stonecutter active "26.3"

tasks.register("buildAll") {
	group = "build"
	description = "Builds every version and copies the jars to build/libs/"
	stonecutter.versions.forEach { dependsOn(":${it.project}:collectJar") }
}
