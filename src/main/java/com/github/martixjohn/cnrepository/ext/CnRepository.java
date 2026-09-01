package com.github.martixjohn.cnrepository.ext;

/**
 * China Repository
 */
public enum CnRepository {

    /**
     * Aliyun
     */
    ALIYUN("https://maven.aliyun.com/repository/public/"),
    /**
     * Tencent cloud
     */
    TENCENT("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"),
    /**
     * Netease(163)
     */
    NETEASE("http://mirrors.163.com/maven/repository/maven-public/"),
    ;
    private final String url;

    CnRepository(String url) {
        this.url = url;
    }

    /**
     * get url for repository
     *
     * @return url
     */
    public String getUrl() {
        return this.url;
    }


    /**
     * does the repository supports https
     *
     * @return true if supports https
     */
    public boolean isSecure() {
        return this.url.startsWith("https");
    }

}
