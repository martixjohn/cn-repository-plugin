package io.github.martixjohn.cnrepository;

/**
 * China Repository
 */
public enum CnRepository {

    /**
     * 阿里云
     * <p>
     * Aliyun
     */
    ALIYUN("https://maven.aliyun.com/repository/public/"),
    /**
     * 腾讯云
     * <p>
     * Tencent cloud
     */
    TENCENT("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"),
    /**
     * 网易
     * <p>
     * Netease(163)
     */
    NETEASE("http://mirrors.163.com/maven/repository/maven-public/"),

    /**
     * 不使用仓库
     * <p>
     * Not Applied
     */
    NONE("");
    private final String url;

    CnRepository(String url) {
        this.url = url;
    }

    /**
     * Returns the url for repository
     *
     * @return url
     */
    public String getUrl() {
        return this.url;
    }


    /**
     * Does the repository supports https
     *
     * @return true if supports https
     */
    public boolean isSecure() {
        return this.url.startsWith("https");
    }

}
