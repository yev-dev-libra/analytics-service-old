
select * from apollo.research_item where id = 3558;
select * from apollo.research_item order by id desc;
select * from signal_category;
select * from signal_category_properties;
select * from signal_source order by id desc;
select * from process_request order by id desc; 
select * from signals.`signal`;

-- delete from process_request where id < 75;
select * from `signal`;

select * from `signal` sig inner join signal_source ss on sig.signal_data_source_id = ss.id
where ss.source_id = 408412;

set foreign_key_checks=0;

select * from signal_source where object_source_id = 409561;

delete from signal_source where id > 0;
delete from process_request where id > 0;
delete from `signal` where id > 0;

select * from process_request pr join signal_source ss on pr.id = ss.process_request_id where pr.process_status = 'ONS_SEND_MESSAGE_FAIL';

-- 456 pr
-- 452

update process_request pr
	inner join signal_source ss 
    on pr.id = ss.process_request_id
set 
	pr.process_status = 'ONS_SEND_MESSAGE_SUCCESS'
    where ss.id = 452;
    
update signal_source ss 
	inner join process_request pr
    on ss.process_request_id = pr.id
set 
	pr.process_status = 'ONS_SEND_MESSAGE_FAIL'
    where ss.id = 452;



-- Support queries
set @process_status = 'ONS_SEND_MESSAGE_FAIL';

select 	ss.id 'signal_source_id', 
		sspr.request_type 'signal_source_request_type',
        sspr.process_status 'signal_source_request_status',
		ss.object_source_id 'object_id', 
        ss.object_source_clazz 'object_class_name', 
        ss.source_type 'source_type', 
        tss.id 'signal_trigger_id',
        sspr.request_type 'signal_trigger_request_type',
        sspr.process_status 'signal_trigger_request_status',
        tss.object_source_id 'parent_object_id',
        tss.object_source_clazz 'parent_object_class_name',
		tss.source_type 'parent_source_type',
        sc.type 'signal_type',
        sc.description
        
from signal_source ss 
	inner join process_request sspr on ss.process_request_id = sspr.id 
    left outer join signal_source tss on ss.parent_source_id = tss.id
    left outer join process_request tspr on tss.process_request_id = tspr.id
    left outer join `signal` s on ss.id = s.id
    left outer join signal_type st on s.type_id = s.id
    left outer join signal_category sc on s.signal_category_id = sc.id
    where sspr.process_status = 'ONS_SEND_MESSAGE_FAIL'; 