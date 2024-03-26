
CREATE TABLE IF NOT EXISTS tb_user(
  id SERIAL CONSTRAINT pk_id_user PRIMARY KEY UNIQUE NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL UNIQUE,
  password TEXT NOT NULL,
  user_role VARCHAR(10),
  img_profile TEXT NULL
);


CREATE TABLE IF NOT EXISTS tb_project(
  id SERIAL CONSTRAINT pk_id_project PRIMARY KEY UNIQUE NOT NULL,
  title VARCHAR(250) NOT NULL,
  link_project TEXT NOT NULL,
  description TEXT NULL,
  img_url TEXT NULL,
  img_data TEXT NULL,
  tags VARCHAR(250)
);


