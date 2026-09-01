package com.github.martixjohn.cnrepository.ext;

import org.gradle.api.provider.Property;

public interface CnRepositoryExtension {

    Property<CnRepository> getRepository();
}
