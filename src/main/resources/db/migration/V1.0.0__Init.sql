CREATE TABLE `address` (
                           `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
                           `city`       VARCHAR(255) NOT NULL COMMENT 'Thành phố',
                           `street`     VARCHAR(255) NOT NULL COMMENT 'Đường phố',
                           `status`     bit         NOT NULL DEFAULT 1 COMMENT 'Trạng thái: 0 - INACTIVE, 1 - ACTIVE',
                           `deleted`    bit       NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                           `created_at` timestamp COMMENT 'Thời gian tạo',
                           `updated_at` timestamp COMMENT 'Thời gian cập nhật',
                           PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT 'Địa chỉ người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                        `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
                        `username`   VARCHAR(255) NOT NULL COMMENT 'Tên đăng nhập',
                        `password`   VARCHAR(255) NOT NULL COMMENT 'Mật khẩu',
                        `email`      VARCHAR(255) DEFAULT NULL COMMENT 'Email người dùng',
                        `phone`      VARCHAR(20)  NOT NULL COMMENT 'Số điện thoại',
                        `status`     BIT          NOT NULL DEFAULT 1 COMMENT 'Trạng thái: 0 - INACTIVE, 1 - ACTIVE',
                        `role`       INT          NOT NULL COMMENT 'Vai trò: 0 - ADMIN, 1 - USER, 2 - Shipper',
                        `address_id` INT UNSIGNED DEFAULT NULL COMMENT 'Địa chỉ của người dùng',
                        `deleted`    BIT          NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                        `created_at` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Thời gian tạo',
                        `updated_at` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Thời gian cập nhật',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_username` (`username`),
                        UNIQUE KEY `UK_email` (`email`),
                        CONSTRAINT `FK_user_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL
) ENGINE = InnoDB COMMENT 'Bảng lưu thông tin người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `category` (
                            `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
                            `name`        VARCHAR(255) NOT NULL COMMENT 'Tên thể loại',
                            `description` VARCHAR(255) DEFAULT NULL COMMENT 'Mô tả thể loại',
                            `status`      BIT NOT NULL DEFAULT 1 COMMENT 'Trạng thái: 0 - INACTIVE, 1 - ACTIVE',
                            `deleted`     BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                            `created_at`  TIMESTAMP COMMENT 'Thời gian tạo',
                            `updated_at`  TIMESTAMP COMMENT 'Thời gian cập nhật',
                            PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT 'Thể loại'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
                           `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
                           `name`             VARCHAR(255) NOT NULL COMMENT 'Tên sản phẩm',
                           `price`            DOUBLE NOT NULL COMMENT 'Giá sản phẩm',
                           `category_id`      INT UNSIGNED NOT NULL COMMENT 'ID thể loại sản phẩm',
                           `quantity`         INT NOT NULL DEFAULT 0 COMMENT 'Số lượng sản phẩm',
                           `short_description` VARCHAR(255) DEFAULT NULL COMMENT 'Mô tả ngắn',
                           `long_description` TEXT DEFAULT NULL COMMENT 'Mô tả chi tiết',
                           `status`           BIT NOT NULL DEFAULT 1 COMMENT 'Trạng thái: 0 - INACTIVE, 1 - ACTIVE',
                           `deleted`          BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                           `created_at`       TIMESTAMP COMMENT 'Thời gian tạo',
                           `updated_at`       TIMESTAMP COMMENT 'Thời gian cập nhật',
                           PRIMARY KEY (`id`),
                           CONSTRAINT `FK_category_product` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE = InnoDB COMMENT 'Sản phẩm'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `cart` (
                        `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
                        `user_id`    INT UNSIGNED NOT NULL COMMENT 'ID người dùng sở hữu giỏ hàng',
                        PRIMARY KEY (`id`),
                        CONSTRAINT `FK_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB COMMENT 'Giỏ hàng của người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `cart_item` (
                             `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
                             `quantity`     INT NOT NULL DEFAULT 1 COMMENT 'Số lượng sản phẩm trong giỏ',
                             `cart_id`      INT UNSIGNED NOT NULL COMMENT 'ID giỏ hàng',
                             `product_id`   INT UNSIGNED NOT NULL COMMENT 'ID sản phẩm',
                             PRIMARY KEY (`id`),
                             CONSTRAINT `FK_cartitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ,
                             CONSTRAINT `FK_cartitem_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE = InnoDB COMMENT 'Chi tiết sản phẩm trong giỏ hàng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `order` (
                         `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `user_id`      INT UNSIGNED NOT NULL COMMENT 'ID người dùng',
                         `address_id`   INT UNSIGNED NOT NULL COMMENT 'ID địa chỉ giao hàng',
                         `total_price`  DOUBLE NOT NULL COMMENT 'Tổng tiền',
                         `order_status` VARCHAR(50) COMMENT 'Trạng thái đơn hàng',
                         `created_at`       TIMESTAMP COMMENT 'Thời gian tạo',
                         `updated_at`       TIMESTAMP COMMENT 'Thời gian cập nhật',
                         `deleted`      BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                         `city`       VARCHAR(255) NOT NULL COMMENT 'Thành phố',
                         `street`     VARCHAR(255) NOT NULL COMMENT 'Đường phố',
                         PRIMARY KEY (`id`),
                         CONSTRAINT `FK_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB COMMENT 'Đơn hàng của người dùng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `order_item` (
                              `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `order_id`     INT UNSIGNED NOT NULL COMMENT 'ID đơn hàng',
                              `product_id`   INT UNSIGNED NOT NULL COMMENT 'ID sản phẩm',
                              `quantity`     INT NOT NULL COMMENT 'Số lượng sản phẩm',
                              `price`        DOUBLE NOT NULL COMMENT 'Giá sản phẩm tại thời điểm đặt hàng',
                              `deleted`      BIT NOT NULL DEFAULT 0 COMMENT 'Trạng thái xóa: 0 - Chưa xóa, 1 - Đã xóa',
                              PRIMARY KEY (`id`),
                              CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ,
                              CONSTRAINT `FK_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE = InnoDB COMMENT 'Sản phẩm trong đơn hàng'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;














































-- CREATE TABLE `permissions`
-- (
--     `id`            int unsigned NOT NULL AUTO_INCREMENT,
--     `title`             varchar(255)          DEFAULT NULL COMMENT 'Lưu trữ tiêu đề hoặc tên của quyền, giúp mô tả ngắn gọn về quyền đó',
--     `permission`        varchar(255) NOT NULL COMMENT 'Lưu trữ tên quyền, dùng để xác định quyền trong hệ thống. Đây là một giá trị duy nhất trong bảng, đảm bảo không có hai quyền trùng nhau.',
--     `parent_permission` varchar(255)          DEFAULT NULL COMMENT 'Lưu trữ quyền cha (parent permission), nếu quyền này thuộc một quyền cấp cao hơn nó',
--     `can_view`          bit                   DEFAULT NULL COMMENT '`0`: là không có quyền xem, `1`: là có quyền xem',
--     `can_write`         bit                   DEFAULT NULL COMMENT '`0`: là không có quyền ghi, `1`: là có quyền ghi',
--     `can_approval`      bit                   DEFAULT NULL COMMENT '`0`: là không có quyền duyệt, `1`: là có quyền duyệt',
--     `can_decision`      bit                   DEFAULT NULL COMMENT '`0`: là không có quyền xóa, `1`: là có quyền xóa',
--     `type`              int          NOT NULL COMMENT '0 - ADMIN, 1 - CUSTOMER, 2 -SHIPPER',
--     `status`            int          NOT NULL DEFAULT '1' COMMENT 'Trạng thái của quyền. `1`: Đang hoạt động, `0`: Không hoạt động',
--     `deleted`           bit          NOT NULL DEFAULT 0,
--     `created_at`        timestamp    NULL NOT NULL,
--     `updated_at`        timestamp    NULL NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (permission,`type`)
-- ) ENGINE = InnoDB COMMENT 'Quyền'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `roles`
-- (
--     `id`         int unsigned NOT NULL AUTO_INCREMENT,
--     `object_id`  int unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến admin, customer,shipper tương ứng với trường type',
--     `name`       varchar(255) NOT NULL COMMENT 'Tên của vai trò',
--     `note`       TEXT                  DEFAULT NULL COMMENT 'Ghi chú bổ sung về vai trò',
--     `type`       int          NOT NULL COMMENT '0 - ADMIN, 1 - Customer, 2 - Shipper',
--     `status`     int          NOT NULL DEFAULT '1' COMMENT 'Trạng thái hoạt động: `0`: Không hoạt động, 1: Hoạt động',
--     `deleted`    bit          NOT NULL DEFAULT b'0',
--     `created_at` timestamp    NULL     DEFAULT NULL,
--     `updated_at` timestamp    NULL     DEFAULT NULL,
--     PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB COMMENT 'Vai trò'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `role_permission`
-- (
--     `id`            int unsigned NOT NULL AUTO_INCREMENT,
--     `role_id`       int unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến vai trò',
--     `permission_id` int unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến quyền hạn',
--     `can_view`      bit                   DEFAULT NULL COMMENT 'Quyền xem: 0 - Không có quyền, 1 - Có quyền',
--     `can_write`     bit                   DEFAULT NULL COMMENT 'Quyền viết: 0 - Không có quyền, 1 - Có quyền',
--     `can_approval`  bit                   DEFAULT NULL COMMENT 'Quyền duyệt: 0 - Không có quyền, 1 - Có quyền',
--     `can_decision`  bit                   DEFAULT NULL COMMENT 'Quyền xóa: 0 - Không có quyền, 1 - Có quyền',
--     `deleted`       bit          NOT NULL DEFAULT 0,
--     `created_at`    timestamp    NULL     DEFAULT NULL,
--     `updated_at`    timestamp    NULL     DEFAULT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`role_id`, `permission_id`),
--     FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
--     FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
-- ) ENGINE = InnoDB COMMENT 'Quyền của vai trò'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `user`
-- (
--     `id`            int unsigned NOT NULL AUTO_INCREMENT,
--     `username`      varchar(255) NOT NULL COMMENT 'Tên đăng nhập',
--     `password`      varchar(255) DEFAULT NULL COMMENT 'Mật khẩu',
--     `name`          varchar(255) NOT NULL COMMENT 'Tên người dùng',
--     `email`         varchar(255) NOT NULL ,
--     `phone`         varchar(20)  NOT NULL ,
--     `gender`        int          DEFAULT NULL,
--     `role_id`    int unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến vai trò của người dùng',
--     `deleted`       bit(1)       NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    datetime     NOT NULL,
--     `updated_at`    datetime     NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`username`),
--     FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`)
-- ) ENGINE = InnoDB COMMENT 'Người dùng'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `category`
-- (
--     `id`            int unsigned NOT NULL AUTO_INCREMENT,
--     `name`          varchar(255)          DEFAULT NULL COMMENT 'Tên category',
--     `description`   varchar(250)          DEFAULT NULL,
--     `status`        int          NOT NULL COMMENT 'Trạng thái hoạt động: `0`: Không hoạt động, 1: Hoạt động',
--     `deleted`       bit(1)       NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    datetime     NOT NULL,
--     `updated_at`    datetime     NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`name`)
-- ) ENGINE = InnoDB COMMENT 'Thể loại'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `cart`
-- (
--     `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT,
--     `user_id`       INT UNSIGNED NOT NULL COMMENT 'Khóa ngoại tham chiếu đến id của người dùng',
--     `deleted`       BIT(1)       NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Thời gian tạo',
--     `updated_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Thời gian cập nhật',
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`user_id`),
--     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
-- ) ENGINE = InnoDB COMMENT 'Giỏ hàng'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `item`
-- (
--     `id`            int unsigned NOT NULL AUTO_INCREMENT,
--     `name`          varchar(255) NOT NULL COMMENT 'Tên đồ ăn',
--     `price`         double          NOT NULL ,
--     `inventory`     int          NOT NULL DEFAULT (0) COMMENT 'Lượng đồ ăn còn tồn trong kho',
--     `description`   varchar(250)          DEFAULT NULL,
--     `category_id`   int                   DEFAULT NULL COMMENT 'đồ ăn thuộc loại nào',
--     `status`        int          NOT NULL COMMENT 'Trạng thái hoạt động: `0`: Không hoạt động, 1: Hoạt động',
--     `deleted`       bit(1)       NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    datetime     NOT NULL,
--     `updated_at`    datetime     NOT NULL,
--
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`name`),
--     FOREIGN KEY (`category_id`) references `category`(`id`)
--
-- ) ENGINE = InnoDB COMMENT 'Đồ ăn'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `item_cart`
-- (
--     `id`            int    unsigned NOT NULL AUTO_INCREMENT,
--     `cart_id`       int    unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến giỏ hàng',
--     `item_id`       int    unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến thực phẩm',
--     `price`         double unsigned NOT NULL COMMENT 'Giá của đồ ăn',
--     `total_bill`    double unsigned NOT NULL COMMENT 'Tổng tiền',
--     `quantity`      int    unsigned NOT NULL DEFAULT 0 COMMENT 'Số lượng hàng trong giỏ hàng',
--     `deleted`       bit(1)          NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    datetime        NOT NULL,
--     `updated_at`    datetime        NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`cart_id`,`item_id`),
--     FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`),
--     FOREIGN KEY (`item_id`) REFERENCES `item`(`id`)
-- ) ENGINE = InnoDB COMMENT 'Item trong giỏ hàng'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `order`
-- (
--     `id`            int    unsigned NOT NULL AUTO_INCREMENT,
--     `user_id`        int unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến id của người dùng',
--     `total_bill`     double       NOT NULL DEFAULT 0 COMMENT 'Tổng tiền',
--     `note`          text,
--     `description`   text,
--     `pay_date`      datetime        NOT NULL,
--     `deleted`        bit(1)       NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `status`         varchar(20)  NOT NULL COMMENT 'Tình trạng của đơn hàng PENDING - Chưa xử lý, PROCESSING - Đang chuẩn bị, SHIPPED - Đang Giao hàng, DELIVERED - Giao hàng thành công, CANCELLED - Hủy đơn hàng',
--     `created_at`     datetime     NOT NULL,
--     `updated_at`     datetime     NOT NULL,
--     PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB COMMENT 'Đơn hàng'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `item_order`
-- (
--     `id`            int    unsigned NOT NULL AUTO_INCREMENT,
--     `order_id`      int    unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến đơn hàng',
--     `item_id`       int    unsigned NOT NULL COMMENT 'Khóa ngoại tham chiếu đến đồ ăn',
--     `total_bill`    double unsigned NOT NULL COMMENT 'Tổng tiền',
--     `quantity`      int    unsigned NOT NULL DEFAULT 0 COMMENT 'Số lượng hàng trong giỏ hàng',
--     `deleted`       bit(1)          NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    datetime        NOT NULL,
--     `updated_at`    datetime        NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY (`order_id`,`item_id`),
--     FOREIGN KEY (`order_id`) REFERENCES `order`(`id`),
--     FOREIGN KEY (`item_id`) REFERENCES `item`(`id`)
-- ) ENGINE = InnoDB COMMENT 'Đơn hàng'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;
--
-- CREATE TABLE `transaction`
-- (
--     `id`            int    unsigned NOT NULL AUTO_INCREMENT,
--     `user_id`       int    unsigned NOT NULL ,
--     `quantity`      int    unsigned NOT NULL DEFAULT 0,
--     `note`          text,
--     `description`   text,
--     `pay_date`      datetime        NOT NULL,
--     `deleted`       bit(1)          NOT NULL DEFAULT 0 COMMENT 'Đánh dấu trạng thái xóa của bản ghi: `0`: Chưa xóa, `1`: Đã xóa',
--     `created_at`    datetime        NOT NULL,
--     `updated_at`    datetime        NOT NULL,
--     PRIMARY KEY (`id`),
--     FOREIGN KEY (`user_id`) REFERENCES user(`id`)
-- ) ENGINE = InnoDB COMMENT 'Đơn hàng'
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;



