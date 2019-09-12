create database if not exists db_agenda_v2;

create table if not exists db_agenda_v2.tb_contatos(
	con_id int unsigned,
	con_nome varchar(50),
	con_fone varchar(15),
	constraint primary key(con_id)
);

select * from db_agenda_v2.tb_contatos;

insert into db_agenda_v2.tb_contatos
	(con_nome, con_fone)
values
	('WESKLEY BEZERRA', '99675-3679'),
	('LUCAS MATHEUS', '98877-2233');
	
select * from db_agenda_v2.tb_contatos;
