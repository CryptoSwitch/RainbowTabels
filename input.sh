#/bin/bash
cd /home/gravypod/test
split --verbose -l 1000000 HashList.txt output/Hashes_4

cd /home/gravypod/test/output
declare -a files=`ls Hashes_*`

for file in $files
do
echo [`date +%H:%M`] Starting $file...

  mysql -u mysql_user -p'mysql_pass' rainbowtables -e "CREATE TABLE IF NOT EXISTS $file (pass VARCHAR(300), hash VARCHAR(40), UNIQUE (hash))";

  mysqlimport -u mysql_user -p"mysql_pass" rainbowtables $file --fields-terminated-by=' ' --lines-terminated-by='\n' --local

cat > $file << EOF
EOF

echo $file complete.

done