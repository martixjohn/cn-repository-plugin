package com.github.martixjohn.cnrepository.ext;

import org.gradle.api.provider.Property;

public interface CnRepositorySettingsExtension extends CnRepositoryExtension {

    Property<Boolean> getApplyToPlugin();
}
