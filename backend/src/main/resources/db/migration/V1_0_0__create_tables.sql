-- public.category definition

CREATE TABLE public.category (
	id SERIAL PRIMARY KEY,
	"name" varchar(255) NULL,
	CONSTRAINT uk_46ccwnsi9409t36lurvtyljak UNIQUE (name)
);


-- public.sub_category definition

CREATE TABLE public.sub_category (
	id SERIAL PRIMARY KEY,
	"name" varchar(255) NULL,
	category_id int NULL,
	CONSTRAINT fkl65dyy5me2ypoyj8ou1hnt64e FOREIGN KEY (category_id) REFERENCES category(id)
);


-- public.unemployment_rate definition

CREATE TABLE public.unemployment_rate (
	id SERIAL PRIMARY KEY,
	rate int NOT NULL,
	sex bool,
	"year" int NOT NULL,
	sub_category_id int NULL,
	CONSTRAINT fk30i39ue02cxpdh4liv9w0mm2o FOREIGN KEY (sub_category_id) REFERENCES sub_category(id)
);


-- public.wage_distribution definition

CREATE TABLE public.wage_distribution (
	id SERIAL PRIMARY KEY,
	count int NOT NULL,
	"year" int NOT NULL,
	sub_category_id int NULL,
	wage_group_id int NULL,
	CONSTRAINT fk890a1t7e4m1m9tn4fwj1tb6a5 FOREIGN KEY (wage_group_id) REFERENCES sub_category(id),
	CONSTRAINT fkrcm6g73xl2urb0607r90lhd1u FOREIGN KEY (sub_category_id) REFERENCES sub_category(id)
);


-- public.average_wage definition

CREATE TABLE public.average_wage (
	id SERIAL PRIMARY KEY,
	sex bool,
	wage int NOT NULL,
	"year" int NOT NULL,
	sub_category_id int NULL,
	CONSTRAINT fkcprkx87ojkl05jfvmixqf4qqy FOREIGN KEY (sub_category_id) REFERENCES sub_category(id)
);


-- public.employee_count definition

CREATE TABLE public.employee_count (
	id SERIAL PRIMARY KEY,
	count int NOT NULL,
	sex bool,
	"year" int NOT NULL,
	sub_category_id int NULL,
	CONSTRAINT fkn1dw6ew8q8w80jquwinqewt1s FOREIGN KEY (sub_category_id) REFERENCES sub_category(id)
);


-- hibernate validation requirements

CREATE SEQUENCE public.hibernate_sequence;