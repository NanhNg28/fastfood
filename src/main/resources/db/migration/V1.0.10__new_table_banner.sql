CREATE TABLE `banner`
(
    id            int unsigned NOT NULL AUTO_INCREMENT ,
    link          varchar(400)   DEFAULT NULL ,
    image_id      int  UNSIGNED ,
    deleted       bit          NOT NULL DEFAULT 0,
    created_at    timestamp    NOT NULL,
    updated_at    timestamp    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`image_id`) references `upload_file`(`id`)
) ENGINE = InnoDB COMMENT ='banner'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;