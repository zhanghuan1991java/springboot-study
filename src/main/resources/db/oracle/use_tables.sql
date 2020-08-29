create table a_user(
    id number(32),
    name varchar2(500),
    other_name varchar2(500),
    phone varchar2(50),
    addr varchar2(500),
    identity_code varchar2(30),
    create_time date default sysdate,
    update_time date
);