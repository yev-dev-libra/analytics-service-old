select si.* 
from stockindicators si inner join stocks s on si.instrument_id = s.id 
inner join stocks_groups sg on s.id = sg.stock_id
where sg.group_id = 216
and si.stamp_date = '2017-11-14' ;