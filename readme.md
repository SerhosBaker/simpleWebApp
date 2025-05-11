Для начальной инициализации БД я использую скрипт:

```sql
CREATE SCHEMA smarkov;

create table if not exists smarkov.event
(
id       uuid,
title    text not null,
position bit varying(5) not null
);

select *
from smarkov.event;

insert into smarkov.event (id, title, "position")
values ('320dfdc4-a7d4-4ab6-bf2e-28ca7dc6cfd2', 'meine frau', B'10101');

DROP TABLE smarkov.event;
```

Затем я запускаю генерацию классов jooq вот так
```
./mvnw clean install -Dmaven.test.skip=true
```
