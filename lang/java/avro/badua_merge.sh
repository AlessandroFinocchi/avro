#Usage:
#badua_merge.sh merge.xml badua_SchemaValidNamesTest.xml badua_SchemaInvalidNamesTest.xml badua_SchemaNullNamesTest.xml badua_SchemaWOFieldTest.xml badua_SchemaInvalidFieldTest.xml badua_SchemaNullTest.xml badua_SchemaJacocoTest.xml

# Check if at least two files are provided
if [ "$#" -lt 2 ]; then
    echo "Usage: $0 output.xml input1.xml input2.xml [input3.xml ...]"
    exit 1
fi

output_file="$1"
shift
input_files=("$@")

# Temporary file to preserve the structure
cp "${input_files[0]}" "$output_file"

# Iterate through all input files
for file in "${input_files[@]}"; do
    # Extract all <du> elements
    while IFS= read -r line; do
        if [[ $line =~ \<du.*covered=\"([01])\" ]]; then
            covered_value=${BASH_REMATCH[1]}
            entry=$(echo "$line" | sed -E 's/covered="[01]"/covered="0"/')

            # Check if the same line exists in the output file
            if grep -q "$entry" "$output_file"; then
                # If any file has covered="1", update the final output
                if [[ $covered_value == "1" ]]; then
                    sed -i "s|$entry|${entry//covered=\"0\"/covered=\"1\"}|" "$output_file"
                fi
            fi
        fi
    done < "$file"
done

echo "Merged file created: $output_file"
