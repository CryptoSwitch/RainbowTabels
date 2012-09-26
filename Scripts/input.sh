#/bin/bash

read -p "File to read? (Use full path): " input

cd /home/gravypod
split --verbose -a 5 -l 1000000 $input /home/gravypod/test/output/Hashes_pre_entry_

php input.php

cd /home/gravypod/test/output
declare -a files=`ls Hashes_*`

for file in $files
do
echo [`date +%H:%M:%S`] Starting $file...

  mysql -u mysql_user -p'mysql_pass' rainbowtables -e "CREATE TABLE IF NOT EXISTS $file (pass VARCHAR(300), hash VARCHAR(40), UNIQUE (hash))";

  mysqlimport -u mysql_user -p"mysql_pass" rainbowtables $file --fields-terminated-by=' ' --lines-terminated-by='\n' --local

cat > $file << EOF
EOF

echo $file complete.

done