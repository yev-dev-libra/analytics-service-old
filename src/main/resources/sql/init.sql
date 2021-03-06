-- Populating apollo_analytics 
INSERT INTO analytics(id,version,created_on,updated_on, name,description,run_type,analytics_type) VALUES (1,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','Apollo Screener','Collection of defined Investment Styles', 'ON_DEMAND','APOLLO_SCREENER');


-- Populating analytics_view 
INSERT INTO analytics_view(id,name, description, analytics_id,priority, version,created_on,updated_on) VALUES (1,'Apollo Selection','Apollo Selection Styles',1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');

-- Populating investment_style 
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (1,'Apollo Classics', 'The Apollo Classics are those that we would be happy adding to at current positions, given the positive combination of value, growth, volatility and momentum.', 1, 1,1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (2,'Stocks on the Rocks', 'The Apollo SotR are companies that are currently unloved by the market, and in the absence or special situations or the reporting season, should underperform.', 2, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (3,'Value Buy', 'These are companies trading with a Value Indicator score of 20% or below, meaning that there is a Deep Value opportunity here,  with a upside downside ratio of 4:1.', 3, 1, 1, '0', '2017-11-02 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (4,'Value Sell', 'These are companies trading with a Value Indicator score of 80% or above meaning that the stock looks overpriced in relation to future value, with a downside upside ratio of 4:1.', 4, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (5,'Apollo Growth', 'These are stocks that are being re-rated by the market and the discount rate needing to be applied is low (or falling)', 5, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (6,'Apollo Value', 'These are stocks that are being de-rated by the market and the discount rate needing to be applied is high (or rising)', 6, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (7,'Below 12m Pessimistic', 'These are stocks trading at a discount to Apollo''s most pessimistic 12-month scenario.', 7, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');
INSERT INTO analytics_investment_style( id, name, description, priority, analytics_id, view_id,version,created_on,updated_on) VALUES (8,'Above 12m Optimistic', 'These are stocks trading at a premium to Apollo''s most optimistic 12-month scenario.', 8, 1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');

-- Query Parameters:

-- Apollo Classics
INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (1,'AND','STAR_RATING');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (1,'GREATER_THAN_OR_EQUAL','3.0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (2,'AND','FAIR_VALUE_CHANGE_1M');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (2,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (3,'AND','INTRINSIC_VALUE_CHANGE_1M');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (3,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (4,'AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (4,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (5,'AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (5,'LESS_THAN_OR_EQUAL','1');



-- Stocks on the Rocks
INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (6,'AND','STAR_RATING');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (6,'LESS_THAN_OR_EQUAL','2.0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (7,'AND','FAIR_VALUE_CHANGE_1M');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (7,'LESS_THAN','0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (8,'AND','INTRINSIC_VALUE_CHANGE_1M');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (8,'LESS_THAN','0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (9,'AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (9,'GREATER_THAN_OR_EQUAL','0');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (10,'AND','PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (10,'LESS_THAN_OR_EQUAL','0.5');

INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (11,'AND','INTRINSIC_VALUE_PCT');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (11,'GREATER_THAN','0.2');

-- Value Buy
INSERT INTO analytics_parameter( id,composition_type, data_field_type) VALUES (12,'AND','INTRINSIC_VALUE_PCT');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (12,'LESS_THAN_OR_EQUAL','0.2');

-- Value Sell
INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (13,'AND','INTRINSIC_VALUE_PCT');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (13,'GREATER_THAN_OR_EQUAL','0.8');

-- Apollo "Growth"
INSERT INTO analytics_parameter( id,composition_type, data_field_type) VALUES (14,'AND','MEDIAN_DISCOUNT_TO_FAIR_VALUE');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (14,'GREATER_THAN_OR_EQUAL','0');

-- Apollo "Value"
INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (15,'AND','MEDIAN_DISCOUNT_TO_FAIR_VALUE');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (15,'LESS_THAN','0');


-- Long term Pessimistic
INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (16,'AND','LONG_TERM_PESSIMISTIC');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (16,'LESS_THAN','0');

-- Long term Optimistic
INSERT INTO analytics_parameter( id, composition_type, data_field_type) VALUES (17,'AND','LONG_TERM_OPTIMISTIC');
INSERT INTO analytics_decimal_parameter( id, operand, decimal_value ) VALUES (17,'GREATER_THAN','0');



-- Populating investment_style parameters

-- Apollo Classics
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(1,1,1,1); --AND STAR_RATING GREATER_THAN_OR_EQUAL  >= 3.0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(2,1,2,2); --FAIR_VALUE_CHANGE_1M >= 0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(3,1,3,3); --INTRINSIC_VALUE_CHANGE_1M >= 0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(4,1,4,4); -- PCT_IN_FAIR_VALUE_RANGE > 0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(5,1,5,5); -- PCT_IN_FAIR_VALUE_RANGE < 1

-- Stocks on the rock
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(6,2,6,1); --AND STAR_RATING <= 2 
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(7,2,7,2); --AND FAIR_VALUE_CHANGE_1M < 0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(8,2,8,3); --AND INTRINSIC_VALUE_CHANGE_1M < 0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(9,2,9,4); --AND PCT_IN_FAIR_VALUE_RANGE >= 0
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(10,2,10,5); --AND PCT_IN_FAIR_VALUE_RANGE <= 0.5
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(11,2,11,6); --AND INTRINSIC_VALUE_PCT >= 0.2

-- Value Buy
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(12,3,12,1); --AND INTRINSIC_VALUE_PCT  <= 0.2

-- Value Sell
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(13,4,13,1); --AND INTRINSIC_VALUE_PCT  >=  0.8

-- Value Growth
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(14,5,14,1); --AND MEDIAN_DISCOUNT_TO_FAIR_VALUE >= 0

-- Apollo "Value"
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(15,6,15,1); --AND MEDIAN_DISCOUNT_TO_FAIR_VALUE < 0

-- Long term Pessimistic
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(16,7,16,1); --AND LONG_TERM_PESSIMISTIC < 0

-- Long term Optimistic
INSERT INTO analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(17,8,17,1); --AND LONG_TERM_OPTIMISTIC > 0

-- Populating investment_style_field_parameter

-- Field Parameters:
-- TODO: add net discount to fv, value indicator scrore
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (1,'STAR_RATING');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (2,'FAIR_VALUE_CHANGE_1M');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (3,'FAIR_VALUE_CHANGE_3M');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (4,'INTRINSIC_VALUE_CHANGE_1M');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (5,'INTRINSIC_VALUE_CHANGE_3M');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (6,'PCT_IN_FAIR_VALUE_RANGE');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (7,'MEDIAN_DISCOUNT_TO_FAIR_VALUE');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (8,'DISCOUNT_PREMIUM_TO_FAIR_VALUE');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (9,'NET_DISCOUNT_MEDIAN_FAIR_VALUE');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (10,'EXPECTED_RETURN_2M');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (11,'DISCOUNT_PREMIUM_TO_INTRINSIC_VALUE');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (12,'LONG_TERM_PESSIMISTIC');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (13,'LONG_TERM_OPTIMISTIC');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (14,'STOCK_ID');
INSERT INTO analytics_field_parameter( id, data_field_type) VALUES (15,'STAMP_DATE');

-- Apollo Classics

INSERT INTO analytics_investment_style_field_parameter(id,investment_style_id, field_parameter_id,priority ) VALUES(1,1,14,1); --STAR_RATING
INSERT INTO analytics_investment_style_field_parameter(id,investment_style_id, field_parameter_id,priority ) VALUES(2,1,15,2); --STAR_RATING
INSERT INTO analytics_investment_style_field_parameter(id,investment_style_id, field_parameter_id,priority ) VALUES(3,1,1,3); --STAR_RATING
INSERT INTO analytics_investment_style_field_parameter(id,investment_style_id, field_parameter_id,priority ) VALUES(4,1,2,4); --FAIR_VALUE_CHANGE_1M
INSERT INTO analytics_investment_style_field_parameter(id,investment_style_id, field_parameter_id,priority ) VALUES(5,1,5,5); --INTRINSIC_VALUE_CHANGE_3M
INSERT INTO analytics_investment_style_field_parameter(id,investment_style_id, field_parameter_id,priority ) VALUES(6,1,6,6); --PCT_IN_FAIR_VALUE_RANGE

