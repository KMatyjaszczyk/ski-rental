drop table if exists "user_role";
drop table if exists "role";
drop table if exists "user";

drop table if exists "equipment";
drop table if exists "size";
drop table if exists "manufacturer";
drop table if exists "equipment_category";

create table if not exists "role" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text,
    "deleted" bool not null default false
    );
alter table "role" add constraint "role_pk" primary key ("id");
alter table "role" add constraint "role_name_uq" unique ("name");

create table if not exists "user" (
    "id" serial,
    "name" varchar(50) not null,
    "surname" varchar(50) not null,
    "phone_number" varchar(13) not null,
    "email" varchar(255),
    "password" varchar(255),
    "is_registered" bool not null,
    "description" text,
    "deleted" bool not null default false
    );
alter table "user" add constraint "user_pk" primary key ("id");
alter table "user" add constraint "user_phone_number_uq" unique ("phone_number");

create table if not exists "user_role" (
    "user_id" int4 not null,
    "role_id" int4 not null
);
alter table "user_role" add constraint "user_role_pk" primary key ("user_id", "role_id");
alter table "user_role" add constraint "user_role_user_fk" foreign key("user_id") references "user"("id")
    on update cascade on delete restrict;
alter table "user_role" add constraint "user_role_role_fk" foreign key("role_id") references "role"("id")
    on update cascade on delete restrict;

create table if not exists "manufacturer" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text,
    "deleted" bool not null default false
    );
alter table "manufacturer" add constraint "manufacturer_pk" primary key ("id");
alter table "manufacturer" add constraint "manufacturer_name_uq" unique ("name");

create table if not exists "equipment_category" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text,
    "deleted" bool not null default false
    );
alter table "equipment_category" add constraint "equipment_category_pk" primary key ("id");
alter table "equipment_category" add constraint "equipment_category_name_uq" unique ("name");

create table if not exists "equipment" (
    "id" serial,
    "model" varchar(50) not null,
    "manufacturer_id" int4 not null,
    "equipment_category_id" int4 not null,
    "description" text,
    "deleted" bool not null default false
    );
alter table "equipment" add constraint "equipment_pk" primary key ("id");
alter table "equipment" add constraint "equipment_manufacturer_fk" foreign key("manufacturer_id") references "manufacturer"("id")
    on update cascade on delete restrict;
alter table "equipment" add constraint "equipment_equipment_category_fk" foreign key("equipment_category_id") references "equipment_category"("id")
    on update cascade on delete restrict;

create table if not exists "size" (
    "id" serial,
    "size" varchar(15) not null,
    "equipment_category_id" int4 not null,
    "description" text,
    "deleted" bool not null default false
    );
alter table "size" add constraint "size_pk" primary key ("id");
alter table "size" add constraint "size_equipment_category_fk" foreign key("equipment_category_id") references "equipment_category"("id")
    on update cascade on delete restrict;