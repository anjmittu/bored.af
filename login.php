<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $email = $_POST['email'];

	$sql = "SELECT email, pass_hash FROM user WHERE email = '$email';";
	$result = $conn->query($sql);
	if(count($result) == 1){
		$result=$result->fetchAll();
		if($result[0]["email"] ==  $_POST["email"]){
			if(password_verify($_POST["password"], $result[0]["pass_hash"])){
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
    echo $e->getMessage();
    fail();
}

function fail(){
	echo '{"verified": false}';
}

?>
