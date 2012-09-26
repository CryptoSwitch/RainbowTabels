<?php
$prefix="Hashes_"; //Set prefix for files
$n="a";  //Set starting value for suffix
$fn="$prefix$n";  //Ending filename is prefix+suffix
$list=scandir('/home/gravypod/test/output'); //Get names of files into an array
foreach ($list as $filename) { //Loop for each file
  $fn="$prefix$n"; //Get new ending filename, as it may change later in loop
#  echo $fn; //Testing code only
  if((substr($filename, 0, 17))=="Hashes_pre_entry_") { //Find files that have not yet been enetered into MySQL
    echo "Found hash file $filename...\n"; //Report each filename found
  while((file_exists($fn))=="TRUE") {  //Loop to not overwrite existing files
    echo "Found existing file $fn, skipping...\n"; //Report existing filenames found
    $n = ++$n; //Advance suffix one character
    $fn="$prefix$n"; //Reset filename for this loop
    }
  shell_exec("mv $filename $fn"); //Rename not yet entered filename to entered filename
  echo "Renaming $filename to $fn\n"; //Report renaming activity
  $n = ++$n; //Advance suffix
  }
}
?>