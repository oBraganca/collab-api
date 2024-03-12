#!/bin/bash

echo ==================== DELETING CONFIG SERVER ====================
image_id=$(./get-image-id-by-name.sh "obraganca/config-server")

# Check if the image ID is empty (image not found)
if [ -z "$image_id" ]; then
  echo "Image '$image_name' not found."
else
    docker rmi "$image_id"

    # Check if the removal was successful
    if [ $? -eq 0 ]; then
        echo "Image '$image_name' with ID '$image_id' removed successfully."
    else
        echo "Failed to remove image '$image_name' with ID '$image_id'."
    fi

fi

echo ""

echo ==================== DELETING EUREKA SERVER ==================== 
image_id=$(./get-image-id-by-name.sh "obraganca/eureka-server")

# Check if the image ID is empty (image not found)
if [ -z "$image_id" ]; then
  echo "Image '$image_name' not found."
else
    # Remove the Docker image
    docker rmi "$image_id"

    # Check if the removal was successful
    if [ $? -eq 0 ]; then
        echo "Image '$image_name' with ID '$image_id' removed successfully."
    else
        echo "Failed to remove image '$image_name' with ID '$image_id'."
    fi

fi



echo ""

echo ==================== DELETING API GATEWAY ====================
image_id=$(./get-image-id-by-name.sh "obraganca/api-gateway")

# Check if the image ID is empty (image not found)
if [ -z "$image_id" ]; then
  echo "Image '$image_name' not found."

else

    # Remove the Docker image
    docker rmi "$image_id"

    # Check if the removal was successful
    if [ $? -eq 0 ]; then
        echo "Image '$image_name' with ID '$image_id' removed successfully."
    else
        echo "Failed to remove image '$image_name' with ID '$image_id'."
    fi
fi



echo ""

echo ==================== BUILDING USER SERVICE ====================
image_id=$(./get-image-id-by-name.sh "obraganca/user-service")

# Check if the image ID is empty (image not found)
if [ -z "$image_id" ]; then
  echo "Image '$image_name' not found."
else
    # Remove the Docker image
    docker rmi "$image_id"

    # Check if the removal was successful
    if [ $? -eq 0 ]; then
        echo "Image '$image_name' with ID '$image_id' removed successfully."
    else
        echo "Failed to remove image '$image_name' with ID '$image_id'."
    fi

fi


echo ""

echo ==================== BUILDING EMAIL SERVICE ====================
image_id=$(./get-image-id-by-name.sh "obraganca/email-service")

# Check if the image ID is empty (image not found)
if [ -z "$image_id" ]; then
  echo "Image '$image_name' not found."
else
    # Remove the Docker image
    docker rmi "$image_id"

    # Check if the removal was successful
    if [ $? -eq 0 ]; then
        echo "Image '$image_name' with ID '$image_id' removed successfully."
    else
        echo "Failed to remove image '$image_name' with ID '$image_id'."
    fi

fi

