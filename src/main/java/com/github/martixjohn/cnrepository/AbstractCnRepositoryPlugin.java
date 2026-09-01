package com.github.martixjohn.cnrepository;

import com.github.martixjohn.cnrepository.ext.CnRepository;
import com.github.martixjohn.cnrepository.ext.CnRepositoryExtension;
import org.gradle.api.Action;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.provider.Property;

public class AbstractCnRepositoryPlugin {

    protected final void conventionUrl(Property<CnRepository> property) {
        property.convention(CnRepository.ALIYUN);
    }

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
