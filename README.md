
## Gradle中国镜像插件

为你的Gradle项目自动添加国内仓库

包含两个插件:

- Settings级别(scope)插件: com.github.martixjohn.cn-repository-plugin
- Project级别(scope)插件: com.github.martixjohn.cn-repository-project-plugin


## 快速上手

settings.gradle.kts
```kotlin
import com.github.martixjohn.cnrepository.ext.CnRepository.*

plugins {
    id("com.github.martixjohn.cn-repository-plugin") version "1.0.0"
}

// 配置仓库
cnRepository {
    // 应用于插件仓库和依赖仓库
    repository = ALIYUN // 默认
    // 可选，不应用于插件仓库
    applyPlugin = true // 默认
}
```

仓库目前支持以下，可联系作者添加

```class
public enum CnRepository {
    ALIYUN("https://maven.aliyun.com/repository/public/"),
    TENCENT("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"),
    NETEASE("http://mirrors.163.com/maven/repository/maven-public/");
    // ...
}
```


另外，也提供了只应用于Project级别的插件，**但不能指定插件依赖的仓库**

build.gradle.kts
```kotlin
import com.github.martixjohn.cnrepository.ext.CnRepository.*

plugins {
    id("com.github.martixjohn.cn-repository-project-plugin") version "1.0.0"
}

// 配置仓库
cnRepository {
    // 应用于插件仓库和依赖仓库
    repository = ALIYUN // 默认
}
```