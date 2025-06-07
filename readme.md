Для начальной инициализации БД я использую скрипт:

```sql
CREATE SCHEMA smarkov;

create table if not exists smarkov.event
(
    id       smallint
        constraint task_pk
            primary key,
    title    text           not null,
    position bit varying(5) not null
);

CREATE TABLE IF NOT EXISTS smarkov.task
(
    id          uuid         not null
        constraint smarkov_task_pk
            primary key,
    event_id    smallint     not null
        constraint smarkov_task_fk_event_id
            references smarkov.event,
    visibility  boolean default false,
    title       varchar(200) not null,
    description varchar(400)
);

select *
from smarkov.event;

insert into smarkov.event (id, title, "position")
values ('320dfdc4-a7d4-4ab6-bf2e-28ca7dc6cfd2', 'meine frau', B'10101');

DROP TABLE smarkov.event;
```

```sql
create table if not exists smarkov.collection
(
    id       uuid         not null
        constraint collection_pk
            primary key,
    title    varchar(200) not null,
    subtitle text
);

CREATE TABLE IF NOT EXISTS smarkov.track_collection
(
    collection_id uuid,
    track_id      uuid,
    range         integer
);

DROP TABLE smarkov.track_collection;
DROP TABLE smarkov.collection;
```

```sql

create table smarkov.track_progress
(
    invest_id  uuid                                   not null,
    track_id   uuid                                   not null
        constraint track_progress_fk_track
            references smarkov.track,
    inserted   timestamp with time zone default now() not null,
    updated    timestamp with time zone default now() not null,
    visibility boolean                  default true  not null
);

DROP TABLE smarkov.track_progress;

```

Затем я запускаю генерацию классов jooq вот так
```
./mvnw clean install -Dmaven.test.skip=true
```

11.05.2025
Промежуточные выводы на данный момент
- изучать как работает jooq не получится через хабр или вк видео
- изучать как работает jooq можно попробовать через youtube тк там есть ролики с конкретикой, хотя и там слабовато
- попробовать поизучать как работает jooq можно по их документации: https://www.jooq.org/doc/3.20/manual/sql-execution/fetching/record-vs-tablerecord/
