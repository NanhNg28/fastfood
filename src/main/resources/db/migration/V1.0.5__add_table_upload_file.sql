CREATE TABLE `upload_file` (
                               `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID file',
                               `origin_file_path` VARCHAR(255) NOT NULL COMMENT 'Đường dẫn gốc',
                               `thumb_file_path`  VARCHAR(255) DEFAULT NULL COMMENT 'Đường dẫn ảnh thumb (nếu có)',
                               `origin_file_name` VARCHAR(255) NOT NULL COMMENT 'tên gốc',
                               `thumb_file_name`  VARCHAR(255) DEFAULT NULL COMMENT 'tên ảnh thumb (nếu có)',
                               `type`             INT NOT NULL COMMENT 'Loại file (enum UploadFileType)',
                               `width`            INT DEFAULT NULL COMMENT 'Chiều rộng (nếu là ảnh/video)',
                               `height`           INT DEFAULT NULL COMMENT 'Chiều cao (nếu là ảnh/video)',
                               `duration`         INT DEFAULT NULL COMMENT 'Thời lượng (nếu là video)',
                               `size`             BIGINT DEFAULT NULL COMMENT 'Kích thước file (bytes)',
                               `created_at`       TIMESTAMP NOT NULL  COMMENT 'Ngày tạo',
                               `updated_at`       TIMESTAMP NOT NULL COMMENT 'Ngày cập nhật',
                               `deleted`          BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                               PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT 'Thông tin file upload'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;