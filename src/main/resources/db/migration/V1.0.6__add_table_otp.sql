CREATE TABLE `otp`
(
    id            int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id phien xac thuc',
    phone         varchar(20)           DEFAULT NULL COMMENT 'So dien thoai nhan Otp',
    email         varchar(255)          DEFAULT NULL COMMENT 'Email nhan Otp',
    otp           varchar(10)  NOT NULL COMMENT 'Ma OTP',
    attempt_count int          NOT NULL DEFAULT 3 COMMENT 'So lan xac thuc cua phien, toi da 3 lan',
    send_type     int          NOT NULL DEFAULT 0 COMMENT 'Kenh gui Otp: 0 - ZNS, 1 - Email, 2 - SMS ',
    status        int          NOT NULL DEFAULT 0 COMMENT 'Trang thai phien xac thuc: 0 - verify pending, 1 - verified, 2 - failed, 3 - expired, 4 - other',
    deleted       bit          NOT NULL DEFAULT 0,
    created_at    timestamp    NOT NULL,
    updated_at    timestamp    NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT ='Quan ly gui Otp'
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;