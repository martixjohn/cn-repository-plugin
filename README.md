# Gradle中国镜像插件

Gradle China‑Mirror Repository Plugin

[English](./README.en.md)

为你的Gradle项目自动添加国内仓库（但不会替换中央仓库）

目前支持以下仓库源(repository)

- `ALIYUN` (默认, by default): https://maven.aliyun.com/repository/public/
- `TENCENT`: https://mirrors.cloud.tencent.com/nexus/repository/maven-public/
- `NETEASE`: http://mirrors.163.com/maven/repository/maven-public/

项目持续更新中，欢迎提出改进需求

---

为了更符合实际需求，**有两种插件可供选择**：

### 1. Settings级别(scope)插件

为项目依赖和插件配置仓库, id为 `io.github.martixjohn.cn-repository-plugin`

此插件会修改你的settings脚本中`pluginManagement` 和 `dependencyResolutionManagement` DSL


### 2. Project级别(scope)插件

仅为依赖配置仓库, id为 `io.github.martixjohn.cn-repository-project-plugin`

此插件会修改你的build脚本中 `repositories` DSL


# 快速上手

## 使用Settings插件

settings.gradle.kts
```kotlin
import io.github.martixjohn.cnrepository.CnRepository.*

plugins {
    id("io.github.martixjohn.cn-repository-plugin") version "0.2.0"
}

// 配置仓库
cnRepository {
    // 应用于依赖仓库, 使用NONE表示不应用
    onDependencies = TENCENT
    // 应用于插件仓库, 使用NONE表示不应用
    onPlugins = ALIYUN
}
```



## 使用Project插件

build.gradle.kts
```kotlin
import io.github.martixjohn.cnrepository.CnRepository.*

plugins {
    id("io.github.martixjohn.cn-repository-project-plugin") version "0.2.0"
}

// 配置仓库
cnRepository {
    // 应用于依赖仓库, 使用NONE表示不应用
    onDependencies = ALIYUN // 默认
}
```


# 其他说明

1. 并不会替换mavenCentral
2. 在配置被评估后，为最终配置的仓库前添加指定仓库