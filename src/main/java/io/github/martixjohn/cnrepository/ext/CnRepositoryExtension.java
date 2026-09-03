package io.github.martixjohn.cnrepository.ext;

import io.github.martixjohn.cnrepository.CnRepository;
import org.gradle.api.provider.Property;


/**
 * A Gradle extension for the Project plugin
 */
public interface CnRepositoryExtension {
    /**
     * 应用于依赖的仓库
     * <p>
     * Repository applied to plugin (pluginManagement)
     *
     * @return CnRepository
     */
    Property<CnRepository> getOnDependencies();
}
