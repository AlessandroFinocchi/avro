input_file="badua.xml"
modified_file="badua_pretty.xml"
schema_class="org/apache/avro/Schema"

xmllint --format target/$input_file > target/$modified_file

rm target/$input_file
cp "target/$modified_file" "target/badua_Schema.xml"

xmlstarlet ed -L -d \
 "//class[not(contains(@name, '$schema_class'))]" \
 "target/badua_Schema.xml"
