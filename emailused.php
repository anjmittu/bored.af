<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    	$email = $_POST['email'];

	$sql = "SELECT email FROM user WHERE email = '$email';";
	$result = $conn->query($sql);
	
	if($result->row_count() == 0){
		echo '{"newemail": true}';
	}else{
		echo '{"newemail": false}';
	}
}catch(PDOException $e) {
    echo $e->getMessage();
    fail();
}

?>
