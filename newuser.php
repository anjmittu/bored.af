<?php

$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$fname = $conn->quote($_POST["fname"]);
	$lname = $conn->quote($_POST["lname"]);
	$email = $conn->quote($_POST["email"]);
	$pass = $conn->quote(password_hash($_POST["pass"], PASSWORD_DEFAULT ));
	$bday = $conn->quote($_POST["bday"]);
	$gender = $conn->quote($_POST["gender"]);

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