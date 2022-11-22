drop table if exists "payment";
drop table if exists "report";
drop table if exists "rent_item";
drop table if exists "rent";

drop table if exists "user_role";
drop table if exists "role";
drop table if exists "user";

drop table if exists "price";
drop table if exists "item";
drop table if exists "equipment_image";
drop table if exists "equipment";
drop table if exists "size";
drop table if exists "manufacturer";
drop table if exists "equipment_category";
drop table if exists "item_status";


drop table if exists "client_document_type";
drop table if exists "rent_status";
drop table if exists "payment_method";

drop table if exists "rent_item_status";

drop table if exists "report_type";
drop table if exists "report_status";

drop table if exists "rental_data";

create table if not exists "role" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
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
    "description" text
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
    "description" text
    );
alter table "manufacturer" add constraint "manufacturer_pk" primary key ("id");
alter table "manufacturer" add constraint "manufacturer_name_uq" unique ("name");

create table if not exists "equipment_category" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "equipment_category" add constraint "equipment_category_pk" primary key ("id");
alter table "equipment_category" add constraint "equipment_category_name_uq" unique ("name");

create table if not exists "equipment" (
    "id" serial,
    "model" varchar(50) not null,
    "manufacturer_id" int4 not null,
    "equipment_category_id" int4 not null,
    "description" text
    );
alter table "equipment" add constraint "equipment_pk" primary key ("id");
alter table "equipment" add constraint "equipment_manufacturer_fk" foreign key("manufacturer_id") references "manufacturer"("id")
    on update cascade on delete restrict;
alter table "equipment" add constraint "equipment_equipment_category_fk" foreign key("equipment_category_id") references "equipment_category"("id")
    on update cascade on delete restrict;

create table if not exists "equipment_image" (
    "id" serial,
    "image_uuid" varchar(200) not null,
    "equipment_id" int4 not null,
    "description" text
    );
alter table "equipment_image" add constraint "equipment_image_pk" primary key ("id");
alter table "equipment_image" add constraint "equipment_image_image_uuid_uq" unique ("image_uuid");
alter table "equipment_image" add constraint "equipment_image_equipment_fk" foreign key("equipment_id") references "equipment"("id")
    on update cascade on delete restrict;

create table if not exists "size" (
    "id" serial,
    "size" varchar(15) not null,
    "equipment_category_id" int4 not null,
    "description" text
    );
alter table "size" add constraint "size_pk" primary key ("id");
alter table "size" add constraint "size_equipment_category_fk" foreign key("equipment_category_id") references "equipment_category"("id")
    on update cascade on delete restrict;

create table if not exists "item_status" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "item_status" add constraint "item_status_pk" primary key ("id");
alter table "item_status" add constraint "item_status_name_uq" unique ("name");

create table if not exists "item" (
    "id" serial,
    "equipment_id" int4 not null,
    "size_id" int4 not null,
    "item_status_id" int4 not null,
    "last_service_date" date,
    "purchase_price" numeric(8, 2) not null,
    "description" text
    );
alter table "item" add constraint "item_pk" primary key ("id");
alter table "item" add constraint "item_equipment_fk" foreign key("equipment_id") references "equipment"("id")
    on update cascade on delete restrict;
alter table "item" add constraint "item_size_fk" foreign key("size_id") references "size"("id")
    on update cascade on delete restrict;
alter table "item" add constraint "item_item_status_fk" foreign key("item_status_id") references "item_status"("id")
    on update cascade on delete restrict;

create table if not exists "price" (
    "id" serial,
    "item_id" int4 not null,
    "price_value" numeric(8, 2) not null,
    "valid_from" timestamp not null,
    "valid_to" timestamp,
    "description" text
    );
alter table "price" add constraint "price_pk" primary key ("id");
alter table "price" add constraint "price_item_fk" foreign key("item_id") references "item"("id")
    on update cascade on delete restrict;

create table if not exists "client_document_type" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "client_document_type" add constraint "client_document_type_pk" primary key ("id");
alter table "client_document_type" add constraint "client_document_type_name_uq" unique ("name");

create table if not exists "rent_status" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "rent_status" add constraint "rent_status_pk" primary key ("id");
alter table "rent_status" add constraint "rent_status_name_uq" unique ("name");

