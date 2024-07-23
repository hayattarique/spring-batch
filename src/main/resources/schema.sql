CREATE TABLE IF NOT EXISTS product
(
    product_id       bigint PRIMARY KEY auto_increment,
    title            varchar(200),
    description      varchar(200),
    price            int(10),
    discount_percent int(10)

);
