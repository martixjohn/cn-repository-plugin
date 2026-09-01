package com.github.martixjohn.cnrepository;

import com.github.martixjohn.cnrepository.ext.CnRepository;
import com.github.martixjohn.cnrepository.ext.CnRepositoryExtension;
import org.gradle.api.Action;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.provider.Property;


/**
 * Abstract class for plugin
 */
public abstract sealed class AbstractCnRepositoryPlugin permits CnRepositoryProjectPlugin, CnRepositorySettingsPlugin {

    /**
     * for subclass to use
     */
    protected AbstractCnRepositoryPlugin() {
    }

    /**
     * set convention for repository
     *
     * @param property CnRepository
     */
    protected final void conventionUrl(Property<CnRepository> property) {
        property.convention(CnRepository.ALIYUN);
    }

    /**
     * add repository to RepositoryHandler
     *
     * @param extension              CnRepositoryExtension
     * @param dependencyRepositories dependencyRepositories
     */
    protected final void addToRepositories(CnRepositoryExtension extension, RepositoryHandler dependencyRepositories) {
        Action<MavenArtifactRepository> mavenAction = new Action<>() {
            @Override
            public void execute(MavenArtifactRepository mavenArtifactRepository) {
                CnRepository cnRepository = extension.getRepository().get();
                mavenArtifactRepository.setUrl(cnRepository.getUrl());
                mavenArtifactRepository.setAllowInsecureProtocol(!cnRepository.isSecure());
            }
        };
        MavenArtifactRepository repo = dependencyRepositories.maven(mavenAction);
        dependencyRepositories.remove(repo);
        dependencyRepositories.add(0, repo);
    }
}
