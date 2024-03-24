CREATE TABLE "accounts_calculated_values"(
    "id" bigserial NOT NULL,
    "date" DATE NOT NULL,
    "account_id" VARCHAR(255) NOT NULL,
    "amount" BIGINT NOT NULL
);
ALTER TABLE
    "accounts_calculated_values" ADD PRIMARY KEY("id");
CREATE TABLE "operation_type"(
    "id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "descrption" VARCHAR(255) NULL
);
ALTER TABLE
    "operation_type" ADD PRIMARY KEY("id");
CREATE TABLE "banks"(
    "id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "banks" ADD PRIMARY KEY("id");
CREATE TABLE "accounts_type"(
    "id" VARCHAR(255) NOT NULL,
    "description" TEXT NULL
);
ALTER TABLE
    "accounts_type" ADD PRIMARY KEY("id");
CREATE TABLE "transactions"(
    "id" bigserial NOT NULL,
    "composed_id" VARCHAR(255) NOT NULL,
    "account_id" VARCHAR(255) NOT NULL,
    "operation_type_id" VARCHAR(255) NOT NULL,
    "date" DATE NOT NULL,
    "time" TIME(0) WITHOUT TIME ZONE NOT NULL,
    "amount" DOUBLE PRECISION NOT NULL,
    "category_tag_id" VARCHAR(255) NULL,
    "comment" TEXT NULL
);
ALTER TABLE
    "transactions" ADD PRIMARY KEY("id");
ALTER TABLE
    "transactions" ADD CONSTRAINT "transactions_composed_id_unique" UNIQUE("composed_id");
CREATE TABLE "categories_tags"(
    "id" VARCHAR(255) NOT NULL,
    "category_id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NULL
);
ALTER TABLE
    "categories_tags" ADD PRIMARY KEY("id");
CREATE TABLE "currencies"(
    "id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "currencies" ADD PRIMARY KEY("id");
CREATE TABLE "accounts"(
    "id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "bank_id" VARCHAR(255) NOT NULL,
    "account_type_id" VARCHAR(255) NOT NULL,
    "currency_id" VARCHAR(255) NOT NULL,
    "description" TEXT NULL
);
ALTER TABLE
    "accounts" ADD PRIMARY KEY("id");
CREATE TABLE "categories"(
    "id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NULL
);
ALTER TABLE
    "categories" ADD PRIMARY KEY("id");
ALTER TABLE
    "transactions" ADD CONSTRAINT "transactions_account_id_foreign" FOREIGN KEY("account_id") REFERENCES "accounts"("id");
ALTER TABLE
    "accounts" ADD CONSTRAINT "accounts_account_type_id_foreign" FOREIGN KEY("account_type_id") REFERENCES "accounts_type"("id");
ALTER TABLE
    "accounts" ADD CONSTRAINT "accounts_bank_id_foreign" FOREIGN KEY("bank_id") REFERENCES "banks"("id");
ALTER TABLE
    "accounts" ADD CONSTRAINT "accounts_currency_id_foreign" FOREIGN KEY("currency_id") REFERENCES "currencies"("id");
ALTER TABLE
    "transactions" ADD CONSTRAINT "transactions_category_tag_id_foreign" FOREIGN KEY("category_tag_id") REFERENCES "categories_tags"("id");
ALTER TABLE
    "accounts_calculated_values" ADD CONSTRAINT "accounts_calculated_values_account_id_foreign" FOREIGN KEY("account_id") REFERENCES "accounts"("id");
ALTER TABLE
    "categories_tags" ADD CONSTRAINT "categories_tags_category_id_foreign" FOREIGN KEY("category_id") REFERENCES "categories"("id");
ALTER TABLE
    "transactions" ADD CONSTRAINT "transactions_operation_type_id_foreign" FOREIGN KEY("operation_type_id") REFERENCES "operation_type"("id");
