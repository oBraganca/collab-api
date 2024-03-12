@echo off

REM Delete Docker images script

echo ==================== DELETING CONFIG SERVER ====================
for /f %%i in ('get-image-id-by-name.bat "obraganca/config-server"') do set "image_id=%%i"

REM Check if the image ID is empty (image not found)
if "%image_id%" == "" (
    echo Image 'obraganca/config-server' not found.
) else (
    docker rmi "%image_id%"

    REM Check if the removal was successful
    if %errorlevel% equ 0 (
        echo Image 'obraganca/config-server' with ID '%image_id%' removed successfully.
    ) else (
        echo Failed to remove image 'obraganca/config-server' with ID '%image_id%'.
    )
)

echo.

echo ==================== DELETING EUREKA SERVER ====================
for /f %%i in ('get-image-id-by-name.bat "obraganca/eureka-server"') do set "image_id=%%i"

REM Check if the image ID is empty (image not found)
if "%image_id%" == "" (
    echo Image 'obraganca/eureka-server' not found.
) else (
    docker rmi "%image_id%"

    REM Check if the removal was successful
    if %errorlevel% equ 0 (
        echo Image 'obraganca/eureka-server' with ID '%image_id%' removed successfully.
    ) else (
        echo Failed to remove image 'obraganca/eureka-server' with ID '%image_id%'.
    )
)

echo.

echo ==================== DELETING API GATEWAY ====================
for /f %%i in ('get-image-id-by-name.bat "obraganca/api-gateway"') do set "image_id=%%i"

REM Check if the image ID is empty (image not found)
if "%image_id%" == "" (
    echo Image 'obraganca/api-gateway' not found.
) else (
    docker rmi "%image_id%"

    REM Check if the removal was successful
    if %errorlevel% equ 0 (
        echo Image 'obraganca/api-gateway' with ID '%image_id%' removed successfully.
    ) else (
        echo Failed to remove image 'obraganca/api-gateway' with ID '%image_id%'.
    )
)

echo.

echo ==================== DELETING USER SERVICE ====================
for /f %%i in ('get-image-id-by-name.bat "obraganca/user-service"') do set "image_id=%%i"

REM Check if the image ID is empty (image not found)
if "%image_id%" == "" (
    echo Image 'obraganca/user-service' not found.
) else (
    docker rmi "%image_id%"

    REM Check if the removal was successful
    if %errorlevel% equ 0 (
        echo Image 'obraganca/user-service' with ID '%image_id%' removed successfully.
    ) else (
        echo Failed to remove image 'obraganca/user-service' with ID '%image_id%'.
    )
)

echo.

echo ==================== DELETING EMAIL SERVICE ====================
for /f %%i in ('get-image-id-by-name.bat "obraganca/email-service"') do set "image_id=%%i"

REM Check if the image ID is empty (image not found)
if "%image_id%" == "" (
    echo Image 'obraganca/email-service' not found.
) else (
    docker rmi "%image_id%"

    REM Check if the removal was successful
    if %errorlevel% equ 0 (
        echo Image 'obraganca/email-service' with ID '%image_id%' removed successfully.
    ) else (
        echo Failed to remove image 'obraganca/email-service' with ID '%image_id%'.
    )
)
