<?php

$servername = "";
$username = "";
$password = "";
$dbname = "";

try{
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $email = $_POST['email'];

    $sql = "SELECT user_id, lon, lat FROM user JOIN location USING(user_id) WHERE email = '$email';";
    $result = $conn->query($sql);

    if($result->rowCount()!=1){
	fail();
	die();
    }

    $result = $result->fetchAll();

    $id = $result[0]["user_id"];
    $lon_ = $result[0]["lon"];
    $lat_ = $result[0]["lat"];

    $sql = "SELECT user_id, email, lon, lat, ABS($lat_ - lat) AS dlat, ABS($lon_ - lon) AS dlon FROM location JOIN user USING(user_id) WHERE SQRT((ABS($lat_ - lat)*ABS($lat_-lat)) +  (ABS($lon_-lon)*ABS($lon_-lon)))<(1/111);" ;
    

    $result = $conn->query($sql);

    $return = '{"users": [';
    foreach ($result as $row){
        $return .= '"'.$row["email"].'"'.", ";
    }
    $return = substr($return, 0, strlen($return)-2).']}';

    echo $return;



}catch(PDOException $e) {
    	echo $e->getMessage();
	fail();
}

function fail(){
    echo '{"users": []}';
}

?>
