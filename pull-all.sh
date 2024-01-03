#!/bin/bash


#!/bin/bash

echo ==================== PULLING CONFIG SERVER ====================
docker pull obraganca/config-server:1.0

echo ""

echo ==================== PULLING EUREKA SERVER ====================
docker pull obraganca/eureka-server:1.0

echo ==================== PULLING API GATEWAY ====================
docker pull obraganca/api-gateway:1.0

echo ""

echo ==================== PULLING USER SERVICE ====================
docker pull obraganca/user-service:1.0

echo ""

echo ==================== PULLING EMAIL SERVICE ====================
docker pull obraganca/email-service:1.0
