


-- Populating data for analytics schema

-- Populating Data Source
insert into data_source (id, data_source_type,url_base,clazz_name,version,created_on,last_updated_on) values (1,'GROUP','http://localhost:8080/groups/api','com.libra.apollo.entity.Group', '0', '2017-11-01','2017-11-01');
insert into data_source (id, data_source_type,url_base,clazz_name,version,created_on,last_updated_on) values (2,'PORTFOLIO','http://localhost:8080/portfolios/api','com.libra.apollo.entity.Portfolio', '0', '2017-11-01','2017-11-01');

-- Populating OPERATION conditions
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (1,'OPERATION','WHERE', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (2,'OPERATION','AND', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (3,'OPERATION','OR', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (4,'OPERATION','GREATER_THAN', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (5,'OPERATION','LESS_THAN', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (6,'OPERATION','EQUAL', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (7,'OPERATION','NOT_EQUAL', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (8,'OPERATION','GREATER_THAN_OR_EQUAL', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, operator,version,created_on,last_updated_on) values (9,'OPERATION','LESS_THAN_OR_EQUAL', '0', '2017-11-01','2017-11-01');

-- Populating SORT_DIR conditions
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (10,'SORT_DIR','ASC', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (11,'SORT_DIR','DESC', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (12,'SORT_DIR','TOP_DECILE', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (13,'SORT_DIR','BOTTOM_DECILE', '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, sort_direction,version,created_on,last_updated_on) values (14,'SORT_DIR','TOP_BOTTOM_DECILE', '0', '2017-11-01','2017-11-01');

insert into conditions( id, kind, data_field_type, parameter,version,created_on,last_updated_on) values (15,'PARAMETER','FAIR_VALUE', 0.2, '0', '2017-11-01','2017-11-01');
insert into conditions( id, kind, data_field_type, parameter,version,created_on,last_updated_on) values (16,'PARAMETER','INTRINSIC_VALUE',0.2, '0', '2017-11-01','2017-11-01');

-- Populating apollo_analytics 
insert into apollo_analytics(id,name,description,run_type,analytics_type,version,created_on,last_updated_on) values (1,'Apollo Screener','Collection of defined Investment Styles', 'ON_DEMAND','APOLLO_SCREENER', '0', '2017-11-01','2017-11-01');


-- Populating analytics_template 
insert into analytics_template(id,name, analytics_id,version,created_on,last_updated_on) values (1,'ACUS',1, '0', '2017-11-01','2017-11-01');


-- Populating investment_style 
insert into investment_style( id, name, description, priority, template_id, analytics_id, version,created_on,last_updated_on) values (1,'Value Indicator', 'Simple Test condition to check VI request', 1, 1,1, '0', '2017-11-01','2017-11-01');
insert into investment_style( id, name, description, priority, template_id, analytics_id, version,created_on,last_updated_on) values (2,'Intrinsic Value', 'Simple Test condition to check IV request', 1, 1,1, '0', '2017-11-01','2017-11-01');


-- Populating investment_style_condition
insert into investment_style_condition(id,investment_style_id, condition_id,priority ) values(1,1,1,1); -- where
insert into investment_style_condition(id,investment_style_id, condition_id,priority ) values(2,1,15,2); -- parameter FV
insert into investment_style_condition(id,investment_style_id, condition_id,priority ) values(3,1,6,3); -- equals


-- Populating investment_style_condition_datasource

insert into investment_style_datasource(id,data_source_id,investment_style_id,version,created_on,last_updated_on) values(216,1,1,'0', '2017-11-01','2017-11-01');

