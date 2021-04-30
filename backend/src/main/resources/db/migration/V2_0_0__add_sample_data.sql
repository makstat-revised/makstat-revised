-- populate public.category

INSERT INTO "category" ("id", "name") VALUES
(1,	'sector'),
(2,	'ageGroup'),
(3,	'wageGroup');

-- populate public.sub_category

INSERT INTO "sub_category" ("id", "name", "category_id") VALUES
(1,	'agriculture',	1),
(2,	'energy',	1),
(3,	'18-22',	2),
(4,	'23-26',	2),
(5,	'6000-12000',	3),
(6,	'13000-18000',	3);

-- populate public.employee_count

INSERT INTO "employee_count" ("id", "count", "sex", "year", "sub_category_id") VALUES
(1,	21000,	'0',	2010,	1),
(2,	22000,	'1',	2010,	1),
(3,	16000,	'0',	2011,	1),
(4,	15000,	'1',	2011,	1),
(5,	14000,	'0',	2010,	2),
(6,	19000,	'1',	2010,	2),
(7,	25000,	'0',	2011,	2),
(8,	36000,	'1',	2011,	2),
(9,	26000,	'0',	2010,	3),
(10,	28000,	'1',	2010,	3),
(11,	27000,	'0',	2011,	3),
(12,	13000,	'1',	2011,	3),
(13,	31000,	'0',	2010,	4),
(14,	19000,	'1',	2010,	4),
(15,	20000,	'0',	2011,	4),
(16,	33000,	'1',	2011,	4);

-- populate public.wage_distribution

INSERT INTO "wage_distribution" ("id", "count", "year", "sub_category_id", "wage_group_id") VALUES
(1,	133,	2010,	1,	5),
(2,	245,	2010,	1,	6),
(3,	456,	2011,	1,	5),
(4,	564,	2011,	1,	6),
(5,	321,	2010,	2,	5),
(6,	231,	2010,	2,	6),
(7,	321,	2011,	2,	5),
(8,	546,	2011,	2,	6);