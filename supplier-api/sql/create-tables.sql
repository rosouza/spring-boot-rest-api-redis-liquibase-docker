CREATE SCHEMA supplier;

CREATE SEQUENCE supplier.supplier_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE supplier.t_supplier (
	id int8 NOT NULL,
	name varchar(100) NOT NULL,
	country varchar(100) NOT NULL,
	state varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
	address varchar(255) NOT NULL,
	contact_number varchar(15) NULL,
	CONSTRAINT pk_supplier PRIMARY KEY (id)
);
