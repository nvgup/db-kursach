declare
   c int;
begin

   select count(*) into c from user_tables where table_name ='EVALUATIONS';
   if c = 1 then
      execute immediate 'drop table EVALUATIONS';
   end if;		
	
   select count(*) into c from user_tables where table_name ='DIPLOMS';
   if c = 1 then
      execute immediate 'drop table DIPLOMS';
   end if;	
	
   select count(*) into c from user_tables where table_name ='USERS';
   if c = 1 then
      execute immediate 'drop table USERS';
   end if;	
   
   select count(*) into c from user_tables where table_name ='GROUPS';
   if c = 1 then
      execute immediate 'drop table GROUPS';
   end if;
   
   select count(*) into c from user_tables where table_name ='CRITERIAS';
   if c = 1 then
      execute immediate 'drop table CRITERIAS';
   end if;
   
end;