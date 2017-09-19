INSERT INTO users
(user_id_pk,user_id,user_profile_id_fk,user_role_id_fk,mess_id_fk,email,mobile_number,password,is_enable,
accout_non_expired,credentials_non_expired,accout_non_locker,created_by,created_at,updated_by,updated_at)
VALUES ("1","1",null,"1",null,"roshanpatil1904@gmail.com","8308238755","123456",true,true,true,true,"SELF",null,"SELF",null);



UPDATE members_mealcoupens SET is_next_meal_added = FALSE;