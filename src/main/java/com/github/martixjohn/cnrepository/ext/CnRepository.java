package com.github.martixjohn.cnrepository.ext;

public enum CnRepository {
    ALIYUN("https://maven.aliyun.com/repository/public/"),
    TENCENT("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"),
    NETEASE("http://mirrors.163.com/maven/repository/maven-public/"),
    ;
    private final String url;

    CnRepository(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isSecure() {
        return this.url.startsWith("https");
    }

}
