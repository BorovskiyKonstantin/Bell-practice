
CREATE TABLE IF NOT EXISTS Organization (
  id                INTEGER                        COMMENT 'Уникальный идентификатор'   PRIMARY KEY AUTO_INCREMENT,
  name              VARCHAR(255) NOT NULL UNIQUE   COMMENT 'Название организации',
  full_name         VARCHAR(255) NOT NULL UNIQUE   COMMENT 'Полное название организации',
  inn               varchar(10)  NOT NULL UNIQUE   COMMENT 'ИНН организации',
  kpp               varchar(9)   NOT NULL UNIQUE   COMMENT 'КПП организации',
  address           VARCHAR(255) NOT NULL          COMMENT 'Адрес организации',
  phone             VARCHAR(20)  NOT NULL          COMMENT 'Телефон организации',
  is_active         BOOLEAN                        COMMENT 'Активность',
  version           INTEGER      NOT NULL          COMMENT 'Служебное поле hibernate'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
  id                INTEGER                         COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
  org_id            INTEGER      NOT NULL           COMMENT 'Уникальный идентификатор организации',
  name              VARCHAR(255) NOT NULL           COMMENT 'Название офиса',
  address           VARCHAR(255) NOT NULL           COMMENT 'Адрес офиса',
  phone             VARCHAR(20)                     COMMENT 'Телефон офиса',
  is_active         BOOLEAN      NOT NULL           COMMENT 'Активность',
  version           INTEGER      NOT NULL           COMMENT 'Служебное поле hibernate'
);
COMMENT ON TABLE Office IS 'Офис';

-- Индекс и внешний ключ для связи Office *->1 Organization
CREATE INDEX IX_Office_Organization_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE TABLE IF NOT EXISTS Citizenship (
  id                INTEGER                         COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
  citizenship_code  VARCHAR(3)   NOT NULL UNIQUE    COMMENT 'Код страны',
  citizenship_name  VARCHAR(255) NOT NULL UNIQUE    COMMENT 'Гражданство, страна',
  version           INTEGER      NOT NULL           COMMENT 'Служебное поле hibernate'
);
COMMENT ON TABLE Citizenship IS 'Гражданство';

CREATE TABLE IF NOT EXISTS Document_Type (
    id              INTEGER                         COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
    doc_code        VARCHAR(3)   NOT NULL UNIQUE    COMMENT 'Код документа',
    doc_name        VARCHAR(255) NOT NULL UNIQUE    COMMENT 'Имя документа',
    version         INTEGER      NOT NULL           COMMENT 'Служебное поле hibernate'
);
COMMENT ON TABLE Document_Type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Document (
  id                INTEGER                         COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
  doc_type_id       INTEGER      NOT NULL           COMMENT 'id типа документа',
  doc_number        VARCHAR(255) NOT NULL           COMMENT 'Номер документа',
  doc_date          DATE         NOT NULL           COMMENT 'Дата создания документа',
  version           INTEGER      NOT NULL           COMMENT 'Служебное поле hibernate',
  UNIQUE KEY (doc_type_id, doc_number)
);
COMMENT ON TABLE Document IS 'Документ';

-- Индекс и внешний ключ для связи Document *->1 Document_Type
CREATE INDEX IX_Document_Document_Type ON Document (doc_type_id);
ALTER TABLE Document ADD FOREIGN KEY (doc_type_id) REFERENCES Document_Type (id);

CREATE TABLE IF NOT EXISTS User (
  id                INTEGER                         COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
  first_name        VARCHAR(255) NOT NULL           COMMENT 'Имя',
  middle_name       VARCHAR(255)                    COMMENT 'Среднее имя (инициал)',
  last_name         VARCHAR(255) NOT NULL           COMMENT 'Фамилия',
  office_id         INTEGER      NOT NULL           COMMENT 'Уникальный идентификатор офиса',
  position          VARCHAR(255) NOT NULL           COMMENT 'Должность',
  phone             VARCHAR(20)  NOT NULL           COMMENT 'Телефон',
  document_id       INTEGER      NOT NULL UNIQUE    COMMENT 'Уникальный идентификатор документа',
  citizenship_id    INTEGER      NOT NULL           COMMENT 'Уникальный идентификатор гражданства',
  is_identified     BOOLEAN      NOT NULL           COMMENT 'Идентифицирован',
  version           INTEGER      NOT NULL           COMMENT 'Служебное поле hibernate'
);
COMMENT ON TABLE User IS 'Пользователь';

-- Индекс и внешний ключ для связи User *->1 Office
CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office (id);

-- Индекс и внешний ключ для связи User *->1 Citizenship
CREATE INDEX IX_User_Citizenship_Id ON User (citizenship_id);
ALTER TABLE User ADD FOREIGN KEY (citizenship_id) REFERENCES Citizenship (id);

-- Индекс и внешний ключ для связи User 1->1 Document
CREATE INDEX IX_User_Document_Id ON User (document_id);
ALTER TABLE User ADD FOREIGN KEY (document_id) REFERENCES Document (id);
