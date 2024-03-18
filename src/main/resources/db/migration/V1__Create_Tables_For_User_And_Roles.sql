
CREATE TABLE IF NOT EXISTS tb_user(
  id SERIAL CONSTRAINT pk_id_user PRIMARY KEY UNIQUE NOT NULL,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL UNIQUE,
  password TEXT NOT NULL,
  user_role VARCHAR(10)
);

/*
{
    "title":"API Java 2",
    "link_project": "api@java.com",
    "description": "API Java Spring",
    "img_url": "http://img3.com",
    "img_data": "fdsfasdfasdfasdfasdfsadf",
    "tags": "API, backend, Orientação a Objetos, C sharp"
}
*/

CREATE TABLE IF NOT EXISTS tb_project(
  id SERIAL CONSTRAINT pk_id_project PRIMARY KEY UNIQUE NOT NULL,
  title VARCHAR(250) NOT NULL,
  link_project TEXT NOT NULL,
  img_url TEXT NULL,
  img_data TEXT NULL,
  tags VARCHAR(250)
);

INSERT INTO tb_project (title, link_project, img_url, img_data, tags) 
VALUES (
'Projeto backend 1', 
'https://github.com/brunoclaumari/backend-login-com-controller',
'http://projeto1-img.jpg',
'fçdsajfçksaljdhflskajdhflsakjdfs',
'API, backend, Orientação a Objetos, C sharp'
);

INSERT INTO tb_project (title, link_project, img_url, img_data, tags) 
VALUES (
'Projeto backend 2', 
'https://github.com/brunoclaumari/backend-2',
'http://projeto2-img.jpg',
'dakjsdhlakJSHDLk',
'API, Java, Orientação a Objetos, C sharp'
);

INSERT INTO tb_project (title, link_project, img_url, img_data, tags) 
VALUES (
'Projeto frontend 1', 
'https://github.com/brunoclaumari/frontend-1',
'http://projetofront1-img.jpg',
'kfhdsalkfhalskdjhflskjdf',
'API, Angular, Node, npm, frontend'
);

INSERT INTO tb_project (title, link_project, img_url, img_data, tags) 
VALUES (
'Projeto backend 3', 
'https://github.com/brunoclaumari/backend-3',
'http://projeto3-img.jpg',
'kfhdsalkfhalskdjhflskjdf',
'API, Node, npm, backend'
);





