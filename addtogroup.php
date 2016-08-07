<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $email = $_POST['email'];
	$user_id_add = $_POST['user_id'];

	$sql = "SELECT user_id FROM user WHERE email = '$email';";
 	$result = $conn->query($sql);
	
	if($result->rowCount() == 1){
		$result=$result->fetchAll();
		$user_id = $result[0]["user_id"];
	
		$sql = "SELECT MAX(group_id) AS max FROM group WHERE user_id = '$user_id' AND group_leader = true;";
		$result = $conn->query($sql);
		$result=$result->fetchAll();
		$group_id = $result[0]["group_id"];

		$sql = "INSERT INTO group VALUE('$user_id_add', '$group_id', false, NULL);";
	     	$result = $conn->query($sql);

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