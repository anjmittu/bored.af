<?php

$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$sql = "SELECT email, pass_hash FROM user WHERE email = ".$_POST["email"].";";
	$result = $conn->query($sql);
	if(!count($result) == 1){
		if($result[1]["email"] ==  $_POST["email"]){
			if(password_verify($_POST["password"], $result[1]["pass_hash"])){
				echo '{"verified": true}';
			}else{
				fail();
			}
		}else{
			fail();
		}
	}else{
		fail();
	}
}catch(PDOException $e) {
    fail();
}

function fail(){
	echo '{"verified": false}';
}

?>