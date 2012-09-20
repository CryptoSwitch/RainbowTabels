<?php
#$lines = file("HashPHP.txt", FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$file = "HashList-1-5.txt";
$fp = fopen("$file", "r");
while (!feof($fp)) {  //This looped forever
  $content = fgets($fp, 4096);
  $data = explode("\n", $content);
#$data = array($content);
#$data = explode(' ', $content);
  $con = mysql_connect("localhost","USERNAMEGOESHERE","PASSWORDGOESHERE");
  if (!$con)
    {
    die('Could not connect: ' . mysql_error());
    }

  mysql_select_db("rainbowtables", $con);

  foreach($data as $line){
    $line = htmlentities($line);
    $line = explode(" ", $line);
    if($line[0]==""){} else {
    mysql_query("INSERT INTO hashes (pass, hash) 
VALUES 
('$line[0]', '$line[1]')");
#  echo "$line[0] $line[1]\n";
#var_dump($data);
}
}
}
echo "MySQL Insertion Complete"
?>

