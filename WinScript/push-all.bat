@echo off

REM PUSHING CONFIG SERVER
echo ==================== PUSHING CONFIG SERVER ====================
docker push obraganca/config-server:1.0
echo.

REM PUSHING EUREKA SERVER
echo ==================== PUSHING EUREKA SERVER ====================
docker push obraganca/eureka-server:1.0
echo.

REM PUSHING API GATEWAY
echo ==================== PUSHING API GATEWAY ====================
docker push obraganca/api-gateway:1.0
echo.

REM PUSHING USER SERVICE
echo ==================== PUSHING USER SERVICE ====================
docker push obraganca/user-service:1.0
echo.

REM PUSHING EMAIL SERVICE
echo ==================== PUSHING EMAIL SERVICE ====================
docker push obraganca/email-service:1.0
