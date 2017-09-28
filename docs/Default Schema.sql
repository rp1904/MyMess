INSERT INTO `bhojan_demo`.`app_versions` (`id`,`app_link`,`app_type`,`created_at`,`created_by`,`updated_at`,`updated_by`,`device_type`,`is_mandatory`,`release_date`,`release_note`,`version`) VALUES 
 (1,'http://www.mybhojan.com/resources/Bhojan-Mess.apk','MESS','2017-01-01 00:00:00','1','2017-01-01 00:00:00','1','ANDROID','','2017-01-01 00:00:00','Mess Base App','0.0.1'),
 (2,'http://www.mybhojan.com/resources/Bhojan-Member.apk','MEMBER','2017-01-01 00:00:00','1','2017-01-01 00:00:00','1','ANDROID','','2017-01-01 00:00:00','Member Base App','0.0.1'),
 (3,'http://www.mybhojan.com/resources/Bhojan-Mess.apk','MESS','2017-08-06 21:37:41','1','2017-08-06 21:37:41','1','ANDROID','','2017-08-06 21:37:41','Bug Fixes','0.0.2');
INSERT INTO `bhojan_demo`.`user_roles` (`user_role_id`,`role`) VALUES 
 (2,'ROLE_ADMIN'),
 (3,'ROLE_MEMBER'),
 (1,'ROLE_SUPERADMIN');
 INSERT INTO `bhojan_demo`.`users` (`user_id_pk`,`accout_non_expired`,`accout_non_locker`,`created_at`,`created_by`,`updated_at`,`updated_by`,`credentials_non_expired`,`email`,`is_enable`,`mobile_number`,`password`,`user_id`,`mess_id_fk`,`user_profile_id_fk`,`user_role_id_fk`) VALUES  
 ('1','','',NULL,'SELF',NULL,'SELF','','roshanpatil19040@gmail.com','','8308238755','$2a$10$e5KVwe1U1q0Pj/ZU5h6fpOXdInIhQ/0gYcTsSTIqLpuoYgo0dbsju','1',NULL,NULL,1);
 
 INSERT INTO `bhojan_demo`.`admin_settings` (`admin_setting_id`,`default_payable_amount`,`free_trial_days`,`created_at`,`created_by`,`updated_at`,`updated_by`) VALUES (1, 100, 15, '2017-01-01 00:00:00', '1', '2017-01-01 00:00:00', '1');
 
 UPDATE users set password='$2a$10$e5KVwe1U1q0Pj/ZU5h6fpOXdInIhQ/0gYcTsSTIqLpuoYgo0dbsju';