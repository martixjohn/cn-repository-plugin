
## Gradle中国镜像插件

为你的Gradle项目自动添加国内仓库

目前支持以下仓库源(repository)

```
ALIYUN (默认, by default): "https://maven.aliyun.com/repository/public/"
TENCENT: "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/
NETEASE: "http://mirrors.163.com/maven/repository/maven-public/"
```

提供两种插件：

1. Settings级别(scope)插件

为项目依赖和插件配置仓库, id为 `io.github.martixjohn.cn-repository-plugin` 

此插件会修改你的settings脚本中`pluginManagement` 和 `dependencyResolutionManagement` DSL

2. Project级别(scope)插件

仅为依赖配置仓库, id为 `io.github.martixjohn.cn-repository-project-plugin`

此插件会修改你的build脚本中 `repositories` DSL


## 快速上手

### 使用Settings插件

settings.gradle.kts
```kotlin
import io.github.martixjohn.cnrepository.ext.CnRepository.*

plugins {
    id("io.github.martixjohn.cn-repository-plugin") version "1.0.0"
}

// 配置仓库
cnRepository {
    // 应用于插件仓库和依赖仓库
    repository = ALIYUN // 默认
    // 可选，不应用于插件仓库
    applyPlugin = true // 默认
}
```



### 使用Project插件

build.gradle.kts
```kotlin
import io.github.martixjohn.cnrepository.ext.CnRepository.*

plugins {
    id("io.github.martixjohn.cn-repository-project-plugin") version "1.0.0"
}

// 配置仓库
cnRepository {
    // 应用于插件仓库和依赖仓库
    repository = ALIYUN // 默认
}
```