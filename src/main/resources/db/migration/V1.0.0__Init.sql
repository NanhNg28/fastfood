CREATE TABLE `users` (
                         `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `username`   VARCHAR(255) NOT NULL COMMENT 'Tên đăng nhập',
                         `password`   VARCHAR(255) NOT NULL COMMENT 'Mật khẩu',
                         `email`      VARCHAR(255) DEFAULT NULL COMMENT 'Email người dùng',
                         `phone`      VARCHAR(20)  NOT NULL COMMENT 'Số điện thoại',
                         `role`       INT          NOT NULL COMMENT 'Vai trò: 0 - ADMIN, 1 - USER, 2 - EMPLOYEE',
                         `deleted`    BIT          NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                         `created_at`       TIMESTAMP COMMENT 'Thời gian tạo',
                         `updated_at`       TIMESTAMP COMMENT 'Thời gian cập nhật',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_username` (`username`),
                         UNIQUE KEY `UK_email` (`email`)
) ENGINE = InnoDB COMMENT 'Bảng lưu thông tin người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `upload_files`(
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

CREATE TABLE `addresses` (
                             `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
                             `city`       VARCHAR(255) NOT NULL COMMENT 'Thành phố',
                             `street`     VARCHAR(255) NOT NULL COMMENT 'Đường phố',
                             `user_id` INT UNSIGNED DEFAULT NULL COMMENT 'Địa chỉ của người dùng',
                             `deleted`    bit       NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                             `created_at` timestamp COMMENT 'Thời gian tạo',
                             `updated_at` timestamp COMMENT 'Thời gian cập nhật',
                             PRIMARY KEY (`id`),
                             CONSTRAINT `FK_user_address` FOREIGN KEY (`user_id`) REFERENCES users (`id`)
) ENGINE = InnoDB COMMENT 'Địa chỉ người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `categories` (
                              `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `name`        VARCHAR(255) NOT NULL COMMENT 'Tên thể loại',
                              `description` VARCHAR(255) DEFAULT NULL COMMENT 'Mô tả thể loại',
                              `image_id`          INT UNSIGNED NOT NULL,
                              `deleted`     BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                              `created_at`  TIMESTAMP COMMENT 'Thời gian tạo',
                              `updated_at`  TIMESTAMP COMMENT 'Thời gian cập nhật',
                              PRIMARY KEY (`id`),
                              FOREIGN KEY(`image_id`) references upload_files(`id`)
) ENGINE = InnoDB COMMENT 'Thể loại'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `products` (
                            `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
                            `name`             VARCHAR(255) NOT NULL COMMENT 'Tên sản phẩm',
                            `price`            INT NOT NULL COMMENT 'Giá sản phẩm',
                            `category_id`      INT UNSIGNED NOT NULL COMMENT 'ID thể loại sản phẩm',
                            `quantity`         INT NOT NULL DEFAULT 0 COMMENT 'Số lượng sản phẩm',
                            `image_id`          INT UNSIGNED NOT NULL,
                            `short_description` VARCHAR(255) DEFAULT NULL COMMENT 'Mô tả ngắn',
                            `long_description` TEXT DEFAULT NULL COMMENT 'Mô tả chi tiết',
                            `deleted`          BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                            `created_at`       TIMESTAMP COMMENT 'Thời gian tạo',
                            `updated_at`       TIMESTAMP COMMENT 'Thời gian cập nhật',
                            PRIMARY KEY (`id`),
                            FOREIGN KEY(`image_id`) references upload_files(`id`),
                            FOREIGN KEY (`category_id`) REFERENCES categories (`id`)
) ENGINE = InnoDB COMMENT 'Sản phẩm'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `carts` (
                         `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `user_id`    INT UNSIGNED NOT NULL COMMENT 'ID người dùng sở hữu giỏ hàng',
                         PRIMARY KEY (`id`),
                         CONSTRAINT `FK_cart_user` FOREIGN KEY (`user_id`) REFERENCES users (`id`)
) ENGINE = InnoDB COMMENT 'Giỏ hàng của người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `cart_items` (
                              `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `quantity`     INT NOT NULL DEFAULT 1 COMMENT 'Số lượng sản phẩm trong giỏ',
                              `price`        DOUBLE UNSIGNED,
                              `cart_id`      INT UNSIGNED NOT NULL COMMENT 'ID giỏ hàng',
                              `product_id`   INT UNSIGNED NOT NULL COMMENT 'ID sản phẩm',
                              PRIMARY KEY (`id`),
                              CONSTRAINT `FK_cartitem_cart` FOREIGN KEY (`cart_id`) REFERENCES carts (`id`) ,
                              CONSTRAINT `FK_cartitem_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE = InnoDB COMMENT 'Chi tiết sản phẩm trong giỏ hàng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
                          `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
                          `user_id`      INT UNSIGNED NOT NULL COMMENT 'ID người dùng',
                          `address_id`   INT UNSIGNED NOT NULL COMMENT 'ID địa chỉ giao hàng',
                          `total_price`  DOUBLE NOT NULL COMMENT 'Tổng tiền',
                          `order_status` VARCHAR(50) COMMENT 'Trạng thái đơn hàng',
                          `created_at`       TIMESTAMP COMMENT 'Thời gian tạo',
                          `updated_at`       TIMESTAMP COMMENT 'Thời gian cập nhật',
                          `deleted`      BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                          `note`       VARCHAR(255) DEFAULT NULL COMMENT 'Thành phố',
                          PRIMARY KEY (`id`),
                          CONSTRAINT `FK_order_user` FOREIGN KEY (`user_id`) REFERENCES users (`id`)
) ENGINE = InnoDB COMMENT 'Đơn hàng của người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `order_items` (
                               `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
                               `order_id`     INT UNSIGNED NOT NULL COMMENT 'ID đơn hàng',
                               `product_id`   INT UNSIGNED NOT NULL COMMENT 'ID sản phẩm',
                               `quantity`     INT NOT NULL COMMENT 'Số lượng sản phẩm',
                               `price`        INT NOT NULL COMMENT 'Giá sản phẩm tại thời điểm đặt hàng',
                               `deleted`      BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                               `created_at`    datetime        NOT NULL,
                               `updated_at`    datetime        NOT NULL,
                               PRIMARY KEY (`id`),
                               CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ,
                               CONSTRAINT `FK_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE = InnoDB COMMENT 'Sản phẩm trong đơn hàng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `transactions`
(
    `id`            int    unsigned NOT NULL AUTO_INCREMENT,
    `user_id`       int    unsigned NOT NULL ,
    `amount`      int    unsigned NOT NULL DEFAULT 0,
    `content`          text,
    `deleted`       bit          NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
    `created_at`    datetime        NOT NULL,
    `updated_at`    datetime        NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`)
) ENGINE = InnoDB COMMENT 'Đơn hàng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE `banners`
(
    id            int unsigned NOT NULL AUTO_INCREMENT ,
    link          varchar(400)   DEFAULT NULL ,
    image_id      int  UNSIGNED ,
    deleted       bit          NOT NULL DEFAULT 0,
    created_at    timestamp    NOT NULL,
    updated_at    timestamp    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`image_id`) references upload_files(`id`)
) ENGINE = InnoDB COMMENT ='banners'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO users (username, password, email, phone, role, deleted, created_at, updated_at)
VALUES ('admin', '$2a$10$90oddm68E7vWuBmnyv/ecu2yjNXlaYq9C0QHOBXQ93/2xEDX4J.iq', 'admin@gmail.com', '0123456789', 0, FALSE, NOW(), NOW());

CREATE TABLE `otps`
(
    id             int unsigned NOT NULL AUTO_INCREMENT ,
    otp            varchar(10)   DEFAULT NULL ,
    email          varchar(255)   DEFAULT NULL ,
    phone          varchar(30)   DEFAULT NULL ,
    attempt_count   INT,
    send_type       INT,
    verify_status   INT,
    deleted       bit          NOT NULL DEFAULT 0,
    created_at    timestamp    NOT NULL,
    updated_at    timestamp    NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT ='banner'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;