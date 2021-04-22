UPDATE CLIENT_DB SET CLIENT_EMAILCHECK = 'true';
commit;

delete from SHOP;
drop sequence SHOP_idx_seq;
create sequence SHOP_idx_seq;
commit;