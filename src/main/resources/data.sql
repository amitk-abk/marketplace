insert into user (userid, username, name, deliveryAddress, email, mobile) values (1, 'RajeshKumar', 'Rajesh Kumar' ,'No 32, HRB, Kalyan Nagar, Bangalore - 43', 'rajeshk@gmail.com', '9988776655');
insert into user (userid, username, name, deliveryAddress, email, mobile) values (2, 'Karthik', 'S Karthik' ,'Venkateswara society, flat 101, Indira Nagar, Bangalore - 75', 'karthitk@gmail.com', '7967854312');
insert into user (userid, username, name, deliveryAddress, email, mobile) values (3, 'SSuresh', 'Suresh Sharma' ,'The Icon, DRDO Layout, Hebbal, Bangalore - 56', 'sharma.suresh@gmail.com', '5648290187');



insert into category (categoryid, name, categoryCode, description) values (1, 'Full Shirts', 'FULLSHIRTS', 'Full shirts formal, casual');
insert into category (categoryid, name, categoryCode, description) values (2, 'T Shirts', 'TSHIRTS', 'T shirts sports, regular');
insert into category (categoryid, name, categoryCode, description) values (3, 'Watch', 'WATCHES', 'Regular, sports watches');


insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (1, 'Arrow Shirt full', 'ArrowFullFinest3451', '1000', 'Full sleeves finest shirt from Arrow', 2500, 1, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (2, 'Arrow Shirt half', 'ArrowHalfFinest1167', '1000', 'Half sleeves finest shirt from Arrow', 2500, 1, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (3, 'UCB T Shirt', 'UCBT2234', '1000', 'United Colors of Benetton', 800, 2, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (4, 'Allen Solly', 'AllenSollyFullFinest7890', '1000', 'Full sleeves finest shirt from Allen Solly', 2750, 1, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (5, 'Allen Solly', 'AllenSollyCasual4521', '1000', 'Casual wear shirt from Allen Solly', 1500, 1, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (6, 'Adidas', 'AdidasSportsT5555', '1000', 'Sports T shirt from Adidas', 980, 2, 1);

insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (7, 'Titan', 'TitanRegular7890', '1000', 'Titan Watch for regular use', 2450, 3, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (8, 'Adidas', 'AdidasSportsWatch4312', '1000', 'Sports Watch from Adidas', 1950, 3, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (9, 'Timex', 'TimexRegular4532', '1000', 'Regular wear watch from Timex', 2980, 3, 1);
insert into product (productid, name, productCode, quantity, description, price, category_id, status) values (10, 'Fossil', 'FossilBlack9988', '1000', 'Black dial Fossil watch', 2270, 3, 1);


insert into vendor (vendorid, name, city, registrationNumber) values (1, 'Max Retailers', 'Bangalore' ,'MAX178RET234');
insert into vendor (vendorid, name, city, registrationNumber) values (2, 'Smart Buy', 'Pune', 'SMART763B');
insert into vendor (vendorid, name, city, registrationNumber) values (3, 'United Sellers', 'Delhi' ,'UNITED433SELL');