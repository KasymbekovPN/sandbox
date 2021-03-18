CREATE TABLE SINGER (
    ID INT NOT NULL AUTO_INCREMENT
  , FIRST_NAME VARCHAR(60) NOT NULL
  , LAST_NAME VARCHAR(40) NOT NULL
  , BIRTH_DATE DATE
  , VERSION INT NOT NULL DEFAULT 0
  , UNIQUE UQ_SINGER_1 (FIRST_NAME, LAST_NAME)
  , PRIMARY KEY (ID)
);

CREATE TABLE ALBUM (
    ID INT NOT NULL AUTO_INCREMENT
  , SINGER_ID INT NOT NULL
  , TITLE VARCHAR(100) NOT NULL
  , RELEASE_DATE DATE
  , VERSION INT NOT NULL DEFAULT 0
  , UNIQUE UQ_SINGER_ALBUM_1 (SINGER_ID, TITLE)
  , PRIMARY KEY (ID)
  , CONSTRAINT FK_ALBUM_SINGER FOREIGN KEY (SINGER_ID)
REFERENCES SINGER (ID)
);


CREATE TABLE INSTRUMENT (
  INSTRUMENT_ID VARCHAR(20) NOT NULL
     , PRIMARY KEY (INSTRUMENT_ID)
);

CREATE TABLE SINGER_INSTRUMENT (
    SINGER_ID INT NOT NULL
     , INSTRUMENT_ID VARCHAR(20) NOT NULL
     , PRIMARY KEY (SINGER_ID, INSTRUMENT_ID)
     , CONSTRAINT FK_SINGER_INSTRUMENT_1 FOREIGN KEY (SINGER_ID)
         REFERENCES SINGER (ID) ON DELETE CASCADE
     , CONSTRAINT FK_SINGER_INSTRUMENT_2 FOREIGN KEY (INSTRUMENT_ID)
     REFERENCES INSTRUMENT (INSTRUMENT_ID)
);

--create table singer(
--    id int not null auto_increment,
--    first_name varchar(60) not null,
--    last_name varchar(40) not null,
--    birth_date date,
--    version int not null default 0,
--    unique uq_singer_1 (first_name, last_name),
--    primary key (id)
--);
--
--create table album(
--    id int not null auto_increment,
--    singer_id id not null,
--    title varchar(100) not null,
--    release_date date,
--    version int not null default 0,
--    unique uq_singer_album_1 (singer_id, title),
--    primary key (id),
--    constraint fk_album_singer foreign key (singer_id) references singer (id)
--);
--
--create table instrument(
--    instrument_id varchar(20) not null,
--    primary key (instrument_id)
--);
--
--create table singer_insrument(
--    singer_id int not null,
--    instrument_id varchar(20) not null,
--    primary key (singer_id, instrument_id),
--    constraint fk_singer_instrument_1 foreign key (singer_id) references singer (id) on delete cascade
--    constraint fk_singer_instrument_2 foreign key (instrument_id) references instrument (instrument_id)
--);