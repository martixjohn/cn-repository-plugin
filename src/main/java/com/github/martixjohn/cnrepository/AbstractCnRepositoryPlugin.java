package com.github.martixjohn.cnrepository;

import org.gradle.api.Action;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.provider.Property;

public class AbstractCnRepositoryPlugin {

    protected final void createExtension(ExtensionContainer extensions) {
        CnRepositoryExtension extension = extensions.create("cnRepository", CnRepositoryExtension.class);
        Property<CnRepository> repositoryUrl = extension.getRepository();
        repositoryUrl.convention(CnRepository.ALIYUN);
    }

    protected final void addToRepositories(ExtensionContainer extensions, RepositoryHandler dependencyRepositories) {
        Action<MavenArtifactRepository> mavenAction = new Action<>() {
            @Override
            public void execute(MavenArtifactRepository mavenArtifactRepository) {
                CnRepositoryExtension byType = extensions.getByType(CnRepositoryExtension.class);
                CnRepository cnRepository = byType.getRepository().get();
                mavenArtifactRepository.setUrl(cnRepository.getUrl());
            }
        };
        MavenArtifactRepository repo = dependencyRepositories.maven(mavenAction);
        dependencyRepositories.remove(repo);
        dependencyRepositories.add(0, repo);
    }
}
