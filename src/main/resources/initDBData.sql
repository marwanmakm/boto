INSERT INTO public.account_types(description, id)
VALUES ('Check Account', 'check');

INSERT INTO public.account_types(description, id)
VALUES ('Credit Card Account', 'tdc');

INSERT INTO public.banks(id, name)
VALUES ('bdc', 'Banco de Chile');

INSERT INTO public.currencies(id, name)
VALUES ('clp', 'Chilean Peso');

INSERT INTO public.accounts(account_type_id, bank_id, currency_id, description, id, name)
VALUES ('check', 'bdc', 'clp', 'Check Account of Banco de Chile', 'bdc_check',
        'Check Account of Banco de Chile');

INSERT INTO public.accounts(account_type_id, bank_id, currency_id, description, id, name)
VALUES ('tdc', 'bdc', 'clp', 'Credit Card Account of Banco de Chile', 'bdc_tdc',
        'Credit Card Account of Banco de Chile');
