ALTER TABLE `product`
CHANGE `image_path` `image_id` INT;

-- Add image_path column to `category` table
ALTER TABLE `category`
ADD `image_id` INT;

