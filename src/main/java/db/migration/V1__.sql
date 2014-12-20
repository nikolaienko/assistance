create table users
(
	user_id bigserial primary key,
	login varchar(40) not null UNIQUE,
	password bigint not null,
	fullName text not null,
	info text
);


 insert into users values(default, 'test', 3556498, 'Test Test Test');

create table types(
	type_id bigserial primary key,
	value varchar(40)
);
 insert into types values(default, 'auto');
 insert into types values(default, 'moto');
 insert into types values(default, 'bike'); 	
 insert into types values(default, 'other');

create table makes(
	make_id bigserial primary key,
	value varchar(40) not null
);

insert into makes values(default, 'bmw');
insert into makes values(default, 'tesla');
insert into makes values(default, 'ktm'); 	
insert into makes values(default, 'ducaty');
insert into makes values(default, 'other');

create table models(
	model_id bigserial primary key,
	make_id bigint not null,
	value varchar(40) not null,
	FOREIGN KEY(make_id) REFERENCES makes(make_id) ON DELETE CASCADE
);

create table accident_types(
	accident_type_id bigserial primary key,
	value varchar(40) not null
); 

insert into accident_types values(default, 'car accident');
insert into accident_types values(default, 'broken tires');
insert into accident_types values(default, 'no enough flue'); 	
insert into accident_types values(default, 'other');

create table vehicles(
	vehicle_id bigserial primary key,
	type_id bigint not null REFERENCES types(type_id) ON DELETE SET null,
	user_id bigint not null REFERENCES users(user_id) ON DELETE CASCADE,
	model_id bigint not null REFERENCES models(model_id) ON DELETE set null
); 

create table devices(
	device_id bigserial primary key,
	user_id bigint not null REFERENCES users(user_id) ON DELETE CASCADE,
	current_coords text,
	isAlive boolean not null default FALSE
);

create table accidents(
	accident_id bigserial primary key,
	user_id bigint not null REFERENCES users(user_id) ON DELETE set null,
	accident_type_id bigint not null REFERENCES accident_types(accident_type_id) ON DELETE SET null,
	vehicle_id bigint not null REFERENCES vehicles(vehicle_id) ON DELETE set null,
	device_id bigint not null REFERENCES devices(device_id) ON DELETE set null,
	coords text not null,
	resolve_status boolean not null default FALSE
);