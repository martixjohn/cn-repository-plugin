package com.github.martixjohn.cnrepository;

import com.github.martixjohn.cnrepository.ext.CnRepository;
import com.github.martixjohn.cnrepository.ext.CnRepositorySettingsExtension;
import org.gradle.api.Action;
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
     * for gradle to initiate
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


        settings.getGradle().settingsEvaluated(new Action<Settings>() {
            @Override
            public void execute(Settings settings) {
                CnRepositorySettingsExtension extension = settings.getExtensions().getByType(CnRepositorySettingsExtension.class);
                @SuppressWarnings("UnstableApiUsage")
                RepositoryHandler dependencyRepositories = settings.getDependencyResolutionManagement().getRepositories();
                addToRepositories(extension, dependencyRepositories);

                if (extension.getApplyToPlugin().getOrElse(false)) {
                    RepositoryHandler pluginRepositories = settings.getPluginManagement().getRepositories();
                    addToRepositories(extension, pluginRepositories);
                }
            }
        });

    }

}
