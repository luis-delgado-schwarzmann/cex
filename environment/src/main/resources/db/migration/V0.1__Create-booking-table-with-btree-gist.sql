create extension if not exists btree_gist;
create table Bookings (
    bookId bigserial primary key,
    userId integer not null,
    docId integer not null,
    range tstzrange not null,
    exclude using gist (docId with =, range with &&),
    exclude using gist (userId with =, range with &&)
);