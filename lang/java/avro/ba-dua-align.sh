input_file="badua.xml"
modified_file="badua_pretty.xml"
schema_class="org/apache/avro/Schema"
class2="org/apache/avro/data/TimeConversions"

xmllint --format target/$input_file > target/$modified_file

rm target/$input_file
cp "target/$modified_file" "target/badua_Schema.xml"
cp "target/$modified_file" "target/badua_TimeConversions.xml"

xmlstarlet ed -L -d \
 "//class[not(contains(@name, '$schema_class'))]" \
 "target/badua_Schema.xml"

xmlstarlet ed -L -d \
 "//class[not(contains(@name, '$class2'))]" \
 "target/badua_TimeConversions.xml"
