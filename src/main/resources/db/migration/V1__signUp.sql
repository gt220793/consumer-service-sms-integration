
-- ************************************** "BillPay"."consumer"
-- Table: BillPay.consumer

-- DROP TABLE "BillPay".consumer;

-- Table: BillPay.consumer

-- DROP TABLE "BillPay".consumer;

CREATE TABLE "BillPay".consumer
(
    "consumerRefNumber" character varying(36) COLLATE pg_catalog."default" NOT NULL,s
    "firstName" text COLLATE pg_catalog."default" NOT NULL,
    "lastName" text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    "consumerId" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "saltedHash" text COLLATE pg_catalog."default" NOT NULL,
    nonce character varying(64) COLLATE pg_catalog."default" NOT NULL,
    created_ts timestamp with time zone NOT NULL,
    updated_ts timestamp with time zone NOT NULL,
    created_by text COLLATE pg_catalog."default" NOT NULL,
    updated_by text COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL,
    CONSTRAINT consumer_pkey PRIMARY KEY ("consumerRefNumber")
)
WITH (s
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE "BillPay".consumer
    OWNER to postgres;

