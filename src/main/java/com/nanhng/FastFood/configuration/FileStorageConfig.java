package com.nanhng.FastFood.configuration;

import com.nanhng.FastFood.other_service.storage.StorageResource;
import com.nanhng.FastFood.other_service.storage.constants.StorageType;
import com.nanhng.FastFood.other_service.storage.nfs_local.StorageLocal;
import com.nanhng.FastFood.other_service.storage.nfs_local.StorageNfsConfig;
import com.nanhng.FastFood.repository.banner.BannerRepository;
import com.nanhng.FastFood.service.banner.BannerService;
import com.nanhng.FastFood.service.banner.BannerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class FileStorageConfig {

    @Value("${file.storage-type}")
    private String storageType;

    private final Environment environment;

    private final BannerRepository bannerRepository;

    @Bean
    public StorageResource storageResource(){
        StorageType type = StorageType.getType(storageType);
        String directory = environment.getProperty("file.upload-dir");
        String host = environment.getProperty("media.image.website.path");
        return new StorageLocal(new StorageNfsConfig(directory,host));
    }
}
