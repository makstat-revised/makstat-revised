-- populate public.category

INSERT INTO public.category ("name") VALUES('sector');

INSERT INTO public.category ("name") VALUES('ageGroup');


-- populate public.sub_category

INSERT INTO public.sub_category ("name", "category_name")
VALUES('agriculture', 'sector');

INSERT INTO public.sub_category ("name", "category_name")
VALUES('energy', 'sector');

INSERT INTO public.sub_category ("name", "category_name")
VALUES('18-22', 'ageGroup');

INSERT INTO public.sub_category ("name", "category_name")
VALUES('23-26', 'ageGroup');



-- populate public.sex

INSERT INTO public.sex ("type") VALUES('male');

INSERT INTO public.sex ("type") VALUES('female');


-- populate public.employee_count

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'agriculture',
    2010,
    'female',
    12000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'agriculture',
    2010,
    'male',
    15000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'agriculture',
    2011,
    'female',
    16000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'agriculture',
    2011,
    'male',
    17000
);

-- INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
-- VALUES(
--     (SELECT nextval ('public.hibernate_sequence')), 
--     'agriculture',
--     2012,
--     NULL,
--     28000
-- );

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'energy',
    2010,
    'female',
    12000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'energy',
    2010,
    'male',
    12000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'energy',
    2011,
    'female',
    16000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    'energy',
    2011,
    'male',
    14000
);

-- INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
-- VALUES(
--     (SELECT nextval ('public.hibernate_sequence')), 
--     'energy',
--     2012,
--     NULL,
--     29000
-- );

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    '18-22',
    2010,
    'female',
    4000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    '18-22',
    2010,
    'male',
    2000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    '18-22',
    2011,
    'female',
    6000
);

INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
VALUES(
    (SELECT nextval ('public.hibernate_sequence')), 
    '18-22',
    2011,
    'male',
    4000
);

-- INSERT INTO public.employee_count ("id", "sub_category_name", "year", "sex_type", "count")
-- VALUES(
--     (SELECT nextval ('public.hibernate_sequence')), 
--     '18-22',
--     2012,
--     NULL,
--     9000
-- );
