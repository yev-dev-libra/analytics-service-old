
-- Base Query 


select 
	si.id,
	si.instrument_id,
	stamp_date,
	star_rating, 
	fair_value, 
	fair_value_lower, 
	fair_value_upper, 
	intrinsic_value, 
	intrinsic_value_pct, 
	long_term_optimistic,
	long_term_pessimistic,
	long_term_neutral,
	median_discount_to_fair_value, 
	value_indicator_score, 
	discount_premium_to_fair_value, 
	net_discount_median_fair_value, 
	fair_value_change_1m, 
	expected_return_2m, 
	discount_premium_to_intrinsic_value, 
	intrinsic_value_change_3m, 
	fair_value_change_3m, 
	intrinsic_value_change_1m 
from stockindicators si inner join stocks s on si.instrument_id = s.id 
inner join stocks_groups sg on s.id = sg.stock_id
where sg.group_id = 216
and si.stamp_date = '2017-11-14' ;

---------------------------------------------- Apollo Classics ------------------------------------

select 
	si.instrument_id,
	si.stamp_date,
	si.star_rating, 
	si.fair_value, 
	si.fair_value_lower, 
	si.fair_value_upper, 
	si.intrinsic_value, 
	si.intrinsic_value_pct, 
	si.long_term_optimistic,
	si.long_term_pessimistic,
	si.long_term_neutral,
	si.median_discount_to_fair_value, 
	si.value_indicator_score, 
	si.discount_premium_to_fair_value, 
	si.net_discount_median_fair_value, 
	si.fair_value_change_1m, 
	si.expected_return_2m, 
	si.discount_premium_to_intrinsic_value, 
	si.intrinsic_value_change_3m, 
	si.fair_value_change_3m, 
	si.intrinsic_value_change_1m 
from stockindicators si inner join stocks s on si.instrument_id = s.id 
inner join stocks_groups sg on s.id = sg.stock_id
where sg.group_id = 216
and si.stamp_date = '2017-11-14'
and si.star_rating>=3.00
and si.fair_value_change_1m>=0.00 
and si.intrinsic_value_change_1m>=0.00 
and si.pct_in_fair_value_range>=0.00 
and si.pct_in_fair_value_range<1.00 
;


select 
	max(si.stamp_date),
	si.instrument_id,
	si.stamp_date,
	si.star_rating, 
	si.fair_value, 
	si.fair_value_lower, 
	si.fair_value_upper, 
	si.intrinsic_value, 
	si.intrinsic_value_pct, 
	si.long_term_optimistic,
	si.long_term_pessimistic,
	si.long_term_neutral,
	si.median_discount_to_fair_value, 
	si.value_indicator_score, 
	si.discount_premium_to_fair_value, 
	si.net_discount_median_fair_value, 
	si.fair_value_change_1m, 
	si.expected_return_2m, 
	si.discount_premium_to_intrinsic_value, 
	si.intrinsic_value_change_3m, 
	si.fair_value_change_3m, 
	si.intrinsic_value_change_1m 
from stockindicators si
where si.instrument_id in (131123, 131236)
and si.star_rating>=3.00
and si.fair_value_change_1m>=0.00 
and si.intrinsic_value_change_1m>=0.00 
and si.pct_in_fair_value_range>=0.00 
and si.pct_in_fair_value_range<1.00 
group by si.instrument_id
