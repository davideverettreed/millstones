# millstones

A personal library of reusable Kotlin snippets. Pure Kotlin/JVM (Java 8 bytecode), so it works in Android, desktop, and server projects, and is callable from Java.

## Import

Add JitPack to your repositories (`settings.gradle` or root `build.gradle`):

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

Then the dependency:

```groovy
implementation 'com.github.davideverettreed:millstones:1.0.0'
```

## Contents

| Package | Item | Purpose |
|---|---|---|
| `units` | `MassUnit` | Mass conversions (mg, g, kg, t, oz, lb, short tons, elephants, CVN-78 carriers) |

```kotlin
MassUnit.POUNDS.toKilograms(185.0)          // 83.91458845
MassUnit.GRAMS.toMilligrams(12.5)           // 12500 (Long, rounded; for storage)
MassUnit.KILOGRAMS.fromMilligrams(83_914L)  // 0.083914
MassUnit.fromString("lb")                   // MassUnit.POUNDS
```

## Adding a snippet

1. Put it under `src/main/kotlin/com/davideverettreed/millstones/<topic>/`.
2. Add a test under `src/test/kotlin/...` and run `./gradlew test`.
3. Bump `version` in `build.gradle.kts`, commit, and push a matching git tag (e.g. `1.1.0`). JitPack builds from the tag.
