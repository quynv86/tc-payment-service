liquibase \
  --changeLogFile=tcChangeLog.xml \
  --url=jdbc:mariadb://localhost:3306/tc_payment_payment_service \
  --username=root \
  --password=123456 \
  update


