--
-- Initial schema setup for billpay exchange
--

--
-- Name: address; Type: TABLE;
--
--CREATE TABLE  address (
--    id varchar(255) NOT NULL,
--    created_by varchar(25),
--    created_ts timestamp DEFAULT CURRENT_TIMESTAMP,
--    deleted_date timestamp DEFAULT CURRENT_TIMESTAMP ,
--    updated_date timestamp DEFAULT CURRENT_TIMESTAMP ,
--    updated_by varchar(25),
--    updated_ts timestamp  DEFAULT CURRENT_TIMESTAMP,
--    version varchar(10),
--    address1 varchar(100),
--    address2 varchar(100),
--    address_type varchar(15) NOT NULL,
--    city varchar(25),
--    country varchar(25),
--    landmark varchar(25),
--    postal_code varchar(10),
--    address_state varchar(25),
--    street varchar(25)
--);
CREATE TABLE Consumer_Profile_Entity (
   id varchar(15) NOT NULL,
   firstName varchar(50),
   lastName varchar(50),
   emailAddress varchar(50),
   mobileNumber varchar(15)
);