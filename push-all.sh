#!/bin/bash

echo ==================== PUSHING CONFIG SERVER ====================
echo \n\n
docker push obraganca/config-server:1.0

echo ""

echo ==================== PUSHING EUREKA SERVER ====================
docker push obraganca/eureka-server:1.0

echo ""

echo ==================== PUSHING API GATEWAY ====================
docker push obraganca/api-gateway:1.0

echo ""

echo ==================== PUSHING USER SERVICE ====================
docker push obraganca/user-service:1.0

echo ""

echo ==================== PUSHING EMAIL SERVICE ====================
docker push obraganca/email-service:1.0