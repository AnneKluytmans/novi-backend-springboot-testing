INSERT INTO public.cars(
    year, brand, model)
VALUES
    (2011, 'toyota', 'yaris'),
    (2017, 'alfa romeo', '156')
;

-- INSERT INTO public.cars(
--     production_year, brand, model)
-- VALUES
--     (2011, 'toyota', 'yaris'),
--     (2017, 'alfa romeo', '156')
-- ;


INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$VX4F7vxPpDQkyY/odU.coOCAeub9ZkhtbU1k5Eo0RF2CkSI9AMRhO', 'ADMIN');
INSERT INTO users (username, password, role) VALUES ('user', '$2a$10$sat54CbbZIq9SQHtuYLIL.ktO11Wtjiz5GH7vyuyqfYbI0iAg6qga', 'USER');
