


-- Populating data for analytics schema

-- Populating OPERATION conditions
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (1,'OPERATION','AND', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (2,'OPERATION','OR', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (3,'OPERATION','GREATER_THAN', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (4,'OPERATION','LESS_THAN', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (5,'OPERATION','EQUAL', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (6,'OPERATION','NOT_EQUAL', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (7,'OPERATION','GREATER_THAN_OR_EQUAL', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (8,'OPERATION','LESS_THAN_OR_EQUAL', '0', '2017-11-01','2017-11-01');

-- Populating SORT_DIR conditions
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (9,'SORT_DIR','ASC', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (10,'SORT_DIR','DESC', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (11,'SORT_DIR','TOP_DECILE', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (12,'SORT_DIR','BOTTOM_DECILE', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (13,'SORT_DIR','TOP_BOTTOM_DECILE', '0', '2017-11-01','2017-11-01');

insert into conditions( id, kind, data_field_type, parameter,version,created_on,last_updated_on) values (14,'PARAMETER','FAIR_VALUE', 0.2, '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, data_field_type, parameter,version,created_on,last_updated_on) values (15,'PARAMETER','INTRINSIC_VALUE',0.2, '0', '2017-11-01','2017-11-01');

-- Populating apollo_analytics 
insert into apollo_analytics(id,code,name,description,run_type,analytics_type,version,created_on,last_updated_on) values (1,'AS','Apollo Screener','Collection of defined Investment Styles', 'ON_DEMAND','APOLLO_SCREENER', '0', '2017-11-01','2017-11-01');


-- Populating analytics_template 
insert into analytics_template(id, code,name, analytics_id,version,created_on,last_updated_on) values (1,'AC','ACUS',1, '0', '2017-11-01','2017-11-01');


-- Populating investment_style 
insert into investment_style( id, code, name, description, priority, template_id, analytics_id, version,created_on,last_updated_on) values (1,'VL','Value Indicator', '', 1, 1, '0', '2017-11-01','2017-11-01');


-- Populating investment_style_condition

