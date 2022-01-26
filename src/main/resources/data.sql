INSERT INTO client (birth_date, first_name, last_name, middle_name)
VALUES ('01.01.2001', 'Ivan', 'Ivanov', 'Ivanovich'),
       ('02.02.2002', 'Petr', 'Petrov', 'Petrocivh'),
       ('03.03.2003', 'Sidorov', 'Ivanov', 'Sidorovich');

INSERT INTO bankcardtype (card_type, credit_limit, embossing_name_length, frequency_of_service_charges, interest_free_period, interest_rate, payment_system_type, validity)
VALUES ('CREDIT', 30000, 20, 1, 30, 10, 'MASTERCARD', 5),
       ('DEBIT', NULL, 20, 12, NULL, NULL, 'VISA', 3);

INSERT INTO card (contract_number, embossing_name, expire_date, lock_date, locked, number, open_date, bank_card_type_id, client_id)
VALUES (123, 'IVAN IVANOV', '01-01-2025', null, false, 1111111111111111, now(), 1, 1);