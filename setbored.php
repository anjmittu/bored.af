<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    	$bored = $_POST['bored'];
	$email = $_POST['email'];

	$sql = "SELECT user_id FROM user WHERE email = '$email';";
 	$result = $conn->query($sql);
	
	if($result->rowCount() == 1){
		$result=$result->fetchAll();
		$user_id = $result[0]["user_id"];
		
		$sql = "SELECT user_id FROM bored WHERE user_id=$user_id;";
		
		if($conn->query($sql)->rowCount() == 0){
			$sql = "INSERT INTO bored VALUE('$user_id', '$bored');";
			$result = $conn->query($sql);
		}else{
			$sql = "UPDATE bored SET bored=$bored WHERE user_id=$user_id;";
			$result = $conn->query($sql);
		}
		
		echo '{"verified": true}';	
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
