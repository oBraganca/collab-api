#!/bin/bash

echo ==================== BUILDING CONFIG SERVER ====================
cd ./config-server
./mvnw clean install
docker build -t obraganca/config-server:1.0 .
cd ..

echo ""

echo ==================== BUILDING EUREKA SERVER ==================== 
cd ./eureka-server
./mvnw clean install
docker build -t obraganca/eureka-server:1.0 . 
cd ..

echo ""

echo ==================== BUILDING API GATEWAY ====================
cd ./api-gateway
./mvnw clean install
docker build -t obraganca/api-gateway:1.0 .
cd ..

echo ""

echo ==================== BUILDING USER SERVICE ====================
cd ./user-service
./mvnw clean install
docker build -t obraganca/user-service:1.0 . 
cd ..