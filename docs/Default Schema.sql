-- User Roles -------------------------------------------------------------------------------------------

INSERT INTO user_roles (user_role_id,role) 
VALUES (1,'ROLE_SUPERADMIN'),
	   (2,'ROLE_MESS'),
	   (3,'ROLE_MEMBER');
	   
-- Default User ------------------------------------------------------------------------------------------
	   
INSERT INTO users
(user_id_pk,user_id,user_profile_id_fk,user_role_id_fk,mess_id_fk,email,mobile_number,password,is_enable,
accout_non_expired,credentials_non_expired,accout_non_locker,created_by,created_at,updated_by,updated_at)
VALUES ('1','1',null,'1',null,'roshanpatil1904@gmail.com','8308238755','123456',true,true,true,true,'SELF',null,'SELF',null);

-- App Versions ------------------------------------------------------------------------------------------

INSERT INTO 
	app_versions (id,app_link,app_type,created_at,created_by,updated_at,updated_by,device_type,is_mandatory,release_date,release_note,version) 
VALUES
 (1,'http://www.mybhojan.com/resources/Bhojan-Mess.apk','MESS','2017-01-01 00:00:00','1','2017-01-01 00:00:00','1','ANDROID',TRUE,'2017-01-01 00:00:00','Mess Base App','0.0.1'),
 (2,'http://www.mybhojan.com/resources/Bhojan.apk','MEMBER','2017-01-01 00:00:00','1','2017-01-01 00:00:00','1','ANDROID',TRUE,'2017-01-01 00:00:00','Member Base App','0.0.1');