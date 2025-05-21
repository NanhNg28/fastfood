package com.nanhng.FastFood.other_service.storage.nfs_local;

import com.nanhng.FastFood.other_service.storage.StorageConfig;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StorageNfsConfig implements StorageConfig {
    String directory;
}
