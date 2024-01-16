create table sites
(
  id        serial  primary key,
  url       varchar         not null,
  login     varchar unique  not null,
  password  varchar         not null
);

create table shortcuts
(
    id          serial  primary key,
    site_id     int             not null references sites(id),
    url         varchar unique  not null,
    shortcut    varchar         not null,
    count       int     default 0
);