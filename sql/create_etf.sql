CREATE TABLE `conygre`.`exchange_traded_funds` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NOT NULL,
  `symbol` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `transaction_type` INT NOT NULL,
  `quantity_affected` INT NOT NULL,
  `total_quantity` INT NOT NULL,
  `market_value` FLOAT NOT NULL,
  `total_value` FLOAT NOT NULL,
  PRIMARY KEY (`id`));

insert into exchange_traded_funds values (1, "2021-01-01 12:12:12", "MSFT", "Microsoft", 0, 10, 10, 40, 400);
insert into exchange_traded_funds values (2, "2021-01-02 9:42:12", "MSFT", "Microsoft", 0, 10, 20, 45, 850);
insert into exchange_traded_funds values (3, "2021-01-03 12:12:12", "MSFT", "Microsoft", 0, 10, 30, 38, 1230);
insert into exchange_traded_funds values (4, "2021-01-04 16:12:12", "MSFT", "Microsoft", 0, 10, 40, 42, 4564);
insert into exchange_traded_funds values (5, "2021-01-05 10:12:12", "MSFT", "Microsoft", 0, 10, 50, 45, 4548);
insert into exchange_traded_funds values (6, "2021-01-06 12:12:12", "MSFT", "Microsoft", 0, 10, 60, 47, 4545);
insert into exchange_traded_funds values (7, "2021-01-07 13:12:12", "MSFT", "Microsoft", 0, 10, 70, 50, 4568);

insert into exchange_traded_funds values (8, "2021-01-01 15:12:12", "TSLA", "TESLA", 0, 10, 10, 40, 4000);
insert into exchange_traded_funds values (9, "2021-01-02 9:42:12", "TSLA", "TESLA", 0, 10, 20, 45, 8500);
insert into exchange_traded_funds values (10, "2021-01-03 14:12:12", "TSLA", "TESLA", 0, 10, 30, 38, 12030);
insert into exchange_traded_funds values (11, "2021-01-04 11:12:12", "TSLA", "TESLA", 0, 10, 40, 42, 45064);
insert into exchange_traded_funds values (12, "2021-01-05 10:18:12", "TSLA", "TESLA", 0, 10, 50, 45, 45048);
insert into exchange_traded_funds values (13, "2021-01-06 12:12:12", "TSLA", "TESLA", 0, 10, 60, 47, 45405);
insert into exchange_traded_funds values (14, "2021-01-07 14:18:12", "TSLA", "TESLA", 0, 10, 70, 50, 45608);


insert into exchange_traded_funds values (15, "2021-02-01 15:12:12", "FR", "First Majestic", 0, 100, 100, 15, 54783);
insert into exchange_traded_funds values (16, "2021-02-02 9:42:12", "FR", "First Majestic", 0, 100, 200, 20, 54557);
insert into exchange_traded_funds values (17, "2021-03-03 14:12:12", "FR", "First Majestic", 0, 100, 300, 18, 54600);
insert into exchange_traded_funds values (18, "2021-04-04 11:12:12", "FR", "First Majestic", 0, 100, 400, 11, 54181);
insert into exchange_traded_funds values (19, "2021-05-05 10:18:12", "FR", "First Majestic", 0, 100, 500, 10, 54058);
insert into exchange_traded_funds values (20, "2021-06-06 12:12:12", "FR", "First Majestic", 0, 100, 600, 18, 54954);
insert into exchange_traded_funds values (21, "2021-07-07 14:18:12", "FR", "First Majestic", 0, 100, 700, 13, 59553);

insert into exchange_traded_funds values (22, "2021-02-01 12:12:12", "MSFT", "Microsoft", 1, 10, 60, 51, 2985);
insert into exchange_traded_funds values (23, "2021-02-02 9:42:12", "MSFT", "Microsoft", 1, 10, 50, 52, 2203);
insert into exchange_traded_funds values (24, "2021-02-03 12:12:12", "MSFT", "Microsoft", 1, 10, 40, 54, 1860);
insert into exchange_traded_funds values (25, "2021-02-04 16:12:12", "MSFT", "Microsoft", 1, 10, 30, 54, 1330);
insert into exchange_traded_funds values (26, "2021-02-05 10:12:12", "MSFT", "Microsoft", 1, 10, 20, 56, 950);
insert into exchange_traded_funds values (27, "2021-02-06 12:12:12", "MSFT", "Microsoft", 1, 10, 10, 58, 580);
insert into exchange_traded_funds values (28, "2021-02-07 13:12:12", "MSFT", "Microsoft", 1, 10, 0, 60, 0);


insert into exchange_traded_funds values (29, "2021-06-01 15:12:12", "GS", "Goldman Sachs", 0, 1000, 1000, 1000, 4000);
insert into exchange_traded_funds values (30, "2021-06-02 9:42:12", "GS", "Goldman Sachs", 1, 1000, 0, 1100, 0);
insert into exchange_traded_funds values (31, "2021-06-03 14:12:12", "GS", "Goldman Sachs", 0, 1000, 1000, 1050, 12030);
insert into exchange_traded_funds values (32, "2021-06-04 11:12:12", "GS", "Goldman Sachs", 1, 1000, 0, 1000, 0);
insert into exchange_traded_funds values (33, "2021-06-05 10:18:12", "GS", "Goldman Sachs", 0, 1000, 1000, 1100, 45048);
insert into exchange_traded_funds values (34, "2021-06-06 12:12:12", "GS", "Goldman Sachs", 1, 1000, 0, 1200, 0);
insert into exchange_traded_funds values (35, "2021-06-07 14:18:12", "GS", "Goldman Sachs", 0, 1000, 1000, 900, 90000);


insert into exchange_traded_funds values (36, "2021-08-01 15:12:12", "ABM", "ABM Industries", 0, 100, 100, 15, 54783);
insert into exchange_traded_funds values (37, "2021-08-02 9:42:12", "ABM", "ABM Industries", 0, 100, 200, 20, 54557);
insert into exchange_traded_funds values (38, "2021-08-03 14:12:12", "ABM", "ABM Industries", 0, 100, 300, 18, 54600);
insert into exchange_traded_funds values (39, "2021-08-04 11:12:12", "ABM", "ABM Industries", 0, 100, 400, 11, 54181);
insert into exchange_traded_funds values (40, "2021-08-05 10:18:12", "ABM", "ABM Industries", 0, 100, 500, 10, 54058);
insert into exchange_traded_funds values (41, "2021-08-06 12:12:12", "ABM", "ABM Industries", 0, 100, 600, 18, 54954);
insert into exchange_traded_funds values (42, "2021-08-07 14:18:12", "ABM", "ABM Industries", 0, 100, 700, 13, 59553);