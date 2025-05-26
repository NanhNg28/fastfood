package com.nanhng.FastFood.service.repository.upload_file;

import com.nanhng.FastFood.entity.upload_file.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile,Integer> {
    boolean existsByOriginFilePath(String originFilePath);
}
