create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text     not null
);

alter table resume
    owner to postgres;


create table contact
(
    id          serial   not null primary key,
    resume_uuid char(36) not null references resume on delete cascade,
    type        text     not null,
    value       text     not null
);
create unique index contact_uuid_type_index on contact (resume_uuid, type);
alter table contact owner to postgres;


create table section
(
    id          serial   not null primary key,
    resume_uuid CHAR(36) not null references resume on delete cascade,
    type        text     not null,
    content       text     not null
);
create unique index section_resume_uuid_id_rindex on section (resume_uuid, type);
alter table section owner to postgres;
