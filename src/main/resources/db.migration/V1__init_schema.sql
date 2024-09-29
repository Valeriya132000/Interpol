-- interpol
create table public.interpol
(
    id                   bigserial    primary key,
    created_date         timestamp,
    modified_date        timestamp,
    criminal_person      jsonb
);

comment on table public.interpol is 'Информация интерпола';

comment on column public.interpol.id is 'Индентификатор';

comment on column public.interpol.created_date is 'Дата создания записи';

comment on column public.interpol.modified_date is 'Дата изменения записи';

comment on column public.interpol.criminal_person is 'Информация о персоне';


create index interpol_id_index
    on public.interpol ("id");

-- interpol archive
create table public.interpol_archive
(
    id                   bigserial    primary key,
    created_date         timestamp,
    modified_date        timestamp,
    criminal_person      jsonb
);

comment on table public.interpol_archive is 'Архивная информация интерпола';

comment on column public.interpol_archive.id is 'Индентификатор';

comment on column public.interpol_archive.created_date is 'Дата создания записи';

comment on column public.interpol_archive.modified_date is 'Дата изменения записи';

comment on column public.interpol_archive.criminal_person is 'Информация о персоне';


create index interpol_archive_id_index
    on public.interpol_archive ("id");

-- developer
create table public.developer
(
    id                   bigserial    primary key,
    created_date         timestamp,
    modified_date        timestamp,
    developer_person      jsonb
);

comment on table public.developer is 'Информация о разработчиках';

comment on column public.developer.id is 'Индентификатор';

comment on column public.developer.created_date is 'Дата создания записи';

comment on column public.developer.modified_date is 'Дата изменения записи';

comment on column public.developer.developer_person is 'Информация о разработчике';


create index developer_id_index
    on public.developer ("id");

-- developer
create table public.description
(
    id                   bigserial    primary key,
    created_date         timestamp,
    modified_date        timestamp,
    description_app      text
);

comment on table public.description is 'Информация о сервисе';

comment on column public.description.id is 'Индентификатор';

comment on column public.description.created_date is 'Дата создания записи';

comment on column public.description.modified_date is 'Дата изменения записи';

comment on column public.description.description_app is 'Информация о сервисе, его описание';


create index description_id_index
    on public.description ("id");
