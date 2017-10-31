
-- Populating data for analytics schema

-- Populating OPERATION conditions
insert into analytics.conditions( id, kind, operator) values (1,'OPERATION','AND');
insert into analytics.conditions( id, kind, operator) values (2,'OPERATION','OR');
insert into analytics.conditions( id, kind, operator) values (3,'OPERATION','GREATER_THAN');
insert into analytics.conditions( id, kind, operator) values (4,'OPERATION','LESS_THAN');
insert into analytics.conditions( id, kind, operator) values (5,'OPERATION','EQUAL');
insert into analytics.conditions( id, kind, operator) values (6,'OPERATION','NOT_EQUAL');
insert into analytics.conditions( id, kind, operator) values (7,'OPERATION','GREATER_THAN_OR_EQUAL');
insert into analytics.conditions( id, kind, operator) values (8,'OPERATION','LESS_THAN_OR_EQUAL');

-- Populating SORT_DIR conditions
insert into analytics.conditions( id, kind, sort_direction) values (9,'SORT_DIR','ASC');
insert into analytics.conditions( id, kind, sort_direction) values (10,'SORT_DIR','DESC');
insert into analytics.conditions( id, kind, sort_direction) values (11,'SORT_DIR','TOP_DECILE');
insert into analytics.conditions( id, kind, sort_direction) values (12,'SORT_DIR','BOTTOM_DECILE');
insert into analytics.conditions( id, kind, sort_direction) values (13,'SORT_DIR','TOP_BOTTOM_DECILE');

insert into analytics.conditions( id, kind, data_field_type, parameter) values (14,'PARAMETER','FAIR_VALUE', 0.2);
insert into analytics.conditions( id, kind, data_field_type, parameter) values (15,'PARAMETER','INTRINSIC_VALUE',0.2);


-- Populating apollo_analytic 
insert into analytics.apollo_analytics(id,description,name,run_type,analytics_type) values (1,'test', 'test','ON_DEMAND','BASIC_SCREENER');

-- Populating analytics_template 
insert into analytics.analytics_template(id, analytics_id) values (1,1);

-- Populating investment_style 
insert into analytics.investment_style( id, code, name, description, priority, template_id) values (1,'TEST','TEST Name', 'TEST Desc', 1, 1);
