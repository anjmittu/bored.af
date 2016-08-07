<?php

$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$fname = mysql_real_escape_string($_POST["fname"]);
	$lname = mysql_real_escape_string($_POST["lname"]);
	$email = mysql_real_escape_string($_POST["email"]);
	$pass = mysql_real_escape_string(password_hash($_POST["pass"]));
	$bday = mysql_real_escape_string($_POST["bday"]);
	$gender = mysql_real_escape_string($_POST["gender"]);

	$sql = "INSERT INTO user VALUE('$fname', '$lname', '$pass', '$email', '$bday', '$gender');";

	$conn->exec($sql);
	echo '{"completed": true}';
	
}catch(PDOException $e) {
    fail();
}

function fail(){
	echo '{"completed": false}';
}

?>