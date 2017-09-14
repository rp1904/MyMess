#!/bin/bash
echo "Welcome to RP_WORLD"
echo "Please chose option"
echo "1. Stop Tomcat"
echo "2. Start Tomcat"
echo "3. Check Tomcat Status"
echo "4. Logs"
echo "5. Check webapps folder"
echo "99. Remove old war"
read option
if [ $option = 1 ]
        then sudo systemctl stop tomcat
elif [ $option = 2 ]
        then sudo systemctl start tomcat
elif [ $option = 3 ]
		then sudo systemctl status tomcat
elif [ $option = 4 ]
        then tail -f opt/tomcat/logs/catalina.out
elif [ $option = 5 ]
        then ls opt/tomcat/webapps
elif [ $option = 99 ]
        then rm -rf opt/tomcat/webapps/bhojan opt/tomcat/webapps/bhojan.war 
else echo "Not an option!"
fi
#tail -f opt/tomcat/logs/catalina.out