create table if not exists "payment_method" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "payment_method" add constraint "payment_method_pk" primary key ("id");
alter table "payment_method" add constraint "payment_method_name_uq" unique ("name");

create table if not exists "rent" (
    "id" serial,
    "rent_date" timestamp not null,
    "planned_return_date" date not null,
    "end_date" timestamp,
    "client_id" int4 not null,
    "rent_status_id" int4 not null,
    "client_document_type_id" int4 not null,
    "client_document_number" varchar(20) not null,
    "employee_id" int4,
    "description" text
    );
alter table "rent" add constraint "rent_pk" primary key ("id");
alter table "rent" add constraint "rent_client_fk" foreign key("client_id") references "user"("id")
    on update cascade on delete restrict;
alter table "rent" add constraint "rent_rent_status_fk" foreign key("rent_status_id") references "rent_status"("id")
    on update cascade on delete restrict;
alter table "rent" add constraint "rent_client_document_type_fk" foreign key("client_document_type_id") references "client_document_type"("id")
    on update cascade on delete restrict;
alter table "rent" add constraint "rent_employee_fk" foreign key("employee_id") references "user"("id")
    on update cascade on delete restrict;

create table if not exists "payment" (
    "id" serial,
    "rent_id" int4 not null,
    "amount" numeric(9, 2) not null,
    "payment_method_id" int4 not null,
    "realisation_date" timestamp,
    "description" text
    );
alter table "payment" add constraint "payment_pk" primary key ("id");
alter table "payment" add constraint "payment_rent_fk" foreign key("rent_id") references "rent"("id")
    on update cascade on delete restrict;
alter table "payment" add constraint "payment_payment_method_fk" foreign key("payment_method_id") references "payment_method"("id")
    on update cascade on delete restrict;

create table if not exists "rent_item_status" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "rent_item_status" add constraint "rent_item_status_pk" primary key ("id");
alter table "rent_item_status" add constraint "rent_item_status_uq" unique ("name");

create table if not exists "rent_item" (
    "id" serial,
    "rent_id" int4 not null,
    "item_id" int4 not null,
    "rent_price" numeric(8, 2) not null,
    "rent_item_status_id" int4 not null,
    "rented_from" timestamp not null,
    "rented_to" timestamp,
    "paid_amount" numeric(8, 2),
    "description" text
    );
alter table "rent_item" add constraint "rent_item_pk" primary key ("id");
alter table "rent_item" add constraint "rent_item_rent_fk" foreign key("rent_id") references "rent"("id")
    on update cascade on delete restrict;
alter table "rent_item" add constraint "rent_item_item_fk" foreign key("item_id") references "item"("id")
    on update cascade on delete restrict;
alter table "rent_item" add constraint "rent_item_rent_item_status_fk" foreign key("rent_item_status_id") references "rent_item_status"("id")
    on update cascade on delete restrict;

create table if not exists "report_type" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "report_type" add constraint "report_type_pk" primary key ("id");
alter table "report_type" add constraint "report_type_uq" unique ("name");

create table if not exists "report_status" (
    "id" serial,
    "name" varchar(50) not null,
    "description" text
    );
alter table "report_status" add constraint "report_status_pk" primary key ("id");
alter table "report_status" add constraint "report_status_uq" unique ("name");

create table if not exists "report" (
    "id" serial,
    "rent_id" int4 not null,
    "report_date" timestamp not null,
    "report_type_id" int4 not null,
    "description" text not null,
    "report_status_id" int4 not null
);
alter table "report" add constraint "report_pk" primary key ("id");
alter table "report" add constraint "report_rent_fk" foreign key("rent_id") references "rent"("id")
    on update cascade on delete restrict;
alter table "report" add constraint "report_report_type_fk" foreign key("report_type_id") references "report_type"("id")
    on update cascade on delete restrict;
alter table "report" add constraint "report_report_status_fk" foreign key("report_status_id") references "report_status"("id")
    on update cascade on delete restrict;

create table if not exists "rental_data" (
    "id" serial,
    "company_name" varchar(50) not null,
    "tax_id" char(10) not null,
    "regon" char(9) not null,
    "email_address" varchar(254),
    "city" varchar(50),
    "post_code" varchar(10),
    "street" varchar(50),
    "house_number" varchar(10),
    "description" text
    );
alter table "rental_data" add constraint "rental_data_pk" primary key ("id");