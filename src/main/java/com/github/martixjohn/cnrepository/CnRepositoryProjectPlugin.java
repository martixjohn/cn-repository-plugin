package com.github.martixjohn.cnrepository;

import com.github.martixjohn.cnrepository.ext.CnRepository;
import com.github.martixjohn.cnrepository.ext.CnRepositoryExtension;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.provider.Property;

public abstract class CnRepositoryProjectPlugin extends AbstractCnRepositoryPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        ExtensionContainer extensions = project.getExtensions();
        CnRepositoryExtension extension = extensions.create("cnRepository", CnRepositoryExtension.class);
        Property<CnRepository> repositoryUrl = extension.getRepository();
        conventionUrl(repositoryUrl);

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                CnRepositoryExtension extension = project.getExtensions().getByType(CnRepositoryExtension.class);
                RepositoryHandler dependencyRepositories = project.getRepositories();
                addToRepositories(extension, dependencyRepositories);
            }
        });

    }


}
