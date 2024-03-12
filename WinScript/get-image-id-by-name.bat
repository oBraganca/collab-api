@echo off


REM Check if an argument (image name) is provided
if "%~1" == "" (
  echo Usage: %0 ^<image_name^>
  exit /b 1
)

REM Get the image ID by filtering the output of "docker images" command
set image_name=%1
for /f "tokens=*" %%i in ('docker images --format "{{.ID}}" --filter "reference=%image_name%"') do set "image_id=%%i"

REM Check if the image ID is empty (image not found)
if "%image_id%" == "" (
  echo Image '%image_name%' not found.
  exit /b 1
)

REM Print the image ID
echo %image_id%
