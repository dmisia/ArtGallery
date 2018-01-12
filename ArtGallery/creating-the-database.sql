-- create database `Art_Gallery`;
drop table if exists Gallery;

drop table if exists Gallery_Paintings;
drop table if exists Sold_Paintings;

drop table if exists `Buyer/SD`;

drop table if exists Artists;
drop table if exists Buyers;

drop table if exists Rooms;

drop table if exists `Buyer/SD`;

create table Gallery (
	ID int not null primary key,
    gallery_name varchar(50) not null,
    address varchar(60)
);

create table Gallery_Paintings (
	ID int not null primary key auto_increment,
    title varchar(50) not null unique,
    artist int,
    artstyle varchar(30),
    year_of_creation int,
    year_of_acquisition int,
    room int
);

create table Sold_Paintings (
	ID int not null primary key auto_increment,
    title varchar(50) not null unique,
    artist int,
    artstyle varchar(30),
    year_of_creation int,
    year_of_acquisition int,
    buyer int,
    selling_date date not null
);

create table Artists (
	ID int not null primary key auto_increment,
    first_name varchar(20) not null,
    second_name varchar(20),
    birthplace varchar(30),
    year_of_birth int,
    country varchar(50)
);

create table Buyers (
	ID int not null primary key auto_increment,
    first_name varchar(20) not null,
    second_name varchar(20) not null,
    country varchar(50)
);

create table `Buyer/SD` (
	buyerID int not null,
    selling_date date not null
);

create table Rooms (
	ID int not null primary key auto_increment,
    designation varchar(40) not null,
    number_of_paintings int default 0,
    capacity int not null
);

alter table Gallery_Paintings
add foreign key (artist) references Artists(ID);

alter table Gallery_Paintings
add foreign key (room) references Rooms(ID);

alter table Sold_Paintings
add foreign key (artist) references Artists(ID);

alter table Sold_Paintings
add foreign key (buyer) references Buyers(ID);

alter table `Buyer/SD`
add foreign key (buyerID) references Buyers(ID);

SET @disable_trigger = 0;

-- TRIGGERY UPDATE --

drop trigger if exists checkIfMovePossible;
delimiter $$
create trigger checkIfMovePossible
before update on gallery_paintings
for each row
begin
	select number_of_paintings into @a from rooms where ID = new.room;   
    select capacity into @b from rooms where ID = new.room;   
	if (@a = @b) then 
    -- don't update
		SIGNAL SQLSTATE 'ERR0R';
    end if;
end$$
delimiter ;

drop trigger if exists adjustRooms;
delimiter $$
create trigger adjustRooms
after update on gallery_paintings
for each row
begin
	select number_of_paintings into @newroom from rooms where ID = new.room;
    select number_of_paintings into @oldroom from rooms where ID = old.room;
    
	if (new.room <> old.room) then
		update rooms
        set number_of_paintings = @newroom + 1
        where ID = new.room;
        
        SET @disable_trigger = 1;
        update rooms
        set number_of_paintings = @oldroom - 1
        where ID = old.room;
        SET @disable_triggers = 0;
    end if;
end$$
delimiter ;

drop trigger if exists checkIfCapacityOverLimit;
delimiter $$
create trigger checkIfCapacityOverLimit
before update on rooms
for each row
begin
	if (@disable_trigger != 1) then
	select number_of_paintings into @a from rooms where ID = new.ID;   
    select capacity into @b from rooms where ID = new.ID;   
    
	if (@a = @b) then
		-- don't update
		SIGNAL SQLSTATE 'ERR0R';
    end if;
    end if;
end$$
delimiter ;

-- TRIGGERY INSERT --

drop trigger if exists checkArtist;
delimiter $$
create trigger checkArtist
before insert on artists
for each row
begin
	if (new.year_of_birth > 2018) then
    -- don't insert
		SIGNAL SQLSTATE 'ERR0R';
    end if;
    
end$$
delimiter ;

drop trigger if exists checkInitialCapacity;
delimiter $$
create trigger checkInitialCapacity
before insert on rooms
for each row
begin
	if (new.capacity <= 0) then
    -- don't insert
		SIGNAL SQLSTATE 'ERR0R';
    end if;
    
end$$
delimiter ;

drop trigger if exists checkPainting;
delimiter $$
create trigger checkPainting
before insert on gallery_paintings
for each row
begin

	select year_of_birth into @birthdate from artists where artists.ID = new.artist;

	if (new.year_of_acquisition > 2018
    or new.year_of_creation > 2018
	or new.year_of_acquisition < new.year_of_creation
    or new.year_of_creation < @birthdate
    or new.year_of_creation > @birthdate + 100) then
    -- don't insert
		SIGNAL SQLSTATE 'ERR0R';
    end if;
    
end$$
delimiter ;

drop trigger if exists checkSoldPainting;
delimiter $$
create trigger checkSoldPainting
before insert on sold_paintings
for each row
begin

	select year_of_birth into @birthdate from artists where artists.ID = new.artist;

	if (new.selling_date > CURDATE()) then
    -- don't insert
			SIGNAL SQLSTATE 'ERR0R';
    end if;
    
end$$
delimiter ;

drop trigger if exists archiveSoldPainting;
delimiter $$
create trigger archiveSoldPainting
after insert on sold_paintings
for each row
begin
-- usun z gal_paint
	delete from gallery_paintings where title = new.title;
-- dodaj do buyer/sd	
    insert into `Buyer/SD` values (new.buyer, new.selling_date);
end$$
delimiter ;

drop trigger if exists adjustRooms2;
delimiter $$
create trigger adjustRooms2
after insert on gallery_paintings
for each row
begin
	select number_of_paintings into @newroom from rooms where ID = new.room;
    
		update rooms
        set number_of_paintings = @newroom + 1
        where ID = new.room;

end$$
delimiter ;