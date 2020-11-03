
CREATE TABLE IF NOT EXISTS Organization (
  id                INTEGER         COMMENT 'Уникальный идентификатор'              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name              VARCHAR(255)    COMMENT 'Название организации'                  NOT NULL UNIQUE,
  full_name         VARCHAR(255)    COMMENT 'Полное название организации'           NOT NULL UNIQUE,
  inn               BIGINT UNSIGNED COMMENT 'ИНН организации'                       NOT NULL UNIQUE,
  kpp               BIGINT UNSIGNED COMMENT 'КПП организации'                       NOT NULL UNIQUE,
  address           VARCHAR(255)    COMMENT 'Адрес организации'                     NOT NULL,
  phone             VARCHAR(20)     COMMENT 'Телефон организации',
  is_active         BOOLEAN         COMMENT 'Активность'                            NOT NULL,
  version           INTEGER         COMMENT 'Служебное поле hibernate'              NOT NULL
) COMMENT = 'Организация';

CREATE TABLE IF NOT EXISTS Office (
  id                INTEGER         COMMENT 'Уникальный идентификатор'              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  org_id            INTEGER         COMMENT 'Уникальный идентификатор организации'  NOT NULL,
  name              VARCHAR(255)    COMMENT 'Название офиса'                        NOT NULL,
  address           VARCHAR(255)    COMMENT 'Адрес офиса'                           NOT NULL,
  phone             VARCHAR(20)     COMMENT 'Телефон офиса',
  is_active         BOOLEAN         COMMENT 'Активность'                            NOT NULL,
  version           INTEGER         COMMENT 'Служебное поле hibernate'              NOT NULL
) COMMENT = 'Офис';

-- Индекс и внешний ключ для связи Office *->1 Organization
CREATE INDEX IX_Office_Organization_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE TABLE IF NOT EXISTS Citizenship (
  id                INTEGER         COMMENT 'Уникальный идентификатор'              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  citizenship_code  VARCHAR(3)      COMMENT 'Код страны'                            NOT NULL UNIQUE,
  citizenship_name  VARCHAR(255)    COMMENT 'Гражданство, страна'                   NOT NULL UNIQUE,
  version           INTEGER         COMMENT 'Служебное поле hibernate'              NOT NULL
) COMMENT = 'Гражданство';

CREATE TABLE IF NOT EXISTS Document_Type (
    id              INTEGER         COMMENT 'Уникальный идентификатор'              NOT NULL PRIMARY KEY AUTO_INCREMENT,
    doc_code        VARCHAR(3)      COMMENT 'Код документа'                         NOT NULL UNIQUE,
    doc_name        VARCHAR(255)    COMMENT 'Имя документа'                         NOT NULL UNIQUE,
    version         INTEGER         COMMENT 'Служебное поле hibernate'              NOT NULL
) COMMENT = 'Тип документа';

CREATE TABLE IF NOT EXISTS Document (
  id                INTEGER         COMMENT 'Уникальный идентификатор'              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  doc_type_id       INTEGER         COMMENT 'id типа документа'                     NOT NULL,
  doc_number        VARCHAR(255)    COMMENT 'Номер документа'                       NOT NULL,
  doc_date          DATE            COMMENT 'Дата создания документа'               NOT NULL,
  version           INTEGER         COMMENT 'Служебное поле hibernate'              NOT NULL,
  UNIQUE KEY (doc_type_id, doc_number)
) COMMENT = 'Документ';

-- Индекс и внешний ключ для связи Document *->1 Document_Type
CREATE INDEX IX_Document_Document_Type ON Document (doc_type_id);
ALTER TABLE Document ADD FOREIGN KEY (doc_type_id) REFERENCES Document_Type (id);

CREATE TABLE IF NOT EXISTS User (
  id                INTEGER         COMMENT 'Уникальный идентификатор'              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name        VARCHAR(255)    COMMENT 'Имя'                                   NOT NULL,
  middle_name       VARCHAR(255)    COMMENT 'Среднее имя (инициал)',
  last_name         VARCHAR(255)    COMMENT 'Фамилия'                               NOT NULL,
  office_id         INTEGER         COMMENT 'Уникальный идентификатор офиса'        NOT NULL,
  position          VARCHAR(255)    COMMENT 'Должность'                             NOT NULL,
  phone             VARCHAR(20)     COMMENT 'Телефон'                               NOT NULL,
  document_id       INTEGER         COMMENT 'Уникальный идентификатор документа'    NOT NULL UNIQUE,
  citizenship_id    INTEGER         COMMENT 'Уникальный идентификатор гражданства'  NOT NULL,
  is_identified     BOOLEAN         COMMENT 'Идентифицирован'                       NOT NULL,
  version           INTEGER         COMMENT 'Служебное поле hibernate'              NOT NULL
) COMMENT = 'Пользователь';

-- Индекс и внешний ключ для связи User *->1 Office
CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office (id);

-- Индекс и внешний ключ для связи User *->1 Citizenship
CREATE INDEX IX_User_Citizenship_Id ON User (citizenship_id);
ALTER TABLE User ADD FOREIGN KEY (citizenship_id) REFERENCES Citizenship (id);

-- Индекс и внешний ключ для связи User 1->1 Document
CREATE INDEX IX_User_Document_Id ON User (document_id);
ALTER TABLE User ADD FOREIGN KEY (document_id) REFERENCES Document (id);
