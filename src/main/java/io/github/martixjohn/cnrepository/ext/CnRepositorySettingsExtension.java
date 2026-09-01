package io.github.martixjohn.cnrepository.ext;

import org.gradle.api.provider.Property;

/**
 * An Gradle extension for the Settings Plugin
 */
public interface CnRepositorySettingsExtension extends CnRepositoryExtension {

    /**
     * Is the repository applied to plugin repository (pluginManagement)
     *
     * @return is it applied to plugins management
     */
    Property<Boolean> getApplyToPlugin();
}
