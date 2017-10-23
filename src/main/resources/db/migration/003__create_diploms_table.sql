CREATE TABLE "DIPLOMS" (
    "OWNER" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"NAME" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
	"FILE_PATH" VARCHAR2(256 BYTE) NOT NULL ENABLE, 
	
	CONSTRAINT "DIPLOMS_PK" PRIMARY KEY ("OWNER")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 
    CONSTRAINT "DIPLOMS_USERS_FK1" FOREIGN KEY ("OWNER")
	REFERENCES "USERS" ("LOGIN") ON DELETE CASCADE ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
 
 
