drop table   audit_info;
  CREATE TABLE audit_info (id varchar(100) NOT NULL, 
  source_obj_id varchar(50), source_obj_name varchar(50), 
  created_by varchar(50), last_modified_by varchar(50), 
  created_date varchar(50), 
  last_modified_date varchar(50), 
  diff_log varchar(50), 
  time_stamp DATE, source_obj JSONB, 
  CONSTRAINT PK300 PRIMARY KEY (id));
