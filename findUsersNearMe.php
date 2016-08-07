<?php
<?php

$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";


$url = '52.206.229.115/login.php';
$data = array('email' => $_POST["email"], 'password' => $_POST["pass"]);

// use key 'http' even if you send the request to https://...
$options = array(
    'http' => array(
        'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
        'method'  => 'POST',
        'content' => http_build_query($data)
    )
);
$context  = stream_context_create($options);
$result = file_get_contents($url, false, $context);
if ($result === FALSE) { fail(); die();}

if(!json_decode($result)["verified"]){
    fail(); die;
}

try{
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $email = $_POST['email'];

    $sql = "SELECT id, lon, lat FROM user JOIN location USING(user_id) WHERE email = $email;";
    $result = $conn->query($sql)->fetchAll();


    $id = $result[0]["id"];
    $lon = $result[0]["lon"];
    $lat = $result[0]["lat"];

    $sql = "SELECT user_id, email, lon AS lon2, lat AS lat2, RADIANS(lat2-$lat) AS dLat, RADIANS(lon2-$lon) AS dLon, RADIANS($lat) AS rLat1,
            RADIANS(lat2) AS rLat2, SIN(dLat/2)*SIN(dLat/2) + SIN(dLon/2) * SIN(dLon/2) * COS(rLat1) * COS(rLat2) AS a, 2*ATAN2(SQRT(a),
            SQRT(1-a)) AS c, 6371*c AS d FROM location JOIN user USING(user_id) WHERE d<1;";
    $result = $conn->query($sql);

    $return = '{"users": [';
    foreach ($result as $row){
        $return .= '"'.$row["email"].'"'.", ";
    }
    $result = substr($return, 0, strlen($return)-2).']}';

    echo $return;



}catch(PDOException $e) {
    fail();
}

function fail(){
    echo '{"users": []}';
}

?>
?>