#!/bin/bash

echo ==================== BUILDING CONFIG SERVER ====================
cd ./config-server
if ./mvnw clean install; then
  docker build -t obraganca/config-server:1.0 .
else
  echo "O comando mvnw falhou. O docker build não será executado."
fi
cd ..

echo ""

echo ==================== BUILDING EUREKA SERVER ==================== 
cd ./eureka-server
if ./mvnw clean install; then
  docker build -t obraganca/eureka-server:1.0 . 
else
  echo "O comando mvnw falhou. O docker build não será executado."
fi
cd ..

echo ""

echo ==================== BUILDING API GATEWAY ====================
cd ./api-gateway
if ./mvnw clean install; then
  docker build -t obraganca/api-gateway:1.0 .
else
  echo "O comando mvnw falhou. O docker build não será executado."
fi
cd ..

echo ""

echo ==================== BUILDING USER SERVICE ====================
cd ./user-service
if ./mvnw clean install; then
  docker build -t obraganca/user-service:1.0 . 
else
  echo "O comando mvnw falhou. O docker build não será executado."
fi
cd ..

echo ==================== BUILDING EMAIL SERVICE ====================
cd ./email-service
if ./mvnw clean install; then
  docker build -t obraganca/email-service:1.0 . 
else
  echo "O comando mvnw falhou. O docker build não será executado."
fi
cd ..

