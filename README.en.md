# Gradle China‑Mirror Repository Plugin

[中文](./README.md)

Automatically configures China Maven repositories (mirrors) for your Gradle project.

Supported repository sources (Currently):

- `ALIYUN` (default): [https://maven.aliyun.com/repository/public/](https://maven.aliyun.com/repository/public/)
- `TENCENT`: [https://mirrors.cloud.tencent.com/nexus/repository/maven](https://mirrors.cloud.tencent.com/nexus/repository/maven)‑public/
- `NETEASE`: [http://mirrors.163.com/maven/repository/maven](http://mirrors.163.com/maven/repository/maven)‑public/

---

Two variants of the plugin are provided to fit different requirements:

### 1. Settings‑scoped Plugin

Configures repositories for both build‑plugin resolution and project dependencies.

Plugin ID: `io.github.martixjohn.cn‑repository‑plugin`

This plugin modifies the `pluginManagement` and `dependencyResolutionManagement` blocks inside your settings script.

### 2. Project‑scoped Plugin

Configures repositories **only for project dependencies**.

Plugin ID: `io.github.martixjohn.cn‑repository‑project‑plugin`

This plugin modifies the `repositories` block inside your build script.

---

## Quick Start

### Settings‑scoped Plugin

`settings.gradle.kts`

```
import io.github.martixjohn.cnrepository.ext.CnRepository.*

plugins {
    id("io.github.martixjohn.cn-repository-plugin") version "0.1.0"
}

cnRepository {
    // Applies mirror to both plugin and dependency repositories
    repository = ALIYUN // Default value
    // Optional: toggle mirror for plugin repositories
    applyPlugin = true // Default value
}
```

### Project‑scoped Plugin

`build.gradle.kts`

```
import io.github.martixjohn.cnrepository.ext.CnRepository.*

plugins {
    id("io.github.martixjohn.cn-repository-project-plugin") version "0.1.0"
}

cnRepository {
    repository = ALIYUN // Default value
}
```