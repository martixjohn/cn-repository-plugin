package io.github.martixjohn.cnrepository;

import io.github.martixjohn.cnrepository.ext.CnRepository;
import io.github.martixjohn.cnrepository.ext.CnRepositorySettingsExtension;
import org.gradle.api.Plugin;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.initialization.Settings;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.provider.Property;


/**
 * Settings scope plugin
 */
public final class CnRepositorySettingsPlugin extends AbstractCnRepositoryPlugin implements Plugin<Settings> {
    /**
     * For gradle to initiate
     */
    public CnRepositorySettingsPlugin() {
    }

    @Override
    public void apply(Settings settings) {
        ExtensionContainer extensions = settings.getExtensions();
        CnRepositorySettingsExtension extension = extensions.create("cnRepository", CnRepositorySettingsExtension.class);
        Property<CnRepository> repositoryUrl = extension.getRepository();
        conventionUrl(repositoryUrl);
        extension.getApplyToPlugin().convention(true);


        settings.getGradle().settingsEvaluated(settings1 -> {
            CnRepositorySettingsExtension extension1 = settings1.getExtensions().getByType(CnRepositorySettingsExtension.class);
            @SuppressWarnings("UnstableApiUsage")
            RepositoryHandler dependencyRepositories = settings1.getDependencyResolutionManagement().getRepositories();
            addToRepositories(extension1, dependencyRepositories);

            if (extension1.getApplyToPlugin().getOrElse(false)) {
                RepositoryHandler pluginRepositories = settings1.getPluginManagement().getRepositories();
                addToRepositories(extension1, pluginRepositories);
            }
        });

    }

}
