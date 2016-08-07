<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$email = $_POST['email'];

	$sql = "SELECT user_id FROM user WHERE email = '$email';";
 	$result = $conn->query($sql);
	
	if($result->rowCount() == 1){
		$result=$result->fetchAll();
		$user_id = $result[0]["user_id"];
		
		$sql = "SELECT name FROM interest WHERE user_id = '$user_id';";
		$result = $conn->query($sql);

		if ($result->rowCount() >= 1){

		   $return = '{"interests": [';
		   foreach ($result as $row) {
		   	$return=$return.'"'.$row['name'].'"'.', ';
		   }
		   $return=substr($return, 0, (strlen($return)-2));
		   $return=$return.']}';

		   echo $return;
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
	echo '{"interests": []}';
}

?>
