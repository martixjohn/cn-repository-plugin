package io.github.martixjohn.cnrepository.ext;

import io.github.martixjohn.cnrepository.CnRepository;
import org.gradle.api.provider.Property;

/**
 * An Gradle extension for the Settings Plugin
 */
public interface CnRepositorySettingsExtension extends CnRepositoryExtension {

    /**
     * 应用于插件的仓库
     * <p>
     * Repository applied to plugins (pluginManagement)
     *
     * @return CnRepository
     */
    Property<CnRepository> getOnPlugins();
}
