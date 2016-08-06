<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$fname = $_POST["fname"];
	$lname = $_POST["lname"];
	$email =$_POST["email"];
	$pass = password_hash($_POST["pass"], PASSWORD_BCRYPT );
	$bday = $_POST["bday"];
	$gender = $_POST["gender"];
	$sql = "INSERT INTO user(first_name, last_name, pass_hash, email, birthdate, gender) VALUE('$fname', '$lname', '$pass', '$email', '$bday', '$gender');";

	$conn->exec($sql);
	echo '{"completed": true}';
	
}catch(PDOException $e) {
    echo $e->getMessage();
}

function fail(){
	echo '{"completed": false}';
}

?>
