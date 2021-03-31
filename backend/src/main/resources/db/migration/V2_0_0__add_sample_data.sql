-- populate public.pet

INSERT INTO public.pet (id, "name")
VALUES((SELECT nextval ('public.hibernate_sequence')), 'shark');
