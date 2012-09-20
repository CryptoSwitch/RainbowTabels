<?php

$lines = file("HashList.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
#$con = mysql_connect("localhost","USERGOESHERE","PASSWORDGOESHERE");
#if (!$con)
#  {
#  die('Could not connect: ' . mysql_error());
#  }

#mysql_select_db("rainbowtables", $con);

foreach($lines as $line){
  $line = htmlentities($line);
  $line = explode(" ", $line);
}
#  if(strlen($line[0]>7)){
#    $line[0]="";
#    $line[1]="";
#    echo '$line[0] $line[1]';
#}
echo "test";
#  mysql_query("INSERT INTO hashes (pass, hash) 
#VALUES 
#('$line[0]', '$line[1]')");
# echo "$line[1] $line[0]";
?>
