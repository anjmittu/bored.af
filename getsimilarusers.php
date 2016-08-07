<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";
$return = array();

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$email = $_POST['email'];

	$sql = "SELECT user_id FROM user WHERE email = '$email';";
 	$result = $conn->query($sql);
	
	if($result->rowCount() == 1){
		$result=$result->fetchAll();
		$user_id = $result[0]["user_id"];

		$returnjs = '{"users": [';
		$sql = "SELECT name FROM interest WHERE user_id = '$user_id';";
		$result = $conn->query($sql);

		if ($result->rowCount() >= 1){

		   foreach ($result as $row) {
		   	   $interest = $row['name'];
			   $sql = "SELECT user_id FROM interest WHERE name = '$interest';";
			   $result2 = $conn->query($sql);
			   if($result2->rowCount() >= 1){
			   	foreach ($result2 as $row2) {
					$returnjs = $returnjs.'"'.$row2['user_id'].'"'.', ';
			   	}
			  
			   } else {
				fail();
			   }
		   }

		   $returnjs = substr($returnjs, 0, (strlen($returnjs)-2));
		   $returnjs = $returnjs.']}';

		   echo $returnjs;
		   
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
	echo '{"users": []}';
}

?>
