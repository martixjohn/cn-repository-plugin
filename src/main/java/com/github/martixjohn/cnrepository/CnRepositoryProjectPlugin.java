package com.github.martixjohn.cnrepository;

import com.github.martixjohn.cnrepository.ext.CnRepository;
import com.github.martixjohn.cnrepository.ext.CnRepositoryExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.provider.Property;

/**
 * Project scope plugin
 */
public final class CnRepositoryProjectPlugin extends AbstractCnRepositoryPlugin implements Plugin<Project> {

    /**
     * For gradle to initiate
     */
    public CnRepositoryProjectPlugin() {}

    @Override
    public void apply(Project project) {
        ExtensionContainer extensions = project.getExtensions();
        CnRepositoryExtension extension = extensions.create("cnRepository", CnRepositoryExtension.class);
        Property<CnRepository> repositoryUrl = extension.getRepository();
        conventionUrl(repositoryUrl);

        project.afterEvaluate(project1 -> {
            CnRepositoryExtension extension1 = project1.getExtensions().getByType(CnRepositoryExtension.class);
            RepositoryHandler dependencyRepositories = project1.getRepositories();
            addToRepositories(extension1, dependencyRepositories);
        });

    }


}
