package io.github.martixjohn.cnrepository.ext;

import org.gradle.api.provider.Property;


/**
 * A Gradle extension for the Project plugin
 */
public interface CnRepositoryExtension {

    /**
     * China Repository to use
     *
     * @return the repository to use
     */
    Property<CnRepository> getRepository();
}
