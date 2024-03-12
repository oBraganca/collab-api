#!/bin/bash

#!/bin/bash

# Check if an argument (image name) is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <image_name>"
  exit 1
fi

# Get the image ID by filtering the output of "docker images" command
image_name="$1"
image_id=$(docker images --format "{{.ID}}" --filter "reference=$image_name")

# Check if the image ID is empty (image not found)
if [ -z "$image_id" ]; then
  echo "Image '$image_name' not found."
  exit 1
fi

# Print the image ID
echo $image_id