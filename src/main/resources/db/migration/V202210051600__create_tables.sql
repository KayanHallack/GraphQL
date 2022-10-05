create table CLIENT (
    ID    bigint auto_increment primary key,
    EMAIL varchar(255) null,
    NAME  varchar(255) null
);

create table PRODUCT(
    ID    bigint auto_increment primary key,
    NAME  varchar(255) null,
    VALUE double       not null
);

create table PURCHASE
(
    ID         bigint auto_increment primary key,
    DATE       datetime     null,
    QUANTITY int          null,
    STATUS     varchar(255) null,
    CLIENT_ID bigint       null,
    PRODUCT_ID bigint       null,
    constraint FK_PURCHASE_CLIENT foreign key (CLIENT_ID) references CLIENT (ID),
    constraint FK_PURCHASE_PRODUCT foreign key (PRODUCT_ID) references PRODUCT (ID)
);

