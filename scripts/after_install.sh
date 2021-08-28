echo '================='
echo 'Running set_owner'
echo '================='

chown -R ec2-user /var/www/minipetpee-backend

cd /var/www/minipetpee-backend

gradle build

nohup java -jar /var/www/minipetpee-backend/build/libs/minipetpee-0.0.1-SNAPSHOT.jar 1> minipetpee.out 2> minipetpee.err &