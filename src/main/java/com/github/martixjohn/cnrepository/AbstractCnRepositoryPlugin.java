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
     * For subclass to use
     */
    protected AbstractCnRepositoryPlugin() {
    }

    /**
     * Set convention for repository
     *
     * @param property CnRepository
     */
    protected final void conventionUrl(Property<CnRepository> property) {
        property.convention(CnRepository.ALIYUN);
    }

    /**
     * Add the repository to RepositoryHandler
     *
     * @param extension              CnRepositoryExtension
     * @param dependencyRepositories RepositoryHandler
     */
    protected final void addToRepositories(CnRepositoryExtension extension, RepositoryHandler dependencyRepositories) {
        Action<MavenArtifactRepository> mavenAction = mavenArtifactRepository -> {
            CnRepository cnRepository = extension.getRepository().get();
            mavenArtifactRepository.setUrl(cnRepository.getUrl());
            mavenArtifactRepository.setAllowInsecureProtocol(!cnRepository.isSecure());
        };
        MavenArtifactRepository repo = dependencyRepositories.maven(mavenAction);
        dependencyRepositories.remove(repo);
        dependencyRepositories.addFirst(repo);
    }
}
