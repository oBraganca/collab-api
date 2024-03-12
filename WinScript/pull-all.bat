@echo off

REM PULLING CONFIG SERVER
echo ==================== PULLING CONFIG SERVER ====================
docker pull obraganca/config-server:1.0
echo.

REM PULLING EUREKA SERVER
echo ==================== PULLING EUREKA SERVER ====================
docker pull obraganca/eureka-server:1.0
echo.

REM PULLING API GATEWAY
echo ==================== PULLING API GATEWAY ====================
docker pull obraganca/api-gateway:1.0
echo.

REM PULLING USER SERVICE
echo ==================== PULLING USER SERVICE ====================
docker pull obraganca/user-service:1.0
echo.

REM PULLING EMAIL SERVICE
echo ==================== PULLING EMAIL SERVICE ====================
docker pull obraganca/email-service:1.0
