
-- Populating data for analytics schema

-- Populating Data Source
INSERT INTO analytics.analytics_data_source (id,type,version,created_on, updated_on, url_base, clazz_name) VALUES (1,'GROUP', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','http://localhost:8080/groups/api','com.libra.apollo.entity.Group');
INSERT INTO analytics.analytics_data_source (id,type,version,created_on, updated_on, url_base, clazz_name) VALUES (2,'PORTFOLIO', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','http://localhost:8080/portfolios/api','com.libra.apollo.entity.Portfolio');



-- Parameters
-- DECIMAL_PARAMETER
INSERT INTO analytics.analytics_query_parameter( id, type, version, created_on, updated_on, composition_type, data_field_type, operand) VALUES (1,'DATE_PARAMETER', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','STAMP_DATE', 'EQUAL');
INSERT INTO analytics.analytics_query_parameter( id, type, version, created_on, updated_on, composition_type, data_field_type, operand, decimal_value ) VALUES (2,'DECIMAL_PARAMETER', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','STAR_RATING', 'GREATER_THAN_OR_EQUAL','3.0');
INSERT INTO analytics.analytics_query_parameter( id, type, version, created_on, updated_on, composition_type, data_field_type, operand, decimal_value ) VALUES (3,'DECIMAL_PARAMETER', '0', '2017-12-01 00:00:00','2017-12-01 00:00:00','AND','STAR_RATING', 'LESS_THAN_OR_EQUAL','2.0');


-- Populating ID_PARAMETER

-- Populating apollo_analytics 
INSERT INTO analytics.analytics(id,version,created_on,updated_on, name,description,run_type,analytics_type) VALUES (1,'0', '2017-12-01 00:00:00','2017-12-01 00:00:00','Apollo Analyzer','Collection of defined Investment Styles', 'ON_DEMAND','APOLLO_ANALYZER');


-- Populating analytics_view 
INSERT INTO analytics.analytics_view(id,name, analytics_id,priority, version,created_on,updated_on) VALUES (1,'Apollo Analyzer',1, 1, '0', '2017-12-01 00:00:00','2017-12-01 00:00:00');


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
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(1,1,1,1); --WHERE_STAMP_DATE_EQUAL
INSERT INTO analytics.analytics_investment_style_parameter(id,investment_style_id, parameter_id,priority ) VALUES(2,1,2,2); --AND STAR_RATING GREATER_THAN_OR_EQUAL 3.0

-- Populating stock_indicators. Stoxx 600 extract for the 14.11.2017
INSERT INTO apollo.stockindicators (id,created_on,updated_on,version,instrument_id, stamp_date, star_rating, fair_value, fair_value_lower, fair_value_upper, intrinsic_value, intrinsic_value_pct, long_term_optimistic,long_term_pessimistic,long_term_neutral,discount_to_fair_value,median_discount_to_fair_value, discount_premium_to_fair_value, net_discount_median_fair_value, fair_value_change_1m, expected_return_2m, discount_premium_to_intrinsic_value, intrinsic_value_change_3m, fair_value_change_3m, intrinsic_value_change_1m ) 
values (1,'2017-11-01 00:00:00','2017-11-01 00:00:00','0','131121', '2017-12-01', '0.0000', '31.6012', '28.4264', '33.4066', '32.6237', '0.37626205541834734', '42.2965', '23.3895', '32.6315', '0.37626205541834734', '0.0145','0.05','0.1','0.2','0.2','0.5', '0.5', '0.5', '0.5' ) ;

