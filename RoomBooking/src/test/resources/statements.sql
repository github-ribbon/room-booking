/* delete data */

delete from reservation;
delete from room_manager;
delete from usr_group_auth;
delete from usr_group_usr;
delete from authority;
delete from usr_group;
delete from usr;
delete from person_group_person;
delete from person_group;
delete from person;
delete from bookable_room;
delete from room_attribute;
delete from room_attribute_type;
delete from room;
delete from building;
delete from campus;
delete from db_status;


/* restart sequences */

ALTER SEQUENCE person_seq
  RESTART WITH 10000;
  
 ALTER SEQUENCE reservation_seq
  RESTART WITH 10000;
  
/* insert data */
  

-- kampusy (3)

insert into campus(description,name,campus_id) values('Największy kampus szkoły wyższej położony w 

centrum ursynowa. Wybudowany w 1994 roku. Znakomity dojazd(około 100 metrów od stacji metra). 

Wydziały informatyki i politologii. Biblioteka i hala sportowa.','Warszawa Ursynów 

KEN','Warszawa');

insert into campus(description,name,campus_id) values('Kampus szkoły wyższej mieszczący się w 

Radomiu przy ul. Słonecznej 13. Wybudowany w 1999 roku. Studenci studiują tutaj na wydziale 

ekonomii.','Radom słoneczna','Radom');


insert into campus(description,name,campus_id) values('Kampus zlokalizowany w Płocku przy ulicy 

Wesołej 45. Jest to pozamiejscowy ośrodek szkoły, gdzie można studiować politologię.','Płock 

wesoła ','Płock');


-- budynki


-- dla kampusu Warszawa

insert into building(description,name,building_id,campus_id) values('Główny, składający się z 

dwóch pięter budynek kampusu, gdzie prowadzone są zajęcia dydaktyczne. W skład wchodzi kilka aul. 

Znajdują się bufet oraz punkt informacji na parterze.','Główny na Ursynowie','KEN3','Warszawa');



insert into building(description,name,building_id,campus_id) values('Budynek, w którym znajdują 

się sale przeznaczone do ćwiczeń z fizyki i elektroniki.','Techniczny','KEN5','Warszawa');


insert into building(description,name,building_id,campus_id) values('Centrum sportu uczelni. Hala 

sportowa, siłownia.','Sportowy','KEN7','Warszawa');


-- dla kampusu Radom


insert into building(description,name,building_id,campus_id) values('Główny budynek kampusu w 

Radomiu. Prowadzone są zajęcia dydaktyczne. Znajdują się dwie aule.','Główny','Słon36','Radom');


insert into building(description,name,building_id,campus_id) values('Drugi, mniejszy budynek 

kampusu w Radomiu. Składający się głównie z sal komputerowych i 

siłowni.','Mniejszy','Słon40A','Radom');

-- dla kampusu Płock


insert into building(description,name,building_id,campus_id) values('W jedynym budynku kampusu 

prowadzone są zajęcia z politologii.','Wesoła','Wesoła16K','Płock');







-- pomieszczenia


-- dla Warszawa, KEN3 

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Mieści się tutaj punkt rekrutacji.','7','KEN3','Warszawa');  


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Mieści się tutaj punkt informacji.','2','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Sala konferencyjna na parterze','23','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Największa aula na parterze.','A1','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Średnich rozmiarów aula na parterze.','A2','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Średnich rozmiarów aula na parterze.','A3','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Średnich rozmiarów aula na parterze.','A4','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Mini aula na parterze.','A5','KEN3','Warszawa');




insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Sala konferencyjna na pierwszym piętrze.','57','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Sala dydaktyczna na pierwszym piętrze.','69','KEN3','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Sala dydaktyczna na pierwszym piętrze.','73','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Sala dydaktyczna na pierwszym piętrze.','76','KEN3','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Sala dydaktyczna na pierwszym piętrze.','78','KEN3','Warszawa');







insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Pracownia komputerowa na pierwszym 

piętrze.','Lab1IT','KEN3','Warszawa');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Pracownia komputerowa na pierwszym 

piętrze.','Lab2IT','KEN3','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Pracownia komputerowa na pierwszym 

piętrze.','Lab3IT','KEN3','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Pracownia komputerowa na pierwszym 

piętrze.','Lab4IT','KEN3','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Pokój samorządu studenckiego.','16','KEN3','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w głównym 

budynku kampusu na Ursynowie. Biuro praktyk i staży studenckich.','26K','KEN3','Warszawa');




-- dla budynku KEN5, Warszawa


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w budynku 

kampusu na Ursynowie. Sala do ćwiczeń z fizyki.','14F','KEN5','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w budynku 

kampusu na Ursynowie. Sala do ćwiczeń z elektroniki.','26E','KEN5','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w budynku 

kampusu na Ursynowie. Sala dydaktyczna.','37','KEN5','Warszawa');



-- dla budynku KEN7, Warszawa

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w budynku 

kampusu na Ursynowie. Pomieszczenie obok sali gimnastycznej.','S4','KEN7','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w budynku 

kampusu na Ursynowie. Siłownia.','Sil','KEN7','Warszawa');



insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w budynku 

kampusu na Ursynowie. Duża sala gimnastyczna do gry w futsal, siatkówkę, 

koszykówkę.','S1','KEN7','Warszawa');



-- dla budynku Słon36, Radom

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Punkt informacji.','1','Słon36','Radom');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Sala dydaktyczna','14','Słon36','Radom');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Sala dydaktyczna','17','Słon36','Radom');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Aula.','A1','Słon36','Radom');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Aula.','A2','Słon36','Radom');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Sala konferencyjna.','29','Słon36','Radom');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w większym 

budynku kampusu w Radomiu. Sala konferencyjna.','32','Słon36','Radom');


-- dla budynku Słon40A,Radom

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w mniejszym 

budynku kampusu w Radomiu. Sala dydaktyczna.','6','Słon40A','Radom');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w mniejszym 

budynku kampusu w Radomiu. Pracownia komputerowa.','Lab1','Słon40A','Radom');


insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w mniejszym 

budynku kampusu w Radomiu. Pracownia komputerowa.','Lab2','Słon40A','Radom');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w mniejszym 

budynku kampusu w Radomiu. Siłownia.','Sil','Słon40A','Radom');

-- dla budynku Wesoła16K,Płock

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w jedynym 

budynku kampusu w Płocku. Punkt informacji.','3','Wesoła16K','Płock');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w jedynym 

budynku kampusu w Płocku. Sala konferencyjna.','12','Wesoła16K','Płock');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w jedynym 

budynku kampusu w Płocku. Laboratorium komputerowe.','Lab','Wesoła16K','Płock');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w jedynym 

budynku kampusu w Płocku. Sala dydaktyczna.','21C','Wesoła16K','Płock');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w jedynym 

budynku kampusu w Płocku. Sala dydaktyczna.','27','Wesoła16K','Płock');

insert into room(description,room_id,building_id,campus_id) values('Pomieszczenie w jedynym 

budynku kampusu w Płocku. Sala dydaktyczna.','34','Wesoła16K','Płock');



-- room attribute type


insert into room_attribute_type(description,room_attribute_type_id) values('Kolor ścian','kolor');
insert into room_attribute_type(description,room_attribute_type_id) values('Informacja o 

projektorze w pomieszczeniu','projektor');
insert into room_attribute_type(description,room_attribute_type_id) values('Informacja o 

zastosowanych środkach ochrony przed promieniami słonecznymi.','nasłonecznienie');
insert into room_attribute_type(description,room_attribute_type_id) values('Liczba miejsc 

siedzących.','miejsca siedzące');
insert into room_attribute_type(description,room_attribute_type_id) values('Użyty procesor w 

laboratorium komputerowym.','procesor');
insert into room_attribute_type(description,room_attribute_type_id) values('Powierzchnia w 

pomieszczeniu.','powierzchnia');
insert into room_attribute_type(description,room_attribute_type_id) values('Informacje o tablicy w 

pomieszczeniu.','tablica');
insert into room_attribute_type(description,room_attribute_type_id) values('Należy określić, czy w 

pomieszczeniu znajduje się zegar.','zegar');
insert into room_attribute_type(description,room_attribute_type_id) values('Sposób klimatyzowania 

pomieszczenia.','klimatyzacja');
insert into room_attribute_type(description,room_attribute_type_id) values('Sposób dostępu do 

internetu.','internet');








-- room attribute


insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('niebieski','7','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','7','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('30m2','7','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','7','KEN3','klimatyzacja','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','7','KEN3','internet','Warszawa');





insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('niebieski','2','KEN3','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','2','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('15m2','2','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','2','KEN3','klimatyzacja','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','2','KEN3','internet','Warszawa');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','23','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Optoma 3500 ANSI Lum.','23','KEN3','projektor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','23','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('30','23','KEN3','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('50m2','23','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('porcelanowa, interaktywna','23','KEN3','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','23','KEN3','zegar','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','23','KEN3','klimatyzacja','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','23','KEN3','internet','Warszawa');








insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A1','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Optoma 3500 ANSI Lum.','A1','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A1','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('800','A1','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('1000m2','A1','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, interaktywna','A1','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A1','KEN3','zegar','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A1','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','A1','KEN3','internet','Warszawa');





insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A2','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('BenQ 2300 ANSI Lum.','A2','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A2','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('400','A2','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('650m2','A2','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, interaktywna','A2','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A2','KEN3','zegar','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A2','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','A2','KEN3','internet','Warszawa');





insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A3','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('BenQ 2300 ANSI Lum.','A3','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A3','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('370','A3','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('600m2','A3','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','A3','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A3','KEN3','zegar','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A3','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','A3','KEN3','internet','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A4','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Epson 1800 ANSI Lum.','A4','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A4','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('390','A4','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('630m2','A4','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','A4','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A4','KEN3','zegar','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A4','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','A4','KEN3','internet','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A5','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Epson 1800 ANSI Lum.','A5','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A5','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('200','A5','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('320m2','A5','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, porcelanowa','A5','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A5','KEN3','zegar','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A5','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','A5','KEN3','internet','Warszawa');








insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','57','KEN3','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('BenQ 2300 ANSI Lum.','57','KEN3','projektor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','57','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('20','57','KEN3','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('40m2','57','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('korkowa, obrotowa','57','KEN3','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','57','KEN3','klimatyzacja','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','57','KEN3','internet','Warszawa');








insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','69','KEN3','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Acer 100 ANSI Lum.','69','KEN3','projektor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','69','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('54','69','KEN3','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('60m2','69','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','69','KEN3','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','69','KEN3','klimatyzacja','Warszawa');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','73','KEN3','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','73','KEN3','zegar','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','73','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('50','73','KEN3','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('64m2','73','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','73','KEN3','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','73','KEN3','klimatyzacja','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','76','KEN3','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','76','KEN3','internet','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','76','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('65','76','KEN3','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('70m2','76','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','76','KEN3','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','76','KEN3','klimatyzacja','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','78','KEN3','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','78','KEN3','internet','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','78','KEN3','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('45','78','KEN3','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('60m2','78','KEN3','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, korkowa','78','KEN3','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','78','KEN3','klimatyzacja','Warszawa');










insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab1IT','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Epson 1800 ANSI Lum.','Lab1IT','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','Lab1IT','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('40','Lab1IT','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Core i7 (4 rdzenie)','Lab1IT','KEN3','procesor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('90m2','Lab1IT','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, interaktywna','Lab1IT','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Lab1IT','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','Lab1IT','KEN3','internet','Warszawa');








insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab2IT','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Epson 1800 ANSI Lum.','Lab2IT','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','Lab2IT','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('30','Lab2IT','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Core i7 (4 rdzenie)','Lab2IT','KEN3','procesor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('76m2','Lab2IT','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, interaktywna','Lab2IT','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Lab2IT','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','Lab2IT','KEN3','internet','Warszawa');









insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab3IT','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','Lab3IT','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('34','Lab3IT','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Core i5 (2 rdzenie)','Lab3IT','KEN3','procesor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('70m2','Lab3IT','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, interaktywna','Lab3IT','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Lab3IT','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','Lab3IT','KEN3','internet','Warszawa');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab4IT','KEN3','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','Lab4IT','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('34','Lab4IT','KEN3','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Core i5 (2 rdzenie)','Lab4IT','KEN3','procesor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('50m2','Lab4IT','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, interaktywna','Lab4IT','KEN3','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Lab4IT','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('przewodowy','Lab4IT','KEN3','internet','Warszawa');












insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('niebieski','16','KEN3','kolor','Warszawa');


insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Acer 100 ANSI Lum.','16','KEN3','projektor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','16','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('40m2','16','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','16','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','16','KEN3','internet','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('niebieski','26K','KEN3','kolor','Warszawa');


insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','26K','KEN3','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('30m2','26K','KEN3','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','26K','KEN3','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','26K','KEN3','internet','Warszawa');






-- KEN5 


insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('szary','14F','KEN5','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','14F','KEN5','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('40','14F','KEN5','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('70m2','14F','KEN5','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','14F','KEN5','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','14F','KEN5','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('przewodowy','14F','KEN5','internet','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('szary','26E','KEN5','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','26E','KEN5','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('25','26E','KEN5','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('78m2','26E','KEN5','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','26E','KEN5','tablica','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','26E','KEN5','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('przewodowy','26E','KEN5','internet','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','37','KEN5','kolor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Acer 100 ANSI Lum.','37','KEN5','projektor','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','37','KEN5','nasłonecznienie','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('45','37','KEN5','miejsca siedzące','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('57m2','37','KEN5','powierzchnia','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','37','KEN5','tablica','Warszawa');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','37','KEN5','klimatyzacja','Warszawa');







-- KEN7



insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','S4','KEN7','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('10','S4','KEN7','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('20m2','S4','KEN7','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','S4','KEN7','zegar','Warszawa');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','Sil','KEN7','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','Sil','KEN7','nasłonecznienie','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('70m2','Sil','KEN7','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Sil','KEN7','zegar','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Sil','KEN7','klimatyzacja','Warszawa');








insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','S1','KEN7','kolor','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('400','S1','KEN7','miejsca siedzące','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('1100m2','S1','KEN7','powierzchnia','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','S1','KEN7','klimatyzacja','Warszawa');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','S1','KEN7','internet','Warszawa');






-- Słon36,Radom





insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('beżowy','1','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','1','Słon36','nasłonecznienie','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('25m2','1','Słon36','powierzchnia','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','1','Słon36','klimatyzacja','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','1','Słon36','internet','Radom');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','14','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('BenQ 2300 ANSI Lum.','14','Słon36','projektor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','14','Słon36','nasłonecznienie','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('42','14','Słon36','miejsca siedzące','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('62m2','14','Słon36','powierzchnia','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, obrotowa','14','Słon36','tablica','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','14','Słon36','zegar','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','14','Słon36','klimatyzacja','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy, przewodowy','14','Słon36','internet','Radom');









insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','17','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','17','Słon36','nasłonecznienie','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('32','17','Słon36','miejsca siedzące','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('42m2','17','Słon36','powierzchnia','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','17','Słon36','tablica','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','17','Słon36','zegar','Radom');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A1','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Epson 1800 ANSI Lum.','A1','Słon36','projektor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A1','Słon36','nasłonecznienie','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('400','A1','Słon36','miejsca siedzące','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('650m2','A1','Słon36','powierzchnia','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, porcelanowa','A1','Słon36','tablica','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A1','Słon36','klimatyzacja','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','A1','Słon36','internet','Radom');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','A2','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('BenQ 2300 ANSI Lum.','A2','Słon36','projektor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','A2','Słon36','nasłonecznienie','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('370','A2','Słon36','miejsca siedzące','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('560m2','A2','Słon36','powierzchnia','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','A2','Słon36','tablica','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','A2','Słon36','klimatyzacja','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','A2','Słon36','internet','Radom');








insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('niebieski','29','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Acer 100 ANSI Lum.','29','Słon36','projektor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','29','Słon36','nasłonecznienie','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('20','29','Słon36','miejsca siedzące','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('40m2','29','Słon36','powierzchnia','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('korkowa, obrotowa, interaktywna','29','Słon36','tablica','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','29','Słon36','klimatyzacja','Radom');









insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('niebieski','32','Słon36','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('żaluzje','32','Słon36','nasłonecznienie','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('15','32','Słon36','miejsca siedzące','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('25m2','32','Słon36','powierzchnia','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('korkowa','32','Słon36','tablica','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','32','Słon36','klimatyzacja','Radom');







-- Słon40A, Radom

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('beżowy','6','Słon40A','kolor','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','6','Słon40A','nasłonecznienie','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('30','6','Słon40A','miejsca siedzące','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('45m2','6','Słon40A','powierzchnia','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','6','Słon40A','tablica','Radom');





insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab1','Słon40A','kolor','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Acer 100 ANSI Lum.','Lab1','Słon40A','projektor','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','Lab1','Słon40A','nasłonecznienie','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('27','Lab1','Słon40A','miejsca siedzące','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Core i5 (2 rdzenie)','Lab1','Słon40A','procesor','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('54m2','Lab1','Słon40A','powierzchnia','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','Lab1','Słon40A','tablica','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Lab1','Słon40A','klimatyzacja','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('przewodowy','Lab1','Słon40A','internet','Radom');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab2','Słon40A','kolor','Radom');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','Lab2','Słon40A','nasłonecznienie','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('25','Lab2','Słon40A','miejsca siedzące','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Core i5 (2 rdzenie)','Lab2','Słon40A','procesor','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('50m2','Lab2','Słon40A','powierzchnia','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','Lab2','Słon40A','tablica','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Lab2','Słon40A','klimatyzacja','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('przewodowy','Lab2','Słon40A','internet','Radom');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('biały','Sil','Słon40A','kolor','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('25m2','Sil','Słon40A','powierzchnia','Radom');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','Sil','Słon40A','klimatyzacja','Radom');




-- Wesoła16K, Płock


insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','3','Wesoła16K','kolor','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('15m2','3','Wesoła16K','powierzchnia','Płock');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','12','Wesoła16K','kolor','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Acer 100 ANSI Lum.','12','Wesoła16K','projektor','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('rolety','12','Wesoła16K','nasłonecznienie','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('20','12','Wesoła16K','miejsca siedzące','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('37m2','12','Wesoła16K','powierzchnia','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('korkowa','12','Wesoła16K','tablica','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','12','Wesoła16K','zegar','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','12','Wesoła16K','klimatyzacja','Płock');
insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('bezprzewodowy','12','Wesoła16K','internet','Płock');









insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zielony','Lab','Wesoła16K','kolor','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','Lab','Wesoła16K','nasłonecznienie','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('35','Lab','Wesoła16K','miejsca siedzące','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('Intel Celeron (1 rdzeń)','Lab','Wesoła16K','procesor','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('56m2','Lab','Wesoła16K','powierzchnia','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','Lab','Wesoła16K','tablica','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('przewodowy','Lab','Wesoła16K','internet','Płock');






insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','21C','Wesoła16K','kolor','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('zasłony','21C','Wesoła16K','nasłonecznienie','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('27','21C','Wesoła16K','miejsca siedzące','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('49m2','21C','Wesoła16K','powierzchnia','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa, obrotowa','21C','Wesoła16K','tablica','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','21C','Wesoła16K','zegar','Płock');







insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','27','Wesoła16K','kolor','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('23','27','Wesoła16K','miejsca siedzące','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('44m2','27','Wesoła16K','powierzchnia','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','27','Wesoła16K','tablica','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','27','Wesoła16K','zegar','Płock');









insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kremowy','34','Wesoła16K','kolor','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('32','34','Wesoła16K','miejsca siedzące','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('51m2','34','Wesoła16K','powierzchnia','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('kredowa','34','Wesoła16K','tablica','Płock');

insert into room_attribute(value,room_id,building_id,room_attribute_type_id,campus_id) values

('jest','34','Wesoła16K','zegar','Płock');








-- person


insert into person(person_id,family_name,given_name) values(900,'Kowalski','Jan');
insert into person(person_id,family_name,given_name) values(901,'Stążecki','Andrzej');
insert into person(person_id,family_name,given_name) values(902,'Nowak','Adam');
insert into person(person_id,family_name,given_name) values(903,'Grabowska','Halina');
insert into person(person_id,family_name,given_name) values(904,'Zielony','Łukasz');
insert into person(person_id,family_name,given_name) values(905,'Silewska','Irena');
insert into person(person_id,family_name,given_name) values(906,'Kwiatkowska','Małgorzata');
insert into person(person_id,family_name,given_name) values(907,'Nowakowski','Zenon');
insert into person(person_id,family_name,given_name) values(908,'Nowicka','Marta');
insert into person(person_id,family_name,given_name) values(909,'Przypadek','Jacek');
insert into person(person_id,family_name,given_name) values(910,'Sitarek','Adam');
insert into person(person_id,family_name,given_name) values(911,'Wiśniewska','Anna');
insert into person(person_id,family_name,given_name) values(912,'Adamska','Karolina');
insert into person(person_id,family_name,given_name) values(913,'Wesołowski','Karol');
insert into person(person_id,family_name,given_name) values(914,'Czarnecka','Katarzyna');
insert into person(person_id,family_name,given_name) values(915,'Tulipan','Eugeniusz');
insert into person(person_id,family_name,given_name) values(916,'Sroka','Sandra');
insert into person(person_id,family_name,given_name) values(917,'Nowicka','Marta');
insert into person(person_id,family_name,given_name) values(918,'Wiosenny','Marcin');
insert into person(person_id,family_name,given_name) values(919,'Lesicki','Sebastian');
insert into person(person_id,family_name,given_name) values(920,'Markowski','Tomasz');
insert into person(person_id,family_name,given_name) values(921,'Lewicki','Bronisław');
insert into person(person_id,family_name,given_name) values(922,'Zielińska','Sylwia');
insert into person(person_id,family_name,given_name) values(923,'Grzegorzewska','Patrycja');
insert into person(person_id,family_name,given_name) values(924,'Malinowski','Bartosz');
insert into person(person_id,family_name,given_name) values(925,'Jastrząb','Monika');
insert into person(person_id,family_name,given_name) values(926,'Wolicki','Krzysztof');
insert into person(person_id,family_name,given_name) values(927,'Niewiadomska','Aleksandra');
insert into person(person_id,family_name,given_name) values(928,'Malinowska','Barbara');
insert into person(person_id,family_name,given_name) values(929,'Białkowski','Dawid');

insert into person(person_id,family_name,given_name) values(930,'Czachowski','Filip');
insert into person(person_id,family_name,given_name) values(931,'Majewski','Cyprian');
insert into person(person_id,family_name,given_name) values(932,'Wojecka','Agnieszka');
insert into person(person_id,family_name,given_name) values(933,'Kaczorowska','Ewelina');




insert into person(person_id,family_name,given_name) values(934,'Nawrocka','Sylwia');
insert into person(person_id,family_name,given_name) values(935,'Zalewski','Łukasz');
insert into person(person_id,family_name,given_name) values(936,'Maj','Celina');
insert into person(person_id,family_name,given_name) values(937,'Kolecka','Kinga');
insert into person(person_id,family_name,given_name) values(938,'Poleski','Szymon');
insert into person(person_id,family_name,given_name) values(939,'Nowak','Piotr');
insert into person(person_id,family_name,given_name) values(940,'Wilski','Paweł');
insert into person(person_id,family_name,given_name) values(941,'Kret','Janusz');
insert into person(person_id,family_name,given_name) values(942,'Zajączkowska','Anastazja');
insert into person(person_id,family_name,given_name) values(943,'Wiśniowa','Brygida');
insert into person(person_id,family_name,given_name) values(944,'Szumski','Maciej');
insert into person(person_id,family_name,given_name) values(945,'Kowaliński','Jakub');
insert into person(person_id,family_name,given_name) values(946,'Waza','Oldgierd');
insert into person(person_id,family_name,given_name) values(947,'Warnecka','Katarzyna');
insert into person(person_id,family_name,given_name) values(948,'Pajski','Witold');
insert into person(person_id,family_name,given_name) values(949,'Sarska','Milena');
insert into person(person_id,family_name,given_name) values(950,'Polończyk','Wacław');
insert into person(person_id,family_name,given_name) values(951,'Tosińska','Patrycja');
insert into person(person_id,family_name,given_name) values(952,'Lojska','Klaudia');
insert into person(person_id,family_name,given_name) values(953,'Nierezewski','Artur');
insert into person(person_id,family_name,given_name) values(954,'Kalski','Michał');
insert into person(person_id,family_name,given_name) values(955,'Czartolski','Przemysław');
insert into person(person_id,family_name,given_name) values(956,'Tralska','Aleksandra');
insert into person(person_id,family_name,given_name) values(957,'Perojski','Grzegorz');






-- person group

insert into person_group(description,person_group_id) values('Rektorat 

uczelni.','rektorat');

insert into person_group(description,person_group_id) values('Samorząd 

studencki uczelni.','samorząd studencki');

insert into person_group(description,person_group_id) values('Pracownicy 

dziekanatu uczelni.','dziekanat');

insert into person_group(description,person_group_id) values('Kadra dydaktyczna 

uczelni.','wykładowcy');

insert into person_group(description,person_group_id) values('Pracownicy biura 

karier i praktyk studenckich.','biuro karier');

insert into person_group(description,person_group_id) values('Pracownicy 

uczelni.','pracownicy');

insert into person_group(description,person_group_id) values('Pracownicy działu 

planowania.','dział planowania');

insert into person_group(description,person_group_id) values('Studenci 

uczelni.','studenci');

insert into person_group(description,person_group_id) values('Uczniowie 

pobliskiego liceum.','uczniowie liceum');



-- person_group_person

-- rektorat

insert into person_group_person(person_group_id,person_id) values
('rektorat',900);
insert into person_group_person(person_group_id,person_id) values
('rektorat',902);
insert into person_group_person(person_group_id,person_id) values
('rektorat',903);


-- dziekanat

insert into person_group_person(person_group_id,person_id) values
('dziekanat',904);
insert into person_group_person(person_group_id,person_id) values
('dziekanat',905);
insert into person_group_person(person_group_id,person_id) values
('dziekanat',906);
insert into person_group_person(person_group_id,person_id) values
('dziekanat',907);


-- wykładowcy

insert into person_group_person(person_group_id,person_id) values
('wykładowcy',908);
insert into person_group_person(person_group_id,person_id) values
('wykładowcy',909);
insert into person_group_person(person_group_id,person_id) values
('wykładowcy',910);
insert into person_group_person(person_group_id,person_id) values
('wykładowcy',911);
insert into person_group_person(person_group_id,person_id) values
('wykładowcy',912);


-- biuro karier

insert into person_group_person(person_group_id,person_id) values
('biuro karier',913);
insert into person_group_person(person_group_id,person_id) values
('biuro karier',914);
insert into person_group_person(person_group_id,person_id) values
('biuro karier',915);

-- dział planowania

insert into person_group_person(person_group_id,person_id) values
('dział planowania',916);
insert into person_group_person(person_group_id,person_id) values
('dział planowania',917);
insert into person_group_person(person_group_id,person_id) values
('dział planowania',918);
insert into person_group_person(person_group_id,person_id) values
('dział planowania',919);
insert into person_group_person(person_group_id,person_id) values
('dział planowania',920);


-- pracownicy

insert into person_group_person(person_group_id,person_id) values
('pracownicy',900);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',902);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',903);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',904);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',905);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',906);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',907);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',908);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',909);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',910);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',911);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',912);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',913);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',914);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',915);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',916);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',917);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',918);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',919);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',920);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',921);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',922);
insert into person_group_person(person_group_id,person_id) values
('pracownicy',923);

-- samorząd studencki

insert into person_group_person(person_group_id,person_id) values
('samorząd studencki',924);
insert into person_group_person(person_group_id,person_id) values
('samorząd studencki',925);
insert into person_group_person(person_group_id,person_id) values
('samorząd studencki',926);
insert into person_group_person(person_group_id,person_id) values
('samorząd studencki',927);

-- studenci

insert into person_group_person(person_group_id,person_id) values
('studenci',901);
insert into person_group_person(person_group_id,person_id) values
('studenci',924);
insert into person_group_person(person_group_id,person_id) values
('studenci',925);
insert into person_group_person(person_group_id,person_id) values
('studenci',926);
insert into person_group_person(person_group_id,person_id) values
('studenci',927);
insert into person_group_person(person_group_id,person_id) values
('studenci',928);
insert into person_group_person(person_group_id,person_id) values
('studenci',929);

-- uczniowie

insert into person_group_person(person_group_id,person_id) values
('uczniowie liceum',930);
insert into person_group_person(person_group_id,person_id) values
('uczniowie liceum',931);
insert into person_group_person(person_group_id,person_id) values
('uczniowie liceum',932);
insert into person_group_person(person_group_id,person_id) values
('uczniowie liceum',933);
insert into person_group_person(person_group_id,person_id) values
('uczniowie liceum',934);





-- usr





insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'ribbon','a7c471cfd3c42dc6d6a8552ac2c0a22c',901,'stape@o2.pl');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'full','a7c471cfd3c42dc6d6a8552ac2c0a22c',909,'any_mail_01@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aconfg','a7c471cfd3c42dc6d6a8552ac2c0a22c',934,'any_mail_02@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aconfg2','a7c471cfd3c42dc6d6a8552ac2c0a22c',935,'any_mail_03@any_domain.com');


insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aclconf','a7c471cfd3c42dc6d6a8552ac2c0a22c',936,'any_mail_04@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aclconf2','a7c471cfd3c42dc6d6a8552ac2c0a22c',937,'any_mail_05@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'clconfg','a7c471cfd3c42dc6d6a8552ac2c0a22c',938,'any_mail_06@any_domain.com');





insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'clconfg2','a7c471cfd3c42dc6d6a8552ac2c0a22c',NULL,'any_mail_07@any_domain.com');






insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aclg','a7c471cfd3c42dc6d6a8552ac2c0a22c',NULL,'any_mail_08@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aclg2','a7c471cfd3c42dc6d6a8552ac2c0a22c',NULL,'any_mail_09@any_domain.com');






insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'ag','a7c471cfd3c42dc6d6a8552ac2c0a22c',939,'any_mail_10@any_domain.com');





insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'ag2','a7c471cfd3c42dc6d6a8552ac2c0a22c',940,'any_mail_11@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aconf','a7c471cfd3c42dc6d6a8552ac2c0a22c',941,'any_mail_12@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'aconf2','a7c471cfd3c42dc6d6a8552ac2c0a22c',NULL,'any_mail_13@any_domain.com');







insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'confg','a7c471cfd3c42dc6d6a8552ac2c0a22c',942,'any_mail_14@any_domain.com');


insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'confg2','a7c471cfd3c42dc6d6a8552ac2c0a22c',943,'any_mail_15@any_domain.com');





insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'clconf','a7c471cfd3c42dc6d6a8552ac2c0a22c',944,'any_mail_16@any_domain.com');






insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'clconf2','a7c471cfd3c42dc6d6a8552ac2c0a22c',945,'any_mail_17@any_domain.com');






insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'acl','a7c471cfd3c42dc6d6a8552ac2c0a22c',946,'any_mail_18@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'acl2','a7c471cfd3c42dc6d6a8552ac2c0a22c',947,'any_mail_19@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'clg','a7c471cfd3c42dc6d6a8552ac2c0a22c',NULL,'any_mail_20@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'clg2','a7c471cfd3c42dc6d6a8552ac2c0a22c',948,'any_mail_21@any_domain.com');






insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'a','a7c471cfd3c42dc6d6a8552ac2c0a22c',902,'any_mail_22@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'a2','a7c471cfd3c42dc6d6a8552ac2c0a22c',903,'any_mail_23@any_domain.com');



insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'cl','a7c471cfd3c42dc6d6a8552ac2c0a22c',921,'any_mail_24@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'cl2','a7c471cfd3c42dc6d6a8552ac2c0a22c',922,'any_mail_25@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'conf','a7c471cfd3c42dc6d6a8552ac2c0a22c',923,'any_mail_26@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'conf2','a7c471cfd3c42dc6d6a8552ac2c0a22c',950,'any_mail_27@any_domain.com');




insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'g','a7c471cfd3c42dc6d6a8552ac2c0a22c',951,'any_mail_28@any_domain.com');






insert into usr(is_enabled,usr_id,password,person_id,email) values

(true,'g2','a7c471cfd3c42dc6d6a8552ac2c0a22c',952,'any_mail_29@any_domain.com');





-- usr group

insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zarządzania obiektami. Mogą oni zarządzać strukturą kampusów, budynków, 

pomieszczeń.','administratorzy');



insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do dokonywania rezerwacji pomieszczeń.','klienci');



insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zatwierdzania lub anulowania rezerwacji dokonywanych przez 

klientów.','potwierdzający');


insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zarządzania grupami osób i użytkowników, w tym rejestracji nowych 

użytkowników.','zarządzający grupami');



insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zatwierdzania lub anulowania rezerwacji dokonywanych przez klientów. 

Członkowie tej grupy są najbardziej odpowiedzialni i szybko podejmują 

decyzję.','potwierdzający pierwszego stopnia');

insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zatwierdzania lub anulowania rezerwacji dokonywanych przez klientów. 

Członkowie tej grupy są odpowiedzialni i szybko podejmują decyzję.','potwierdzający drugiego stopnia');


insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zatwierdzania lub anulowania rezerwacji dokonywanych przez klientów. 

Członkowie tej grupy mogą decydować o rezerwacjach w kampusie Warszawa.','potwierdzający w kampusie Warszawa');


insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zatwierdzania lub anulowania rezerwacji dokonywanych przez klientów. 

Członkowie tej grupy mogą decydować o rezerwacjach w kampusie Radom.','potwierdzający w kampusie Radom');


insert into usr_group(description,usr_group_id) values('Grupa użytkowników 

posiadająca prawo do zatwierdzania lub anulowania rezerwacji dokonywanych przez klientów. 

Członkowie tej grupy mogą decydować o rezerwacjach w kampusie Płock.','potwierdzający w kampusie Płock');









-- authority

insert into authority(authority_id) values('ROLE_ADMIN');
insert into authority(authority_id) values('ROLE_CLIENT');
insert into authority(authority_id) values('ROLE_CONFIRMER');
insert into authority(authority_id) values('ROLE_GROUP');

-- usr_group_auth


insert into usr_group_auth(usr_group_id,authority_id) values('administratorzy','ROLE_ADMIN');
insert into usr_group_auth(usr_group_id,authority_id) values('klienci','ROLE_CLIENT');
insert into usr_group_auth(usr_group_id,authority_id) values('potwierdzający','ROLE_CONFIRMER');
insert into usr_group_auth(usr_group_id,authority_id) values('zarządzający grupami','ROLE_GROUP');





-- usr_group_usr



insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający pierwszego stopnia','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający drugiego stopnia','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Warszawa','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Radom','ribbon');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Płock','ribbon');





insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający pierwszego stopnia','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający drugiego stopnia','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Warszawa','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Radom','full');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Płock','full');





insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','aconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','aconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Warszawa','aconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Radom','aconfg');




insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aconfg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','aconfg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','aconfg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Radom','aconfg2');






insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aclconf');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','aclconf');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','aclconf');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający drugiego stopnia','aclconf');




insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aclconf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','aclconf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','aclconf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Radom','aclconf2');




insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','clconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','clconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','clconfg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Radom','clconfg');






insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','clconfg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','clconfg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','clconfg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Płock','clconfg2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aclg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','aclg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','aclg');





insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aclg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','aclg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','aclg2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','ag');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','ag');



insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','ag2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','ag2');




insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aconf');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','aconf');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający drugiego stopnia','aconf');

insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','aconf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','aconf2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','confg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','confg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający drugiego stopnia','confg');



insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','confg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','confg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Warszawa','confg2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','clconf');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','clconf');

insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający drugiego stopnia','clconf');




insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','clconf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','clconf2');

insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający pierwszego stopnia','clconf2');




insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','acl');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','acl');



insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','acl2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','acl2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','clg');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','clg');




insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','clg2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','clg2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','a');




insert into  usr_group_usr(usr_group_id,usr_id) values
('administratorzy','a2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','cl');



insert into  usr_group_usr(usr_group_id,usr_id) values
('klienci','cl2');





insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','conf');

insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający pierwszego stopnia','conf');



insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający','conf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający pierwszego stopnia','conf2');
insert into  usr_group_usr(usr_group_id,usr_id) values
('potwierdzający w kampusie Warszawa','conf2');



insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','g');




insert into  usr_group_usr(usr_group_id,usr_id) values
('zarządzający grupami','g2');










-- bookable room


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'23','KEN3','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'A1','KEN3','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'A2','KEN3','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'A3','KEN3','Warszawa');




insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'A5','KEN3','Warszawa');




insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'57','KEN3','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'69','KEN3','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'73','KEN3','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'78','KEN3','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Lab1IT','KEN3','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Lab2IT','KEN3','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Lab4IT','KEN3','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'14F','KEN5','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'26E','KEN5','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'37','KEN5','Warszawa');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'S4','KEN7','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Sil','KEN7','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'S1','KEN7','Warszawa');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'14','Słon36','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'17','Słon36','Radom');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'A1','Słon36','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'A2','Słon36','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'29','Słon36','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'32','Słon36','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'6','Słon40A','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Lab1','Słon40A','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Lab2','Słon40A','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Sil','Słon40A','Radom');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'12','Wesoła16K','Płock');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'Lab','Wesoła16K','Płock');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'21C','Wesoła16K','Płock');


insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'27','Wesoła16K','Płock');

insert into bookable_room(is_robot,room_id,building_id,campus_id) values
(false,'34','Wesoła16K','Płock');








-- room_manager




insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','23','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','23','KEN3','Warszawa');


insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','A1','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','A1','KEN3','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','A2','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','A2','KEN3','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','A3','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','A3','KEN3','Warszawa');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','A5','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','A5','KEN3','Warszawa');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','57','KEN3','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','57','KEN3','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','57','KEN3','Warszawa');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','69','KEN3','Warszawa');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','Lab1IT','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','Lab1IT','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','Lab1IT','KEN3','Warszawa');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','Lab4IT','KEN3','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','Lab4IT','KEN3','Warszawa');


insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','14F','KEN5','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','26E','KEN5','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający','37','KEN5','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','37','KEN5','Warszawa');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','S4','KEN7','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Warszawa','S4','KEN7','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','Sil','KEN7','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','S1','KEN7','Warszawa');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','S1','KEN7','Warszawa');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','14','Słon36','Radom');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Radom','14','Słon36','Radom');



insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','A1','Słon36','Radom');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Radom','A1','Słon36','Radom');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający pierwszego stopnia','A2','Słon36','Radom');
insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Radom','A2','Słon36','Radom');


insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający','6','Słon40A','Radom');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Radom','Lab1','Słon40A','Radom');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Radom','Lab2','Słon40A','Radom');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','Sil','Słon40A','Radom');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Radom','Sil','Słon40A','Radom');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający drugiego stopnia','12','Wesoła16K','Płock');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Płock','12','Wesoła16K','Płock');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Płock','Lab','Wesoła16K','Płock');

insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający','21C','Wesoła16K','Płock');


insert into room_manager(usr_group_id,room_id,building_id,campus_id) values
('potwierdzający w kampusie Płock','27','Wesoła16K','Płock');





-- db status

insert into db_status values('C');
insert into db_status values('U');
insert into db_status values('D');

































INSERT INTO reservation (reservation_id, 

building_id, campus_id, created, db_status_id, modified, person_group_id, purpose, 

reservation_date, reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (900, 'KEN3', 

'Warszawa', '2013-03-02 09:02:23.623', 'U', '2013-03-02 09:02:23.623','dziekanat', '', '2013-05-25 

00:00:00', NULL, '57', '2013-05-25 10:00:00', '2013-05-25 12:00:00','cl');


INSERT INTO reservation (reservation_id, building_id, campus_id, created, 

db_status_id, modified, person_group_id, purpose, reservation_date, reservation_parent_id, 

room_id, time_from, time_to, usr_id) VALUES (903, 'Wesoła16K', 'Płock', '2013-03-02 10:19:39.491', 

'D', '2013-03-02 10:19:39.491','uczniowie liceum', '', '2013-05-20 00:00:00', NULL, 'Lab', '2013-05-20 

14:00:00', '2013-05-20 17:30:00','ribbon');



INSERT INTO reservation (reservation_id, building_id, campus_id, created, db_status_id, modified, 

person_group_id, purpose, reservation_date, reservation_parent_id, room_id, time_from, time_to, 

usr_id) VALUES (905, 'KEN7', 'Warszawa', '2013-03-02 10:21:44.612', 'C', '2013-03-02 

10:21:44.612', 'uczniowie liceum', '', '2013-05-23 00:00:00', NULL, 'S1', '2013-05-23 15:00:00', '2013-05-23 

17:00:00', 'ribbon');




INSERT INTO reservation (reservation_id, building_id, 

campus_id, created, db_status_id, modified, person_group_id, purpose, reservation_date, 

reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (907, 'Słon40A', 'Radom', 

'2013-03-02 10:23:19.488', 'D', '2013-03-02 10:23:19.488', 'dział planowania', '', '2013-05-30 00:00:00', NULL, 

'6', '2013-05-30 11:00:00', '2013-05-30 14:00:00','ribbon');





INSERT INTO reservation 

(reservation_id, building_id, campus_id, created, db_status_id, modified, person_group_id, 

purpose, reservation_date, reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES 

(906, 'KEN3', 'Warszawa', '2013-03-02 10:22:34.1', 'U', '2013-03-02 10:22:34.1','wykładowcy', '',
'2013-6-03 00:00:00', NULL, 'A2', '2013-06-03 09:00:00', '2013-06-03 11:00:00','ribbon');



INSERT INTO 

reservation (reservation_id, building_id, campus_id, created, db_status_id, modified, 

person_group_id, purpose, reservation_date, reservation_parent_id, room_id, time_from, time_to, 

usr_id) VALUES (902, 'Wesoła16K', 'Płock', '2013-03-02 10:18:45.679', 'D', '2013-03-02 

15:16:31.37', 'studenci', '', '2013-05-26 00:00:00', NULL, '34', '2013-05-26 09:00:00', '2013-05-26 

12:00:00','ribbon');



INSERT INTO reservation (reservation_id, building_id, campus_id, created, 

db_status_id, modified, person_group_id, purpose, reservation_date, reservation_parent_id, 

room_id, time_from, time_to, usr_id) VALUES (908, 'KEN3', 'Warszawa', '2013-03-02 10:24:21.156', 

'U', '2013-03-08 13:11:20.132','rektorat', '', '2013-05-14 00:00:00', NULL, '57', '2013-05-14 12:00:00', 

'2013-05-14 14:15:00','ribbon');


INSERT INTO reservation (reservation_id, building_id, campus_id, 

created, db_status_id, modified, person_group_id, purpose, reservation_date, 

reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (904, 'KEN5', 'Warszawa', 

'2013-03-02 10:20:49.202', 'U', '2013-03-08 13:17:33.189','uczniowie liceum', '', '2013-05-12 00:00:00', NULL, 

'14F', '2013-05-12 14:00:00', '2013-05-12 15:00:00','ribbon');



INSERT INTO 

reservation (reservation_id, building_id, campus_id, created, db_status_id, modified, 

person_group_id, purpose, reservation_date, reservation_parent_id, room_id, time_from, time_to, 

usr_id) VALUES (914, 'KEN5', 'Warszawa', '2013-03-08 13:21:02.055', 'U', '2013-03-08 

13:23:30.435', 'uczniowie liceum', '', '2013-05-19 00:00:00', NULL, '14F', '2013-05-19 11:00:00', '2013-05-19 

14:00:00', 'ribbon');



INSERT INTO reservation (reservation_id, 

building_id, campus_id, created, db_status_id, modified, person_group_id, purpose, 

reservation_date, reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (915, 

'KEN5', 'Warszawa', '2013-03-08 13:22:25.95', 'C', '2013-03-08 13:22:25.95','uczniowie liceum', '', '2013-05-19 

00:00:00', 914, '14F', '2013-05-19 11:00:00', '2013-05-19 12:00:00', 'ribbon');



INSERT INTO reservation (reservation_id, building_id, campus_id, created, 

db_status_id, modified, person_group_id, purpose, reservation_date, reservation_parent_id, 

room_id, time_from, time_to, usr_id) VALUES (916, 'KEN5', 'Warszawa', '2013-03-08 13:23:30.435', 

'D', '2013-03-08 13:24:51.837', 'uczniowie liceum', '', '2013-05-19 00:00:00', 914, '14F', '2013-05-19 

14:30:00', '2013-05-19 17:00:00', 'ribbon');
INSERT INTO reservation (reservation_id, building_id, 

campus_id, created, db_status_id, modified, person_group_id, purpose, reservation_date, 

reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (901, 'KEN3', 'Warszawa', 

'2013-03-02 09:49:16.724', 'C', '2013-03-08 12:19:53.846','samorząd studencki', '', '2013-05-27 00:00:00', NULL, 

'69', '2013-05-27 12:15:00', '2013-05-27 14:30:00', 'ribbon');


INSERT INTO reservation (reservation_id, 

building_id, campus_id, created, db_status_id, modified, person_group_id, purpose, 

reservation_date, reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (909, 'KEN3', 

'Warszawa', '2013-03-02 14:06:46.106', 'C', '2013-03-02 14:06:46.106', 'dziekanat', '', '2013-05-09 

00:00:00', NULL, 'A5', '2013-05-09 10:00:00', '2013-05-09 12:00:00', 'ribbon');
INSERT INTO reservation 

(reservation_id, building_id, campus_id, created, db_status_id, modified, person_group_id, 

purpose, reservation_date, reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES 

(910, 'KEN5', 'Warszawa', '2013-03-02 14:08:47.207', 'C', '2013-03-08 12:33:11.18', 'uczniowie liceum', '', 

'2013-05-26 00:00:00', NULL, '14F', '2013-05-26 16:00:00', '2013-05-26 18:00:00', 'ribbon');
INSERT INTO 

reservation (reservation_id, building_id, campus_id, created, db_status_id, modified, 

person_group_id, purpose, reservation_date, reservation_parent_id, room_id, time_from, time_to, 

usr_id) VALUES (911, 'KEN3', 'Warszawa', '2013-03-02 14:10:47.185', 'C', '2013-03-02 

14:10:47.185','pracownicy', '', '2013-05-12 00:00:00', NULL, '69', '2013-05-12 16:25:00', '2013-05-12 

19:00:00', 'ribbon');

INSERT INTO reservation (reservation_id, building_id, campus_id, created, 

db_status_id, modified, person_group_id, purpose, reservation_date, reservation_parent_id, 

room_id, time_from, time_to, usr_id) VALUES (913, 'KEN3', 'Warszawa', '2013-03-08 13:11:20.132', 

'C', '2013-03-08 13:13:58.019','rektorat', '', '2013-05-14 00:00:00', 908, '57', '2013-05-14 13:00:00', 

'2013-05-14 14:15:00', 'ribbon');


INSERT INTO reservation (reservation_id, building_id, campus_id, 

created, db_status_id, modified, person_group_id, purpose, reservation_date, 

reservation_parent_id, room_id, time_from, time_to, usr_id) VALUES (917, 'Słon36', 'Radom', 
'2013-03-08 13:28:24.951', 'U', '2013-03-08 13:34:15.813', 'studenci', '', '2013-05-16 00:00:00', NULL, 

'14', 

'2013-05-16 09:00:00', '2013-05-16 12:00:00', 'ribbon');





  