
-- Populating data for analytics schema

-- Populating Data Source
INSERT INTO analytics.analytics_data_source (id,type,version,created_on, updated_on, url_base, clazz_name) VALUES (1,'GROUP', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','http://localhost:8080/groups/api','com.libra.apollo.entity.Group');
INSERT INTO analytics.analytics_data_source (id,type,version,created_on, updated_on, url_base, clazz_name) VALUES (2,'PORTFOLIO', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','http://localhost:8080/portfolios/api','com.libra.apollo.entity.Portfolio');



-- Parameters

-- Apollo Classics
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (1,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','STAR_RATING');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (1,'GREATER_THAN_OR_EQUAL','3.0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (2,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','FAIR_VALUE_CHANGE_1M');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (2,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (3,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','INTRINSIC_VALUE_CHANGE_1M');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (3,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (4,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (4,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (5,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (5,'LESS_THAN_OR_EQUAL','1');


-- Stocks on the Rocks
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (6,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','STAR_RATING');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (6,'LESS_THAN_OR_EQUAL','2.0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (7,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','FAIR_VALUE_CHANGE_1M');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (7,'LESS_THAN','0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (8,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','INTRINSIC_VALUE_CHANGE_1M');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (8,'LESS_THAN','0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (9,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (9,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (10,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (10,'LESS_THAN_OR_EQUAL','0.5');

INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (11,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','INTRINSIC_VALUE_PCT');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (11,'GREATER_THAN','0.2');

-- Value Buy
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (12,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','INTRINSIC_VALUE_PCT');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (12,'LESS_THAN_OR_EQUAL','0.2');

-- Value Sell
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (13,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','INTRINSIC_VALUE_PCT');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (13,'GREATER_THAN_OR_EQUAL','0.8');

-- Apollo "Growth"
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (14,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','MEDIAN_DISCOUNT_TO_FAIR_VALUE');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (14,'GREATER_THAN_OR_EQUAL','0');

-- Apollo "Value"
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (15,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','MEDIAN_DISCOUNT_TO_FAIR_VALUE');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (15,'LESS_THAN','0');


-- Long term Pessimistic
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (16,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','LONG_TERM_PESSIMISTIC');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (16,'LESS_THAN','0');

-- Long term Optimistic
INSERT INTO analytics.analytics_parameter( id, version, created_on, updated_on, composition_type, data_field_type) VALUES (17,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','LONG_TERM_OPTIMISTIC');
INSERT INTO analytics.decimal_parameter( id, operand, decimal_value ) VALUES (17,'GREATER_THAN','0');

-- Populating apollo_analytics 
INSERT INTO analytics.analytics(id,version,created_on,updated_on, name,description,run_type,analytics_type) VALUES (1,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','Apollo Screener','Collection of defined Investment Styles', 'ON_DEMAND','APOLLO_SCREENER');


-- Populating analytics_view 
INSERT INTO analytics.analytics_view(id,name, description, analytics_id,priority, version,created_on,updated_on) VALUES (1,'Apollo Selection','Apollo Selection Styles',1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');


-- Populating investment_style 
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (1,'Apollo Classics', 'The Apollo Classics are those that we would be happy adding to at current positions, given the positive combination of value, growth, volatility and momentum.', 1, 1,1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (2,'Stocks on the Rocks', 'The Apollo SotR are companies that are currently unloved by the market, and in the absence or special situations or the reporting season, should underperform.', 1, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (3,'Value Buy', 'These are companies trading with a Value Indicator score of 20% or below, meaning that there is a Deep Value opportunity here,  with a upside downside ratio of 4:1.', 1, 1, 1, '0', '2017-11-02 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (4,'Value Sell', 'These are companies trading with a Value Indicator score of 80% or above meaning that the stock looks overpriced in relation to future value, with a downside upside ratio of 4:1.', 1, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (5,'Apollo Growth', 'These are stocks that are being re-rated by the market and the discount rate needing to be applied is low (or falling)', 1, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (6,'Apollo Value', 'These are stocks that are being de-rated by the market and the discount rate needing to be applied is high (or rising)', 1, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (7,'Below 12m Pessimistic', 'These are stocks trading at a discount to Apollo''s most pessimistic 12-month scenario.', 1, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics.analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (8,'Above 12m Optimistic', 'These are stocks trading at a premium to Apollo''s most optimistic 12-month scenario.', 1, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');


-- Populating investment_style parameters

-- Apollo Classics
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(1,1,1,1); --AND STAR_RATING GREATER_THAN_OR_EQUAL  >= 3.0
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(2,1,2,2); --FAIR_VALUE_CHANGE_1M >= 0
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(3,1,3,3); --INTRINSIC_VALUE_CHANGE_1M >= 0
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(4,1,4,4); -- PCT_IN_FAIR_VALUE_RANGE > 0
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(5,1,5,5); -- PCT_IN_FAIR_VALUE_RANGE < 1

-- Populating stock_indicators. Stoxx 600 extract for the 2017-12-01

-- Apollo Classics
INSERT INTO apollo.stockindicators (
	id,
	version,
	created_on,
	updated_on,
	instrument_id, 
	stamp_date, 
	star_rating,
	fair_value_change_1m,
	intrinsic_value_change_1m,
	pct_in_fair_value_range
) 
values (
	1,
	'0',
	'2017-11-01 00:00:00',
	'2017-11-01 00:00:00',
	'1', -- instrument_id
	'2017-12-01', 
	'3.0000', -- star_rating
	'0.1',  -- fair_value_change_1m
	'0.1', -- intrinsic_value_change_1m
	'0.8166' -- pct_in_fair_value_range
) ;
-- Apollo Classics
INSERT INTO apollo.stockindicators (
	id,
	version,
	created_on,
	updated_on,
	instrument_id, 
	stamp_date, 
	star_rating,
	fair_value_change_1m,
	intrinsic_value_change_1m,
	pct_in_fair_value_range
) 
values (
	2,
	'0',
	'2017-11-01 00:00:00',
	'2017-11-01 00:00:00',
	'2', -- instrument_id
	'2017-12-02', 
	'3.0000', -- star_rating
	'0.1',  -- fair_value_change_1m
	'0.1', -- intrinsic_value_change_1m
	'0.8166' -- pct_in_fair_value_range
) ;

