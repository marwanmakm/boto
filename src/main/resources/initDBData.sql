INSERT INTO public.account_types(description, id)
VALUES
    ('Check Account', 'check'),
    ('Credit Card Account', 'tdc');

INSERT INTO public.banks(id, name)
VALUES
    ('santander', 'Banco Santander'),
    ('bdc', 'Banco de Chile');

INSERT INTO public.currencies(id, name)
VALUES ('clp', 'Chilean Peso');

INSERT INTO public.accounts(account_type_id, bank_id, currency_id, description, id, name)
VALUES
    ('check', 'santander', 'clp', 'Check Account of Banco Santander', 'santander_check','Check Account of Banco Santander'),
    ('check', 'bdc', 'clp', 'Check Account of Banco de Chile', 'bdc_check','Check Account of Banco de Chile'),
    ('tdc', 'bdc', 'clp', 'Credit Card Account of Banco de Chile', 'bdc_tdc','Credit Card Account of Banco de Chile');

INSERT INTO public.tags(id, name, description)
VALUES
    ('services', 'Common Living Services', 'This englobes services like Electricity, Water and Internet Bill'),
    ('common_expenses', 'Common Expenses', ''),
    ('rent', 'Chilean Peso', ''),
    ('personal', 'Chilean Peso', ''),
    ('buy_binance', 'Chilean Peso', ''),
    ('sell_binance', 'Chilean Peso', ''),
    ('investment', 'Chilean Peso', ''),
    ('internal_transfer', 'Chilean Peso', ''),
    ('personal_pay', 'Chilean Peso', ''),
    ('payment', 'Chilean Peso', ''),
    ('yude', 'Chilean Peso', ''),
    ('tdc_bill', 'Chilean Peso', ''),
    ('buy_usd', 'Chilean Peso', ''),
    ('sell_usd', 'Chilean Peso', '');
