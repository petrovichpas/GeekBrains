ALTER TABLE `products` DROP FOREIGN KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv`; GO
ALTER TABLE `products` DROP FOREIGN KEY `FKog2rp4qthbtt2lfyhfo32lsw9`; GO

ALTER TABLE `products_pictures` DROP FOREIGN KEY `FKh3amnci4cl7xcl1al140xw79e`; GO

DROP TABLE `products`; GO

ALTER TABLE `brands` DROP KEY `UC_BRANDSNAME_COL`; GO
DROP TABLE `brands`; GO

ALTER TABLE `categories` DROP KEY `UC_CATEGORIESNAME_COL`; GO
DROP TABLE `categories`; GO

ALTER TABLE `pictures` DROP FOREIGN KEY `FKe9cv52k04xoy6cj8xy308gnw3`; GO
ALTER TABLE `pictures` DROP KEY `UK_ehsu2tyinopypjox1ijxt3g3c`; GO
ALTER TABLE `products_pictures` DROP FOREIGN KEY `FKloucf8ggy74nmdej2jmvttvi4`; GO
DROP TABLE `pictures`; GO

DROP TABLE `products_pictures`; GO

DROP TABLE `pictures_data`; GO

