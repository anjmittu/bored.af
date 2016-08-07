<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$user_id = $_POST['user_id'];

	$sql = "SELECT fname FROM user WHERE user_id = '$user_id';";
 	$result = $conn->query($sql);
	
	if($result->rowCount() == 1){
		$result=$result->fetchAll();
		$fname = $result[0]["fname"];

		   echo '{"fname": $fname}';
		} else {
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
	echo '{"fname": ""}';
}

?>
