plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.mokekinho"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	//security
	

	val jjwtVersion = "0.13.0"

	implementation("org.springframework.boot:spring-boot-starter-security") //por padrão ele ja vai bloquear todas as telas e redirecionar pra tela de login
	//implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
	//runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
	//runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
