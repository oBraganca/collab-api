
REM Change directory to the parent directory

echo ==================== BUILDING CONFIG SERVER ====================
cd ../config-server
call 
call .\mvnw.cmd clean install
if %errorlevel% equ 0 (
  docker build -t obraganca/config-server:1.0 .
) else (
  echo mvnw command failed. Docker build will not be executed.
)

echo.

echo ==================== BUILDING EUREKA SERVER ====================
cd ../eureka-server

call .\mvnw.cmd clean install
if %errorlevel% equ 0 (
  docker build -t obraganca/eureka-server:1.0 .
) else (
  echo mvnw command failed. Docker build will not be executed.
)

echo.

echo ==================== BUILDING API GATEWAY ====================
cd ../api-gateway

call .\mvnw.cmd clean install
if %errorlevel% equ 0 (
  docker build -t obraganca/api-gateway:1.0 .
) else (
  echo mvnw command failed. Docker build will not be executed.
)
echo.

echo ==================== BUILDING USER SERVICE ====================
cd ../user-service

call .\mvnw.cmd clean install
if %errorlevel% equ 0 (
  docker build -t obraganca/user-service:1.0 .
) else (
  echo mvnw command failed. Docker build will not be executed.
)

echo.

echo ==================== BUILDING EMAIL SERVICE ====================
cd ../email-service

call .\mvnw.cmd clean install
if %errorlevel% equ 0 (
  docker build -t obraganca/email-service:1.0 .
) else (
  echo mvnw command failed. Docker build will not be executed.
)
cd ../WinScript
