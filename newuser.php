<?php

$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$sql = "INSERT INTO user VALUE(\"{$_POST["fname"]}\",
							\"{$_POST["lname"]}\",
							\"{$_POST["email"]}\",
							\"{password_hash($_POST["fname"])}\",
							\"{$_POST["bday"]}\",
							'{$_POST["gender"]}',);";

	$conn->exec($sql);
	echo '{"completed": true}';
	
}catch(PDOException $e) {
    fail();
}

function fail(){
	echo '{"completed": false}';
}

?>