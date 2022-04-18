-- public.tb_master_user_role definition

-- Drop table

-- DROP TABLE public.tb_master_user_role;

CREATE TABLE public.tb_master_user_role (
	user_id serial4 NOT NULL,
	username varchar(50) NOT NULL,
	"name" varchar(125) NOT NULL,
	"password" varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	privelege varchar(3) NOT NULL,
	created_on timestamp NOT NULL,
	last_login timestamp NULL,
	create_on timestamp NULL,
	CONSTRAINT tb_master_user_role_email_key UNIQUE (email),
	CONSTRAINT tb_master_user_role_pkey PRIMARY KEY (user_id),
	CONSTRAINT tb_master_user_role_username_key UNIQUE (username)
);