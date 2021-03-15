create table singer(
    id int not null auto_increament,
    first_name varchar(60) not null,
    last_name varchar(40) not null,
    birth_date date,
    version int not null default 0,
    unique uq_singer_1 (first_name, last_name),
    primary key (id)
);

create table album(
    id int not null auto_increament,
    singer_id id not null,
    title varchar(100) not null,
    release_date date,
    version int not null default 0,
    unique uq_singer_album_1 (singer_id, title),
    primary key (id),
    constraint fk_album_singer foreign key (singer_id) references singer (id)
);

create table instrument(
    instrument_id varchar(20) not null,
    primary key (instrument_id)
);

create table singer_insrument(
    singer_id int not null,
    instrument_id varchar(20) not null,
    primary key (singer_id, instrument_id),
    constraint fk_singer_instrument_1 foreign key (singer_id) references singer (id) on delete cascade
    constraint fk_singer_instrument_2 foreign key (instrument_id) references instrument (instrument_id)
);