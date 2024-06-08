#!/bin/bash

# Input file
input_file="databases.conf"
# Output file
output_file="/docker-entrypoint-initdb.d/init.sql"

# Check if the input file exists
if [[ ! -f $input_file ]]; then
    echo "Input file $input_file does not exist."
    exit 1
fi

# Create or overwrite the output file
> $output_file

# Read each line from the input file and generate the corresponding SQL command
while IFS= read -r service; do
    echo "CREATE DATABASE IF NOT EXISTS \`$service\`;" >> $output_file
done < $input_file

echo "File $output_file has been generated."
