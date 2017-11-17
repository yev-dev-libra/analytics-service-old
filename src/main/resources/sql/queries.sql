select si.* 
from stockindicators si inner join stocks s on si.instrument_id = s.id 
inner join stocks_groups sg on s.id = sg.stock_id
where sg.group_id = 216
and si.stamp_date = '2017-11-14' ;


select si.instrument_id, stocks.sedol, stocks.name, si.id as stockindicators_id , si.stamp_date
from stockindicators si inner join stocks s on si.instrument_id = s.id 
inner join stocks_groups sg on s.id = sg.stock_id
inner join stocks on sg.stock_id = stocks.id
where sg.group_id = 216
and si.stamp_date = '2017-11-14' ;